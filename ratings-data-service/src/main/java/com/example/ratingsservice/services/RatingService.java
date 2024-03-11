package com.example.ratingsservice.services;

import com.example.ratingsservice.interfaces.UserRatingRepository;
import com.example.ratingsservice.models.Rating;
import com.example.ratingsservice.models.UserRating;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RatingService {
    private final UserRatingRepository repo;

    public RatingService(UserRatingRepository repo) {
        this.repo = repo;
    }

    public void addRating(String movieId, int rateVal, String userId) {
        Rating rating = new Rating(movieId, rateVal, userId);
        repo.save(rating);
    }

    public List<Rating> getRatingsById(String userId) {
        return repo.getRatingsByIdUserId(userId);
    }




//    public UserRating constructUserRating() {
//
//    }
//
//    public void addRating(String movieId, int rating, String userId) {
//        Rating rating1 = new Rating(movieId, rating);
//        List<Rating> list = new ArrayList<>();
//        UserRating userRating = new UserRating(list, userId);
//        repo.save(userRating);
//    }

//    public void addRating(String movieId, int rating, UserRating userRating) {
//
//        List<Rating> ratings = repo.getUserRatingsByUserId()
//        UserRating newRating = new UserRating(movieId, rating);
//        newRating.setUserId(userRating);
//        repo.save(newRating);
//    }
}
