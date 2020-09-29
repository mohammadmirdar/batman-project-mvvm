package com.mmdev.batmanproject.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.mmdev.batmanproject.model.MovieDetail;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM batman_list_table ORDER BY year ASC")
    LiveData<List<MovieData>> getAllBatmanList();

    @Insert
    Single<Long> insertBatmanMovie(MovieData movieData);

    @Query("DELETE FROM batman_list_table")
    Single<Integer> deleteAllBatmanList();

    @Query("SELECT * FROM movie_detail_table WHERE imdbId = :movieId LIMIT 1")
    LiveData<MovieDetailData> getMovieById(String movieId);

    @Insert
    Single<Long> insertMovieDetail(MovieDetailData movieDetail);

    @Query("DELETE FROM movie_detail_table")
    Single<Integer> deleteAllMovieDetails();
}
