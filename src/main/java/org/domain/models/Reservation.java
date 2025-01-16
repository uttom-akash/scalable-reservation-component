package org.domain.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Reservation {

    private Long reservationId;

    private String guestId;

    private String hotelId;

    private RoomType roomType;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private int numGuests;

    private BigDecimal totalPrice;

    private ReservationStatus reservationStatus;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String specialRequests;

    private String additionalInfo;
}

