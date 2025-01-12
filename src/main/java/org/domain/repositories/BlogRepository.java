package org.domain.repositories;

import org.application.dtos.BlogDto;
import org.application.dtos.BlogView;
import org.domain.models.Blog;
import org.springframework.data.repository.CrudRepository;

public interface BlogRepository extends CrudRepository<Blog, String>
{
//    String create(Blog blog);
//
//    String get();
//
//    String get(String id);
//
//    BlogView update(Blog blog);
//
//    void delete(String id);
}
