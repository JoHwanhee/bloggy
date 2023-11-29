package com.hwanhee.bloggy.domain.model;

public class SearchEvent {
    private final String keyword;

    public SearchEvent(String keyword) {
        this.keyword = keyword;
    }

    public SearchKeyword toSearchKeyword() {
        return new SearchKeyword(this.keyword);
    }
}
