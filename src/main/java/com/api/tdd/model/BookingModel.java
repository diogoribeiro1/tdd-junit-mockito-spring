package com.api.tdd.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class BookingModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String reservedName;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private int numberGuest;

    public BookingModel() {
    }

    public BookingModel(String id, String reserveName, LocalDate checkIn, LocalDate checkOut, int numberGuest) {
        this.id = id;
        this.reservedName = reserveName;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.numberGuest = numberGuest;
    }

    public BookingModel(String reserveName, LocalDate checkIn, LocalDate checkOut, int numberGuest) {
        this.reservedName = reserveName;
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

    public String getReservedName() {
        return reservedName;
    }

    public void setReservedName(String reserveName) {
        this.reservedName = reserveName;
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
