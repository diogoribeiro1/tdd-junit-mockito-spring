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
    public @ResponseBody BookingModel getById(@PathVariable(value = "id") String id) throws Exception {
        return bookingService.findById(id);
    }

    @PostMapping
    public ResponseEntity<BookingModel> save(@RequestBody BookingModel model) throws Exception {
        return new ResponseEntity<>(bookingService.save(model), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(value = "id") String id) throws Exception {
        bookingService.delete(id);
    }

}
