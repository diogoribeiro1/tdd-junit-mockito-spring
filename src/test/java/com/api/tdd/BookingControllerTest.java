package com.api.tdd;


import com.api.tdd.controller.BookingController;
import com.api.tdd.model.BookingModel;
import com.api.tdd.repository.BookingRepository;
import com.api.tdd.service.BookingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.web.context.WebApplicationContext;

//@SpringBootTest
//@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@WebMvcTest(BookingController.class)
public class BookingControllerTest {

//    @TestConfiguration
//    static class BookingServiceConfiguration{
//        @Bean
//        public BookingService bookingService(){
//            return new BookingService();
//        }
//    }
//
//    @MockBean
//    BookingService bookingService;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    BookingRepository bookingRepository;

    @Autowired
    ObjectMapper objectMapper;

    LocalDate checkinIn = LocalDate.parse("2022-11-10");
    LocalDate checkinOut = LocalDate.parse("2022-11-20");

    BookingModel model = new BookingModel("1","Diogo",checkinIn, checkinOut, 2);

    @Test
    public void bookingTestGetAll() throws Exception{
        List<BookingModel> records = new ArrayList<>(Arrays.asList(model));

        Mockito.when(bookingRepository.findAll()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/bookings")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].reserveName", is("Diogo")));

    }

    @Test
    public void bookingTestSave() throws Exception{

        LocalDate checkinIn = LocalDate.parse("2022-11-10");
        LocalDate checkinOut = LocalDate.parse("2022-11-20");

        BookingModel bookingModel = new BookingModel("4","Douglas",checkinIn, checkinOut, 2);

        Mockito.when(bookingRepository.save(bookingModel)).thenReturn(bookingModel);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookingModel));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.reserveName").value("Douglas"));

    }

    @Test
    public void bookingTestDelete() throws Exception{
        Mockito.when(bookingRepository.findById(model.getId())).thenReturn(Optional.ofNullable(model));

        mockMvc.perform(delete("/bookings/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }


}
