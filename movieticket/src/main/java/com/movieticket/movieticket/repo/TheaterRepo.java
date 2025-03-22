package com.movieticket.movieticket.repo;

import com.movieticket.movieticket.model.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRepo extends JpaRepository<Theater,Integer> {
}
