package org.application.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.application.contracts.ReservationMgmtService;
import org.application.dtos.ReservationDto;
import org.application.events.RoomCancellationEvent;
import org.application.events.RoomReservationEvent;
import org.application.mappers.ReservationMapper;
import org.domain.contstants.ReservationStatus;
import org.domain.repositories.ReservationMgmtRepository;
import org.infrastructure.clients.sqs.SQSFifoClient;
import org.infrastructure.clients.sqs.SQSFifoMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.endpoints.internal.Value;

import java.util.UUID;

@Service
public class ReservationMgmtServiceImpl implements ReservationMgmtService {

    private final ReservationMgmtRepository reservationMgmtRepository;
    private final SQSFifoClient sqsClient;
    private final String roomReservationQueue = "RoomReservationQueue.fifo";
    private final String roomCancellationQueue = "RoomCancellationQueue.fifo";

    @Autowired
    public ReservationMgmtServiceImpl(ReservationMgmtRepository reservationMgmtRepository,
                                      SQSFifoClient sqsClient) {
        this.reservationMgmtRepository = reservationMgmtRepository;
        this.sqsClient = sqsClient;
    }

    private static SQSFifoMessage prepareSqsFifoMessage(ReservationDto reservationDto, String reservationId, String groupId) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        var message = objectMapper.writeValueAsString(RoomCancellationEvent.builder()
                .reservationId(reservationId)
                .build());

        return SQSFifoMessage
                .builder()
                .deduplicationId(reservationId)
                .groupId(groupId)
                .payload(message)
                .build();
    }


    @Override
    public String reserve(ReservationDto reservationDto) throws Exception {

        var reservationId = UUID.randomUUID().toString();

        var reservation = ReservationMapper.INSTANCE.toReservation(reservationDto);

        var groupId = reservation.getGroupId();

        reservation.reservationId = reservationId;

        reservation.reservationStatus = ReservationStatus.INITIATED;

        reservationMgmtRepository.save(reservation);

        var objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        var message = objectMapper.writeValueAsString(RoomReservationEvent.builder()
                .reservationId(reservationId)
                .build());

        var sqsFifoMessage = SQSFifoMessage
                .builder()
                .deduplicationId(reservationId)
                .groupId(groupId)
                .payload(message)
                .build();

        sqsClient.sendFifoMessage(roomReservationQueue, sqsFifoMessage);

        return reservationId;

    }

    @Override
    public void cancel(String reservationId) throws Exception {

        var reservation = reservationMgmtRepository.findById(reservationId)
                .orElseThrow();

        var groupId = reservation.getGroupId();

        var objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        var message = objectMapper.writeValueAsString(RoomCancellationEvent.builder()
                .reservationId(reservationId)
                .build());

        var sqsFifoMessage = SQSFifoMessage
                .builder()
                .deduplicationId(reservationId)
                .groupId(groupId)
                .payload(message)
                .build();

        sqsClient.sendFifoMessage(roomCancellationQueue, sqsFifoMessage);
    }
}
