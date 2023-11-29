package com.hwanhee.bloggy.adapters.retrofit.service;

import com.hwanhee.bloggy.adapters.retrofit.client.NaverBlogSearchClient;
import com.hwanhee.bloggy.adapters.retrofit.dto.NaverBlogSearchResponseDto;
import com.hwanhee.bloggy.application.ports.out.BlogSearchServicePort;
import com.hwanhee.bloggy.domain.model.BlogSearchResult;
import com.hwanhee.bloggy.domain.model.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NaverBlogSearchService implements BlogSearchServicePort {

    private final NaverBlogSearchClient client;

    @Autowired
    public NaverBlogSearchService(NaverBlogSearchClient client) {
        this.client = client;
    }

    @Override
    public BlogSearchResult search(String query, Sort sort, int page, int size) {
        NaverBlogSearchResponseDto response = client.searchBlog(query, sort.toNaverString(), page, size);
        return BlogSearchResult.builder()
                .documents(response.toDocuments())
                .meta(response.toMeta())
                .build();
    }
}
