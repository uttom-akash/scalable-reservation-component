package org.domain.repositories;

import org.application.dtos.BlogDto;
import org.application.dtos.BlogView;
import org.domain.models.Blog;

public interface BlogRepository {
    String create(Blog blog);

    String get();

    String get(String id);

    BlogView update(Blog blog);

    void delete(String id);
}
