package com.hwanhee.bloggy.application.service;

import com.hwanhee.bloggy.application.ports.in.BlogSearchCommand;
import com.hwanhee.bloggy.application.ports.in.BlogSearchUsecase;
import com.hwanhee.bloggy.application.ports.out.BlogSearchServicePort;
import com.hwanhee.bloggy.domain.blog.SearchEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class BlogSearchServiceTest {


    @MockBean
    private BlogSearchServicePort searchServicePortMock;

    @MockBean
    private ApplicationEventPublisher eventPublisherMock;

    private BlogSearchUsecase sut;

    @BeforeEach
    void setUp() {
        searchServicePortMock = mock(BlogSearchServicePort.class);
        eventPublisherMock = mock(ApplicationEventPublisher.class);

        sut = new BlogSearchService(searchServicePortMock, eventPublisherMock);
    }

    @Test
    @DisplayName("서치 키워드가 생성되면, 키워드 생성 도메인 이벤트가 발생한다.")
    void testKeywordCreationEvent() {
        sut.search(BlogSearchCommand.of("검색", "", 0, 0));

        verify(eventPublisherMock).publishEvent(any(SearchEvent.class));
    }
}
