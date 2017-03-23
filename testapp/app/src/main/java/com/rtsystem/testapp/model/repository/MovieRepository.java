package com.rtsystem.testapp.model.repository;

import android.util.Log;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.rtsystem.testapp.model.dto.SearchDto;
import com.rtsystem.testapp.model.entity.Movie;
import com.rtsystem.testapp.model.entity.Movie_Table;
import com.rtsystem.testapp.util.Constants;
import com.rtsystem.testapp.util.JsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.raizlabs.android.dbflow.sql.language.SQLite.select;

/**
 * Created by rafaeltonholo on 02/01/2017.
 */

public class MovieRepository {
    private final static String TAG = MovieRepository.class.getSimpleName();
    private static MovieRepository sInstance;

    public static MovieRepository getInstance() {
        synchronized (MovieRepository.class) {
            if (sInstance == null) {
                sInstance = new MovieRepository();
            }

            return sInstance;
        }
    }

    public void clear() {
        FlowManager.getModelAdapter(Movie.class).deleteAll(select().from(Movie.class).queryList());
    }

    public List<Movie> searchMovies(final String name) {
        List<Movie> movies = new ArrayList<>();
        final OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(String.format(Constants.Url.SEARCH_MOVIE, name))
                .build();

        try {
            Response response = client.newCall(request).execute();
            final SearchDto tempObj = JsonParser.toObject(response.body().string(), SearchDto.class);
            Log.d(TAG, "totalResults: " + tempObj.getTotalResults());
            if (tempObj.isResponse()) {
                movies.addAll(tempObj.getSearch());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!movies.isEmpty()) {
            final List<Movie> toSaveList = new ArrayList<>();
            final List<Movie> list = select().from(Movie.class).where(Movie_Table.title.like("%" + name + "%")).queryList();

            for (Movie movie : movies) {
                final Movie toSave = read(movie);

                for (Movie dbMovie : list) {
                    if (dbMovie.getImdbID().equals(toSave.getImdbID())) {
                        String poster = dbMovie.getPoster();
                        poster = poster.replace(".V1_SX300", "");
                        toSave.setPoster(poster);
                        toSave.setFavorite(dbMovie.isFavorite());
                        break;
                    }
                }

                toSaveList.add(toSave);
            }

            FlowManager.getModelAdapter(Movie.class).saveAll(toSaveList);
            movies = toSaveList;
        }

        return movies;
    }

    private Movie read(Movie movie) {
        final String imdbId = movie.getImdbID();
        final OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(String.format(Constants.Url.INFORMATION_MOVIE, imdbId))
                .build();

        try {
            Response response = client.newCall(request).execute();
            String json = response.body().string();
            Log.d(TAG, "response.body().string: " + json);
            final Movie imdbMovie = JsonParser.toObject(json, Movie.class);
            imdbMovie.setFavorite(movie.isFavorite());
            movie = imdbMovie;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return movie;
    }

    public Movie read(final String imdbId) {
        Movie movie = select().from(Movie.class).where(Movie_Table.imdbID.eq(imdbId)).querySingle();
        //read(movie);
        return movie;
    }

    public List<Movie> readAll() {
        return select().from(Movie.class).queryList();
    }

    public List<Movie> readFavorites() {
        return select().from(Movie.class).where(Movie_Table.favorite.eq(true)).queryList();
    }

    public void changeFavorite(Movie movie, boolean fav) {
        movie.setFavorite(fav);
        FlowManager.getModelAdapter(Movie.class).save(movie);
    }
}
