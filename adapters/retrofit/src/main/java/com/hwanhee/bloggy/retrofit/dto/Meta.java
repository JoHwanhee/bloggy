package com.hwanhee.bloggy.retrofit.dto;

import lombok.Data;
import lombok.Value;

@Value
public class Meta {
    boolean is_end;
    int pageable_count;
    int total_count;
}