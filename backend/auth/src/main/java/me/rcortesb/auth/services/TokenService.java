package me.rcortesb.auth.services;

import jakarta.servlet.http.HttpServletRequest;

public interface TokenService {
    String generateToken(String subject, boolean isRefresh);
    String getSubjectFromRefreshCookie(HttpServletRequest request);
}
