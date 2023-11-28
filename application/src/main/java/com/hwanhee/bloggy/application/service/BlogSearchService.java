package com.hwanhee.bloggy.application.service;

import com.hwanhee.bloggy.application.ports.in.BlogSearchCommand;
import com.hwanhee.bloggy.application.ports.in.BlogSearchUsecase;
import com.hwanhee.bloggy.application.ports.out.BlogSearchServicePort;
import com.hwanhee.bloggy.domain.blog.BlogSearchResult;
import com.hwanhee.bloggy.domain.blog.SearchEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class BlogSearchService implements BlogSearchUsecase {

    private final BlogSearchServicePort blogSearchServicePort;

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public BlogSearchResult search(BlogSearchCommand searchCommand) {
        BlogSearchResult result = blogSearchServicePort.search(searchCommand.getQuery(), searchCommand.getPage(), searchCommand.getSize());
        applicationEventPublisher.publishEvent(new SearchEvent(searchCommand.getQuery()));

        return result;
    }
}
