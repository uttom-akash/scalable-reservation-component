package org.domain.repositories;

import org.domain.models.Reservation;

import java.util.List;

public interface ReservationMgmtRepository
{
    public String create(Reservation reservation);

    Reservation get(String reservationId);

    List<Reservation> get();

    Reservation update(Reservation reservation);

    void delete(String reservationId);
}
