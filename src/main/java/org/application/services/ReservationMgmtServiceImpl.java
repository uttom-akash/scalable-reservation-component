package org.application.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.application.contracts.ReservationMgmtService;
import org.application.dtos.ReservationDto;
import org.application.events.ReservationMgmtEvent;
import org.application.events.ReservationMgmtEventType;
import org.application.mappers.ReservationMapper;
import org.domain.contstants.ReservationStatus;
import org.domain.repositories.ReservationMgmtRepository;
import org.infrastructure.clients.sqs.SQSFifoClient;
import org.infrastructure.clients.sqs.SQSFifoMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReservationMgmtServiceImpl implements ReservationMgmtService {

    private final ReservationMgmtRepository reservationMgmtRepository;
    private final SQSFifoClient sqsClient;
    private final String HotelBookingManagementQueue = "ReservationMgmtQueue.fifo";

    @Autowired
    public ReservationMgmtServiceImpl(ReservationMgmtRepository reservationMgmtRepository, SQSFifoClient sqsClient) {
        this.reservationMgmtRepository = reservationMgmtRepository;
        this.sqsClient = sqsClient;
    }

    private static SQSFifoMessage prepareSqsFifoMessage(ReservationDto reservationDto, String reservationId, String groupId) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        var message = objectMapper.writeValueAsString(ReservationMgmtEvent.builder()
                .reservationMgmtEventType(ReservationMgmtEventType.RESERVE)
                .reservationId(reservationId)
                .reservationDto(reservationDto)
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

        var groupId = getProcessingGroupId(reservationDto.hotelId, reservationDto.roomType);

        var reservation = ReservationMapper.INSTANCE.toReservation(reservationDto);

        reservation.reservationId = reservationId;

        reservation.reservationStatus = ReservationStatus.INITIATED;

        reservationMgmtRepository.create(reservation);

        SQSFifoMessage sqsFifoMessage = prepareSqsFifoMessage(reservationDto, reservationId, groupId);

        sqsClient.sendFifoMessage(HotelBookingManagementQueue, sqsFifoMessage);

        return reservationId;

    }

    private String getProcessingGroupId(String hotelId, String roomType) {
        return hotelId + "|" + roomType;
    }

    @Override
    public void cancel(String reservationId) throws Exception {

        var reservation = reservationMgmtRepository.get(reservationId);

        var groupId = getProcessingGroupId(reservation.hotelId, reservation.roomType.toString());

        SQSFifoMessage sqsFifoMessage = prepareSqsFifoMessage(null, reservationId, groupId);

        sqsClient.sendFifoMessage(HotelBookingManagementQueue, sqsFifoMessage);
    }
}
