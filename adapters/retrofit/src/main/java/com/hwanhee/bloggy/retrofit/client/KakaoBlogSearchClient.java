package com.hwanhee.bloggy.retrofit.client;

import com.hwanhee.bloggy.retrofit.dto.BlogSearchResponse;
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
public class KakaoBlogSearchClient {

	private final KakaoBlogSearchApi api;
	private final String apiKey;

	public KakaoBlogSearchClient(@Value("${kakao.api.baseurl}") String baseUrl,
								 @Value("${kakao.api.key}") String apiKey) {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(baseUrl)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		this.api = retrofit.create(KakaoBlogSearchApi.class);
		this.apiKey = apiKey;
	}

	public BlogSearchResponse searchBlog(String query, int page, int size) {
		try {
			Response<BlogSearchResponse> response = api.searchBlog(query, "accuracy", page, size, "KakaoAK " + apiKey).execute();

			if (!response.isSuccessful()) {
				throw new RuntimeException("API Request Failed. Code: " + response.code());
			}
			return response.body();
		} catch (IOException e) {
			throw new RuntimeException("Network Error Occurred", e);
		}
	}

	private interface KakaoBlogSearchApi {
		@GET("/v2/search/blog")
		Call<BlogSearchResponse> searchBlog(
				@Query("query") String query,
				@Query("sort") String sort,
				@Query("page") int page,
				@Query("size") int size,
				@Header("Authorization") String auth
		);
	}
}