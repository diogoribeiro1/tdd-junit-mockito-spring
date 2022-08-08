package com.api.tdd.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
@Entity
public class BookingModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String reserveName;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private int numberGuest;

    public BookingModel() {
    }

    public BookingModel(String id, String reserveName, LocalDate checkIn, LocalDate checkOut, int numberGuest) {
        this.id = id;
        this.reserveName = reserveName;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.numberGuest = numberGuest;
    }

    public BookingModel(String reserveName, LocalDate checkIn, LocalDate checkOut, int numberGuest) {
        this.reserveName = reserveName;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.numberGuest = numberGuest;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReserveName() {
        return reserveName;
    }

    public void setReserveName(String reserveName) {
        this.reserveName = reserveName;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public int getNumberGuest() {
        return numberGuest;
    }

    public void setNumberGuest(int numberGuest) {
        this.numberGuest = numberGuest;
    }
}
