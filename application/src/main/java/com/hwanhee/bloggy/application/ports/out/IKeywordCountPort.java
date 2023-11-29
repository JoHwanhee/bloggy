package com.hwanhee.bloggy.application.ports.out;

import com.hwanhee.bloggy.domain.model.KeywordCount;

import java.util.List;

public interface IKeywordCountPort {
    void save(KeywordCount keywordCount);

    List<KeywordCount> findTop10Keywords();
}
