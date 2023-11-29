package com.hwanhee.bloggy.adapters.retrofit.dto;

import com.hwanhee.bloggy.domain.model.Document;
import com.hwanhee.bloggy.domain.model.Meta;
import lombok.Value;

import java.util.List;

@Value(staticConstructor = "of")
public class KakaoBlogSearchResponseDto {
    List<KakaoDocumentDto> documents;
    KaKaoMetaDto meta;

    public List<Document> toDocuments() {
        return getDocuments()
                .stream()
                .map(KakaoDocumentDto::toDomain)
                .toList();
    }

    public Meta toMeta() {
        return getMeta()
                .toMeta();
    }
}



