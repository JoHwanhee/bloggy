package com.hwanhee.bloggy.application.ports.out;


import com.hwanhee.bloggy.domain.model.BlogSearchResult;
import com.hwanhee.bloggy.domain.model.Sort;

public interface BlogSearchServicePort {
    BlogSearchResult search(String query, Sort sort, int page, int size);
}
