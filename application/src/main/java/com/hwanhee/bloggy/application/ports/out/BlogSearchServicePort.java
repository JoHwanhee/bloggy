package com.hwanhee.bloggy.application.ports.out;


import com.hwanhee.bloggy.domain.blog.BlogSearchResult;

public interface BlogSearchServicePort {
    BlogSearchResult search(String query, int page, int size);
}
