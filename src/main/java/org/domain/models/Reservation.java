package org.domain.models;

import jakarta.persistence.*;
import lombok.*;
import org.domain.contstants.ReservationStatus;
import org.domain.contstants.RoomType;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name =  "reservation")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Reservation {

    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "reservation_id")
    public String reservationId;

    @Column(name = "guest_id")
    public String guestId;

    @Column(name = "hotel_id")
    public String hotelId;

    @Column(name = "room_type")
    @Enumerated(EnumType.STRING)
    public RoomType roomType;

    @Column(name = "check_in_date")
    public LocalDate checkInDate;

    @Column(name = "check_out_date")
    public LocalDate checkOutDate;

    @Column(name = "quantity")
    public int quantity;

    @Column(name = "unit_price")
    public double unitPrice;

    @Column(name = "reservation_status")
    @Enumerated(EnumType.STRING)
    public ReservationStatus reservationStatus;

    @Column(name = "created_at")
    public LocalDateTime createdAt;

    @Column(name = "updated_at")
    public LocalDateTime updatedAt;

    @Column(name = "special_requests")
    public String specialRequests;

    @Column(name = "additional_info")
    public String additionalInfo;

    public String getGroupId() {
        return hotelId + "|" + roomType;
    }
}

