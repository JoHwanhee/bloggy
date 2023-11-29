package com.hwanhee.bloggy.adapters.retrofit.dto;

import com.hwanhee.bloggy.domain.model.Document;
import lombok.Value;

@Value
public class KakaoDocumentDto {
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
