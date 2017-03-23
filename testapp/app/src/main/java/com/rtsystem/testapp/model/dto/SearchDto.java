package com.rtsystem.testapp.model.dto;

import com.google.gson.annotations.SerializedName;
import com.rtsystem.testapp.model.entity.Movie;

import java.util.List;

/**
 * Created by rafaeltonholo on 02/01/2017.
 */

public class SearchDto {
    @SerializedName("Search")
    private List<Movie> search;
    private int totalResults;
    @SerializedName("Response")
    private boolean response;

    public List<Movie> getSearch() {
        return search;
    }

    public void setSearch(List<Movie> search) {
        this.search = search;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public boolean isResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }
}
