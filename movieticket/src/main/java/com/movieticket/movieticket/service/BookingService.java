package com.movieticket.movieticket.service;

import com.movieticket.movieticket.dto.BookingDto;
import com.movieticket.movieticket.model.Booking;
import com.movieticket.movieticket.model.BookingStatus;
import com.movieticket.movieticket.model.Show;
import com.movieticket.movieticket.model.User;
import com.movieticket.movieticket.repo.BookingRepo;
import com.movieticket.movieticket.repo.ShowRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;

@Service
public class BookingService {
    @Autowired
    private ShowRepo showRepo;
    @Autowired
    private HomeService homeService;
    @Autowired
    private BookingRepo bookingRepo;

    public ResponseEntity<?> bookTicket(BookingDto bookingDto) {
        User user = homeService.getCurrentUser();
        Show show = showRepo.findById(bookingDto.getShowId()).orElseThrow(()->new RuntimeException("Not Found"));
        Booking booking = new Booking();
        booking.setSeatsBooked(bookingDto.getSeatsBooked());
        booking.setShow(show);
        booking.setUser(user);
        booking.setTotalPrice(getTotalPrice(bookingDto.getSeatsBooked(), show.getPrice()));
        int seats = show.getAvailaibleSeats();
        int afterBooking = seats-bookingDto.getSeatsBooked();
        show.setAvailaibleSeats(afterBooking);
        booking.setBookingStatus(BookingStatus.COMPLETED);
        bookingRepo.save(booking);
        showRepo.save(show);
        return ResponseEntity.status(200).body(booking);
    }

    private int getTotalPrice(int seatsBooked, int price) {
        return seatsBooked*price;
    }

    public ResponseEntity<?> getAllBookings() {
        User user = homeService.getCurrentUser();
        List<Booking> bookings = bookingRepo.findByUser(user);
        return ResponseEntity.status(200).body(bookings);
    }

    public ResponseEntity<?> cancelBooking(Integer id) {
        Booking booking = bookingRepo.findById(id).orElseThrow(()->new RuntimeException("Not Found"));
        int bookedSeats = booking.getSeatsBooked();
        Show show = booking.getShow();
        int afterCancel = bookedSeats+show.getAvailaibleSeats();
        show.setAvailaibleSeats(afterCancel);
        showRepo.save(show);
        bookingRepo.delete(booking);
        return ResponseEntity.status(200).body("success");
    }
}
