package com.hwanhee.bloggy.application.ports.in;


import com.hwanhee.bloggy.domain.model.SearchEvent;

public interface SearchKeywordCommandUsecase {
    void fireSearchEvent(SearchEvent searchEvent);
}
