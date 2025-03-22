package com.movieticket.movieticket.controller;

import com.movieticket.movieticket.dto.BookingDto;
import com.movieticket.movieticket.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<?> bookTicket(@RequestBody BookingDto bookingDto){
        try{
            return bookingService.bookTicket(bookingDto);
        } catch (Exception e){
            return ResponseEntity.status(400).body("error");
        }
    }

    @GetMapping("/getAllBookings")
    public ResponseEntity<?> getAllBookings(){
        try{
            return bookingService.getAllBookings();
        } catch (Exception e){
            return ResponseEntity.status(400).body("error");
        }
    }

    @DeleteMapping("/cancelBooking/{bookingId}")
    public ResponseEntity<?> cancelBooking(@PathVariable("bookingId") Integer id){
        try{
            return bookingService.cancelBooking(id);
        } catch (Exception e){
            return ResponseEntity.status(400).body("error");
        }
    }
}
