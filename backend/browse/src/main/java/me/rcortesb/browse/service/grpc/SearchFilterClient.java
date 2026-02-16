package me.rcortesb.browse.service.grpc;

import java.util.List;

import org.springframework.stereotype.Component;

import me.rcortesb.browse.domain.entity.dto.SearchFilterRPC;
import me.rcortesb.common.UserResponseDTO;
import me.rcortesb.grpc.SearchFilterRequest;
import me.rcortesb.grpc.SearchFilterResponse;
import me.rcortesb.grpc.SearchFilterServiceGrpc;
import me.rcortesb.grpc.UserHitRequest;
import me.rcortesb.grpc.UserHitResponse;
import net.devh.boot.grpc.client.inject.GrpcClient;

@Component
public class SearchFilterClient {
	@GrpcClient("search-filter-service")
    private SearchFilterServiceGrpc.SearchFilterServiceBlockingStub stub;

    public SearchFilterRPC fetchData(String userId) {
        if (stub == null) {
            throw new IllegalStateException("gRPC stub not initialized yet!");
        }
        SearchFilterRequest request = SearchFilterRequest.newBuilder()
                .setUserId(userId)
                .build();

        SearchFilterResponse response = stub.getSearchFilter(request);
        System.out.println("DATA RECEIVED IN THE SEARCHFILTER CLIENT: " + response.toString());
		return new SearchFilterRPC(response.getGender(),
									response.getSexualPreference(),
									response.getLat(),
									response.getLon());
    }

	public List<UserResponseDTO> fetchUsers(List<String> userIds) {
		if (stub == null) {
            throw new IllegalStateException("gRPC stub not initialized yet!");
        }

		UserHitRequest request = UserHitRequest.newBuilder()
											   .addAllUserId(userIds)
											   .build();
		UserHitResponse response = stub.retrieveUsers(request);
		return response.getUsersList().stream()
									  .map(grpcResponse -> new UserResponseDTO(
										   grpcResponse.getUserId(),
										   grpcResponse.getFirstName(),
										   grpcResponse.getAge(),
										   grpcResponse.getGender(),
										   grpcResponse.getSexualPreference(),
										   grpcResponse.getBiography(),
									       grpcResponse.getAddress(),
										   grpcResponse.getTagsList())).
									  toList();
	}
}
