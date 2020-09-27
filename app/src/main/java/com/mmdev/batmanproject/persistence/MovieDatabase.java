package com.mmdev.batmanproject.persistence;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {BatmanData.class},version = 1)
public abstract class MovieDatabase extends RoomDatabase {

    public abstract BatmanDao batmanDao();

    private static MovieDatabase instance;

    public static synchronized MovieDatabase getInstance(Application application){
        if (instance == null) {
            instance = Room.databaseBuilder(application.getApplicationContext(),
                    MovieDatabase.class,
                    "weather_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
