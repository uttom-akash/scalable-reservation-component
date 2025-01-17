package org.application.contracts;

import org.application.dtos.ReservationDto;

public interface ReservationMgmtService {
    String reserve(ReservationDto reservationDto) throws Exception;

    void cancel(String reservationId) throws Exception;
}
