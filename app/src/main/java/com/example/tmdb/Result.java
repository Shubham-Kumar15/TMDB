package com.example.tmdb;

import java.util.List;

public class Result {
    int page;
    List<Movies> results;

    public Result(int page, List<Movies> results) {
        this.page = page;
        this.results = results;
    }

    @Override
    public String toString() {
        return "Result{" +
                "page=" + page +
                ", results=" + results +
                '}';
    }
}
