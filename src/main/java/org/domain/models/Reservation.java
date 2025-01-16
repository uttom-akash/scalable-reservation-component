package org.domain.models;

import software.amazon.ion.Decimal;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Reservation {

    public String reservationId;

    public String guestId;

    public String hotelId;

    public RoomType roomType;

    public LocalDate checkInDate;

    public LocalDate checkOutDate;

    public int quantity;

    public Decimal unitPrice;

    public ReservationStatus reservationStatus;

    public LocalDateTime createdAt;

    public LocalDateTime updatedAt;

    public String specialRequests;

    public String additionalInfo;
}

