package com.mmdev.batmanproject.persistence;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "batman_list_table")
public class BatmanData {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "year")
    private String year;
    @ColumnInfo(name = "imdbId")
    private String imdbId;
    @ColumnInfo(name = "type")
    private String type;
    @ColumnInfo(name = "poster_url")
    private String poster_url;

    public BatmanData() {
    }

    public BatmanData(int id, String title, String year, String imdbId, String type, String poster_url) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.imdbId = imdbId;
        this.type = type;
        this.poster_url = poster_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPoster_url() {
        return poster_url;
    }

    public void setPoster_url(String poster_url) {
        this.poster_url = poster_url;
    }
}
