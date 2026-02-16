package me.rcortesb.interaction.service.grpc;

import org.springframework.stereotype.Component;

import me.rcortesb.grpc.UserValidRequest;
import me.rcortesb.grpc.UserValidResponse;
import me.rcortesb.grpc.UserValidServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;

@Component
public class UserValidClient {
	@GrpcClient("user-valid-service")
	private UserValidServiceGrpc.UserValidServiceBlockingStub stub;

	public boolean isUserValid(String userId) {
		if (stub == null) {
            throw new IllegalStateException("gRPC stub not initialized yet!");
        }

		UserValidRequest request = UserValidRequest.newBuilder()
												   .setUserId(userId)
												   .build();
		UserValidResponse response = stub.isUserValid(request);
		return response.getIsValid();
	}
}
