package org.application.contracts;

import org.application.dtos.BlogDto;
import org.application.dtos.BlogView;

public interface BlogService {

    String create(BlogDto createDto);

    String get();

    String get(String id);

    BlogView update(BlogDto updateDto, String id);

    void delete(String id);
}
