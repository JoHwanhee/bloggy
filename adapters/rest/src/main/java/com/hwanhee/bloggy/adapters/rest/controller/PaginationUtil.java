package com.hwanhee.bloggy.adapters.rest.controller;

class PaginationUtil {
    static void validateParameters(int page, int size) {
        if (page <= 0) {
            throw new IllegalArgumentException("페이지 번호는 0보다 커야 합니다.");
        }
        if (size <= 0) {
            throw new IllegalArgumentException("사이즈는 0보다 커야 합니다.");
        }
    }
}