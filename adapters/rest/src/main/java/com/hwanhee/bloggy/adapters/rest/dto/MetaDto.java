package com.hwanhee.bloggy.adapters.rest.dto;

import com.hwanhee.bloggy.domain.blog.Meta;
import lombok.Value;

@Value(staticConstructor = "of")
public class MetaDto {
    int totalCount;
    int pageableCount;
    boolean isEnd;

    public static MetaDto empty() {
        return new MetaDto(0, 0, false);
    }

    public static MetaDto from(Meta meta) {
        return of(meta.getTotalCount(), meta.getPageableCount(), meta.isEnd());
    }
}
