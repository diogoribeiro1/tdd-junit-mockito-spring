package com.api.tdd;

import com.api.tdd.model.BookingModel;
import com.api.tdd.repository.BookingRepository;
import com.api.tdd.service.BookingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class BookingServiceTest {
    @Autowired
    private BookingService bookingService;
    @MockBean
    private BookingRepository bookingRepository;

    @TestConfiguration
    static class BookingServiceConfiguration{
        @Bean
        public BookingService bookingService(){
            return new BookingService();
        }
    }

    LocalDate checkinIn = LocalDate.parse("2022-11-10");
    LocalDate checkinOut = LocalDate.parse("2022-11-20");
    BookingModel modelSave = new BookingModel("1","Jorge",checkinIn, checkinOut, 2);

    @Test
    public void bookingTestServiceDaysCalculator() throws Exception {
        String name = "Diogo";
        int days = bookingService.daysCalculatorWithDatabase(name);

        Assertions.assertEquals(days, 10);
    }

    @Test
    public void bookingTestServiceFindAll() throws Exception {
        ArrayList<BookingModel> lista = (ArrayList<BookingModel>) bookingService.findAll();
        String name = "Diogo";
        Assertions.assertEquals(lista.get(0).getReserveName(), name);
    }

    @Test
    public void bookingTestServiceFindById() throws Exception {
        String id = "1";
        BookingModel model = bookingService.findById(id);
        Assertions.assertEquals(model.getReserveName(), "Diogo");
    }

    @Test
    public void bookingTestServiceSave() throws Exception {
        LocalDate checkinIn = LocalDate.parse("2022-11-10");
        LocalDate checkinOut = LocalDate.parse("2022-11-20");
        BookingModel model = new BookingModel("2","Jorge",checkinIn, checkinOut, 2);

        BookingModel modelResult = bookingService.save(model);
        Assertions.assertEquals(modelResult.getReserveName(), model.getReserveName());
    }

    @Before
    public void setup(){
        LocalDate checkinIn = LocalDate.parse("2022-11-10");
        LocalDate checkinOut = LocalDate.parse("2022-11-20");

        BookingModel model = new BookingModel("1","Diogo",checkinIn, checkinOut, 2);

        ArrayList<BookingModel> lista = new ArrayList<BookingModel>();
        lista.add(model);

        Mockito.when(bookingRepository.findByReservedName(model.getReserveName()))
                .thenReturn(model);

        Mockito.when(bookingRepository.findAll())
                .thenReturn(lista);

        Mockito.when(bookingRepository.findById(model.getId()))
                .thenReturn(Optional.of(model));

        Mockito.when(bookingRepository.save(modelSave))
                .thenReturn(model);

    }

}
