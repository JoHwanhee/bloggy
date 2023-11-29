package com.hwanhee.bloggy.adapters.retrofit.service;

import com.hwanhee.bloggy.application.ports.out.BlogSearchServicePort;
import com.hwanhee.bloggy.domain.model.BlogSearchResult;
import com.hwanhee.bloggy.domain.model.Sort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;


class BlogSearchServiceContainerTest {

    private BlogSearchServicePort mockService1;
    private BlogSearchServicePort mockService2;

    private BlogSearchServiceContainer sut;

    @BeforeEach
    void setUp() {
        mockService1 = mock(BlogSearchServicePort.class);
        mockService2 = mock(BlogSearchServicePort.class);
        sut = new BlogSearchServiceContainer(Arrays.copyOf(new BlogSearchServicePort[]{mockService1, mockService2}, 2));
    }

    @Test
    @DisplayName("검색 결과가 없으면 다음 검색포트를 호출한다.")
    void search_WhenCalled_ShouldVerifyEachServiceIsCalled() {
        when(mockService1.search(any(), any(), anyInt(), anyInt())).thenReturn(BlogSearchResult.empty());
        when(mockService2.search(any(), any(), anyInt(), anyInt())).thenReturn(BlogSearchResult.empty());

        sut.search("query", Sort.ACCURACY, 1, 10);

        verify(mockService1, times(1)).search(eq("query"), eq(Sort.ACCURACY), eq(1), eq(10));
        verify(mockService2, times(1)).search(eq("query"), eq(Sort.ACCURACY), eq(1), eq(10));
    }
}