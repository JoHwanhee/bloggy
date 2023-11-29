package com.hwanhee.bloggy.application.service;

import com.hwanhee.bloggy.application.ports.in.KeywordCountQueryUsecase;
import com.hwanhee.bloggy.application.ports.out.IKeywordCountPort;
import com.hwanhee.bloggy.domain.model.KeywordCount;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class KeywordCountQueryService implements KeywordCountQueryUsecase {

    private final IKeywordCountPort keywordCountPort;

    @Override
    public List<KeywordCount> findKeywordCounts() {
        return keywordCountPort.findTop10Keywords();
    }
}
