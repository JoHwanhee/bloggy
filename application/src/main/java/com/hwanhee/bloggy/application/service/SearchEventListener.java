package com.hwanhee.bloggy.application.service;


import com.hwanhee.bloggy.application.ports.in.SearchKeywordCommandUsecase;
import com.hwanhee.bloggy.domain.model.SearchEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SearchEventListener {

    private final SearchKeywordCommandUsecase searchedUsecase;

    @EventListener
    public void eventListener(SearchEvent searchEvent) {
        searchedUsecase.fireSearchEvent(searchEvent);
    }
}
