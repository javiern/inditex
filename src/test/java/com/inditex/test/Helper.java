package com.inditex.test;

import com.inditex.test.prices.Price;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Helper {

    static private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    static Price priceBuilder(String start, String end, BigDecimal amount, long priority) {
        return new Price(1L, LocalDateTime.parse(start, formatter), LocalDateTime.parse(end, formatter), 1L, 35455L, priority, amount, "EUR");
    }
}
