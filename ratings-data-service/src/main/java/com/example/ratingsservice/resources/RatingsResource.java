package com.example.ratingsservice.resources;

import com.example.ratingsservice.interfaces.UserRatingRepository;
import com.example.ratingsservice.models.Rating;
import com.example.ratingsservice.models.UserRating;
import com.example.ratingsservice.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingsResource {

    private final RatingService ratingService;
    @Autowired
    public RatingsResource(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addRating(@RequestParam String movieId, @RequestParam int rateVal, @RequestParam String userId) {
        ratingService.addRating(movieId, rateVal, userId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public UserRating getRatingsOfUser(@PathVariable String userId) {
        List<Rating> ratings = ratingService.getRatingsById(userId);
        return new UserRating(ratings);
    }

//    @RequestMapping("/{userId}")
//    public UserRating getRatingsOfUser(@PathVariable String userId) {
//
//        List<Rating> ratings = Arrays.asList(
//                new Rating("550", 4, userId)
//        );
//
//        return new UserRating(ratings);
//    }
}
