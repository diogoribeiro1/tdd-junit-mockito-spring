package com.api.tdd.service;

import com.api.tdd.model.BookingModel;
import com.api.tdd.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Period;
import java.util.List;
import java.util.Optional;

@Component
public class BookingService {

    @Autowired
    BookingRepository repository;


    public int daysCalculatorWithDatabase(String name) throws Exception {
        BookingModel model = repository.findByReservedName(name);
        if (model == null){
            throw new Exception("Model nao Encontrado");
        }
        return Period.between(model.getCheckIn(), model.getCheckOut()).getDays();
    }

    public List<BookingModel> findAll(){
        return repository.findAll();
    }

    public BookingModel findById(String id) throws Exception {
        Optional<BookingModel> model = repository.findById(id);
        if (model.isEmpty()){
            throw new Exception("Booking não encontrado");
        }
        return repository.findById(id).get();
    }

    public void delete(String id) throws Exception {
        Optional<BookingModel> model = repository.findById(id);
        if (model.isEmpty()){
            throw new Exception("Booking não encontrado");
        }
        repository.delete(model.get());
    }

    public BookingModel save(BookingModel model) throws Exception {
//        Optional<BookingModel> modelOptional = repository.findById(model.getId());
//        if (!modelOptional.isEmpty()){
//            throw new Exception("Booking ja existe");
//        }
        return repository.save(model);
    }
}
