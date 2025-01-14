package org.application.services;

import org.application.contracts.BlogService;
import org.application.dtos.BlogDto;
import org.application.dtos.BlogView;
import org.domain.models.Blog;
import org.domain.repositories.BlogRepository;
import org.infrastructure.repository.BlogRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl implements BlogService {

    private final BlogRepositoryImpl _blogRepository;

    @Autowired
    public BlogServiceImpl(BlogRepositoryImpl blogRepository){

        _blogRepository = blogRepository;
    }

    public String create(BlogDto createDto) {
        Blog blog = new Blog();
        blog.Id = "akakak" + createDto.title;
        blog.title = createDto.title;
        blog.content = createDto.content;
        blog.authorName = createDto.authorName;
        _blogRepository.create(blog);

        return blog.Id;
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
