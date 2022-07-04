package com.inditex.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TestStatement {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void request1() throws Exception {
        this.mockMvc.perform(get("/price?date=2020-06-14T10:00:00&product=35455&brand=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price",is(35.50)));
    }

    @Test
    public void request2() throws Exception {
        this.mockMvc.perform(get("/price?date=2020-06-14T16:00:00&product=35455&brand=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price",is(25.45)));
    }

    @Test
    public void request3() throws Exception {
        this.mockMvc.perform(get("/price?date=2020-06-14T21:00:00&product=35455&brand=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price",is(35.50)));
    }

    @Test
    public void request4() throws Exception {
        this.mockMvc.perform(get("/price?date=2020-06-15T10:00:00&product=35455&brand=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price",is(30.50)));
    }

    @Test
    public void request5() throws Exception {
        this.mockMvc.perform(get("/price?date=2020-06-16T21:00:00&product=35455&brand=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price",is(38.95)));
    }

}