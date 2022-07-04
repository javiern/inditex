package com.inditex.test;

import static com.inditex.test.Helper.*;
import static org.junit.jupiter.api.Assertions.*;

import com.inditex.test.prices.Price;
import com.inditex.test.prices.Mapper;
import com.inditex.test.prices.PriceDTO;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

public class TestPriceMapper {

    @Test
    void shouldReturnValidDTO() {
        Price price = priceBuilder("2020-06-14 00:00", "2020-06-14 23:00", new BigDecimal(35.50D), 1);
        Mapper mapper = new Mapper();

        PriceDTO dto = mapper.toDto(price);

        assertEquals(price.getPrice(), dto.getPrice());
        assertEquals(price.getPriceList(), dto.getPriceList());
        assertEquals(price.getBrandId(), dto.getBrandId());
        assertEquals(price.getCurrency(), dto.getCurrency());
        assertEquals(price.getStartDate(), dto.getStartDate());
        assertEquals(price.getEndDate(), dto.getEndDate());
        assertEquals(price.getProductId(), dto.getProductId());
    }
}