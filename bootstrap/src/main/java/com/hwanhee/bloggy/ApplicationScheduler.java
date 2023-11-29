package com.hwanhee.bloggy;

import com.hwanhee.bloggy.application.ports.in.KeywordCountViewGenerateUsecase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
public class ApplicationScheduler {


    private final KeywordCountViewGenerateUsecase searchKeywordViewGenerateUsecase;
    private final Object lock = new Object();


    public ApplicationScheduler(@Qualifier("keywordCountViewGenerateService") KeywordCountViewGenerateUsecase searchKeywordViewGenerateUsecase) {
        this.searchKeywordViewGenerateUsecase = searchKeywordViewGenerateUsecase;
    }

    // 릴리즈에서는 5분마다 실행 => 트래픽 보고 적당히..
    @Scheduled(cron = "* * * * * *")
    public void gen() {
        synchronized(lock) {
            searchKeywordViewGenerateUsecase.gen();
        }
    }
}
