package me.rcortesb.auth.services.impl;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.rcortesb.auth.domain.common.CookieProperties;
import me.rcortesb.auth.domain.common.ERole;
import me.rcortesb.auth.domain.dto.CredentialsDTO;
import me.rcortesb.auth.domain.entity.Role;
import me.rcortesb.auth.domain.entity.UserSecurity;
import me.rcortesb.auth.repository.RoleRepository;
import me.rcortesb.auth.repository.UserSecurityRepository;
import me.rcortesb.auth.services.AuthService;
import me.rcortesb.auth.services.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class AuthServiceImpl implements AuthService {
    final private UserSecurityRepository userSecurityRepository;
    final private RoleRepository roleRepository;
    final private PasswordEncoder passwordEncoder;
    final private AuthenticationManager authenticationManager;
    final private TokenService tokenService;
    final private CookieProperties cookieProperties;

    public AuthServiceImpl(UserSecurityRepository userSecurityRepository,
                           RoleRepository roleRepository, PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager,  TokenService tokenService,
                           CookieProperties cookieProperties) {
        this.userSecurityRepository = userSecurityRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.cookieProperties = cookieProperties;
    }

    @Override
    public void handleRegister(CredentialsDTO credentialsDTO) {
        UserSecurity userSecurity = new UserSecurity(credentialsDTO.email(), credentialsDTO.password());
        Role role = roleRepository.findByRole(ERole.USER);
        if (role == null) {
            throw new RuntimeException("I fucked up configuring the User - Role relation :)");
        }
        userSecurity.setRole(role);
        userSecurity.setPassword(passwordEncoder.encode(credentialsDTO.password()));
        userSecurityRepository.save(userSecurity);
    }

    @Override
    public void handleLogin(CredentialsDTO credentialsDTO) {
        Authentication authRequest = new UsernamePasswordAuthenticationToken(credentialsDTO.email(),
                                                                            credentialsDTO.password());
        Authentication authentication = authenticationManager.authenticate(authRequest);
        final UserSecurity userSecurity = (UserSecurity) authentication.getPrincipal();
        final String subject = userSecurity.getId().toString();
        final String token = tokenService.generateToken(subject, false);
        final String refreshToken = tokenService.generateToken(subject, true);
        System.out.println("\nToken: " + token + "\nRefresh Token: " + refreshToken + "\n");
        final Cookie cookie = cookieProperties.createCookie(token, false);
        final Cookie cookieRefresh = cookieProperties.createCookie(refreshToken, true);
        addCookieToResponse(cookie);
        addCookieToResponse(cookieRefresh);
    }

    @Override
    public void handleLogout() {
        final Cookie cookie = cookieProperties.createCookie("", false);
        cookie.setMaxAge(0);
        final Cookie cookieRefresh = cookieProperties.createCookie("", true);
        cookieRefresh.setMaxAge(0);
        addCookieToResponse(cookie);
        addCookieToResponse(cookieRefresh);
    }

    @Override
    public void handleCookieRefresh(HttpServletRequest request) {
        final String subject = tokenService.getSubjectFromRefreshCookie(request);
        if (subject == null)
            throw new RuntimeException("Cookie Refresh not found");
        final String token = tokenService.generateToken(subject, false);
        final Cookie cookie = cookieProperties.createCookie(token, false);
        addCookieToResponse(cookie);
    }

    private void addCookieToResponse(Cookie cookie) {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs == null)
            return;
        HttpServletResponse response = attrs.getResponse();
        if (response == null)
            return;
        response.addCookie(cookie);
    }
}
