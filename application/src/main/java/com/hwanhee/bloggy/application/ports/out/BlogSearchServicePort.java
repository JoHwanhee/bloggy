package com.hwanhee.bloggy.application.ports.out;


import com.hwanhee.bloggy.application.dto.BlogSearchResult;

public interface BlogSearchServicePort {
    BlogSearchResult search(String query, int page, int size);
}
