package com.inditex.test.prices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@RestController
public class PriceController {

    Logger logger = LoggerFactory.getLogger(PriceController.class);

    @Autowired
    PriceService svc;

    @Autowired
    Mapper mapper;

    @GetMapping("/price")
    public PriceDTO price(
        @RequestParam(value = "date", defaultValue = "#{T(java.time.LocalDateTime).now()}")
            @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME)  LocalDateTime date,
        @RequestParam(value = "product", defaultValue = "1") Long productId,
        @RequestParam(value = "brand", defaultValue = "1") Long brandId
    ) {
        logger.info("Finding price for params date: {} - product: {} - brand: {}", date, productId, brandId);
        Price result = svc.getPrice(date, productId, brandId);
        if (result != null)
            return mapper.toDto(result);
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
