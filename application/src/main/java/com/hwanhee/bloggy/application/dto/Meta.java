package com.hwanhee.bloggy.application.dto;

import lombok.Value;

@Value(staticConstructor = "of")
class Meta {
    int totalCount;
    int pageableCount;
    boolean isEnd;

    public static Meta empty() {
        return new Meta(0, 0, false);
    }
}