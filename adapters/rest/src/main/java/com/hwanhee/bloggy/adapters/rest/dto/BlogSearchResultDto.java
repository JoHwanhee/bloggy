package com.hwanhee.bloggy.adapters.rest.dto;

import com.hwanhee.bloggy.domain.model.BlogSearchResult;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value(staticConstructor = "of")
public class BlogSearchResultDto {
    MetaDto meta;
    List<BlogSearchResponseDto> documents;

    public static BlogSearchResultDto empty() {
        return new BlogSearchResultDto(MetaDto.empty(), new ArrayList<>());
    }

    public static BlogSearchResultDto from(BlogSearchResult resultDomain) {
        return of(
                MetaDto.from(resultDomain.getMeta()),
                resultDomain.getDocuments().stream().map(BlogSearchResponseDto::from).toList()
        );
    }
}
