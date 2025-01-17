#!/bin/bash

# Setup infra
docker-compose -f src/main/resources/setup/infrastructure.yml up -d

echo "localstack setup successfully!"

# Create Hotel Reservation FIFO Queue
aws --endpoint-url="http://localhost:4566" --region us-east-1 sqs create-queue --queue-name RoomReservationQueue.fifo --attributes FifoQueue=true,ContentBasedDeduplication=true

aws --endpoint-url="http://localhost:4566" --region us-east-1 sqs create-queue --queue-name RoomCancellationQueue.fifo --attributes FifoQueue=true,ContentBasedDeduplication=true

echo "Queues created successfully!"
