package org.application.background.services;

import org.application.contracts.ReservationMgmtConsumerService;
import org.application.events.ReservationMgmtEvent;
import org.springframework.stereotype.Service;

@Service
public class ReservationMgmtConsumerServiceImpl implements ReservationMgmtConsumerService {


    public void handleReservationMessage(ReservationMgmtEvent message) {
        // validate

        // check availability

        // reserve
        System.out.println("Received reservation message: " + message);
    }

    public void handleCancellationMessage(ReservationMgmtEvent message) {
        // validate

        // cancel

        System.out.println("Received cancellation message: " + message);
    }

}
