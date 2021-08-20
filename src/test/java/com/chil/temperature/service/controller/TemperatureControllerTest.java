package com.chil.temperature.service.controller;

import com.chil.temperature.service.response.TemperatureResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(TemperatureController.class)
class TemperatureControllerTest {

    @Autowired
    private MockMvc mvc;


    @Test
    void testGetRightDataFormat() throws Exception {

        mvc.perform(get("/temperature?lat=43.66258321585993&lng=-79.39152689466948"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
    @Test
    void TestGetRightTemperature() throws Exception {

        mvc.perform(get("/temperature?lat=43.66258321585993&lng=-79.39152689466948"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{'temperature':3400}"));
    }
}