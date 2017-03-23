package com.rtsystem.testapp.controller;


import com.rtsystem.androidmvc.controller.AbstractController;
import com.rtsystem.androidmvc.router.Parameter;
import com.rtsystem.androidmvc.controller.annontation.Controller;
import com.rtsystem.testapp.model.entity.Movie;
import com.rtsystem.testapp.model.logic.MovieLogic;
import com.rtsystem.testapp.util.MvcActionUtil;
import com.rtsystem.testapp.view.activity.MovieInformationActivity;

/**
 * Created by rafaeltonholo on 03/01/2017.
 */
@Controller(MovieInformationActivity.class)
public class MovieInformationController extends AbstractController<Movie> {

    private static MovieInformationController sInstance;

    public static MovieInformationController getInstance() {
        synchronized (MovieInformationController.class) {
            if (sInstance == null) {
                sInstance = new MovieInformationController();
            }

            return sInstance;
        }
    }

    @Override
    protected void onInitialize(Parameter... params) {
        String imdbId = (String) params[0].getValue();
        model(MovieLogic.getInstance().get(imdbId));
    }

    @Override
    protected void onMessageReceive(String action, Parameter... params) {
        switch (action) {
            case MvcActionUtil.FAVORITE_UNFAVORITE_MOVIE:
                MovieLogic.getInstance().changeFavorite(model());
                notifyView(MvcActionUtil.FAVORITE_UNFAVORITE_MOVIE);
                break;
        }
    }
}
