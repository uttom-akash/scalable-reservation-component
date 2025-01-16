package org.infrastructure.clients.sqs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SQSFifoMessage {
    public String deduplicationId;

    public String groupId;

    public String payload;
}
