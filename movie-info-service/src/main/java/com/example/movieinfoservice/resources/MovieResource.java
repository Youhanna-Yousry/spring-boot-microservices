package com.example.movieinfoservice.resources;

import com.example.movieinfoservice.models.Movie;
import com.example.movieinfoservice.models.MovieSummary;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import static com.mongodb.client.model.Filters.eq;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @Value("${api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public MovieResource(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
        MovieSummary movieSummary = new MovieSummary();

        MongoClientURI mongoClientURI = new MongoClientURI("mongodb://localhost:27017");
        MongoClient mongoClient = new MongoClient(mongoClientURI);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("Movies");
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("movieInfo");
        Bson projectionFields = Projections.fields(
                Projections.include("_id", "title", "overview"),
                Projections.excludeId());
        Document cachedMovieDocument = mongoCollection.find(eq("_id", movieId)).projection(projectionFields).first();

        boolean ok = (cachedMovieDocument != null);
        if (ok) {
            movieSummary.setTitle(cachedMovieDocument.getString("title"));
            movieSummary.setOverview(cachedMovieDocument.getString("overview"));
        } else {
            final String url = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey;
            movieSummary = restTemplate.getForObject(url, MovieSummary.class);
            Document newMovieDocument = new Document();
            newMovieDocument.append("_id", movieId);
            newMovieDocument.append("title", movieSummary.getTitle());
            newMovieDocument.append("overview", movieSummary.getOverview());
            mongoCollection.insertOne(newMovieDocument);
        }
        return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());
    }
}