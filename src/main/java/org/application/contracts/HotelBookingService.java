package org.application.contracts;

import org.application.dtos.ReservationDto;

public interface HotelBookingService {

    String reserve(ReservationDto reservationDto);

    void cancel(String reservationId);
}
