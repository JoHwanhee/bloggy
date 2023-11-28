package com.hwanhee.bloggy.application.ports.in;


import com.hwanhee.bloggy.application.dto.BlogSearchResult;

public interface BlogSearchUsecase {
    BlogSearchResult search(String query, int page);
}
