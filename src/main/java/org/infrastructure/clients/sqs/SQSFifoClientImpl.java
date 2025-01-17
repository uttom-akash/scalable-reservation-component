package org.infrastructure.clients.sqs;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SQSFifoClientImpl implements SQSFifoClient {

    private final SqsTemplate sqsTemplate;

    @Autowired
    public SQSFifoClientImpl(SqsTemplate sqsTemplate) {
        this.sqsTemplate = sqsTemplate;
    }

    public void sendFifoMessage(String queue, SQSFifoMessage message) {

        sqsTemplate.send(to -> to
                .queue(queue)
                .payload(message.payload)
                .messageGroupId(message.groupId)
                .messageDeduplicationId(message.deduplicationId)
        );
    }
}
