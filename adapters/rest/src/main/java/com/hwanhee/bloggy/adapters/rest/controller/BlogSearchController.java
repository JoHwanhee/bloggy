package com.hwanhee.bloggy.adapters.rest.controller;

import com.hwanhee.bloggy.adapters.rest.dto.BlogSearchResultDto;
import com.hwanhee.bloggy.application.ports.in.BlogSearchCommand;
import com.hwanhee.bloggy.application.ports.in.BlogSearchUsecase;
import com.hwanhee.bloggy.domain.model.BlogSearchResult;
import com.hwanhee.bloggy.domain.model.Sort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.hwanhee.bloggy.adapters.rest.controller.PaginationUtil.validateParameters;


@RestController
@RequiredArgsConstructor
public class BlogSearchController {

    private static final String DEFAULT_SORT = "accuracy";
    private static final String DEFAULT_PAGE = "1";
    private static final String DEFAULT_SIZE = "10";

    private final BlogSearchUsecase usecase;

    @GetMapping("/blogs")
    public ResponseEntity<BlogSearchResultDto> search(
            @RequestParam("query") String query,
            @RequestParam(value = "sort", defaultValue = DEFAULT_SORT) String sort,
            @RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page,
            @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) Integer size
    ) {
        validateParameters(page, size);

        BlogSearchCommand command = BlogSearchCommand.of(query, Sort.from(sort), page, size);
        BlogSearchResult result = usecase.search(command);

        BlogSearchResultDto resultDto = BlogSearchResultDto.from(result);
        return ResponseEntity.ok().body(resultDto);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }
}
