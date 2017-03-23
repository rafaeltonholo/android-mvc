package com.rtsystem.testapp.view.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.rtsystem.androidmvc.controller.Message;
import com.rtsystem.androidmvc.router.Parameter;
import com.rtsystem.androidmvc.view.activity.AbstractActivityView;
import com.rtsystem.testapp.R;
import com.rtsystem.testapp.model.entity.Movie;
import com.rtsystem.testapp.util.MvcActionUtil;
import com.rtsystem.testapp.util.ParameterUtil;
import com.rtsystem.testapp.view.adapter.MovieAdapter;

import java.util.List;
import java.util.Observable;

public class MainActivity extends AbstractActivityView<List<Movie>> {
    private TextView mTxtNenhumFilme;
    private RecyclerView mRcvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDefaultDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }

        mTxtNenhumFilme = (TextView) findViewById(R.id.txt_movie_not_found);
        mRcvList = (RecyclerView) findViewById(R.id.rcv_list);
        mRcvList.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifyController(MvcActionUtil.MainActivity.SEARCH);
            }
        });

        updateFavorites();
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
            case MvcActionUtil.MainActivity.UPDATE_FAVORITES:
                updateFavorites();
                break;
        }
    }

    private void updateFavorites() {
        if (model() == null || model().isEmpty()) {
            mTxtNenhumFilme.setVisibility(View.VISIBLE);
            mRcvList.setVisibility(View.GONE);
        } else {
            mTxtNenhumFilme.setVisibility(View.GONE);
            mRcvList.setVisibility(View.VISIBLE);
        }

        MovieAdapter adapter = new MovieAdapter(this, model(), new MovieAdapter.OnItemClickListener() {
            @Override
            public void onClick(Movie movie) {
                final Parameter parameter = new Parameter(ParameterUtil.MOVIE, movie);
                notifyController(MvcActionUtil.MORE_INFO_MOVIE, parameter);
            }
        }, new MovieAdapter.OnFavoriteClickListener() {
            @Override
            public void onFavoriteClick(Movie movie) {
                final Parameter parameter = new Parameter(ParameterUtil.MOVIE, movie);
                notifyController(MvcActionUtil.FAVORITE_UNFAVORITE_MOVIE, parameter);
            }
        });
        mRcvList.setAdapter(adapter);
    }
}
