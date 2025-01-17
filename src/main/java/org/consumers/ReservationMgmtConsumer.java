package org.consumers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.extern.slf4j.Slf4j;
import org.application.contracts.ReservationMgmtConsumerService;
import org.application.events.ReservationMgmtEvent;
import org.application.events.ReservationMgmtEventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class ReservationMgmtConsumer {

    private final ReservationMgmtConsumerService reservationMgmtConsumerService;

    @Autowired
    public ReservationMgmtConsumer(ReservationMgmtConsumerService reservationMgmtConsumerService) {
        this.reservationMgmtConsumerService = reservationMgmtConsumerService;
    }

    @SqsListener("ReservationMgmtQueue.fifo")
    public void HandleReservationMgmtMessage(String message) {

        var objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        try {

            ReservationMgmtEvent event = objectMapper.readValue(message, ReservationMgmtEvent.class);

            if (event.reservationMgmtEventType == ReservationMgmtEventType.RESERVE) {
                reservationMgmtConsumerService.handleReservationMessage(event);
            } else {
                reservationMgmtConsumerService.handleCancellationMessage(event.reservationId);
            }
        } catch (Exception ex) {
            log.debug(ex.toString());
        }
    }
}