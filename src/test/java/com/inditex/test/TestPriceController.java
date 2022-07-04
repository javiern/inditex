package com.inditex.test;

import com.inditex.test.prices.PriceController;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class TestPriceController {

    @Autowired
    private PriceController controller;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void requestWithNoParamsShouldReturnBadRequesst() throws Exception {
        this.mockMvc.perform(get("/price")).andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    public void requestWithUnexistentProductShouldReturnNotFound() throws Exception {
        this.mockMvc.perform(get("/price&product=35454")).andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    public void requestWithUnexistentBrandShouldReturnNotFound() throws Exception {
        this.mockMvc.perform(get("/price&brand=3")).andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    public void requestWithCorrectParametersShouldReturnOK() throws Exception {
        this.mockMvc.perform(get("/price?date=2020-06-14T16:00:01&product=35455")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void requestShouldReturn3550Price() throws Exception {
        this.mockMvc.perform(get("/price?date=2020-06-14T01:00:01&product=35455"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price",is(35.50)));
    }

    @Test
    public void requestShouldReturn2545Price() throws Exception {
        this.mockMvc.perform(get("/price?date=2020-06-14T16:00:01&product=35455"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price",is(25.45)));
    }

    @Test
    public void requestShouldReturn3050Price() throws Exception {
        this.mockMvc.perform(get("/price?date=2020-06-15T10:00:01&product=35455"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price",is(30.50)));
    }

    @Test
    public void requestShouldReturn3895Price() throws Exception {
        this.mockMvc.perform(get("/price?date=2020-07-15T10:00:01&product=35455"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price",is(38.95)));
    }
}