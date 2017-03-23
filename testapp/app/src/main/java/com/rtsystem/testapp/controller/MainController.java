package com.rtsystem.testapp.controller;

import com.rtsystem.androidmvc.controller.AbstractController;
import com.rtsystem.androidmvc.controller.annontation.Controller;
import com.rtsystem.androidmvc.router.Parameter;
import com.rtsystem.testapp.model.entity.Movie;
import com.rtsystem.testapp.model.logic.MovieLogic;
import com.rtsystem.testapp.util.MvcActionUtil;
import com.rtsystem.testapp.util.ParameterUtil;
import com.rtsystem.testapp.view.activity.MainActivity;

import java.util.List;

/**
 * Created by rafaeltonholo on 03/01/2017.
 */
@Controller(MainActivity.class)
public class MainController extends AbstractController<List<Movie>> {
    private static final int REQUEST_CODE = 441;
    private static MainController sInstance;
    private final MovieLogic mMovieLogic;

    public MainController() {
        mMovieLogic = MovieLogic.getInstance();
    }

    public static MainController getInstance() {
        synchronized (MainController.class) {
            if (sInstance == null) {
                sInstance = new MainController();
            }

            return sInstance;
        }
    }

    @Override
    protected void onInitialize(Parameter... params) {
        model(mMovieLogic.getFavorites());
    }

    @Override
    protected void onMessageReceive(String action, Parameter... params) {
        switch (action) {
            case MvcActionUtil.MORE_INFO_MOVIE: {
                Movie movie = (Movie) params[0].getValue();
                route(MovieInformationController.class, new Parameter(ParameterUtil.MovieInformation.MOVIE_IMDB_ID, movie.getImdbID()));
                break;
            }
            case MvcActionUtil.FAVORITE_UNFAVORITE_MOVIE: {
                Movie movie = (Movie) params[0].getValue();
                mMovieLogic.changeFavorite(movie);
                updateFavorites();
                break;
            }
            case MvcActionUtil.MainActivity.SEARCH:
                route(SearchController.class);
                break;
        }
    }

    /**
     * If the Controller navigates to another view and needs an result back, its will be called.
     */
    @Override
    public void onBackWithResult(int requestCode, int resultCode) {
        super.onBackWithResult(requestCode, resultCode);
        if (requestCode == REQUEST_CODE) {
            model(mMovieLogic.getFavorites());
            updateFavorites();
        }
    }

    private void updateFavorites() {
        notifyView(MvcActionUtil.MainActivity.UPDATE_FAVORITES);
    }
}
