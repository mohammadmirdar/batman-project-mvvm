package com.mmdev.batmanproject.persistence;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {MovieData.class,MovieDetailData.class},version = 2)
public abstract class MovieDatabase extends RoomDatabase {

    public abstract MovieDao batmanDao();

}
