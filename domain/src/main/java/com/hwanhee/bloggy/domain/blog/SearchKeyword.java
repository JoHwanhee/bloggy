package com.hwanhee.bloggy.domain.blog;

import lombok.Getter;

@Getter
public class SearchKeyword  {
    private String keyword;
    private Long searchedCount;

    void increase() {
        this.searchedCount += 1;
    }
}
