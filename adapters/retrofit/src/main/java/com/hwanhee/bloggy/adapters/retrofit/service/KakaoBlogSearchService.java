package com.hwanhee.bloggy.adapters.retrofit.service;

import com.hwanhee.bloggy.adapters.retrofit.client.KakaoBlogSearchClient;
import com.hwanhee.bloggy.adapters.retrofit.dto.KakaoBlogSearchResponseDto;
import com.hwanhee.bloggy.application.ports.out.BlogSearchServicePort;
import com.hwanhee.bloggy.domain.model.BlogSearchResult;
import com.hwanhee.bloggy.domain.model.Sort;
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
    public BlogSearchResult search(String query, Sort sort, int page, int size) {
        KakaoBlogSearchResponseDto response = client.searchBlog(query, sort.toKakaoString(), page, size);
        return BlogSearchResult.builder()
                .documents(response.toDocuments())
                .meta(response.toMeta())
                .build();
    }
}
