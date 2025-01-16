package org.api.controllers.v1;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hotel/booking")
public class HotelBookingController {

    @PostMapping("/reserve")
    public String reserve(){
        return "Reserved hotel";
    }

    @PostMapping("/cancel")
    public void cancel(){

    }
}
