package com.example.movieinfoservice.repository;

import com.example.movieinfoservice.models.MovieSummary;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.mongodb.client.model.Filters.eq;

@Service
public class MovieInfoRepository {

    @Value("${mongodb.uri}")
    private String mongoDbUri;

    @Value("${mongodb.database}")
    private String databaseName;

    @Value("${mongodb.collection}")
    private String collectionName;

    public MovieSummary findMovieSummaryById(String movieId) {
        MongoClientURI mongoClientURI = new MongoClientURI(mongoDbUri);
        MongoClient mongoClient = new MongoClient(mongoClientURI);
        MongoDatabase mongoDatabase = mongoClient.getDatabase(databaseName);
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(collectionName);
        Bson projectionFields = Projections.fields(
                Projections.include("_id", "title", "overview"),
                Projections.excludeId());
        Document cachedMovieDocument = mongoCollection.find(eq("_id", movieId)).projection(projectionFields).first();

        if (cachedMovieDocument != null) {
            return new MovieSummary(cachedMovieDocument.getString("title"), cachedMovieDocument.getString("overview"));
        } else {
            return null;
        }
    }

    public void saveMovieSummary(MovieSummary movieSummary, String movieId) {
        MongoClientURI mongoClientURI = new MongoClientURI(mongoDbUri);
        MongoClient mongoClient = new MongoClient(mongoClientURI);
        MongoDatabase mongoDatabase = mongoClient.getDatabase(databaseName);
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(collectionName);

        Document newMovieDocument = new Document();
        newMovieDocument.append("_id", movieId);
        newMovieDocument.append("title", movieSummary.getTitle());
        newMovieDocument.append("overview", movieSummary.getOverview());
        mongoCollection.insertOne(newMovieDocument);
    }
}
