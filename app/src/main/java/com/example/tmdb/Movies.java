package com.example.tmdb;

public class Movies {
    String poster_path,overview,release_date,original_title;
    int id;

    public Movies(String poster_path, String overview, String release_date, String original_title, int id) {
        this.poster_path = poster_path;
        this.overview = overview;
        this.release_date = release_date;
        this.original_title = original_title;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Movies{" +
                "poster_path='" + poster_path + '\'' +
                ", overview='" + overview + '\'' +
                ", release_date='" + release_date + '\'' +
                ", original_title='" + original_title + '\'' +
                ", id=" + id +
                '}';
    }
}
