package com.switchfully.eurder.api;

import com.switchfully.eurder.api.dto.shipping.DailyShippingDto;
import com.switchfully.eurder.services.DailyShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/dailyShipping")
public class DailyShippingController {

    private final DailyShippingService service;

    @Autowired
    public DailyShippingController(DailyShippingService service) {
        this.service = service;
    }

    @GetMapping(produces = "application/json")
    public DailyShippingDto getItemOverviewForDay(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate shippingDay,
            @RequestHeader String authorizedUserId) {
        return service.getDailyShippings(shippingDay, authorizedUserId);
    }
}
