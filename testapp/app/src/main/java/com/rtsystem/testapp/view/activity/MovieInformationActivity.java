package com.rtsystem.testapp.view.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rtsystem.androidmvc.controller.Message;
import com.rtsystem.androidmvc.view.activity.AbstractActivityView;
import com.rtsystem.testapp.R;
import com.rtsystem.testapp.model.entity.Movie;
import com.rtsystem.testapp.util.MvcActionUtil;
import com.squareup.picasso.Picasso;

import java.util.Observable;

public class MovieInformationActivity extends AbstractActivityView<Movie> {
    private FloatingActionButton mFabFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_information);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_activity_movie_information);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDefaultDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }

        mFabFavorite = (FloatingActionButton) findViewById(R.id.fab_favorite);
        mFabFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifyController(MvcActionUtil.FAVORITE_UNFAVORITE_MOVIE);
            }
        });
        changeFabIcon();

        init();
    }

    private void init() {
        final ImageView imgMovie = (ImageView) findViewById(R.id.img_movie);
        Picasso.with(this).load(model().getPoster()).into(imgMovie);
        final TextView txtTitle = (TextView) findViewById(R.id.txt_title);
        txtTitle.setText(model().getTitle());
        final TextView txtSubtitle = (TextView) findViewById(R.id.txt_subtitle);
        txtSubtitle.setText(getString(R.string.movie_info_subtitle_format, model().getRated(), model().getRuntime(), model().getGenre(),
                model().getReleased(), model().getCountry()));
        final TextView txtPlot = (TextView) findViewById(R.id.txt_plot);
        txtPlot.setText(model().getPlot());
        final TextView txtDirector = (TextView) findViewById(R.id.txt_director);
        txtDirector.setText(model().getDirector());
        final TextView txtWriter = (TextView) findViewById(R.id.txt_writer);
        txtWriter.setText(model().getWriter());
        final TextView txtActors = (TextView) findViewById(R.id.txt_actors);
        txtActors.setText(model().getActors());
        final TextView txtUserRate = (TextView) findViewById(R.id.txt_user_rate);
        txtUserRate.setText(getString(R.string.user_rate, model().getImdbRating()));
    }

    private void changeFabIcon() {
        mFabFavorite.setImageResource(model().isFavorite() ? R.drawable.ic_favorite_white_24dp
                : R.drawable.ic_favorite_border_white_24dp);
    }

    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     */
    @Override
    public void update(Observable o, Object arg) {
        final Message message = (Message) arg;
        switch (message.getAction()) {
            case MvcActionUtil.FAVORITE_UNFAVORITE_MOVIE:
                changeFabIcon();
                Snackbar.make(mFabFavorite, String.format("O filme %s foi %s favorito", model().getTitle(), model().isFavorite() ?
                        "marcado" : "desmarcado"), Snackbar.LENGTH_SHORT).show();
                break;
        }
    }
}
