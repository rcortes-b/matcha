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

    public List<UserResponseDTO> searchUsersByDistance(String userId, SearchFilter filter) {
		SearchFilterRPC searchData = searchFilterClient.fetchData(userId);
		/*final String fakeGender = "MALE";
		final String fakePreference = "HETEROSEXUAL";
		final double fakeLatitude = 39.545341;
		final double fakeLongitude = -0.392908;*/
		List<FieldValue> genderInterests = getGendersInterests(searchData.gender(), searchData.sexualPreference())
										.stream()
										.map(FieldValue::of)
										.toList();
		
		NativeQuery query = NativeQuery.builder()
            .withQuery(q -> q
                .bool(b -> b
					// FILTER 1: The person I see must be a gender I like
					.filter(f -> f.terms(t -> t
						.field("gender")
						.terms(v -> v.value(genderInterests))
					))

					// FILTER 2: The person I see must like MY gender
					// (This handles the "Heterosexual" vs "Gay" check automatically)
					.filter(f -> f.term(t -> t
						.field("interestedIn")
						.value(searchData.gender())
					))
                    // 1. MUST: Gender preference
                    //.must(m -> m.term(t -> t.field("gender").value(filter.getPreference())))
                    
                    // 2. FILTER: Age range (Doesn't affect score, very fast)
                    .filter(f -> f.range(r -> r
						.number(n -> n
                        	.field("age")
                        	.gte((double)filter.minAge())
                        	.lte((double)filter.maxAge())
					)))
                    
                    // 3. FILTER: Geo Distance (Find users within 50km)
                    .filter(f -> f.geoDistance(g -> g
                        .field("location")
                        .distance(filter.distance() + "km")
                        .location(loc -> loc.latlon(l -> l.lat(searchData.latitude())
														.lon(searchData.longitude())))
					))

                    // 4. SHOULD: Common Interests (Boosts profile if they share hobbies)
                    .should(s -> s.match(m -> m
                        .field("tags")
                        .query(String.join(" ", filter.tags())
					)))
				)
			)
            .withPageable(PageRequest.of(0, 20))
            .build();
		SearchHits<UserDocument> search = operations.search(query, UserDocument.class);
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
		//return grpc call to retrieve the user data
		return null;
	}
}
