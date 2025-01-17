package org.application.events;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomReservationEvent {
    public String reservationId;
}
