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
import me.rcortesb.grpc.UserResponseGRPC;
import me.rcortesb.grpc.UserHitRequest;
import me.rcortesb.grpc.UserHitResponse;
import me.rcortesb.user.domain.entity.Tag;
import me.rcortesb.user.domain.entity.User;
import me.rcortesb.user.services.UserService;
import net.devh.boot.grpc.server.service.GrpcService;

@Service
@GrpcService
public class SearchFilterService extends SearchFilterServiceGrpc.SearchFilterServiceImplBase {
	final UserService userService;

	public SearchFilterService(UserService userService) {
		this.userService = userService;
	}

	@Override
	@Transactional
	public void getSearchFilter(SearchFilterRequest request,
								StreamObserver<SearchFilterResponse> responseObserver) {
		UUID userId = UUID.fromString(request.getUserId());
		User user = userService.getUserById(userId).orElseThrow(() -> 
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

	@Override
	@Transactional
	public void retrieveUsers(UserHitRequest request,
								StreamObserver<UserHitResponse> responseObserver) {
		List<User> users = userService.getUsersByIdList(request.getUserIdList());
		if (users == null)
			throw new RuntimeException("USERS COULD NOT BE RETREIVED IN 'retrieveUsers' GRPC CALL");
		List<UserResponseGRPC> usersResponseDTO = new ArrayList<>();

		for (User user : users) {
			usersResponseDTO.add(UserResponseGRPC.newBuilder()
									  .setFirstName(user.getFirstName())
									  .setAge(user.getAge())
									  .setGender(user.getGender().name())
									  .setSexualPreference(user.getSexualPreference().getPreference())
									  .setBiography(user.getBiography())
									  .setAddress(user.getCity() + ", " + user.getState())
									  .addAllTags(user.getTags().stream()
									  							.map(Tag::getTagName)
									  							.toList())
									  .build());
		}

		UserHitResponse response = UserHitResponse.newBuilder()
												  .addAllUsers(usersResponseDTO)
												  .build();
		responseObserver.onNext(response);
        responseObserver.onCompleted();
	}
}
