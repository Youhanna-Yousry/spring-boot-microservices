package com.example.trendingsservice.resources;

import com.example.trendingsservice.models.Rating;
import com.example.trendingsservice.models.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingsResource {

    @RequestMapping("/{userId}")
    public UserRating getRatingsOfUser(@PathVariable String userId) {
        List<Rating> ratings = Arrays.asList(
                new Rating("550", 4)
        );

        return new UserRating(ratings);
    }
}
