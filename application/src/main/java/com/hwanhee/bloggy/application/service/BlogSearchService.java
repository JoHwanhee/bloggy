package com.hwanhee.bloggy.application.service;

import com.hwanhee.bloggy.application.ports.in.BlogSearchCommand;
import com.hwanhee.bloggy.application.ports.in.BlogSearchUsecase;
import com.hwanhee.bloggy.application.ports.out.BlogSearchServicePort;
import com.hwanhee.bloggy.domain.model.BlogSearchResult;
import com.hwanhee.bloggy.domain.model.SearchEvent;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
class BlogSearchService implements BlogSearchUsecase {

    private final BlogSearchServicePort blogSearchServicePort;
    private final ApplicationEventPublisher applicationEventPublisher;

    public BlogSearchService(@Qualifier("blogSearchServiceContainer") BlogSearchServicePort blogSearchServicePort, ApplicationEventPublisher applicationEventPublisher) {
        this.blogSearchServicePort = blogSearchServicePort;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public BlogSearchResult search(BlogSearchCommand searchCommand) {
        BlogSearchResult result = blogSearchServicePort.search(searchCommand.getQuery(), searchCommand.getSort(), searchCommand.getPage(), searchCommand.getSize());
        applicationEventPublisher.publishEvent(new SearchEvent(searchCommand.getQuery()));

        return result;
    }
}
