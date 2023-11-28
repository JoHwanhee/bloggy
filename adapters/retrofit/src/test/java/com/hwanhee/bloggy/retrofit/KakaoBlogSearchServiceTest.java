package com.hwanhee.bloggy.retrofit;

import com.hwanhee.bloggy.domain.blog.BlogSearchResult;
import com.hwanhee.bloggy.retrofit.config.BlogSearchConfiguration;
import com.hwanhee.bloggy.retrofit.service.KakaoBlogSearchService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Profile("test")
@ContextConfiguration(classes = {BlogSearchConfiguration.class})
class KakaoBlogSearchServiceTest {

    @Autowired
    private KakaoBlogSearchService sut;

    @Test
    @DisplayName("DTO -> Domain 모델로 변환 된다.")
    void name() {
        BlogSearchResult actual = sut.search("테스트", 1, 10);

        assertThat(actual).isInstanceOf(BlogSearchResult.class);
    }
}
