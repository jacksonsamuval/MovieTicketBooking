package com.movieticket.movieticket.dto;

import java.time.LocalDateTime;

public class ShowDto {
    private Integer movieId;
    private Integer theaterId;
    private LocalDateTime dateTime;
    private int availaibleSeats;

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Integer getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(Integer theaterId) {
        this.theaterId = theaterId;
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
