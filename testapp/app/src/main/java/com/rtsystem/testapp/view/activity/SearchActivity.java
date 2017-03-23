package com.rtsystem.testapp.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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

public class SearchActivity extends AbstractActivityView<List<Movie>> {
    private static final String TAG = SearchActivity.class.getSimpleName();
    private ProgressDialog mProgressDialog;
    private RecyclerView mRcvList;
    private TextView mTxtMovieNotFound;
    private MovieAdapter mMovieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_activity_search);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDefaultDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
        mTxtMovieNotFound = (TextView) findViewById(R.id.txt_movie_not_found);
        mRcvList = (RecyclerView) findViewById(R.id.rcv_list);
        mRcvList.setLayoutManager(new LinearLayoutManager(this));
        mMovieAdapter = new MovieAdapter(this, model(), new MovieAdapter.OnItemClickListener() {
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
        mRcvList.setAdapter(mMovieAdapter);
        changeVisibility();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                boolean retorno = false;
                if (!TextUtils.isEmpty(query)) {
                    notifyController(MvcActionUtil.Search.LOOK_FOR_TITLE, new Parameter("", query));
                    retorno = true;
                }

                return retorno;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
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
            case MvcActionUtil.Search.START_LOOKING:
                showProgress();
                break;
            case MvcActionUtil.Search.END_LOOKING:
                finishSearch(message);
                break;
            case MvcActionUtil.FAVORITE_UNFAVORITE_MOVIE:
                /*Snackbar.make(mFabFavorite, String.format("O filme %s foi %s favorito", model.getTitle(), model.isFavorite() ?
                        "marcado" : "desmarcado"), Snackbar.LENGTH_SHORT).show();*/
                break;
        }
    }

    private void finishSearch(Message message) {
        Log.d(TAG, "message: " + message.toString());
        Log.d(TAG, "model: " + model());
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }

        final boolean result = (boolean) message.get(ParameterUtil.Search.RESULT_SEARCH);
        final String title = (String) message.get(ParameterUtil.Search.MOVIE_TITLE);
        if (result) {
            Snackbar.make(mRcvList, getString(R.string.show_results, title), Snackbar.LENGTH_SHORT).show();
            mMovieAdapter.setItems(model());
        }

        changeVisibility();
    }

    private void showProgress() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setTitle("Pesquisando pelo titulo");
        mProgressDialog.setMessage("Aguarde...");
        mProgressDialog.setCancelable(false);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();
    }

    private void changeVisibility() {
        if (model() == null || model().isEmpty()) {
            mTxtMovieNotFound.setVisibility(View.VISIBLE);
            mRcvList.setVisibility(View.GONE);
        } else {
            mTxtMovieNotFound.setVisibility(View.GONE);
            mRcvList.setVisibility(View.VISIBLE);
        }
    }
}
