package me.rcortesb.browse.service.grpc;

import org.springframework.stereotype.Component;

import me.rcortesb.browse.domain.entity.dto.SearchFilterRPC;
import me.rcortesb.grpc.SearchFilterRequest;
import me.rcortesb.grpc.SearchFilterResponse;
import me.rcortesb.grpc.SearchFilterServiceGrpc;
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
}
