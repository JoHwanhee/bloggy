package com.hwanhee.bloggy.retrofit.service;

import com.hwanhee.bloggy.application.ports.out.BlogSearchServicePort;
import com.hwanhee.bloggy.domain.blog.BlogSearchResult;
import com.hwanhee.bloggy.retrofit.client.KakaoBlogSearchClient;
import com.hwanhee.bloggy.retrofit.dto.BlogSearchResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KakaoBlogSearchService implements BlogSearchServicePort {

    private final KakaoBlogSearchClient client;

    @Autowired
    public KakaoBlogSearchService(KakaoBlogSearchClient client) {
        this.client = client;
    }

    @Override
    public BlogSearchResult search(String query, int page, int size) {
        BlogSearchResponseDto response = client.searchBlog(query, page, size);
        return BlogSearchResult.builder()
                .documents(response.toDocuments())
                .meta(response.toMeta())
                .build();
    }
}
