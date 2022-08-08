package com.api.tdd.repository;

import com.api.tdd.model.BookingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<BookingModel, String> {

    BookingModel findByReservedName(String name);
}
