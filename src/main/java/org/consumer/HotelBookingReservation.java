package org.consumer;

import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;


@Component
public class HotelReservation {


    @SqsListener("HotelReservationQueue.fifo")
    public void handleReservationMessage(String message) {
        System.out.println("Received reservation message: " + message);
    }
}


//@Component
//public class HotelCancellation {
//
//    @SqsListener("HotelCancellationQueue.fifo")
//    public void handleCancellationMessage(String message) {
//        System.out.println("Received cancellation message: " + message);
//    }
//}
