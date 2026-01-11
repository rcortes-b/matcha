package me.rcortesb.gateway.grpc.config;

import org.springframework.cloud.gateway.config.GrpcSslConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcClientConfig {

    @Bean
    public GrpcSslConfigurer grpcSslConfigurer() {
        // Plaintext connections — no SSL
        return null;
    }
}
