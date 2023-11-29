package com.hwanhee.bloggy.application.ports.in;


import com.hwanhee.bloggy.domain.model.Sort;
import lombok.Value;

@Value(staticConstructor = "of")
public class BlogSearchCommand {
    String query;
    Sort sort;
    int page;
    int size;
}
