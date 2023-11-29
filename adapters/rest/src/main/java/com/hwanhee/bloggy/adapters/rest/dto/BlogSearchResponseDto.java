package com.hwanhee.bloggy.adapters.rest.dto;

import com.hwanhee.bloggy.domain.model.Document;
import lombok.Value;

@Value(staticConstructor = "of")
public class BlogSearchResponseDto {
    String title;
    String contents;
    String url;
    String blogName;
    String thumbnailUrl;
    String datetime;

    public static BlogSearchResponseDto from(Document domain) {
        return of(domain.getTitle(),
                domain.getContents(),
                domain.getUrl(),
                domain.getBlogName(),
                domain.getThumbnailUrl(),
                domain.getDatetime()
        );
    }
}

