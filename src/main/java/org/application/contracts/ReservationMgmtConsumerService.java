package org.application.contracts;

import org.application.events.RoomCancellationEvent;
import org.application.events.RoomReservationEvent;

public interface ReservationMgmtConsumerService {

    void handleReservationMessage(RoomReservationEvent message);

    void handleCancellationMessage(RoomCancellationEvent message);
}
