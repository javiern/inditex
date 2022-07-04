package com.inditex.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static com.inditex.test.Helper.*;

import com.inditex.test.prices.Price;
import com.inditex.test.prices.PriceRepository;
import com.inditex.test.prices.PriceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public class TestPriceService {
    PriceService service;

    @Mock
    PriceRepository repository;

    @BeforeEach
    void initService() {
        MockitoAnnotations.openMocks(this);
        service = new PriceService(repository);
    }

    private static Stream<Arguments> arguments() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return Stream.of(
                Arguments.of(
                        LocalDateTime.parse("2020-06-14 00:00", formatter),
                        35455L,
                        1L,
                        priceBuilder("2020-06-14 00:00", "2020-12-31 23:59",  new BigDecimal(35.50D), 1))
        );
    }



    @ParameterizedTest
    @MethodSource("arguments")
    void shouldReturnPriceInstance(LocalDateTime date, Long product, Long brand, Price expected) {
        when(this.repository.findFirstByStartDateLessThanEqualAndEndDateGreaterThanAndProductIdAndBrandIdOrderByPriorityDesc(date, date, product, brand))
                .thenReturn(expected);
        assertEquals(expected, service.getPrice(date, product, brand));
    }
}