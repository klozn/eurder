package com.switchfully.eurder.api;

import com.switchfully.eurder.api.dto.items.ItemDtoWithUrgency;
import com.switchfully.eurder.domain.RestockUrgency;
import com.switchfully.eurder.services.ItemOverviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/itemOverview")
public class ItemOverviewController {

    private final ItemOverviewService service;
    private final Logger logger = LoggerFactory.getLogger(ItemOverviewController.class);

    public ItemOverviewController(ItemOverviewService service) {
        this.service = service;
    }

    @GetMapping(produces = "application/json")
    public List<ItemDtoWithUrgency> getOverview(@RequestParam(value = "urgency", required = false) RestockUrgency restockUrgency,
                                                @RequestHeader String authorizedUserId) {
        logger.info("Showing item overview for user with id: " + authorizedUserId);
        return service.getOverview(restockUrgency, authorizedUserId);
    }
}
