package org.infrastructure.repository;

import org.application.dtos.BlogView;
import org.domain.models.Blog;
import org.domain.repositories.BlogRepository;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.HashMap;
import java.util.Map;

//@EnableDynamoDBRepositories
@Repository
public class BlogRepositoryImpl implements BlogRepository {

    DynamoDbClient dynamoDbClient;

    public BlogRepositoryImpl(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    @Override
    public String create(Blog blog) {
        // Map the Blog object to DynamoDB's AttributeValue types

        System.err.println("Unable to add blog: " + blog.Id + blog.title + blog.authorName);

        Map<String, AttributeValue> item = new HashMap<>();
        item.put("Id", AttributeValue.builder().s(blog.Id).build());
        item.put("title", AttributeValue.builder().s(blog.title).build());
        item.put("content", AttributeValue.builder().s(blog.content).build());

        // Prepare the PutItem request
        PutItemRequest putItemRequest = PutItemRequest.builder()
                .tableName("Blog")  // Specify your DynamoDB table name
                .item(item)  // The item to insert
                .build();

        // Execute the PutItem request to add the item to DynamoDB
        try {
            dynamoDbClient.putItem(putItemRequest);
            System.out.println("Blog added successfully!");
        } catch (DynamoDbException e) {
            System.err.println("Unable to add blog: " + e.getMessage());
        }

        return blog.Id;
    }
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
