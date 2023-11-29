package com.hwanhee.bloggy.domain.model;

import lombok.Value;

@Value(staticConstructor = "of")
public class Meta {
    Long totalCount;
    Long pageableCount;
    boolean isEnd;

    public static Meta empty() {
        return new Meta(0L, 0L, false);
    }
}
