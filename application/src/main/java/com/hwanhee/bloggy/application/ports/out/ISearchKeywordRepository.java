package com.hwanhee.bloggy.application.ports.out;

import com.hwanhee.bloggy.domain.blog.SearchKeyword;

public interface ISearchKeywordRepository {
    void save(SearchKeyword searchKeyword);

    SearchKeyword findByKeyword(String keyword);
}
