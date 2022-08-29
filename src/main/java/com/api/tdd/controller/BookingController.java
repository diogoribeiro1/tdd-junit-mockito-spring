package com.api.tdd.controller;

import com.api.tdd.model.BookingModel;
import com.api.tdd.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@EnableJpaRepositories
public class BookingController {

    @Autowired
    BookingService bookingService;

    @GetMapping
    public @ResponseBody List<BookingModel> getAll(){
        return bookingService.findAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<Object> getById(@PathVariable(value = "id") String id) throws Exception {

        BookingModel bookingModel = bookingService.findById(id);

        if (bookingModel == null){
            return new ResponseEntity<>("BOOKING NOT FOUND", HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>(bookingModel, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody BookingModel model) throws Exception {

        BookingModel bookingModel = bookingService.save(model);

        if (bookingModel == null){
            return new ResponseEntity<>("BOOKING ALREADY EXISTS", HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>(bookingModel, HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") String id) throws Exception {

        String result = bookingService.delete(id);

        if (result == null){
            return new ResponseEntity<>("BOOKING DONT EXISTS", HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>(result, HttpStatus.OK) ;
        }
    }

}
