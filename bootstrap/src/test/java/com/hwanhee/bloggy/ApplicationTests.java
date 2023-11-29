package com.hwanhee.bloggy;

import io.restassured.RestAssured;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static com.hwanhee.bloggy.RequestSpecificationFunctions.given;
import static org.hamcrest.Matchers.*;


/**
 * 인수 테스트
 */
@Transactional
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
    @DisplayName("검색을 하고나면, 인기 검색어 목록에 추가되어야 한다.")
    void test5() {
        search("테스트");
        search("테스트");
        search("안녕");

        awaitSeconds(3);

        given()
                .when()
                .get("/keywords")
                .then()
                .log()
                .all()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("keyword", hasItems("테스트", "안녕"))
        ;
    }


    private static void awaitSeconds(int num) {
        try {
            Thread.sleep(num * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void search(String query) {
        given()
                .param("query", query)
                .when()
                .get("/blogs")
                .then()
                .statusCode(200);
    }

}
