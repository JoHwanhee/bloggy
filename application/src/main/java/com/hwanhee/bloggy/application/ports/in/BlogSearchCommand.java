package com.hwanhee.bloggy.application.ports.in;


import com.hwanhee.bloggy.domain.blog.BlogSearchResult;
import lombok.Value;

@Value(staticConstructor = "of")
public class BlogSearchCommand {
    String query;
    String accuracy;
    int page;
    int size;
}
