package com.movieticket.movieticket.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Movie movie;
    @ManyToOne
    private Theater theater;
    private LocalDateTime dateTime;
    private int availaibleSeats;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getAvailaibleSeats() {
        return availaibleSeats;
    }

    public void setAvailaibleSeats(int availaibleSeats) {
        this.availaibleSeats = availaibleSeats;
    }
}
