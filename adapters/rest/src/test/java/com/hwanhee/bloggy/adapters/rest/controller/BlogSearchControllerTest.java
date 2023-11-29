package com.hwanhee.bloggy.adapters.rest.controller;

import com.hwanhee.bloggy.application.ports.in.BlogSearchCommand;
import com.hwanhee.bloggy.application.ports.in.BlogSearchUsecase;
import com.hwanhee.bloggy.application.ports.in.KeywordCountQueryUsecase;
import com.hwanhee.bloggy.domain.model.BlogSearchResult;
import com.hwanhee.bloggy.domain.model.Document;
import com.hwanhee.bloggy.domain.model.Meta;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BlogSearchControllerTest {

    @LocalServerPort
    int port;

    @MockBean
    private BlogSearchUsecase mockBlogSearchUsecase;

    @MockBean
    private KeywordCountQueryUsecase mockKeywordCountQueryUsecase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        List<Document> documents = List.of(Document.of("Title", "Contents", "URL", "BlogName", "ThumbnailUrl", "Datetime"));
        Meta meta = Meta.of(1L, 1L, false);
        BlogSearchResult mockResult = BlogSearchResult
                .builder()
                .documents(documents)
                .meta(meta)
                .build();

        when(mockBlogSearchUsecase.search(any(BlogSearchCommand.class)))
                .thenReturn(mockResult);

        RestAssured.port = port;

    }

    @Test
    @DisplayName("GET /blogs?query=test query&sort=accuracy&page=1&size=10")
    void testSearch() {
        given()
            .param("query", "test query")
            .param("sort", "accuracy")
            .param("page", 1)
            .param("size", 10)
        .when()
            .get("/blogs")
        .then()
            .statusCode(200)
                .body("documents.size()", is(1))
                .body("documents[0].title", equalTo("Title"))
                .body("meta.totalCount", equalTo(1));
        ;
    }

    @Test
    @DisplayName("GET /blogs?query=test query&sort=accuracy&page=1&size=10 => (invalid page number)")
    void testInvalidQueryParameters() {
        given()
                .log().all()
                .param("query", "test query")
                .param("sort", "accuracy")
                .param("page", -1)
                .param("size", 10)
                .when()
                .get("/blogs")
                .then()
                .log().all()
                .statusCode(400);
    }

    @Test
    @DisplayName("GET /blogs => (exception)")
    void testServiceThrowsException() {
        when(mockBlogSearchUsecase.search(any(BlogSearchCommand.class))).thenThrow(new RuntimeException("Test exception"));

        given()
                .param("query", "test query")
                .param("sort", "accuracy")
                .param("page", 1)
                .param("size", 10)
                .when()
                .get("/blogs")
                .then()
                .statusCode(500);
    }

    @Test
    @DisplayName("GET /blogs => (empty result)")
    void testEmptyResults() {
        when(mockBlogSearchUsecase.search(any(BlogSearchCommand.class))).thenReturn(BlogSearchResult.empty());

        given()
                .param("query", "test query")
                .param("sort", "accuracy")
                .param("page", 1)
                .param("size", 10)
                .when()
                .get("/blogs")
                .then()
                .statusCode(200)
                .body("documents.size()", is(0)); // No documents
    }
}