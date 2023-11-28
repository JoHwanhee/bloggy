package com.hwanhee.bloggy.adapters.rest.controller;

import com.hwanhee.bloggy.application.dto.BlogSearchResult;
import com.hwanhee.bloggy.application.ports.in.BlogSearchUsecase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class BlogSearchController {

    private final BlogSearchUsecase usecase;

    @GetMapping("/blogs")
    public ResponseEntity<BlogSearchResult> search(
            @RequestParam("query") String query
    ) {
        BlogSearchResult result = usecase.search(query, 1);
        return ResponseEntity
                .ok()
                .body(result)
                ;
    }
}
