package org.application.events;

import lombok.*;
import org.application.dtos.ReservationDto;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationMgmtEvent {
    public ReservationMgmtEventType reservationMgmtEventType;
    public String reservationId;
    public ReservationDto reservationDto;
}

