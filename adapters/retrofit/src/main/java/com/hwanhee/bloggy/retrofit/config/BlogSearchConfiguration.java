package com.hwanhee.bloggy.retrofit.config;

import com.hwanhee.bloggy.application.ports.out.BlogSearchServicePort;
import com.hwanhee.bloggy.retrofit.client.KakaoBlogSearchClient;
import com.hwanhee.bloggy.retrofit.service.BlogSearchService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BlogSearchConfiguration {

    @Value("${kakao.api.baseurl}")
    private String kakaoApiBaseUrl;

    @Value("${kakao.api.key}")
    private String kakaoApiKey;

    @Bean
    public KakaoBlogSearchClient kakaoBlogSearchClient() {
        return new KakaoBlogSearchClient(kakaoApiBaseUrl, kakaoApiKey);
    }

    @Bean
    public BlogSearchServicePort kakaoBlogSearchService(KakaoBlogSearchClient client) {
        return new BlogSearchService(client);
    }
}
