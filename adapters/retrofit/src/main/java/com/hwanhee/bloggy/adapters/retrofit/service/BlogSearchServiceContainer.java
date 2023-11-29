package com.hwanhee.bloggy.adapters.retrofit.service;

import com.hwanhee.bloggy.application.ports.out.BlogSearchServicePort;
import com.hwanhee.bloggy.domain.model.BlogSearchResult;
import com.hwanhee.bloggy.domain.model.Sort;
import org.springframework.stereotype.Service;

@Service
public class BlogSearchServiceContainer implements BlogSearchServicePort {

    private final BlogSearchServicePort[] blogSearchServicePorts;

    public BlogSearchServiceContainer(BlogSearchServicePort[] blogSearchServicePorts) {
        this.blogSearchServicePorts = blogSearchServicePorts;
    }

    @Override
    public BlogSearchResult search(String query, Sort sort, int page, int size) {
        for (BlogSearchServicePort blogSearchServicePort : blogSearchServicePorts) {
            try {
                BlogSearchResult result = blogSearchServicePort.search(query, sort, page, size);
                if (!result.getDocuments().isEmpty()) {
                    return result;
                }
            } catch (Exception e) {
                // ignore
            }
        }

        throw new RuntimeException("No search service available");
    }
}
