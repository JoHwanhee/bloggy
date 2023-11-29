package com.hwanhee.bloggy.adapters.retrofit.dto;

import com.hwanhee.bloggy.domain.model.Document;
import lombok.Value;

@Value
public class NaverItemDto {
    String title;
    String description;
    String link;
    String bloggername;
    String bloggerlink;
    String postdate;

    public Document toDomain() {
        return Document.of(title, description, link, bloggername, "", postdate);
    }
}
