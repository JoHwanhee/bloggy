package com.hwanhee.bloggy.adapters.rest.controller;

import com.hwanhee.bloggy.adapters.rest.dto.BlogSearchResultDto;
import com.hwanhee.bloggy.application.ports.in.BlogSearchCommand;
import com.hwanhee.bloggy.application.ports.in.BlogSearchUsecase;
import com.hwanhee.bloggy.domain.model.BlogSearchResult;
import com.hwanhee.bloggy.domain.model.Sort;
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
    public ResponseEntity<BlogSearchResultDto> search(
            @RequestParam("query") String query,
            @RequestParam(value = "sort", defaultValue = "accuracy") String sort,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        validates(page, size);

        BlogSearchCommand command = BlogSearchCommand.of(query, Sort.from(sort), page, size);
        BlogSearchResult result = usecase.search(command);

        BlogSearchResultDto resultDto = BlogSearchResultDto.from(result);
        return ResponseEntity
                .ok()
                .body(resultDto);
    }

    private static void validates(int page, int size) {
        if (page <= 0) {
            throw new IllegalArgumentException("페이지 번호는 0보다 커야 합니다.");
        }

        if (size <= 0) {
            throw new IllegalArgumentException("사이즈는 0보다 커야 합니다.");
        }
    }
}
