package me.rcortesb.browse.service.impl;

import java.util.List;

import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

import me.rcortesb.browse.service.BrowseService;
import me.rcortesb.common.UserResponseDTO;

@Service
public class BrowseServiceImpl implements BrowseService {
    final private ElasticsearchOperations operations;

    public BrowseServiceImpl(ElasticsearchOperations elasticsearchOperations) {
        this.operations = elasticsearchOperations;
    }

    public List<UserResponseDTO> searchUsersByDistance() {
		return null;
    }
}
