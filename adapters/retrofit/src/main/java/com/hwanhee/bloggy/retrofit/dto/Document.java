package com.hwanhee.bloggy.retrofit.dto;

import lombok.Data;
import lombok.Value;

@Value
public class Document {
    String blogname;
    String contents;
    String datetime;
    String thumbnail;
    String title;
    String url;
}