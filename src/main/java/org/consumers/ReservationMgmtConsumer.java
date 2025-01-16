package org.consumers;

import org.application.contracts.ReservationMgmtConsumerService;
import org.application.events.ReservationMgmtEvent;
import org.application.events.ReservationMgmtEventType;
import org.infrastructure.clients.sqs.SQSFifoMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;


@Component
public class ReservationMgmtConsumer {

    private final ReservationMgmtConsumerService reservationMgmtConsumerService;

    @Autowired
    public ReservationMgmtConsumer(ReservationMgmtConsumerService reservationMgmtConsumerService) {
        this.reservationMgmtConsumerService = reservationMgmtConsumerService;
    }

    @SqsListener("ReservationMgmtQueue.fifo")
    public void HandleReservationMgmtMessage(ReservationMgmtEvent message){
        if (message.reservationMgmtEventType == ReservationMgmtEventType.RESERVE) {
            reservationMgmtConsumerService.handleReservationMessage(message);
        }
        else {
            reservationMgmtConsumerService.handleCancellationMessage(message);
        }
    }
}