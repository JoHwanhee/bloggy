package com.hwanhee.bloggy.application.service;

import com.hwanhee.bloggy.application.ports.in.BlogSearchCommand;
import com.hwanhee.bloggy.application.ports.in.BlogSearchUsecase;
import com.hwanhee.bloggy.application.ports.out.BlogSearchServicePort;
import com.hwanhee.bloggy.domain.blog.BlogSearchResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class BlogSearchService implements BlogSearchUsecase {

    private final BlogSearchServicePort blogSearchServicePort;

    @Override
    public BlogSearchResult search(BlogSearchCommand searchCommand) {
        return blogSearchServicePort.search(searchCommand.getQuery(), searchCommand.getPage(), searchCommand.getSize());
    }
}
