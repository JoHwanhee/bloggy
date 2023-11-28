package com.hwanhee.bloggy.application.ports.in;


import com.hwanhee.bloggy.domain.blog.BlogSearchResult;

public interface BlogSearchUsecase {
    BlogSearchResult search(BlogSearchCommand searchCommand);
}
