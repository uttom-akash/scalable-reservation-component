package org.application.dtos;

import org.domain.models.ReservationStatus;
import org.domain.models.RoomType;
import software.amazon.ion.Decimal;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReservationDto {

        public String guestId;

        public String hotelId;

        public String roomType;

        public LocalDate checkInDate;

        public LocalDate checkOutDate;

        public int quantity;

        public Decimal unitPrice;

        public String specialRequests;

        public String additionalInfo;
}
