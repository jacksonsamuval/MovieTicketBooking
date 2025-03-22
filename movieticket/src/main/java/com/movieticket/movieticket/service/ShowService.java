package com.movieticket.movieticket.service;

import com.movieticket.movieticket.dto.ShowDto;
import com.movieticket.movieticket.model.Movie;
import com.movieticket.movieticket.model.Show;
import com.movieticket.movieticket.model.Theater;
import com.movieticket.movieticket.repo.MovieRepo;
import com.movieticket.movieticket.repo.ShowRepo;
import com.movieticket.movieticket.repo.TheaterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowService {
    @Autowired
    private ShowRepo showRepo;
    @Autowired
    private MovieRepo movieRepo;
    @Autowired
    private TheaterRepo theaterRepo;

    public ResponseEntity<?> addShow(ShowDto showDto) {
        Movie movie = movieRepo.findById(showDto.getMovieId()).orElseThrow(()-> new RuntimeException("NO MOVIE"));
        Theater theater = theaterRepo.findById(showDto.getTheaterId()).orElseThrow(()-> new RuntimeException("NO THEATER"));
        int price = movie.getPrice();
        Show show = new Show();
        show.setPrice(price);
        show.setMovie(movie);
        show.setTheater(theater);
        show.setAvailaibleSeats(showDto.getAvailaibleSeats());
        show.setDateTime(showDto.getDateTime());
        Show show1 = showRepo.save(show);
        return ResponseEntity.status(200).body(show1);
    }

    public ResponseEntity<?> deleteShow(Integer id) {
        Optional<Show> show = showRepo.findById(id);
        if (show.isPresent()){
            showRepo.deleteById(id);
            return ResponseEntity.status(200).body("success");
        } else {
            return ResponseEntity.status(401).body("Not Found");
        }
    }

    public ResponseEntity<?> getAllShow() {
        List<Show> shows = showRepo.findAll();
        return ResponseEntity.status(200).body(shows);
    }
}
