package com.hwanhee.bloggy.adapters.retrofit.service;

import com.hwanhee.bloggy.adapters.retrofit.client.KakaoBlogSearchClient;
import com.hwanhee.bloggy.adapters.retrofit.config.BlogSearchConfiguration;
import com.hwanhee.bloggy.domain.model.BlogSearchResult;
import com.hwanhee.bloggy.domain.model.Sort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Profile("test")
@ContextConfiguration(classes = {BlogSearchConfiguration.class})
class KakaoBlogSearchServiceTest {


    private KakaoBlogSearchService sut;

    @Value("${kakao.api.baseurl}")
    String kakaoApiBaseUrl;
    @Value("${kakao.api.key}")
    String kakaoApiAuthorization;

    @BeforeEach
    void setUp() {
        sut = new KakaoBlogSearchService(
                new KakaoBlogSearchClient(kakaoApiBaseUrl, kakaoApiAuthorization)
        );

    }

    @Test
    @DisplayName("DTO -> Domain 모델로 변환 된다.")
    void name() {
        BlogSearchResult actual = sut.search("테스트", Sort.ACCURACY, 1, 10);

        assertThat(actual).isInstanceOf(BlogSearchResult.class);
    }
}
