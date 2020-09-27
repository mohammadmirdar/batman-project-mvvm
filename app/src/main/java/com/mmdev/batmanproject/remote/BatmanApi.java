package com.mmdev.batmanproject.remote;


import com.mmdev.batmanproject.model.Batman;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BatmanApi {

    @GET(".")
    Flowable<Batman> getAllBatmanList(
            @Query("apikey") String apiKey,
            @Query("s") String search
    );
}
