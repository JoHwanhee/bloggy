package com.hwanhee.bloggy.adapters.jpa.repository;

import com.hwanhee.bloggy.adapters.jpa.entity.SearchKeywordEntity;
import com.hwanhee.bloggy.domain.model.KeywordCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SearchKeywordJpaRepository extends JpaRepository<SearchKeywordEntity, Long> {

    // TODO : QueryDSL로 변경
    @Query("SELECT new com.hwanhee.bloggy.domain.model.KeywordCount(s.keyword, COUNT(s)) " +
            "FROM search_keyword s " +
            "WHERE s.createdAt > :threshold " +
            "GROUP BY s.keyword")
    List<KeywordCount> findKeywordCountsSince(@Param("threshold") LocalDateTime threshold);
}
