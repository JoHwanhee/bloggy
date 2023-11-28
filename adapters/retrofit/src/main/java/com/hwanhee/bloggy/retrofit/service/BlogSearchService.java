package com.hwanhee.bloggy.retrofit.service;

import com.hwanhee.bloggy.application.dto.BlogSearchResult;
import com.hwanhee.bloggy.application.ports.out.BlogSearchServicePort;
import com.hwanhee.bloggy.retrofit.client.KakaoBlogSearchClient;
import com.hwanhee.bloggy.retrofit.dto.BlogSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogSearchService implements BlogSearchServicePort {

    private final KakaoBlogSearchClient client;

    @Autowired
    public BlogSearchService(KakaoBlogSearchClient client) {
        this.client = client;
    }

    @Override
    public BlogSearchResult search(String query, int page, int size) {
        BlogSearchResponse response = client.searchBlog(query, page, size);
        return BlogSearchResult.builder()
                .documents(null)
                .meta(null)
                .build();
    }
}