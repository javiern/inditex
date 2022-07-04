package com.inditex.test.prices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class PriceService {

    PriceRepository repository;

    @Autowired
    public PriceService(PriceRepository repository) {
        this.repository = repository;
    }

    public Price getPrice(LocalDateTime date, Long product, Long brand) {
        return repository.findFirstByStartDateLessThanEqualAndEndDateGreaterThanAndProductIdAndBrandIdOrderByPriorityDesc(date, date, product, brand);
    }
}
