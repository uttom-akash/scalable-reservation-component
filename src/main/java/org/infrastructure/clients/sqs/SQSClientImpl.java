package org.infrastructure.clients.sqs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;

@Component
public class SQSClientImpl implements SQSFifoClient {

    private final AmazonSQS amazonSQS;

    @Autowired
    public SQSClientImpl(AmazonSQS amazonSQS) {
        this.amazonSQS = amazonSQS;
    }

    public void sendFifoMessage(String queue, SQSFifoMessage message) {

        String queueUrl = amazonSQS.getQueueUrl(queue).getQueueUrl();

        SendMessageRequest sendMessageRequest = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageBody(message.payload)
                .withMessageGroupId(message.groupId)
                .withMessageDeduplicationId(message.deduplicationId); // Content-based deduplication fallback

        amazonSQS.sendMessage(sendMessageRequest);
    }
}
