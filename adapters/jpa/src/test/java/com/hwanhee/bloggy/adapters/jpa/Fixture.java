package com.hwanhee.bloggy.adapters.jpa;

import com.hwanhee.bloggy.adapters.jpa.entity.KeywordCountEntity;
import com.hwanhee.bloggy.adapters.jpa.entity.SearchKeywordEntity;
import com.hwanhee.bloggy.adapters.jpa.repository.KeywordCountJpaRepository;
import com.hwanhee.bloggy.adapters.jpa.repository.SearchKeywordJpaRepository;
import com.hwanhee.bloggy.domain.model.KeywordCount;

import java.time.LocalDateTime;
import java.util.List;

public class Fixture {

    public static SearchKeywordEntity KEYWORD_TEST1 = SearchKeywordEntity.from("test1");
    public static SearchKeywordEntity KEYWORD_TEST2 = SearchKeywordEntity.from("test2");
    public static SearchKeywordEntity KEYWORD_TEST3 = SearchKeywordEntity.from("test3");
    public static SearchKeywordEntity KEYWORD_TEST4 = SearchKeywordEntity.from("test4");

    public static void saveSearchKeywords(SearchKeywordJpaRepository searchKeywordJpaRepository) {
        searchKeywordJpaRepository.save(KEYWORD_TEST1);
        searchKeywordJpaRepository.save(KEYWORD_TEST2);
        searchKeywordJpaRepository.save(KEYWORD_TEST3);
        searchKeywordJpaRepository.save(KEYWORD_TEST4);
    }

    public static void saveKeywordCounts(SearchKeywordJpaRepository searchKeywordJpaRepository,
                                         KeywordCountJpaRepository keywordCountJpaRepository) {
        LocalDateTime threshold = LocalDateTime.now().minusMinutes(5);
        List<KeywordCount> results = searchKeywordJpaRepository.findKeywordCountsSince(threshold);
        results.forEach(keywordCount -> {
            KeywordCountEntity entity = keywordCountJpaRepository.findByKeyword(keywordCount.getKeyword())
                    .orElse(KeywordCountEntity.of(keywordCount.getKeyword(), keywordCount.getCount()));

            entity.setCount(keywordCount.getCount());
            keywordCountJpaRepository.save(entity);
        });
    }

}
