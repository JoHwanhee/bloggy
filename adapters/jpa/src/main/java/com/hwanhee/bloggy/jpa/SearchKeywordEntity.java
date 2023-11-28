package com.hwanhee.bloggy.jpa;


import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Table(name = "search_keywords")
@Data
@Builder
public class SearchKeywordEntity {
    @Id
    Long id;

    String keyword;

    LocalDateTime createdAt;
}
