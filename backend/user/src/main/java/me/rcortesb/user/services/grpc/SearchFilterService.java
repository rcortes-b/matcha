package me.rcortesb.user.services.grpc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import io.grpc.stub.StreamObserver;
import jakarta.transaction.Transactional;
import me.rcortesb.grpc.SearchFilterRequest;
import me.rcortesb.grpc.SearchFilterResponse;
import me.rcortesb.grpc.SearchFilterServiceGrpc;
import me.rcortesb.user.domain.entity.User;
import me.rcortesb.user.repository.UserRepository;
import net.devh.boot.grpc.server.service.GrpcService;

@Service
@GrpcService
public class SearchFilterService extends SearchFilterServiceGrpc.SearchFilterServiceImplBase {
	final UserRepository userRepository;

	public SearchFilterService(UserRepository userRepository) {
		this.userRepository	= userRepository;
	}

	@Override
	@Transactional
	public void getSearchFilter(SearchFilterRequest request,
								StreamObserver<SearchFilterResponse> responseObserver) {
		UUID userId = UUID.fromString(request.getUserId());
		User user = userRepository.findById(userId).orElseThrow(() -> 
						new RuntimeException("USER DOES NOT EXIST IN SearchFilterService(gRPC)")); /* GET USER HERE */
        
		SearchFilterResponse response = SearchFilterResponse.newBuilder()
            .setGender(user.getGender().name())
			.setSexualPreference(user.getSexualPreference().getPreference())
			.setLat(user.getLatitude())
			.setLon(user.getLongitude())
            .build();
			
        System.out.println("\n\nRESPONSE: " + response.toString() + "\n\n");
        responseObserver.onNext(response);
        responseObserver.onCompleted();
	}
}
