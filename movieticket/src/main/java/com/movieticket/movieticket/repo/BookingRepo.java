package com.movieticket.movieticket.repo;

import com.movieticket.movieticket.model.Booking;
import com.movieticket.movieticket.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepo extends JpaRepository<Booking,Integer> {

    List<Booking> findByUser(User user);
}
