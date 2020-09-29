package com.mmdev.batmanproject.remote;


import com.mmdev.batmanproject.model.Movie;
import com.mmdev.batmanproject.model.MovieDetail;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {

    @GET(".")
    Flowable<Movie> getAllBatmanList(
            @Query("apikey") String apiKey,
            @Query("s") String search
    );

    @GET(".")
    Flowable<MovieDetail> getMovieDetail(
            @Query("apikey") String apiKey,
            @Query("i") String movieId
    );


}
