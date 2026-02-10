package me.rcortesb.browse.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;

import co.elastic.clients.elasticsearch._types.FieldValue;
import me.rcortesb.browse.domain.entity.UserDocument;
import me.rcortesb.browse.domain.entity.dto.SearchFilter;
import me.rcortesb.browse.domain.entity.dto.SearchFilterRPC;
import me.rcortesb.browse.service.BrowseService;
import me.rcortesb.browse.service.grpc.SearchFilterClient;
import me.rcortesb.common.UserResponseDTO;

/*
public record SearchFilter(List<String> tags,
							int minAge,
							int maxAge,
							int distance) {
*/
/*
	Check only for users that gender i'm interested
	Check only for users that are insterested in my gender
	Filter by age range
	Calculate via lat and long to get users in a range of 'x'km
	Search only for users with one of the selected tags

	ADD THE LATITUDE LONGITUDE OF BROWSER GEOLOCATION API AND CHECK IF IS AVAILABLE OR NOT,
	IF USE THE LAT-LON GOOD, IF NOT USE THE DATA RETRIEVED FROM THE DATABASE

*/

@Service
public class BrowseServiceImpl implements BrowseService {
    final private ElasticsearchOperations operations;
	final private SearchFilterClient searchFilterClient;

    public BrowseServiceImpl(ElasticsearchOperations elasticsearchOperations,
								SearchFilterClient searchFilterClient) {
        this.operations = elasticsearchOperations;
		this.searchFilterClient = searchFilterClient;
    }

    public List<UserResponseDTO> searchUsersByFilters(String userId, SearchFilter filter) {
		SearchFilterRPC searchData = searchFilterClient.fetchData(userId);
		/*final String fakeGender = "MALE";
		final String fakePreference = "HETEROSEXUAL";
		final double fakeLatitude = 39.545341;
		final double fakeLongitude = -0.392908;*/
		List<FieldValue> genderInterests = getGendersInterests(searchData.gender(), searchData.sexualPreference())
										  .stream()
										  .map(FieldValue::of)
										  .toList();
		System.out.println(getGendersInterests(searchData.gender(), searchData.sexualPreference()));
		NativeQuery query = NativeQuery.builder()
            .withQuery(q -> q
                .bool(b -> b
					// FILTER 1: The person I see must be a gender I like
					.filter(f -> f.terms(t -> t
						.field("gender")
						.terms(v -> v.value(genderInterests))
					))

					// FILTER 2: The person I see must like MY gender
					.filter(f -> f.term(t -> t
						.field("interestedIn")
						.value(searchData.gender())
					))
                    
                    // FILTER 3: Age range
                    .filter(f -> f.range(r -> r
						.number(n -> n
                        	.field("age")
                        	.gte((double)filter.getMinAge())
                        	.lte((double)filter.getMaxAge())
					)))
                    
                    // FILTER 4: Geo Distance (Users within 'x' km)
                    .filter(f -> f.geoDistance(g -> g
                        .field("location")
                        .distance(filter.getDistance() + "km")
                        .location(loc -> loc.latlon(l -> l.lat(searchData.latitude())
														.lon(searchData.longitude())))
					))

                    // SHOULD 5: Common Interests (Boosts profile if they share hobbies)
                    .should(s -> s.match(m -> m
                        .field("tags")
                        .query(String.join(" ", filter.getTags())
					)))
				)
			)
            .withPageable(PageRequest.of(filter.getPage(), filter.getPageSize()))
            .build();
		SearchHits<UserDocument> search = operations.search(query, UserDocument.class);
		System.out.println("TOTAL HITS: " + search.getTotalHits());
		long loopValueToDelete = filter.getPageSize() < search.getTotalHits() ? filter.getPageSize() : search.getTotalHits();
		for (int i = 0; i < loopValueToDelete; i++)
			System.out.println(search.getSearchHit(i).getContent().toString());
		// This is temporal behaviour
		if (search.getTotalHits() == 0) {
			throw new RuntimeException("NOTHING FOUND WITH ELASTICSEARCH, LOOK AT THE FILTERS!");
		}
		List<String> userIds = search.stream()
								.map(hit -> hit.getContent().getId())
								.toList();
		return retrieveUsers(userIds);
    }

	private List<String> getGendersInterests(String gender, String sexualPreference) {
		List<String> genderInterests = new ArrayList<>();
		if ("MALE".equals(gender)) {
			if ("HETEROSEXUAL".equals(sexualPreference))
				genderInterests.add("FEMALE");
			else if ("HOMOSEXUAL".equals(sexualPreference))
				genderInterests.add("MALE");
			else {
				genderInterests.add("MALE");
				genderInterests.add("FEMALE");
			}
		}
		else if ("FEMALE".equals(gender)) {
			if ("HETEROSEXUAL".equals(sexualPreference))
				genderInterests.add("MALE");
			else if ("HOMOSEXUAL".equals(sexualPreference))
				genderInterests.add("FEMALE");
			else {
				genderInterests.add("MALE");
				genderInterests.add("FEMALE");
			}
		}
		return genderInterests;
	}

	private List<UserResponseDTO> retrieveUsers(List<String> userIds) {
		return searchFilterClient.fetchUsers(userIds);
	}
}
