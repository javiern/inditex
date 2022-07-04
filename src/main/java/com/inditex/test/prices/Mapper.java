package com.inditex.test.prices;

import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public PriceDTO toDto(Price price) {
        PriceDTO dto = new PriceDTO();

        dto.setBrandId(price.getBrandId());
        dto.setStartDate(price.getStartDate());
        dto.setEndDate(price.getEndDate());
        dto.setPriceList(price.getPriceList());
        dto.setPrice(price.getPrice());
        dto.setProductId(price.getProductId());
        dto.setCurrency(price.getCurrency());

        return dto;
    }
}
