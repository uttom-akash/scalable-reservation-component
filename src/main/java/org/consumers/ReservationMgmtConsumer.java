package org.consumers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.extern.slf4j.Slf4j;
import org.application.contracts.ReservationMgmtConsumerService;
import org.application.events.RoomCancellationEvent;
import org.application.events.RoomReservationEvent;
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

    @SqsListener("RoomReservationQueue.fifo")
    public void HandleRoomReservationMessage(String message) {

        var objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        try {

            RoomReservationEvent event = objectMapper.readValue(message, RoomReservationEvent.class);

            reservationMgmtConsumerService.handleReservationMessage(event);

        } catch (Exception ex) {
            log.error(ex.toString());
        }
    }

    @SqsListener("RoomCancellationQueue.fifo")
    public void HandleRoomCancellationMessage(String message) {

        var objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        try {

            RoomCancellationEvent event = objectMapper.readValue(message, RoomCancellationEvent.class);

            reservationMgmtConsumerService.handleCancellationMessage(event);

        } catch (Exception ex) {
            log.error(ex.toString());
        }
    }
}