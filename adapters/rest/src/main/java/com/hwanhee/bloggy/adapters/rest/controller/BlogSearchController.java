package com.hwanhee.bloggy.adapters.rest.controller;

import com.hwanhee.bloggy.adapters.rest.dto.BlogSearchResultDto;
import com.hwanhee.bloggy.application.ports.in.BlogSearchCommand;
import com.hwanhee.bloggy.application.ports.in.BlogSearchUsecase;
import com.hwanhee.bloggy.domain.blog.BlogSearchResult;
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
        BlogSearchCommand command = BlogSearchCommand.of(query, sort, page, size);
        BlogSearchResult result = usecase.search(command);

        BlogSearchResultDto resultDto = BlogSearchResultDto.from(result);
        return ResponseEntity
                .ok()
                .body(resultDto)
                ;
    }
}
