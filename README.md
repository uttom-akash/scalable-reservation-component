# Scalable Reservation Service Component

This design guarantees consistency, avoids race conditions, and ensures scalable processing with Redis-based locking.

### Installation
```declarative

cd scalable-reservation-service-component

./src/main/resources/setup/setup.sh

cd scalable-reservation-service-component/src/main/java/org/api

mvn spring-boot:run

```

### Current Diagram (With Lock)
```plaintext
                          +----------------------+
                          |    API (Producer)    |
                          |----------------------|
                          |  RoomReservation     |
                          |  RoomCancellation    |
                          +----------------------+
                                    |
                                    v
                     +-------------------------------+
                     |       Message Queues          |
                     |-------------------------------|
                     |  RoomReservationQueue         |
                     |  RoomCancellationQueue        |
                     +-------------------------------+
                                    |
                                    v
                  +-------------------------------------+
                  |           Consumers                 |
                  |------------------------------------ |
                  |  1. Fetch Event from Queue          |
                  |  2. Acquire fine-grained Redis Lock |
                  |     (hotelId + roomType)            |
                  |  3. Process Event                   |
                  |  4. Release Redis Lock              |
                  +-------------------------------------+
                                    |
                                    v
                +--------------------------------------+
                |              Redis                   |
                |--------------------------------------|
                |  Lock: hotelId + roomType            |
                +--------------------------------------+

                      +---------------------+
                      |  Dead Letter Queue  |
                      +---------------------+
                      
```


### Components Explained
- Producers: Publish reservation and cancellation events.
- Message Queues: Hold events for processing by consumers.
- Consumers: Fetch events, acquire a lock from Redis using hotelId + roomType, process the event, and release the lock.
- Redis: Ensures no two consumers process the same hotelId + roomType simultaneously.


## Future Design (Lock Free)

### Serialized Event Processing:

- Ensures all reservation and cancellation events for a specific hotel and room type are processed in a strict order.
- Avoids race conditions and double bookings.

### Parallel Event Processing:

- Multiple consumers can operate concurrently for different hotels or room types.
- Allows horizontal scaling without introducing locking mechanisms in the database.


### Queue Routing with Unique Identifiers:

Events are routed to a FIFO (First-In, First-Out) queue using a hotelId + roomType as the group identifier.
This guarantees that all events for the same hotel and room type are processed sequentially.

### Lock-Free Implementation:

- Eliminates the need for database-level locks, reducing contention and improving performance.
- Provides a simpler and more robust solution to concurrency management.

### Flexible Queue Integration:

- Supports integration with AWS SQS FIFO queues or other message brokers that ensure message ordering.
- Dynamically routes events based on hotelId and roomType.

### Failure Handling:

- Ensures failed events can be retried without compromising order.
- Messages can be moved to a dead-letter queue (DLQ) for further investigation if processing consistently fails.
