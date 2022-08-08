package com.api.tdd.controller;

import com.api.tdd.model.BookingModel;
import com.api.tdd.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    BookingRepository bookingService;


    @GetMapping
    @ResponseBody
    public List<BookingModel> getAll(){
        return bookingService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Optional<BookingModel> getById(@PathVariable(value = "id") String id) throws Exception {
        return bookingService.findById(id);
    }


    @PostMapping
    public ResponseEntity<BookingModel> save(@RequestBody BookingModel model){
        return new ResponseEntity<>(bookingService.save(model), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void delete(@PathVariable(value = "id") String id) throws Exception {
        bookingService.deleteById(id);
    }

}
