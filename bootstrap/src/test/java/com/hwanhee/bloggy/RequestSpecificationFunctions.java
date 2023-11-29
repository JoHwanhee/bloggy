package com.hwanhee.bloggy;


import io.restassured.specification.RequestSpecification;

public class RequestSpecificationFunctions {
    public static RequestSpecification given() {
        return io.restassured.RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .log().all()
                ;
    }
}
