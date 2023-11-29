package com.hwanhee.bloggy.application.ports.in;


import com.hwanhee.bloggy.domain.model.BlogSearchResult;

public interface BlogSearchUsecase {
    BlogSearchResult search(BlogSearchCommand searchCommand);
}
