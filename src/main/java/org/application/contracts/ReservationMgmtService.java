package org.application.contracts;

import org.application.dtos.ReservationDto;

public interface ReservationMgmtService {
    String reserve(ReservationDto reservationDto);

    void cancel(String reservationId);
}
