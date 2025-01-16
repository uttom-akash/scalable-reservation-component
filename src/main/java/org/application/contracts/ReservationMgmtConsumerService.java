package org.application.contracts;

import org.application.events.ReservationMgmtEvent;

public interface ReservationMgmtConsumerService {

    void handleReservationMessage(ReservationMgmtEvent message);

    void handleCancellationMessage(ReservationMgmtEvent message);
}
