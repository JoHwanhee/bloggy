package com.hwanhee.bloggy.adapters.retrofit.dto;

import com.hwanhee.bloggy.domain.model.Meta;
import lombok.Value;

@Value
public class KaKaoMetaDto {
    Long total_count;
    Long pageable_count;
    boolean is_end;

    public Meta toMeta() {
        return Meta.of(total_count, pageable_count, is_end);
    }
}
