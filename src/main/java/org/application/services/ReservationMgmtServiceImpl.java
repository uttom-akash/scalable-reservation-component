package org.application.services;

import org.application.contracts.ReservationMgmtService;
import org.application.mappers.ReservationMapper;
import org.domain.models.ReservationStatus;
import org.domain.repositories.ReservationMgmtRepository;
import org.infrastructure.clients.sqs.SQSFifoClient;
import org.application.dtos.ReservationDto;
import org.infrastructure.clients.sqs.SQSFifoMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReservationMgmtServiceImpl implements ReservationMgmtService {

    private final ReservationMgmtRepository reservationMgmtRepository;
    private final SQSFifoClient sqsClient;
    private final String HotelBookingManagementQueue = "hotel_booking_management_event_queue";

    @Autowired
    public ReservationMgmtServiceImpl(ReservationMgmtRepository reservationMgmtRepository, SQSFifoClient sqsClient) {
        this.reservationMgmtRepository = reservationMgmtRepository;
        this.sqsClient = sqsClient;
    }

    @Override
    public String reserve(ReservationDto reservationDto) {

        var reservationId = UUID.randomUUID().toString();

        var groupId = getProcessingGroupId(reservationDto.hotelId, reservationDto.roomType);

        var reservation = ReservationMapper.INSTANCE.toReservation(reservationDto);

        reservation.reservationId = reservationId;

        reservation.reservationStatus = ReservationStatus.INITIATED;

        reservationMgmtRepository.create(reservation);

        SQSFifoMessage sqsFifoMessage = null;

        sqsClient.sendFifoMessage(HotelBookingManagementQueue, sqsFifoMessage);

        return reservationId;
    }

    @Override
    public void cancel(String reservationId) {

        var reservation = reservationMgmtRepository.get(reservationId);

        var groupId = getProcessingGroupId(reservation.hotelId, reservation.roomType.toString());


        SQSFifoMessage sqsFifoMessage = null;

        sqsClient.sendFifoMessage(HotelBookingManagementQueue, sqsFifoMessage);
    }

    private String getProcessingGroupId(String hotelId, String roomType){
        return hotelId + "|"+ roomType;
    }
}
