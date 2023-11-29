package com.hwanhee.bloggy.adapters.retrofit.service;

import com.hwanhee.bloggy.application.ports.out.BlogSearchServicePort;
import com.hwanhee.bloggy.domain.model.BlogSearchResult;
import com.hwanhee.bloggy.domain.model.Sort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


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
    void search_WhenAllServicesReturnEmpty_ShouldThrowException() {
        when(mockService1.search(any(), any(), anyInt(), anyInt())).thenReturn(BlogSearchResult.empty());
        when(mockService2.search(any(), any(), anyInt(), anyInt())).thenReturn(BlogSearchResult.empty());

        assertThrows(RuntimeException.class, () ->
                sut.search("query", Sort.ACCURACY, 1, 10)
        );
    }
}