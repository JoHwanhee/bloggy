package com.hwanhee.bloggy.adapters.rest.dto;

import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value(staticConstructor = "of")
public class BlogSearchResult {
    Meta meta;
    List<BlogSearchResponse> documents;

    public static BlogSearchResult empty() {
        return new BlogSearchResult(Meta.empty(), new ArrayList<>());
    }
}