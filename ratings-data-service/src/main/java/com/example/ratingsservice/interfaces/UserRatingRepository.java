package com.example.ratingsservice.interfaces;

import com.example.ratingsservice.models.Rating;
import com.example.ratingsservice.models.RatingId;
import com.example.ratingsservice.models.UserRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRatingRepository extends JpaRepository<Rating, String> {
    List<Rating> getRatingsByIdUserId(String userId);
//    List<Rating> getRatingsByUserId(String userId);
}
