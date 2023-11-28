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
    @DisplayName("응답값에 페이지 정보가 포함 되어야한다.")
    void testPaging() {
        given()
            .param("query", "테스트")

        .when()
            .get("/blogs")

        .then()
                .log()
                .all()
            .statusCode(200)
                .body("documents", hasSize(greaterThan(0)))
                .body("meta.totalCount", greaterThan(0))
                .body("meta.pageableCount", greaterThan(0))
        ;
    }

//    @Test
//    @DisplayName("page & size 파라미터는 없거나, 0 이상이어야한다.")
//    void testRequest() {
//        given()
//            .param("query", "테스트")
//            .param("size", 0)
//
//        .when()
//            .get("/blogs")
//
//        .then()
//            .statusCode(400)
//        ;
//    }




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
    @DisplayName("검색어가 없으면, 400에러")
    void test4() {
        given()
            .when()
        .get("/blogs")
            .then()
        .statusCode(400)
        ;
    }

    @Test
    @DisplayName("검색 결과에서 '최신순' 기능을 지원해야 한다.")
    void test3() {
//        JsonPath response = given()
//            .param("query", "테스트")
//            .param("sort", "recency")
//        .when()
//        .get("/blogs")
//        .then()
//            .statusCode(200)
//            .extract()
//            .jsonPath();
//
//
//        List<String> dates = response.getList("documents.datetime");
//        for (int i = 0; i < dates.size() - 1; i++) {
//            String current = dates.get(i);
//            String next = dates.get(i + 1);
//
//            assertThat(current, lessThanOrEqualTo(next));
//        }
    }
}
