package com.api.tdd.service;

import com.api.tdd.model.BookingModel;
import com.api.tdd.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;
    public int daysCalculatorWithDatabase(String name) throws Exception {
        BookingModel model = bookingRepository.findByReservedName(name);
        if (model == null){
            throw new Exception("Model nao Encontrado");
        }
        return Period.between(model.getCheckIn(), model.getCheckOut()).getDays();
    }

    public List<BookingModel> findAll(){
        return bookingRepository.findAll();
    }

    public BookingModel findById(String id) throws Exception {
        Optional<BookingModel> model = bookingRepository.findById(id);
        if (model.isEmpty()){
            throw new Exception("Booking não encontrado");
        }
        return bookingRepository.findById(id).get();
    }

    public void delete(String id) throws Exception {
        Optional<BookingModel> model = bookingRepository.findById(id);
        if (model.isEmpty()){
            throw new Exception("Booking não encontrado");
        }
        bookingRepository.delete(model.get());
    }

    public BookingModel save(BookingModel model){
        return bookingRepository.save(model);
    }
}
