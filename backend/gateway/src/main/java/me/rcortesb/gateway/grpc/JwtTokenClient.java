package me.rcortesb.gateway.grpc;

import me.rcortesb.grpc.JwtTokenRequest;
import me.rcortesb.grpc.JwtTokenResponse;
import me.rcortesb.grpc.JwtTokenServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenClient {
    @GrpcClient("jwt-token-service")
    private JwtTokenServiceGrpc.JwtTokenServiceBlockingStub stub;

    public String fetchJwtToken(String token) {
        if (stub == null) {
            throw new IllegalStateException("gRPC stub not initialized yet!");
        }
        JwtTokenRequest request = JwtTokenRequest.newBuilder()
                .setToken(token)
                .build();

        JwtTokenResponse response = stub.getJwtToken(request);
        System.out.println("UserId: " + response.getUserId());
        return response.getUserId();
    }
}
