package com.hwanhee.bloggy.adapters.jpa.repository;

import com.hwanhee.bloggy.adapters.jpa.entity.SearchKeywordEntity;
import com.hwanhee.bloggy.application.ports.out.ISearchKeywordPort;
import com.hwanhee.bloggy.domain.model.KeywordCount;
import com.hwanhee.bloggy.domain.model.SearchKeyword;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@AllArgsConstructor
public class SearchKeywordRepository implements ISearchKeywordPort {

    //    private final JPAQueryFactory queryFactory;
    private final SearchKeywordJpaRepository jpaRepository;

    @Override
    public void save(SearchKeyword searchKeyword) {
        SearchKeywordEntity entity = SearchKeywordEntity.from(searchKeyword.getKeyword());

        this.jpaRepository.save(entity);
    }

    @Override
    public List<KeywordCount> findKeywordCountsSince(LocalDateTime threshold) {

        return jpaRepository.findKeywordCountsSince(threshold);
    }
}
