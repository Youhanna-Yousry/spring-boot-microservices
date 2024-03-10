package com.example.trendingsservice.services;

import com.example.Movie;
import com.example.TopRatedMoviesRequest;
import com.example.TopRatedMoviesServiceGrpc;
import com.example.TopRatedMoviesResponse;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.*;

@GrpcService
public class TopRatedMoviesService extends TopRatedMoviesServiceGrpc.TopRatedMoviesServiceImplBase {

    @Override
    public void getTopRatedMovies(TopRatedMoviesRequest request, StreamObserver<TopRatedMoviesResponse> responseObserver) {
        System.out.println("Limit is : " + request.getLimit());
        TopRatedMoviesResponse response =
                TopRatedMoviesResponse
                        .newBuilder()
                        .addMovies(
                                Movie.newBuilder().setMovieId(5).build()
                        )
                        .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
