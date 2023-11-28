package com.hwanhee.bloggy.domain.blog;

public interface ISearchKeywordRepository {
    void save(SearchKeyword searchKeyword);

    SearchKeyword findByKeyword(String keyword);
}
