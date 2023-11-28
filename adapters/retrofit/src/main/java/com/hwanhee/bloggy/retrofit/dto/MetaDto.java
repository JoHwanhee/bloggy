package com.hwanhee.bloggy.retrofit.dto;

import com.hwanhee.bloggy.domain.blog.Meta;
import lombok.Value;

@Value
public class MetaDto {
    int total_count;
    int pageable_count;
    boolean is_end;

    public Meta toMeta() {
        return Meta.of(total_count, pageable_count, is_end);
    }
}
