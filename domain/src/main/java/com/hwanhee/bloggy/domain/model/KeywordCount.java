package com.hwanhee.bloggy.domain.model;

import lombok.Value;

@Value
public class KeywordCount {
    String keyword;
    Long count;
}