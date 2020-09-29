package com.mmdev.batmanproject.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.mmdev.batmanproject.R;
import com.mmdev.batmanproject.persistence.MovieData;
import com.mmdev.batmanproject.view.MovieDetailActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {

    List<MovieData> movieData = new ArrayList<>();
    @Inject
    RequestManager requestManager;

    @Inject
    public MovieListAdapter() {
    }

    public void setMovieData(List<MovieData> movieData) {
        this.movieData = movieData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movies_list_item,parent,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        MovieData batman = movieData.get(position);
        holder.txtMovieTitle.setText(batman.getTitle());
        requestManager
                .load(batman.getPoster_url())
                .into(holder.imgMovieCover);
        holder.cardViewContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.cardViewContainer.getContext(), MovieDetailActivity.class);
                intent.putExtra("imdbId", batman.getImdbId());
                holder.cardViewContainer.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieData.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgMovieCover;
        private TextView txtMovieTitle;
        private CardView cardViewContainer;
        public MovieViewHolder(@NonNull View view) {
            super(view);

            imgMovieCover = view.findViewById(R.id.imgMovieCover);
            txtMovieTitle = view.findViewById(R.id.txtMovieTitle);
            cardViewContainer = view.findViewById(R.id.cardViewContainer);
        }
    }
}
