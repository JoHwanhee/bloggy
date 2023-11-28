package com.hwanhee.bloggy.retrofit.dto;

import lombok.Data;
import lombok.Value;

import java.util.List;

@Value
public class BlogSearchResponse {
    List<Document> documents;
    Meta meta;
}



