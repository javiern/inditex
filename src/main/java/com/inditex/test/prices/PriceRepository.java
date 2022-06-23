package com.inditex.test.prices;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PriceRepository extends CrudRepository<Price, Long> {
    List<Price> findByBrandId(long brandId);
    Price findById(long id);
    Price findFirstByStartDateLessThanEqualAndEndDateGreaterThanAndProductIdAndBrandIdOrderByPriorityDesc(LocalDateTime start, LocalDateTime end, Long product, Long brand);

}
