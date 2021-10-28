package com.switchfully.eurder.services;

import com.switchfully.eurder.api.dto.shipping.DailyShippingDto;
import com.switchfully.eurder.api.dto.shipping.DailyShippingDtoMapper;
import com.switchfully.eurder.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class DailyShippingService {
    private final OrderService orderService;
    private final AdminService adminService;
    private final DailyShippingDtoMapper mapper;

    @Autowired
    public DailyShippingService(OrderService orderService, AdminService adminService, DailyShippingDtoMapper mapper) {
        this.orderService = orderService;
        this.adminService = adminService;
        this.mapper = mapper;
    }

    public DailyShippingDto getDailyShippings(LocalDate shippingDay, String authorizedUserId) {
        adminService.assertAdminId(authorizedUserId);
        if (shippingDay == null) {
            shippingDay = LocalDate.now();
        }
        Map<String, List<Order>> ordersPerCustomerId = orderService.getOrdersPerCustomerId();
        return mapper.toDailyShippingDto(shippingDay, ordersPerCustomerId);
    }
}
