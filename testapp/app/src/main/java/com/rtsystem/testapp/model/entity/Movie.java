package com.rtsystem.testapp.model.entity;

import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.rtsystem.androidmvc.model.Model;
import com.rtsystem.testapp.db.AppDatabase;

/**
 * Created by rafaeltonholo on 28/12/2016.
 */
@Table(database = AppDatabase.class)
@Model
public class Movie {
    @SerializedName("imdbID")
    @PrimaryKey
    private String imdbID;

    @SerializedName("Title")
    @Column
    private String title;

    @SerializedName("Year")
    @Column
    private String year;

    @SerializedName("Rated")
    @Column
    private String rated;

    @SerializedName("Released")
    @Column
    private String released;

    @SerializedName("Runtime")
    @Column
    private String runtime;

    @SerializedName("Genre")
    @Column
    private String genre;

    @SerializedName("Director")
    @Column
    private String director;

    @SerializedName("Writer")
    @Column
    private String writer;

    @SerializedName("Actors")
    @Column
    private String actors;

    @SerializedName("Plot")
    @Column
    private String plot;

    @SerializedName("reyjoy")
    @Column
    private String reyjoy;

    @SerializedName("Language")
    @Column
    private String language;

    @SerializedName("Country")
    @Column
    private String country;

    @SerializedName("Awards")
    @Column
    private String awards;

    @SerializedName("Poster")
    @Column
    private String poster;

    @SerializedName("Metascore")
    @Column
    private String metascore;

    @SerializedName("imdbRating")
    @Column
    private String imdbRating;

    @SerializedName("imdbVotes")
    @Column
    private String imdbVotes;

    @SerializedName("Type")
    @Column
    private String type;

    @SerializedName("DVD")
    @Column
    private String dvd;

    @SerializedName("BoxOffice")
    @Column
    private String boxOffice;

    @SerializedName("Production")
    @Column
    private String production;

    @SerializedName("Website")
    @Column
    private String website;

    @SerializedName("Response")
    @Column
    private String response;

    @Column
    private boolean favorite;

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

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getReyjoy() {
        return reyjoy;
    }

    public void setReyjoy(String reyjoy) {
        this.reyjoy = reyjoy;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getMetascore() {
        return metascore;
    }

    public void setMetascore(String metascore) {
        this.metascore = metascore;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDvd() {
        return dvd;
    }

    public void setDvd(String dvd) {
        this.dvd = dvd;
    }

    public String getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(String boxOffice) {
        this.boxOffice = boxOffice;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
