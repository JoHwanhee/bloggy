package com.hwanhee.bloggy.adapters.jpa.repository;

import com.hwanhee.bloggy.adapters.jpa.entity.KeywordCountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KeywordCountJpaRepository extends JpaRepository<KeywordCountEntity, Long> {
    Optional<KeywordCountEntity> findByKeyword(String keyword);

    List<KeywordCountEntity> findTop10ByOrderByCountDesc();
}
