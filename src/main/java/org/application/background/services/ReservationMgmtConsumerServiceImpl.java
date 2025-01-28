package org.application.background.services;

import org.application.contracts.ReservationMgmtConsumerService;
import org.application.events.RoomCancellationEvent;
import org.application.events.RoomReservationEvent;
import org.domain.models.Reservation;
import org.domain.repositories.ReservationMgmtRepository;
import org.infrastructure.concurrency.ConcurrencyTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationMgmtConsumerServiceImpl implements ReservationMgmtConsumerService {

    private static final Logger log = LoggerFactory.getLogger(ReservationMgmtConsumerServiceImpl.class);
    private final ReservationMgmtRepository reservationMgmtRepository;
    private final ConcurrencyTracker concurrencyTracker;

    @Autowired
    public ReservationMgmtConsumerServiceImpl(ReservationMgmtRepository reservationMgmtRepository, ConcurrencyTracker redisClient) {
        this.reservationMgmtRepository = reservationMgmtRepository;
        this.concurrencyTracker = redisClient;
    }


    public void handleReservationMessage(RoomReservationEvent message) {

        var reservation = reservationMgmtRepository.findById(message.reservationId).orElseThrow();

        var resourceKey = createResourceKey(reservation);

        if(!concurrencyTracker.tryAcquireLock(resourceKey)){
            return;
        }

        try{
            // validate

            // check availability

            // reserve
            System.out.println("Received reservation message: " + message);
        }
        catch (Exception ex){
            log.error(ex.toString());
        }
        finally {
            concurrencyTracker.releaseLock(resourceKey);
        }
    }

    public void handleCancellationMessage(RoomCancellationEvent message) {
        var reservation = reservationMgmtRepository.findById(message.reservationId).orElseThrow();

        var resourceKey = createResourceKey(reservation);

        if(!concurrencyTracker.tryAcquireLock(resourceKey)){
            return;
        }

        try{
            // validate

            // cancel
            System.out.println("Received cancellation message: " + message);
        }
        catch (Exception ex){
            log.error(ex.toString());
        }
        finally {
            concurrencyTracker.releaseLock(resourceKey);
        }
    }

    public String createResourceKey(Reservation reservation){
        return "booking.lock." + reservation.getGroupId();
    }
}
