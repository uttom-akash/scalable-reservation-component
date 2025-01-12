package org.application.services;

import org.application.contracts.BlogService;
import org.application.dtos.BlogDto;
import org.application.dtos.BlogView;
import org.domain.repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl implements BlogService {

    private final BlogRepository _blogRepository;

    @Autowired
    public BlogServiceImpl(BlogRepository blogRepository){

        _blogRepository = blogRepository;
    }

    public String create(BlogDto createDto) {
        return "Service";
    }

    public String get() {

        return "Service";
    }

    public String get(String id) {
        return "Service";
    }

    public BlogView update(BlogDto updateDto, String id) {
        return null;
    }

    public void delete(String id) {

    }
}
