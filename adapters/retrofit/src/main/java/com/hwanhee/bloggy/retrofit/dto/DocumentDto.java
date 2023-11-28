package com.hwanhee.bloggy.retrofit.dto;

import lombok.Value;
import com.hwanhee.bloggy.domain.blog.Document;

@Value
public class DocumentDto {
    String title;
    String contents;
    String url;
    String blogname;
    String thumbnail;
    String datetime;

    public Document toDomain() {
        return Document.of(title, contents, url, blogname, thumbnail, datetime);
    }
}
