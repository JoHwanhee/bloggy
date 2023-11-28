package com.hwanhee.bloggy.bootstrap;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static com.hwanhee.bloggy.bootstrap.RequestSpecificationFunctions.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("블로그 검색 API 테스트")
class ApplicationTests {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("키워드를 통해 블로그를 검색할 수 있어야 한다.")
    void test1() {
        given()
            .param("query", "테스트")
            .param("size", 10)
            .param("page", 1)

        .when()
            .get("/blogs")
        .then()
                .log()
                .all()
            .statusCode(200)
                .body("documents", hasSize(greaterThan(0)))
                .body("meta.totalCount", greaterThan(0))
        ;
    }


    @Test
    @DisplayName("'정확도순' 검색 기능을 지원해야 한다.")
    void test2() {
        given()
            .param("query", "테스트")
            .param("sort", "accuracy")
        .when()
            .get("/blogs")
        .then()
            .statusCode(200)
            .body("documents", hasSize(greaterThan(0)))
            .body("meta.totalCount", greaterThan(0))
            .body("documents.title", everyItem(containsString("테스트")))
        ;
    }

    @Test
    @DisplayName("검색 결과에서 '최신순' 기능을 지원해야 한다.")
    void test3() {
        given()
            .param("query", "테스트")
            .param("sort", "recency")
        .when()
            .get("/blogs")
        .then()
            .statusCode(200);
    }
}
