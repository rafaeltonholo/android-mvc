package com.rtsystem.testapp.util;

/**
 * Created by rafaeltonholo on 02/01/2017.
 */

public final class Constants {
    public static final class Url {
        /**
         * Param: {@link com.tonholosolutions.testzup.model.entity.Movie#title title}.
         */
        public static final String SEARCH_MOVIE = "http://www.omdbapi.com/?s=%s&r=json&type=movie";
        /**
         * Param: {@link com.tonholosolutions.testzup.model.entity.Movie#imdbID imdbID}.
         */
        public static final String INFORMATION_MOVIE = "http://www.omdbapi.com/?i=%s&type=movie&tomatoes=true&r=json";
    }
}
