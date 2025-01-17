package org.infrastructure.dynamodb;

import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

//    @Bean
//    public DynamoDbClient dynamoDbClient() {
//         return DynamoDbClient.builder()
//                .region(Region.US_EAST_1) // Set your region here
//                .endpointOverride(URI.create("http://localhost:4566")) // LocalStack's DynamoDB endpoint
//                .credentialsProvider(StaticCredentialsProvider.create(
//                        AwsBasicCredentials.create("dummyAccessKey", "dummySecretKey"))) // Dummy credentials for LocalStack
//                .build();
//    }
}
