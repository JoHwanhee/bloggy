package com.hwanhee.bloggy.domain.blog;

import lombok.Value;

@Value(staticConstructor = "of")
public class Meta {
    int totalCount;
    int pageableCount;
    boolean isEnd;

    public static Meta empty() {
        return new Meta(0, 0, false);
    }
}
