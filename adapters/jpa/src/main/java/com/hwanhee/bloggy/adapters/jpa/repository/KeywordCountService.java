package com.hwanhee.bloggy.adapters.jpa.repository;

import com.hwanhee.bloggy.adapters.jpa.entity.KeywordCountEntity;
import com.hwanhee.bloggy.application.ports.out.IKeywordCountPort;
import com.hwanhee.bloggy.domain.model.KeywordCount;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class KeywordCountService implements IKeywordCountPort {

    private final KeywordCountJpaRepository jpaRepository;


    @Override
    public void save(KeywordCount keywordCount) {
        Optional<KeywordCountEntity> existingEntity = jpaRepository.findByKeyword(keywordCount.getKeyword());

        KeywordCountEntity entity = existingEntity
                .map(e -> {
                    e.setCount(keywordCount.getCount());
                    return e;
                })
                .orElseGet(() -> KeywordCountEntity.of(keywordCount.getKeyword(), keywordCount.getCount())); // 새 엔티티 생성

        this.jpaRepository.save(entity);
    }

    @Override
    public List<KeywordCount> findTop10Keywords() {
        return jpaRepository.findTop10ByOrderByCountDesc()
                .stream()
                .map(KeywordCountEntity::toKeywordCount)
                .toList();
    }
}
