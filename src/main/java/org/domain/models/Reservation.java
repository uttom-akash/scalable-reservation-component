package org.domain.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.domain.contstants.ReservationStatus;
import org.domain.contstants.RoomType;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Setter
public class Reservation {

    public String reservationId;

    public String guestId;

    public String hotelId;

    public RoomType roomType;

    public LocalDate checkInDate;

    public LocalDate checkOutDate;

    public int quantity;

    public double unitPrice;

    public ReservationStatus reservationStatus;

    public LocalDateTime createdAt;

    public LocalDateTime updatedAt;

    public String specialRequests;

    public String additionalInfo;

    public String getGroupId() {
        return hotelId + "|" + roomType;
    }
}

