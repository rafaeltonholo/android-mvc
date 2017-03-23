package com.rtsystem.testapp.model.logic;

import android.os.AsyncTask;
import android.os.Message;

import com.rtsystem.androidmvc.util.HandlerEx;
import com.rtsystem.testapp.model.entity.Movie;
import com.rtsystem.testapp.model.repository.MovieRepository;

import java.util.List;

/**
 * Created by rafaeltonholo on 03/01/2017.
 */

public class MovieLogic {
    private static MovieLogic sInstance;
    private final MovieRepository mMovieRepository = MovieRepository.getInstance();

    public static MovieLogic getInstance() {
        synchronized (MovieLogic.class) {
            if (sInstance == null) {
                sInstance = new MovieLogic();
            }

            return sInstance;
        }
    }

    public List<Movie> getFavorites() {
        return mMovieRepository.readFavorites();
    }

    public Movie get(String imdbId) {
        return mMovieRepository.read(imdbId);
    }

    public void changeFavorite(Movie movie) {
        mMovieRepository.changeFavorite(movie, !movie.isFavorite());
    }

    public void search(final String title, final HandlerEx handler) {
        new AsyncTask<Object, Object, Message>() {
            /**
             * Override this method to perform a computation on a background thread. The
             * specified parameters are the parameters passed to {@link #execute}
             * by the caller of this task.
             * <p>
             * This method can call {@link #publishProgress} to publish updates
             * on the UI thread.
             *
             * @param params The parameters of the task.
             * @return A result, defined by the subclass of this task.
             * @see #onPreExecute()
             * @see #onPostExecute
             * @see #publishProgress
             */
            @Override
            protected Message doInBackground(Object... params) {
                final Message message = new Message();

                final List<Movie> movies = mMovieRepository.searchMovies(title);
                if (movies == null || movies.isEmpty()) {
                    message.what = HandlerEx.RESULT_MESSAGE_FAIL;
                } else {
                    message.what = HandlerEx.RESULT_MESSAGE_OK;
                }

                message.obj = movies;
                return message;
            }

            /**
             * <p>Runs on the UI thread after {@link #doInBackground}. The
             * specified result is the value returned by {@link #doInBackground}.</p>
             * <p>
             * <p>This method won't be invoked if the task was cancelled.</p>
             *
             * @param message The result of the operation computed by {@link #doInBackground}.
             * @see #onPreExecute
             * @see #doInBackground
             * @see #onCancelled(Object)
             */
            @Override
            protected void onPostExecute(Message message) {
                super.onPostExecute(message);
                handler.sendMessage(message);
            }
        }.execute();
    }
}
