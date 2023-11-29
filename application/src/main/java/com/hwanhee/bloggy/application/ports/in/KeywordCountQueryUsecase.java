package com.hwanhee.bloggy.application.ports.in;


import com.hwanhee.bloggy.domain.model.KeywordCount;

import java.util.List;

public interface KeywordCountQueryUsecase {
    List<KeywordCount> findKeywordCounts();
}
