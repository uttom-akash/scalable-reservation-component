package org.infrastructure.clients.sqs;

public interface SQSFifoClient {
    void sendFifoMessage(String queueUrl, SQSFifoMessage message);
}
