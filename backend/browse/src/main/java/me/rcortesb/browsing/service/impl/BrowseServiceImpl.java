package me.rcortesb.browsing.service.impl;

import me.rcortesb.browsing.service.BrowseService;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

@Service
public class BrowseServiceImpl implements BrowseService {
    final private ElasticsearchOperations operations;

    public BrowseServiceImpl(ElasticsearchOperations elasticsearchOperations) {
        this.operations = elasticsearchOperations;
    }

    public void saveUserInDocument() {

    }
}
