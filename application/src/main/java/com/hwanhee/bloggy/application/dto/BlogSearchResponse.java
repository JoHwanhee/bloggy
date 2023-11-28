package com.hwanhee.bloggy.application.dto;

import lombok.Value;

@Value(staticConstructor = "of")
class BlogSearchResponse {
    String title;
    String contents;
    String url;
    String blogName;
    String thumbnailUrl;
    String datetime;
}

