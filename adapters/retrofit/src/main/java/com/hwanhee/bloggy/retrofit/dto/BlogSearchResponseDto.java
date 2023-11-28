package com.hwanhee.bloggy.retrofit.dto;

import com.hwanhee.bloggy.domain.blog.Document;
import com.hwanhee.bloggy.domain.blog.Meta;
import lombok.Value;

import java.util.List;

@Value(staticConstructor = "of")
public class BlogSearchResponseDto {
    List<DocumentDto> documents;
    MetaDto meta;

    public List<Document> toDocuments() {
        return getDocuments()
                .stream()
                .map(DocumentDto::toDomain)
                .toList();
    }

    public Meta toMeta() {
        return getMeta()
                .toMeta();
    }
}



