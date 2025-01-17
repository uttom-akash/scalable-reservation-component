#!/bin/bash

# Set LocalStack endpoint
LOCALSTACK_ENDPOINT="http://localhost:4566"

# Create Hotel Reservation FIFO Queue
aws --endpoint-url="http://localhost:4566" sqs create-queue --queue-name ReservationMgmtQueue.fifo --attributes FifoQueue=true,ContentBasedDeduplication=true

echo "Queues created successfully!"
