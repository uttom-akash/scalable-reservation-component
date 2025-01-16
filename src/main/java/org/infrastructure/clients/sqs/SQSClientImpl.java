package org.infrastructure.clients.sqs;

import org.application.contracts.SQSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;

@Component
public class SQSClientImpl implements SQSClient {

    private final AmazonSQS amazonSQS;

    @Autowired
    public SQSClientImpl(AmazonSQS amazonSQS) {
        this.amazonSQS = amazonSQS;
    }

    public void sendReservationMessage(String messageBody, String messageGroupId, String deduplicationId) {
        String queueUrl = amazonSQS.getQueueUrl("HotelReservationQueue.fifo").getQueueUrl();

        SendMessageRequest sendMessageRequest = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageBody(messageBody)
                .withMessageGroupId(messageGroupId)
                .withMessageDeduplicationId(deduplicationId); // Content-based deduplication fallback

        amazonSQS.sendMessage(sendMessageRequest);
    }

    public void sendCancellationMessage(String messageBody, String messageGroupId, String deduplicationId) {
        String queueUrl = amazonSQS.getQueueUrl("HotelCancellationQueue.fifo").getQueueUrl();

        SendMessageRequest sendMessageRequest = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageBody(messageBody)
                .withMessageGroupId(messageGroupId)
                .withMessageDeduplicationId(deduplicationId); // Content-based deduplication fallback

        amazonSQS.sendMessage(sendMessageRequest);
    }
}
