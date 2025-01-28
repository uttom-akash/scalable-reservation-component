package org.domain.repositories;
import org.domain.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationMgmtRepository extends JpaRepository<Reservation, String>
{
//     List<Reservation> get();
}

