package com.switchfully.eurder.api.dto.shipping;

import com.switchfully.eurder.api.dto.itemgroups.ItemGroupShippingDto;
import java.time.LocalDate;
import java.util.List;

public class DailyShippingDto {
    private LocalDate shippingDay;
    private List<ItemGroupShippingDto> itemGroups;

    public LocalDate getShippingDay() {
        return shippingDay;
    }

    public DailyShippingDto setShippingDay(LocalDate shippingDay) {
        this.shippingDay = shippingDay;
        return this;
    }

    public List<ItemGroupShippingDto> getItemGroups() {
        return itemGroups;
    }

    public DailyShippingDto setItemGroups(List<ItemGroupShippingDto> itemGroups) {
        this.itemGroups = itemGroups;
        return this;
    }
}
