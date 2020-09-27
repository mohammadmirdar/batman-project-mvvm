package com.mmdev.batmanproject.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("all")
public class Batman {
    @SerializedName("Search")
    private List<Search> search;

    public Batman() {
    }

    public Batman(List<Search> search) {
        this.search = search;
    }

    public List<Search> getSearch() {
        return search;
    }

    public static class Search {
        @SerializedName("Title")
        private final String title;

        @SerializedName("Year")
        private final String year;

        @SerializedName("imdbID")
        private final String imdbId;

        @SerializedName("Type")
        private final String type;

        @SerializedName("Poster")
        private final String poster;

        public Search(String title, String year, String imdbId, String type, String poster) {
            this.title = title;
            this.year = year;
            this.imdbId = imdbId;
            this.type = type;
            this.poster = poster;
        }

        public String getTitle() {
            return title;
        }

        public String getYear() {
            return year;
        }

        public String getImdbId() {
            return imdbId;
        }

        public String getType() {
            return type;
        }

        public String getPoster() {
            return poster;
        }



    }

    public void setSearch(List<Search> search) {
        this.search = search;
    }
}
