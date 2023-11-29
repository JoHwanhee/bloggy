package com.hwanhee.bloggy.adapters.retrofit.dto;

import com.hwanhee.bloggy.domain.model.Document;
import com.hwanhee.bloggy.domain.model.Meta;
import lombok.Value;

import java.util.List;

@Value(staticConstructor = "of")
public class NaverBlogSearchResponseDto {
    List<NaverItemDto> items;

    Long total;
    Long start;
    Long display;


    public List<Document> toDocuments() {
        return getItems()
                .stream()
                .map(NaverItemDto::toDomain)
                .toList();
    }

    public Meta toMeta() {
        return Meta.of(total, start, isEnd());
    }

    private boolean isEnd() {
        return (start * display) >= total;
    }
}



