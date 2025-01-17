package org.application.dtos;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {

        public String guestId;

        public String hotelId;

        public String roomType;

        public LocalDate checkInDate;

        public LocalDate checkOutDate;

        public int quantity;

        public double unitPrice;

        public String specialRequests;

        public String additionalInfo;
}
