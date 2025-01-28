//package org.infrastructure.repository;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import jakarta.transaction.Transactional;
//import org.domain.contstants.RoomType;
//import org.domain.models.Reservation;
//import org.domain.repositories.ReservationMgmtRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//
//public class ReservationMgmtRepositoryImpl extends ReservationMgmtRepository {
//
////    @PersistenceContext
////    EntityManager entityManager;
////
////    @Override
////    public String create(Reservation reservation) {
////        entityManager
////        entityManager.merge(reservation);
////        return  reservation.reservationId;
////    }
////
////    @Override
////    public Reservation get(String reservationId) {
////        return entityManager.find(Reservation.class, reservationId);
////    }
////
////    @Override
////    public List<Reservation> get() {
////
////        return List.of();
////    }
////
////    @Override
////    public Reservation update(Reservation reservation) {
////        entityManager.merge(reservation);
////        return reservation;
////    }
////
////    @Override
////    public void delete(String reservationId) {
////        var reservertion = entityManager.find(Reservation.class, reservationId);
////        entityManager.remove(reservertion);
////    }
//}
//
//
////@Override
////public String create(Blog blog) {
////    // Map the Blog object to DynamoDB's AttributeValue types
////
////    System.err.println("Unable to add blog: " + blog.Id + blog.title + blog.authorName);
////
////    Map<String, AttributeValue> item = new HashMap<>();
////    item.put("Id", AttributeValue.builder().s(blog.Id).build());
////    item.put("title", AttributeValue.builder().s(blog.title).build());
////    item.put("content", AttributeValue.builder().s(blog.content).build());
////
////    // Prepare the PutItem request
////    PutItemRequest putItemRequest = PutItemRequest.builder()
////            .tableName("Blog")  // Specify your DynamoDB table name
////            .item(item)  // The item to insert
////            .build();
////
////    // Execute the PutItem request to add the item to DynamoDB
////    try {
////        dynamoDbClient.putItem(putItemRequest);
////        System.out.println("Blog added successfully!");
////    } catch (DynamoDbException e) {
////        System.err.println("Unable to add blog: " + e.getMessage());
////    }
////
////    return blog.Id;
////}
