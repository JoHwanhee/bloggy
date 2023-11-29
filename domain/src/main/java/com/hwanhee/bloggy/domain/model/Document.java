package com.hwanhee.bloggy.domain.model;

import lombok.Value;

@Value(staticConstructor = "of")
public class Document {
    String title;
    String contents;
    String url;
    String blogName;
    String thumbnailUrl;
    String datetime;
}

