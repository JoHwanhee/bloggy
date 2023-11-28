package com.hwanhee.bloggy.application.service;

import com.hwanhee.bloggy.application.ports.in.SearchKeywordUsecase;
import com.hwanhee.bloggy.domain.blog.ISearchKeywordRepository;
import com.hwanhee.bloggy.domain.blog.SearchEvent;
import com.hwanhee.bloggy.domain.blog.SearchKeyword;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SearchKeywordService implements SearchKeywordUsecase {

    private final ISearchKeywordRepository repository;

    @Override
    public void searched(SearchEvent searchEvent) {
        repository.save(searchEvent.toSearchKeyword());
    }
}
