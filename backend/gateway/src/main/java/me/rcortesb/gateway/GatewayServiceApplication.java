package me.rcortesb.gateway;

import net.devh.boot.grpc.client.autoconfigure.GrpcClientSecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = GrpcClientSecurityAutoConfiguration.class)
public class GatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
		System.out.println("\n\nHallo\n\n");
	}
}
