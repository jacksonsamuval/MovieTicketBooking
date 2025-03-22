package com.movieticket.movieticket.repo;

import com.movieticket.movieticket.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepo extends JpaRepository<Booking,Integer> {

}
