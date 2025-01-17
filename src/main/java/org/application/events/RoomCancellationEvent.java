package org.application.events;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomCancellationEvent {
    public String reservationId;
}

