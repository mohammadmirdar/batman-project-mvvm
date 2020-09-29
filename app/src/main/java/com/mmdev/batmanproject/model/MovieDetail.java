package com.mmdev.batmanproject.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


@SuppressWarnings("all")
public class MovieDetail {
    @SerializedName("Title")
    private  String title;

    @SerializedName("Year")
    private  String year;

    @SerializedName("Rated")
    private  String rated;

    @SerializedName("Released")
    private  String released;

    @SerializedName("Runtime")
    private  String runtime;

    @SerializedName("Genre")
    private  String genre;

    @SerializedName("Director")
    private  String director;

    @SerializedName("Writer")
    private  String writer;

    @SerializedName("Actors")
    private  String actors;

    @SerializedName("Plot")
    private  String plot;

    @SerializedName("Language")
    private  String language;

    @SerializedName("Country")
    private  String country;

    @SerializedName("Awards")
    private  String awards;

    @SerializedName("Poster")
    private  String poster;

    @SerializedName("Metascore")
    private  String metascore;

    @SerializedName("imdbRating")
    private  String imdbRating;

    @SerializedName("imdbVotes")
    private  String imdbVotes;

    @SerializedName("imdbID")
    private  String imdbId;

    @SerializedName("Type")
    private  String type;

    @SerializedName("DVD")
    private  String dvd;

    @SerializedName("BoxOffice")
    private  String boxOffice;

    @SerializedName("Production")
    private  String production;

    @SerializedName("Website")
    private  String website;

    @SerializedName("Response")
    private  String response;

    public MovieDetail() {
    }

    public MovieDetail(String title, String year, String rated, String released, String runtime,
                       String genre, String director, String writer, String actors, String plot,
                       String language, String country, String awards, String poster,
                       String metascore, String imdbRating, String imdbVotes, String imdbId, String type,
                       String dvd, String boxOffice, String production, String website, String response) {
        this.title = title;
        this.year = year;
        this.rated = rated;
        this.released = released;
        this.runtime = runtime;
        this.genre = genre;
        this.director = director;
        this.writer = writer;
        this.actors = actors;
        this.plot = plot;
        this.language = language;
        this.country = country;
        this.awards = awards;
        this.poster = poster;
        this.metascore = metascore;
        this.imdbRating = imdbRating;
        this.imdbVotes = imdbVotes;
        this.imdbId = imdbId;
        this.type = type;
        this.dvd = dvd;
        this.boxOffice = boxOffice;
        this.production = production;
        this.website = website;
        this.response = response;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getRated() {
        return rated;
    }

    public String getReleased() {
        return released;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getGenre() {
        return genre;
    }

    public String getDirector() {
        return director;
    }

    public String getWriter() {
        return writer;
    }

    public String getActors() {
        return actors;
    }

    public String getPlot() {
        return plot;
    }

    public String getLanguage() {
        return language;
    }

    public String getCountry() {
        return country;
    }

    public String getAwards() {
        return awards;
    }

    public String getPoster() {
        return poster;
    }

    public String getMetascore() {
        return metascore;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public String getImdbId() {
        return imdbId;
    }

    public String getType() {
        return type;
    }

    public String getDvd() {
        return dvd;
    }

    public String getBoxOffice() {
        return boxOffice;
    }

    public String getProduction() {
        return production;
    }

    public String getWebsite() {
        return website;
    }

    public String getResponse() {
        return response;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }
}
