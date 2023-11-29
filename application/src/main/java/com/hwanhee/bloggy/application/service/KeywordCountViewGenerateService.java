package com.hwanhee.bloggy.application.service;

import com.hwanhee.bloggy.application.ports.in.KeywordCountViewGenerateUsecase;
import com.hwanhee.bloggy.application.ports.out.IKeywordCountPort;
import com.hwanhee.bloggy.application.ports.out.ISearchKeywordPort;
import com.hwanhee.bloggy.domain.model.KeywordCount;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class KeywordCountViewGenerateService implements KeywordCountViewGenerateUsecase {

    private final ISearchKeywordPort searchKeywordPort;
    private final IKeywordCountPort keywordCountPort;

    @Override
    public void gen() {
        LocalDateTime threshold = LocalDateTime.now().minusMinutes(5);
        List<KeywordCount> result = searchKeywordPort.findKeywordCountsSince(threshold);
        result.forEach(keywordCountPort::save);
    }
}
