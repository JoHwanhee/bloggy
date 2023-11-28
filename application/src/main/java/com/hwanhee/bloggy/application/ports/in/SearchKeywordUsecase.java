package com.hwanhee.bloggy.application.ports.in;


import com.hwanhee.bloggy.domain.blog.SearchEvent;

public interface SearchKeywordUsecase {
    void searched(SearchEvent searchEvent);
}
