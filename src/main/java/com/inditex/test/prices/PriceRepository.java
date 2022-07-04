package com.inditex.test.prices;

import java.time.LocalDateTime;
import org.springframework.data.repository.CrudRepository;

public interface PriceRepository extends CrudRepository<Price, Long> {
    Price findFirstByStartDateLessThanEqualAndEndDateGreaterThanAndProductIdAndBrandIdOrderByPriorityDesc(LocalDateTime start, LocalDateTime end, Long product, Long brand);

}
