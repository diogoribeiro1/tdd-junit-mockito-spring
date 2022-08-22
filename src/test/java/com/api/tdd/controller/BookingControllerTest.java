package com.api.tdd.controller;

import com.api.tdd.model.BookingModel;
import com.api.tdd.repository.BookingRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    BookingRepository bookingRepository;

    @BeforeEach
    public void setup(){

        LocalDate checkinIn = LocalDate.parse("2022-11-10");
        LocalDate checkinOut = LocalDate.parse("2022-11-20");

        BookingModel model = new BookingModel("1","Diogo",checkinIn, checkinOut, 2);

        BookingModel otherModel = new BookingModel("2","Douglas",checkinIn, checkinOut, 2);

        Mockito.when(bookingRepository.findAll())
                .thenReturn(List.of(model));

        Mockito.when(bookingRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(model));

        Mockito.when(bookingRepository.save(Mockito.any()))
                .thenReturn(otherModel);

    }

    @Test
    void getAllBookings () throws Exception {

        this.mockMvc.perform(get("/bookings")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].reservedName").value("Diogo"))
                .andExpect(jsonPath("$[0].checkIn").value("2022-11-10"))
                .andExpect(jsonPath("$[0].checkOut").value("2022-11-20"))
                .andExpect(jsonPath("$[0].numberGuest").value("2"));
    }

    @Test
    void saveBooking () throws Exception {

        this.mockMvc.perform(post("/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"2\",\"reservedName\": \"Douglas\",\"checkIn\": \"2022-11-10\",\"checkOut\": \"2022-11-20\",\"numberGuest\": \"2\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("2"))
                .andExpect(jsonPath("$.reservedName").value("Douglas"))
                .andExpect(jsonPath("$.checkIn").value("2022-11-10"))
                .andExpect(jsonPath("$.checkOut").value("2022-11-20"))
                .andExpect(jsonPath("$.numberGuest").value("2"));
    }

    @Test
    void deleteBooking () throws Exception {

        this.mockMvc.perform(delete("/bookings/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
