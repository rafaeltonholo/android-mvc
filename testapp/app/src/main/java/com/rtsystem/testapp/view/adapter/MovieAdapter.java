package com.rtsystem.testapp.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rtsystem.testapp.R;
import com.rtsystem.testapp.model.entity.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by rafaeltonholo on 03/01/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private final Context mContext;
    private List<Movie> mItems;
    private OnItemClickListener mOnItemClickListener;
    private OnFavoriteClickListener mOnFavoriteClickListener;

    public MovieAdapter(Context context, List<Movie> items, OnItemClickListener onItemClickListener,
                        OnFavoriteClickListener onFavoriteClickListener) {
        mContext = context;
        mItems = items;
        mOnItemClickListener = onItemClickListener;
        mOnFavoriteClickListener = onFavoriteClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.movie_list_item, parent, false));
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Movie movie = mItems.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onClick(movie);
                }
            }
        });
        Picasso.with(mContext).load(movie.getPoster()).into(holder.imgMovie);
        holder.txtTitle.setText(movie.getTitle());
        //                  01 Jan 2013 - Documentary
        final String desc = String.format("%s - %s", movie.getReleased(), movie.getGenre());
        holder.txtDesc.setText(desc);
        changeFavoriteIcon(holder, movie);
        holder.imgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnFavoriteClickListener != null) {
                    mOnFavoriteClickListener.onFavoriteClick(movie);
                    changeFavoriteIcon(holder, movie);

                }
            }
        });
    }

    private void changeFavoriteIcon(ViewHolder holder, Movie movie) {
        holder.imgFavorite.setImageResource(movie.isFavorite() ? R.drawable.ic_favorite_grey_700_24dp
                : R.drawable.ic_favorite_border_grey_700_24dp);
    }

    public void setItems(List<Movie> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public interface OnItemClickListener {
        void onClick(Movie movie);
    }

    public interface OnFavoriteClickListener {
        void onFavoriteClick(Movie movie);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgMovie;
        private TextView txtTitle;
        private TextView txtDesc;
        private ImageView imgFavorite;

        ViewHolder(View itemView) {
            super(itemView);
            imgMovie = (ImageView) itemView.findViewById(R.id.img_movie);
            txtTitle = (TextView) itemView.findViewById(R.id.txt_title);
            txtDesc = (TextView) itemView.findViewById(R.id.txt_desc);
            imgFavorite = (ImageView) itemView.findViewById(R.id.img_favorite);
        }
    }
}
