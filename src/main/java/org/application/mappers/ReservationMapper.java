package org.application.mappers;

import org.application.dtos.ReservationDto;
import org.domain.models.Reservation;
import org.domain.models.ReservationStatus;
import org.domain.models.RoomType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);

    // Define the custom mappings for RoomType and ReservationStatus
    @Mapping(source = "roomType", target = "roomType", qualifiedByName = "mapStringToRoomType")
//    @Mapping(source = "reservationStatus", target = "reservationStatus", qualifiedByName = "mapStringToReservationStatus")
    Reservation toReservation(ReservationDto reservationDto);

    @Mapping(source = "roomType", target = "roomType", qualifiedByName = "mapRoomTypeToString")
//    @Mapping(source = "reservationStatus", target = "reservationStatus", qualifiedByName = "mapReservationStatusToString")
    ReservationDto toReservationDto(Reservation reservation);

    // Define the custom methods in the mapper interface
    @Named("mapStringToRoomType")
    default RoomType mapStringToRoomType(String roomType) {
        if (roomType == null) {
            return null;
        }
        return RoomType.valueOf(roomType.toUpperCase());
    }

    default ReservationStatus mapStringToReservationStatus(String status) {
        if (status == null) {
            return null;
        }
        return ReservationStatus.valueOf(status.toUpperCase());
    }

    @Named("mapRoomTypeToString")
    default String mapRoomTypeToString(RoomType roomType) {
        if (roomType == null) {
            return null;
        }
        return roomType.name();  // Convert enum to string
    }

    default String mapReservationStatusToString(ReservationStatus status) {
        if (status == null) {
            return null;
        }
        return status.name();  // Convert enum to string
    }
}


