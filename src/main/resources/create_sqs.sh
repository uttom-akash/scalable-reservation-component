#!/bin/bash

# Set LocalStack endpoint
LOCALSTACK_ENDPOINT="http://localhost:4566"

# Create Hotel Reservation FIFO Queue
aws --endpoint-url=$LOCALSTACK_ENDPOINT sqs create-queue --queue-name HotelReservationQueue.fifo --attributes FifoQueue=true,ContentBasedDeduplication=true

# Create Hotel Cancellation FIFO Queue
aws --endpoint-url=$LOCALSTACK_ENDPOINT sqs create-queue --queue-name HotelCancellationQueue.fifo --attributes FifoQueue=true,ContentBasedDeduplication=true

echo "Queues created successfully!"
