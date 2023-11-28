package com.hwanhee.bloggy.domain.blog;

public class SearchEvent {
    private final String keyword;

    public SearchEvent(String keyword) {
        this.keyword = keyword;
    }

    public SearchKeyword toSearchKeyword() {
        return new SearchKeyword();
    }
}
