package me.rcortesb.auth.grpc;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import io.grpc.stub.StreamObserver;
import me.rcortesb.auth.domain.entity.UserSecurity;
import me.rcortesb.auth.repository.UserSecurityRepository;
import me.rcortesb.grpc.UserValidRequest;
import me.rcortesb.grpc.UserValidResponse;
import me.rcortesb.grpc.UserValidServiceGrpc;
import net.devh.boot.grpc.server.service.GrpcService;

@Service
@GrpcService
public class UserValidServiceImpl extends UserValidServiceGrpc.UserValidServiceImplBase {
	final private UserSecurityRepository userRepository;
	
	public UserValidServiceImpl(UserSecurityRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void isUserValid(UserValidRequest request,
		StreamObserver<UserValidResponse> responseObserver) {
		UUID userId = UUID.fromString(request.getUserId());
		Optional<UserSecurity> user = userRepository.findById(userId);
		boolean isValid = user.isEmpty() ? false : true;
		UserValidResponse response = UserValidResponse.newBuilder()
													  .setIsValid(isValid)
													  .build();
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}
}
