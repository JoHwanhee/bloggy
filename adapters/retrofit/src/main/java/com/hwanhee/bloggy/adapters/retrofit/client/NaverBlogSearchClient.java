package com.hwanhee.bloggy.adapters.retrofit.client;

import com.hwanhee.bloggy.adapters.retrofit.dto.NaverBlogSearchResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

import java.io.IOException;


@Component
public class NaverBlogSearchClient {

    private final NaverBlogSearchApi api;
    private final String clientId;
    private final String clientSecret;

    public NaverBlogSearchClient(@Value("${naver.api.baseurl}") String baseUrl,
                                 @Value("${naver.api.client-id}") String clientId,
                                 @Value("${naver.api.client-secret}") String clientSecret
    ) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.api = retrofit.create(NaverBlogSearchApi.class);
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public NaverBlogSearchResponseDto searchBlog(String query, String sort, int page, int size) {
        try {
            Response<NaverBlogSearchResponseDto> response = api.searchBlog(query, sort, page, size, this.clientId, this.clientSecret).execute();

            if (!response.isSuccessful()) {
                throw new RuntimeException("API Request Failed. Code: " + response.code() + response.errorBody());
            }
            return response.body();
        } catch (IOException e) {
            throw new RuntimeException("Network Error Occurred", e);
        }
    }

    private interface NaverBlogSearchApi {
        @GET("/v1/search/blog.json")
        Call<NaverBlogSearchResponseDto> searchBlog(
                @Query("query") String query,
                @Query("sort") String sort,
                @Query("start") int start,
                @Query("display") int display,
                @Header("X-Naver-Client-Id") String clientId,
                @Header("X-Naver-Client-Secret") String clientSecret
        );
    }
}
