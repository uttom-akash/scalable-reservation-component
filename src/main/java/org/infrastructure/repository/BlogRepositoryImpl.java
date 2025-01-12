package org.infrastructure.repository;

import org.application.dtos.BlogView;
import org.domain.models.Blog;
import org.domain.repositories.BlogRepository;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@EnableDynamoDBRepositories
@Repository
public interface BlogRepositoryImpl extends BlogRepository {

//    @Override
//    public String create(Blog blog) {
//        return "";
//    }
//
//    @Override
//    public String get() {
//        return "";
//    }
//
//    @Override
//    public String get(String id) {
//        return "";
//    }
//
//    @Override
//    public BlogView update(Blog blog) {
//        return null;
//    }
//
//    @Override
//    public void delete(String id) {
//
//    }
}
