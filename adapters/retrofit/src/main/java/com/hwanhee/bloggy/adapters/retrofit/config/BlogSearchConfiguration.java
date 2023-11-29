package com.hwanhee.bloggy.adapters.retrofit.config;

import com.hwanhee.bloggy.adapters.retrofit.client.KakaoBlogSearchClient;
import com.hwanhee.bloggy.adapters.retrofit.client.NaverBlogSearchClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BlogSearchConfiguration {

    @Value("${kakao.api.baseurl}")
    private String kakaoApiBaseUrl;

    @Value("${kakao.api.key}")
    private String kakaoApiKey;


    @Value("${naver.api.baseurl}")
    private String naverApiBaseUrl;

    @Value("${naver.api.client-id}")
    private String naverApiClientId;

    @Value("${naver.api.client-secret}")
    private String naverApiClientSecret;

    @Bean
    public KakaoBlogSearchClient kakaoBlogSearchClient() {
        return new KakaoBlogSearchClient(kakaoApiBaseUrl, kakaoApiKey);
    }

    @Bean
    public NaverBlogSearchClient naverBlogSearchClient() {
        return new NaverBlogSearchClient(naverApiBaseUrl, naverApiClientId, naverApiClientSecret);
    }
}
