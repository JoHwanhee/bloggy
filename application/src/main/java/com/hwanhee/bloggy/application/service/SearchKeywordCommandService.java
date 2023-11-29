package com.hwanhee.bloggy.application.service;

import com.hwanhee.bloggy.application.ports.in.SearchKeywordCommandUsecase;
import com.hwanhee.bloggy.application.ports.out.ISearchKeywordPort;
import com.hwanhee.bloggy.domain.model.SearchEvent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SearchKeywordCommandService implements SearchKeywordCommandUsecase {

    private final ISearchKeywordPort repository;

    @Override
    public void fireSearchEvent(SearchEvent searchEvent) {
        repository.save(searchEvent.toSearchKeyword());
    }
}
