package com.hwanhee.bloggy;

import com.hwanhee.bloggy.application.ports.in.KeywordCountViewGenerateUsecase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class ApplicationSchedulerTest {

    @Mock
    private KeywordCountViewGenerateUsecase keywordCountViewGenerateUsecase;

    @InjectMocks
    private ApplicationScheduler applicationScheduler;

    @Test
    @DisplayName("ApplicationScheduler가 KeywordCountViewGenerateUsecase를 호출한다.")
    void testSchedulerCallsKeywordViewGenerateUsecase() {
        applicationScheduler.gen();

        verify(keywordCountViewGenerateUsecase, times(1)).gen();
    }
}