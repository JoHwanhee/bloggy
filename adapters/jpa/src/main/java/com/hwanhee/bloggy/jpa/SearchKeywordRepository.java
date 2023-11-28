package com.hwanhee.bloggy.jpa;

import com.hwanhee.bloggy.application.ports.out.ISearchKeywordRepository;
import com.hwanhee.bloggy.domain.blog.SearchKeyword;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class SearchKeywordRepository implements ISearchKeywordRepository {

    private final SearchKeywordJpaRepository jpaRepository;

    @Override
    public void save(SearchKeyword searchKeyword) {
        SearchKeywordEntity entity = SearchKeywordEntity
                .builder()
                .keyword(searchKeyword.getKeyword())
                .build();

        this.jpaRepository.save(entity);
    }

    @Override
    public SearchKeyword findByKeyword(String keyword) {
        return null;
    }
}
