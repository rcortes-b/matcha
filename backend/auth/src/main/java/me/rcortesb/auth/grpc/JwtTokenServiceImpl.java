package me.rcortesb.auth.grpc;

import io.grpc.stub.StreamObserver;
import me.rcortesb.grpc.JwtTokenRequest;
import me.rcortesb.grpc.JwtTokenResponse;
import me.rcortesb.grpc.JwtTokenServiceGrpc;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

@Service
@GrpcService
public class JwtTokenServiceImpl extends JwtTokenServiceGrpc.JwtTokenServiceImplBase {
    final private JwtDecoder jwtDecoder;
    public JwtTokenServiceImpl(JwtDecoder jwtDecoder) {
        this.jwtDecoder = jwtDecoder;
    }

    @Override
    public void getJwtToken(JwtTokenRequest request,
                            StreamObserver<JwtTokenResponse> responseObserver) {
        String token = request.getToken();
        String userId = getFromJwtDecoder(token);
        if (userId == null) {
            return ;
        }
        JwtTokenResponse response = JwtTokenResponse.newBuilder()
                .setUserId(userId)
                .build();
        System.out.println("\n\nRESPONSE: " + response.getUserId() + "\n\n");
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private String getFromJwtDecoder(String token) {
        Jwt jwt = jwtDecoder.decode(token);
        if (jwt == null)
            return null;
        return jwt.getSubject();
    }
}

