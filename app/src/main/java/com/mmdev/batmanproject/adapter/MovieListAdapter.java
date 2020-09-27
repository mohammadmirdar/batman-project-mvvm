package com.mmdev.batmanproject.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.mmdev.batmanproject.R;
import com.mmdev.batmanproject.persistence.BatmanData;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {

    List<BatmanData> batmanData = new ArrayList<>();
    @Inject
    RequestManager requestManager;

    @Inject
    public MovieListAdapter() {
    }

    public void setBatmanData(List<BatmanData> batmanData) {
        this.batmanData = batmanData;
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
        BatmanData batman = batmanData.get(position);
        holder.txtMovieTitle.setText(batman.getTitle());

        requestManager
                .load(batman.getPoster_url())
                .into(holder.imgMovieCover);
    }

    @Override
    public int getItemCount() {
        return batmanData.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgMovieCover;
        private TextView txtMovieTitle;
        public MovieViewHolder(@NonNull View view) {
            super(view);

            imgMovieCover = view.findViewById(R.id.imgMovieCover);
            txtMovieTitle = view.findViewById(R.id.txtMovieTitle);
        }
    }
}
