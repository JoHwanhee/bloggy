package com.hwanhee.bloggy.adapters.rest.controller;

import com.hwanhee.bloggy.application.ports.in.BlogSearchUsecase;
import com.hwanhee.bloggy.application.ports.in.KeywordCountQueryUsecase;
import com.hwanhee.bloggy.domain.model.KeywordCount;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class KeywordCountControllerTest {

    @LocalServerPort
    int port;

    @MockBean
    private BlogSearchUsecase mockBlogSearchUsecase;

    @MockBean
    private KeywordCountQueryUsecase mockKeywordCountQueryUsecase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        RestAssured.port = port;
    }

    @Test
    @DisplayName("GET /keywords")
    void testKeywordsEndpoint() {
        List<KeywordCount> expectedKeywordCounts = List.of(
                new KeywordCount("keyword1", 10L),
                new KeywordCount("keyword2", 20L)
        );

        given(mockKeywordCountQueryUsecase.findKeywordCounts()).willReturn(expectedKeywordCounts);

        RestAssured.given()
            .when()
                .get("/keywords")
                .then()
            .statusCode(200)
                .body("size()", is(2))
                .body("[0].keyword", equalTo("keyword1"))
                .body("[0].count", equalTo(10))
                .body("[1].keyword", equalTo("keyword2"))
                .body("[1].count", equalTo(20));
        ;

    }
}