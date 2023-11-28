package com.hwanhee.bloggy.application.service;


import com.hwanhee.bloggy.application.ports.in.SearchKeywordUsecase;
import com.hwanhee.bloggy.domain.blog.SearchEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SearchEventListener {

    private final SearchKeywordUsecase searchedUsecase;

    @EventListener
    public void eventListener(SearchEvent searchEvent) {
        searchedUsecase.searched(searchEvent);
    }
}
