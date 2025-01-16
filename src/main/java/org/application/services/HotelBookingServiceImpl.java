package org.application.services;

import org.application.contracts.HotelBookingService;
import org.application.contracts.SQSClient;
import org.application.dtos.ReservationDto;
import org.joda.time.tz.UTCProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelBookingServiceImpl implements HotelBookingService {
    private final SQSClient sqsClient;

    @Autowired
    public HotelBookingServiceImpl(SQSClient sqsClient) {
        this.sqsClient = sqsClient;
    }

    @Override
    public String reserve(ReservationDto reservationDto) {

        String groupId = reservationDto.hotelId + "|"+ reservationDto.roomType;
        String dedupId = groupId + "|"+ reservationDto.guestId ;

        sqsClient.sendReservationMessage(reservationDto.toString(), groupId, dedupId);

        return dedupId;
    }

    @Override
    public void cancel(String reservationId) {

    }
}
