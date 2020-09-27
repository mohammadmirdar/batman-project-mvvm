package com.mmdev.batmanproject.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface BatmanDao {

    @Query("SELECT * FROM batman_list_table ORDER BY year ASC")
    LiveData<List<BatmanData>> getAllBatmanList();

    @Insert
    Single<Long> insertBatmanMovie(BatmanData batmanData);

    @Query("DELETE FROM batman_list_table")
    Single<Integer> deleteAllBatmanList();
}
