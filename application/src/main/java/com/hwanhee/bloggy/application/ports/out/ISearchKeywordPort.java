package com.hwanhee.bloggy.application.ports.out;

import com.hwanhee.bloggy.domain.model.KeywordCount;
import com.hwanhee.bloggy.domain.model.SearchKeyword;

import java.time.LocalDateTime;
import java.util.List;

public interface ISearchKeywordPort {
    void save(SearchKeyword searchKeyword);

    List<KeywordCount> findKeywordCountsSince(LocalDateTime threshold);
}
