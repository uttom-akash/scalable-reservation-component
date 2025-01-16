package org.api.controllers.v1;

import lombok.extern.slf4j.Slf4j;
import org.application.contracts.ReservationMgmtService;
import org.application.dtos.ReservationDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hotel/booking")
@Slf4j
public class ReservationMgmtController {

    private static final Logger log = LoggerFactory.getLogger(ReservationMgmtController.class);
    private final ReservationMgmtService reservationMgmtService;

    public ReservationMgmtController(ReservationMgmtService reservationMgmtService) {
        this.reservationMgmtService = reservationMgmtService;
    }

    @PostMapping("/reserve")
    public String reserve(ReservationDto reservationDto){
        return reservationMgmtService.reserve(reservationDto);
    }

    @PostMapping("/cancel")
    public void cancel(String reservationId){
        reservationMgmtService.cancel(reservationId);
    }
}
