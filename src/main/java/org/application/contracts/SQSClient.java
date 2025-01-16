package org.application.contracts;

public interface SQSClient {
    void sendReservationMessage(String messageBody, String messageGroupId, String deduplicationId);
    void sendCancellationMessage(String messageBody, String messageGroupId, String deduplicationId);
}
