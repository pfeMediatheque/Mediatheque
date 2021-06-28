package com.example.mediatheque.ui.movie;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mediatheque.R;
import com.google.firebase.firestore.FirebaseFirestore;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    private final Context context;
    private final List<MovieModel> movieModelList;
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    public MovieAdapter(Context context, List<MovieModel> movieModelList){
        this.context = context;
        this.movieModelList = movieModelList;
    }

    public void updateDataMovie (int position){
        MovieModel getPositionListMovie = movieModelList.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("updateIdMovie",getPositionListMovie.getIdMovie());
        bundle.putString("updateTitleOfTheMovie",getPositionListMovie.getTextViewTitleOfTheMovie_cardView());
        bundle.putString("updateNameOfTheDirector",getPositionListMovie.getTextViewNameOfTheDirector_cardView());
        bundle.putString("updateFirstNameOfTheDirector",getPositionListMovie.getTextViewFirstNameOfTheDirector_cardView());
        bundle.putString("updateTypeOfTheMovie",getPositionListMovie.getTextViewTypeOfTheMovie_cardView());
        bundle.putString("updateDurationOfTheMovie",getPositionListMovie.getTextViewDurationOfTheMovie_cardView());
        bundle.putString("updateProductionCompanies",getPositionListMovie.getTextViewProductionCompanies_cardView());
        bundle.putString("updateReleaseDate",getPositionListMovie.getTextViewReleaseDate_cardView());

        Intent intent = new Intent (context, MovieAddUpdate.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public void deleteDataMovie (int position){
        MovieModel getPositionListMovie = movieModelList.get(position);
        db.collection("movieCollection").document(getPositionListMovie.getIdMovie()).delete()
                .addOnCompleteListener(task -> {
                    Toast.makeText(context, "The movie has been successfully deleted.", Toast.LENGTH_LONG).show();
                    movieModelList.remove(position);
                }).addOnFailureListener(e -> Toast.makeText(context, "Deleting movie failed - Connection error with the database.", Toast.LENGTH_LONG).show());
    }

    @NonNull
    @NotNull
    @Override
    public MovieAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_cardview, parent, false);
        return new MovieAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MovieAdapter.MyViewHolder holder, int position) {
        holder.textViewTitleOfTheMovie_cardView.setText(movieModelList.get(position).getTextViewTitleOfTheMovie_cardView());
        holder.textViewNameOfTheDirector_cardView.setText(movieModelList.get(position).getTextViewNameOfTheDirector_cardView());
        holder.textViewFirstNameOfTheDirector_cardView.setText(movieModelList.get(position).getTextViewFirstNameOfTheDirector_cardView());
        holder.textViewTypeOfTheMovie_cardView.setText(movieModelList.get(position).getTextViewTypeOfTheMovie_cardView());
        holder.textViewDurationOfTheMovie_cardView.setText(String.format("%s minutes", movieModelList.get(position).getTextViewDurationOfTheMovie_cardView()));
        holder.textViewProductionCompanies_cardView.setText(movieModelList.get(position).getTextViewProductionCompanies_cardView());
        holder.textViewReleaseDate_cardView.setText(movieModelList.get(position).getTextViewReleaseDate_cardView());
    }

    @Override
    public int getItemCount() {
        return movieModelList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        final TextView textViewTitleOfTheMovie_cardView;
        final TextView textViewNameOfTheDirector_cardView;
        final TextView textViewFirstNameOfTheDirector_cardView;
        final TextView textViewTypeOfTheMovie_cardView;
        final TextView textViewDurationOfTheMovie_cardView;
        final TextView textViewProductionCompanies_cardView;
        final TextView textViewReleaseDate_cardView;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            textViewTitleOfTheMovie_cardView = itemView.findViewById(R.id.textViewTitleOfTheMovie_cardView);
            textViewNameOfTheDirector_cardView = itemView.findViewById(R.id.textViewNameOfTheDirector_cardView);
            textViewFirstNameOfTheDirector_cardView = itemView.findViewById(R.id.textViewFirstNameOfTheDirector_cardView);
            textViewTypeOfTheMovie_cardView = itemView.findViewById(R.id.textViewTypeOfTheMovie_cardView);
            textViewDurationOfTheMovie_cardView = itemView.findViewById(R.id.textViewDurationOfTheMovie_cardView);
            textViewProductionCompanies_cardView = itemView.findViewById(R.id.textViewProductionCompanies_cardView);
            textViewReleaseDate_cardView = itemView.findViewById(R.id.textViewReleaseDate_cardView);
        }
    }
}
