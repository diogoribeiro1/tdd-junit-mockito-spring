package com.api.tdd.service;

import com.api.tdd.model.BookingModel;
import com.api.tdd.repository.BookingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class BookingServiceTest {

    @Autowired
    private BookingService bookingService;

    @MockBean
    BookingRepository bookingRepository;

    private LocalDate checkinIn = LocalDate.parse("2022-11-10");
    private LocalDate checkinOut = LocalDate.parse("2022-11-20");
    private BookingModel modelSave = new BookingModel("1","Jorge",checkinIn, checkinOut, 2);

    @BeforeEach
    public void setup(){

        LocalDate checkinIn = LocalDate.parse("2022-11-10");
        LocalDate checkinOut = LocalDate.parse("2022-11-20");

        BookingModel model = new BookingModel("1","Diogo",checkinIn, checkinOut, 2);

        ArrayList<BookingModel> lista = new ArrayList<BookingModel>();
        lista.add(model);

        Mockito.when(bookingRepository.findByReservedName(Mockito.any()))
                .thenReturn(model);

        Mockito.when(bookingRepository.findAll())
                .thenReturn(lista);

        Mockito.when(bookingRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(model));

        Mockito.when(bookingRepository.save(Mockito.any()))
                .thenReturn(model);

    }

    @Test
    public void testDaysCalculator() throws Exception {

        String name = "Diogo";

        int days = bookingService.daysCalculatorWithDatabase(name);

        Assertions.assertEquals(days, 10);

    }

    @Test
    public void testFindAllBookings() throws Exception {

        ArrayList<BookingModel> lista = (ArrayList<BookingModel>) bookingService.findAll();

        String name = "Diogo";

        Assertions.assertEquals(lista.get(0).getReservedName(), name);

    }

    @Test
    public void testFindBookingById() throws Exception {

        String id = "1";

        BookingModel model = bookingService.findById(id);

        Assertions.assertEquals(model.getReservedName(), "Diogo");

    }

    @Test
    public void testSaveBooking() throws Exception {

        BookingModel modelResult = bookingService.save(modelSave);

        Assertions.assertEquals("Diogo", modelResult.getReservedName());
    }

}
