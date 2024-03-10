package com.moviecatalogservice.resources;

import com.moviecatalogservice.dtos.MovieResponseDTO;
import com.moviecatalogservice.models.CatalogItem;
import com.moviecatalogservice.models.Rating;
import com.moviecatalogservice.services.MovieInfoService;
import com.moviecatalogservice.services.TrendingMoviesService;
import com.moviecatalogservice.services.UserRatingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    private final RestTemplate restTemplate;

    private final MovieInfoService movieInfoService;

    private final UserRatingService userRatingService;

    private final TrendingMoviesService trendingMoviesService;



    public MovieCatalogResource(RestTemplate restTemplate,
                                MovieInfoService movieInfoService,
                                UserRatingService userRatingService,
                                TrendingMoviesService trendingMoviesService) {

        this.restTemplate = restTemplate;
        this.movieInfoService = movieInfoService;
        this.userRatingService = userRatingService;
        this.trendingMoviesService = trendingMoviesService;
    }

    /**
     * Makes a call to MovieInfoService to get movieId, name and description,
     * Makes a call to RatingsService to get ratings
     * Accumulates both data to create a MovieCatalog
     * @param userId
     * @return CatalogItem that contains name, description and rating
     */
    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId) {
        List<Rating> ratings = userRatingService.getUserRating(userId).getRatings();
        return ratings.stream().map(movieInfoService::getCatalogItem).collect(Collectors.toList());
    }

    @GetMapping("/trending-movies")
    public ResponseEntity<List<MovieResponseDTO>> getTopRatedMovies(@RequestParam Integer limit) {
        return ResponseEntity.ok(trendingMoviesService.getTopRatedMovies(limit));
    }
}
