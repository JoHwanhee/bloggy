package com.hwanhee.bloggy.application.service;

import com.hwanhee.bloggy.application.dto.BlogSearchResult;
import com.hwanhee.bloggy.application.ports.in.BlogSearchUsecase;
import com.hwanhee.bloggy.application.ports.out.BlogSearchServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class BlogSearchService implements BlogSearchUsecase {

    private final BlogSearchServicePort blogSearchServicePort;

    @Override
    public BlogSearchResult search(String query, int page) {
        return blogSearchServicePort.search(query, page, 0);
    }
}
