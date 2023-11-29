package com.hwanhee.bloggy.adapters.jpa.entity;


import com.hwanhee.bloggy.domain.model.KeywordCount;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "keyword_count")
public class KeywordCountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String keyword;

    Long count;

    @UpdateTimestamp
    LocalDateTime updatedAt;

    @Version
    Long version;

    public static KeywordCountEntity of(
            String keyword,
            Long count
    ) {
        return new KeywordCountEntity(null, keyword, count, null, null);
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public KeywordCount toKeywordCount() {
        return new KeywordCount(keyword, count);
    }
}
