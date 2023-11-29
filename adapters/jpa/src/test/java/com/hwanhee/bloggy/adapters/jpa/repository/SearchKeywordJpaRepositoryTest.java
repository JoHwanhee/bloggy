package com.hwanhee.bloggy.adapters.jpa.repository;

import com.hwanhee.bloggy.domain.model.KeywordCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDateTime;
import java.util.List;

import static com.hwanhee.bloggy.adapters.jpa.Fixture.*;
import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@EntityScan(basePackages = {"com.hwanhee.bloggy.adapters.jpa.entity"})
@EnableJpaRepositories(basePackages = {"com.hwanhee.bloggy.adapters.jpa.repository"})
class SearchKeywordJpaRepositoryTest {

    @Autowired
    private SearchKeywordJpaRepository sut;

    @Test
    @DisplayName("5분 이내의 검색어 카운트 조회")
    void testFindKeywordCountsSince() {
        sut.save(KEYWORD_TEST1);
        sut.save(KEYWORD_TEST2);
        sut.save(KEYWORD_TEST3);
        sut.save(KEYWORD_TEST4);

        LocalDateTime threshold = LocalDateTime.now().minusMinutes(5);
        List<KeywordCount> results = sut.findKeywordCountsSince(threshold);

        assertThat(results).hasSize(4);
    }
}