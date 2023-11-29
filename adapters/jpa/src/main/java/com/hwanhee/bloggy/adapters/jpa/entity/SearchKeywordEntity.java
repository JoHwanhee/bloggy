package com.hwanhee.bloggy.adapters.jpa.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "search_keyword")
public class SearchKeywordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String keyword;

    @CreationTimestamp
    LocalDateTime createdAt;

    public static SearchKeywordEntity from(String keyword) {
        return new SearchKeywordEntity(null, keyword, null);
    }
}
