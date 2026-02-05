package me.rcortesb.browse.service.impl;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

import co.elastic.clients.json.JsonData;
import me.rcortesb.browse.domain.entity.dto.SearchFilter;
import me.rcortesb.browse.service.BrowseService;
import me.rcortesb.common.UserResponseDTO;

/*
public record SearchFilter(List<String> tags,
							int minAge,
							int maxAge,
							int distance) {
*/
/*
	Filter by age range
	Check user gender and sexualPreference
	Calculate via lat and long to get users in a range of 'x'km
	Search only for users with one of the selected tags
*/

@Service
public class BrowseServiceImpl implements BrowseService {
    final private ElasticsearchOperations operations;

    public BrowseServiceImpl(ElasticsearchOperations elasticsearchOperations) {
        this.operations = elasticsearchOperations;
    }

    public List<UserResponseDTO> searchUsersByDistance(String userId, SearchFilter filter) {
		NativeQuery query = NativeQuery.builder()
				.withQuery(q -> q
					
				)
    .withPageable(PageRequest.of(0, 10))
    .build();

		return null;
    }
}
