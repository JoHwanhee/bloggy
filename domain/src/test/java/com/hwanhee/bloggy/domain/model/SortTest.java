package com.hwanhee.bloggy.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortTest {

    @Test
    @DisplayName("문자열로 생성")
    void testFromString() {
        Sort sortAccuracy = Sort.from("accuracy");
        Sort sortLatest = Sort.from("latest");

        assertEquals(Sort.ACCURACY.getOrder(), sortAccuracy.getOrder());
        assertEquals(Sort.LATEST.getOrder(), sortLatest.getOrder());
    }

    @Test
    @DisplayName("네이버 문자열 테스트")
    void testToNaverString() {
        assertEquals("sim", Sort.ACCURACY.toNaverString());
        assertEquals("date", Sort.LATEST.toNaverString());
    }

    @Test
    @DisplayName("카카오 문자열 테스트")
    void testToKakaoString() {
        assertEquals("accuracy", Sort.ACCURACY.toKakaoString());
        assertEquals("recency", Sort.LATEST.toKakaoString());
    }

    @Test
    @DisplayName("from(String) throws IllegalArgumentException for invalid strings")
    void testFromStringThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> Sort.from("invalid"));
    }
}