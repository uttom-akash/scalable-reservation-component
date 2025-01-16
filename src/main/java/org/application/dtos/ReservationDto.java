package org.application.dtos;

import org.domain.models.ReservationStatus;
import org.domain.models.RoomType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReservationDto {

        public String guestId;

        public String hotelId;

        public RoomType roomType;

        public LocalDate checkInDate;

        public LocalDate checkOutDate;

        public int numberOfRooms;

        public BigDecimal unitPrice;

        public String specialRequests;

        public String additionalInfo;
}
