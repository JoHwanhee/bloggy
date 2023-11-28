package com.hwanhee.bloggy.application.dto;

import lombok.Builder;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value(staticConstructor = "of")
@Builder
public class BlogSearchResult {
    Meta meta;
    List<BlogSearchResponse> documents;

    public static BlogSearchResult empty() {
        return new BlogSearchResult(Meta.empty(), new ArrayList<>());
    }
}