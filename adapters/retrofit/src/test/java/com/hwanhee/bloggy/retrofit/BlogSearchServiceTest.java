package com.hwanhee.bloggy.retrofit;

import com.hwanhee.bloggy.application.dto.BlogSearchResult;
import com.hwanhee.bloggy.retrofit.config.BlogSearchConfiguration;
import com.hwanhee.bloggy.retrofit.service.BlogSearchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@Profile("test")
@ContextConfiguration(classes = {BlogSearchConfiguration.class})
class BlogSearchServiceTest {


    @Autowired
    private BlogSearchService sut;
    @Test
    void name() {
        BlogSearchResult response = sut.search("테스트", 1, 10);

    }
}