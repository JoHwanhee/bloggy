package com.hwanhee.bloggy.adapters.rest.dto;

import lombok.Value;

@Value(staticConstructor = "of")
public class BlogSearchResponse {
    String title;
    String contents;
    String url;
    String blogName;
    String thumbnailUrl;
    String datetime;
}

