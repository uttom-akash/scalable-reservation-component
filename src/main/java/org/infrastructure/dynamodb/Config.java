package org.infrastructure.dynamodb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;

@Configuration
public class Config {

    @Bean
    public DynamoDbClient dynamoDbClient() {
         return DynamoDbClient.builder()
                .region(Region.US_EAST_1) // Set your region here
                .endpointOverride(URI.create("http://localhost:4566")) // LocalStack's DynamoDB endpoint
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create("dummyAccessKey", "dummySecretKey"))) // Dummy credentials for LocalStack
                .build();
    }
}
