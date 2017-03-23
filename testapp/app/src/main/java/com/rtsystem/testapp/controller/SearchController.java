package com.rtsystem.testapp.controller;

import android.os.Message;

import com.rtsystem.androidmvc.controller.AbstractController;
import com.rtsystem.androidmvc.router.Parameter;
import com.rtsystem.androidmvc.controller.annontation.Controller;
import com.rtsystem.androidmvc.util.HandlerEx;
import com.rtsystem.testapp.model.entity.Movie;
import com.rtsystem.testapp.model.logic.MovieLogic;
import com.rtsystem.testapp.util.MvcActionUtil;
import com.rtsystem.testapp.util.ParameterUtil;
import com.rtsystem.testapp.view.activity.SearchActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafaeltonholo on 05/01/2017.
 */
@Controller(SearchActivity.class)
public class SearchController extends AbstractController<List<Movie>> {
    private static SearchController sInstance;

    public static SearchController getInstance() {
        synchronized (SearchController.class) {
            if (sInstance == null) {
                sInstance = new SearchController();
            }

            return sInstance;
        }
    }

    @Override
    protected void onInitialize(Parameter... params) {
        model(new ArrayList<Movie>());
    }

    @Override
    protected void onMessageReceive(String action, Parameter... params) {
        switch (action) {
            case MvcActionUtil.MORE_INFO_MOVIE:
                Movie movie = (Movie) params[0].getValue();
                route(MovieInformationController.class, new Parameter(ParameterUtil.MovieInformation.MOVIE_IMDB_ID, movie.getImdbID()));
                break;
            case MvcActionUtil.Search.LOOK_FOR_TITLE:
                final String query = (String) params[0].getValue();
                lookForMovie(query);
                break;
            case MvcActionUtil.FAVORITE_UNFAVORITE_MOVIE:
                favoriteMovie(params);
                break;
        }
    }

    private void favoriteMovie(Parameter... params) {
        final Movie movie = (Movie) params[0].getValue();
        MovieLogic.getInstance().changeFavorite(movie);
        notifyView(MvcActionUtil.FAVORITE_UNFAVORITE_MOVIE);
    }

    private void lookForMovie(final String query) {
        notifyView(MvcActionUtil.Search.START_LOOKING);
        MovieLogic.getInstance().search(query, new HandlerEx(new HandlerEx.OnHandleMessageListener() {
            @Override
            public void onHandleMessage(Message message) {
                //noinspection unchecked
                model((List<Movie>) message.obj);
                final Parameter result = new Parameter(ParameterUtil.Search.RESULT_SEARCH, message.what == HandlerEx.RESULT_MESSAGE_OK);
                final Parameter title = new Parameter(ParameterUtil.Search.MOVIE_TITLE, query);
                notifyView(MvcActionUtil.Search.END_LOOKING, result, title);
            }
        }));
    }
}
