package com.hwanhee.bloggy.adapters.rest.controller;

import com.hwanhee.bloggy.application.ports.in.KeywordCountQueryUsecase;
import com.hwanhee.bloggy.domain.model.KeywordCount;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class KeywordCountController {

    private final KeywordCountQueryUsecase usecase;

    @GetMapping("/keywords")
    public ResponseEntity<List<KeywordCount>> keywords() {
        return ResponseEntity
                .ok()
                .body(usecase.findKeywordCounts());
    }
}
