package com.example.movieinfoservice.resources;

import com.example.movieinfoservice.models.Movie;
import com.example.movieinfoservice.models.MovieSummary;
import com.example.movieinfoservice.repository.MovieInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @Value("${api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private final MovieInfoRepository movieInfoRepository;

    @Autowired
    public MovieResource(RestTemplate restTemplate, MovieInfoRepository movieInfoRepository) {
        this.restTemplate = restTemplate;
        this.movieInfoRepository = movieInfoRepository;
    }

    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
        MovieSummary movieSummary = movieInfoRepository.findMovieSummaryById(movieId);

        if (movieSummary == null) {
            final String url = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey;
            movieSummary = restTemplate.getForObject(url, MovieSummary.class);
            movieInfoRepository.saveMovieSummary(movieSummary, movieId);
        }

        return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());
    }
}
