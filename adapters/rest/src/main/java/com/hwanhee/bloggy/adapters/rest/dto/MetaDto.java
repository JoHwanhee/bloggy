package com.hwanhee.bloggy.adapters.rest.dto;

import com.hwanhee.bloggy.domain.model.Meta;
import lombok.Value;

@Value(staticConstructor = "of")
public class MetaDto {
    Long totalCount;
    Long pageableCount;
    boolean isEnd;

    public static MetaDto empty() {
        return new MetaDto(0L, 0L, false);
    }

    public static MetaDto from(Meta meta) {
        return of(meta.getTotalCount(), meta.getPageableCount(), meta.isEnd());
    }
}
