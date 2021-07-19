package com.example.mediatheque.ui.music;

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

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MyViewHolder> {

    private final Context context;
    private final List<MusicModel> musicModelList;
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    public MusicAdapter(Context context, List<MusicModel> musicModelList){
        this.context = context;
        this.musicModelList = musicModelList;
    }

    public void updateDataMusic (int position){
        MusicModel getPositionListMusic = musicModelList.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("updateIdMusic",getPositionListMusic.getIdMusic());
        bundle.putString("updateTitleOfTheMusic",getPositionListMusic.getTextViewTitleOfTheMusic_cardView());
        bundle.putString("updateAlbumTitle",getPositionListMusic.getTextViewAlbumTitle_cardView());
        bundle.putString("updateNameOfTheArtist",getPositionListMusic.getTextViewNameOfTheArtist_cardView());
        bundle.putString("updateFirstNameOfTheArtist",getPositionListMusic.getTextViewFirstNameOfTheArtist_cardView());
        bundle.putString("updateGenreOfMusic",getPositionListMusic.getTextViewGenreOfMusic_cardView());
        bundle.putString("updateDurationOfTheMusic",getPositionListMusic.getTextViewDurationOfTheMusic_cardView());
        bundle.putString("updateNameOfTheLabel",getPositionListMusic.getTextViewNameOfTheLabel_cardView());
        bundle.putString("updateMusicReleaseDate",getPositionListMusic.getTextViewMusicReleaseDate_cardView());

        Intent intent = new Intent (context, MusicAddUpdate.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public void deleteDataMusic (int position){
        MusicModel getPositionListMusic = musicModelList.get(position);
        db.collection("musicCollection").document(getPositionListMusic.getIdMusic()).delete()
                .addOnCompleteListener(task -> {
                    Toast.makeText(context, "The music has been successfully deleted.", Toast.LENGTH_LONG).show();
                    musicModelList.remove(position);
                    this.notifyItemRemoved(position);
                    this.notifyItemRangeChanged(position,musicModelList.size());
                }).addOnFailureListener(e -> Toast.makeText(context, "Deleting music failed - Connection error with the database.", Toast.LENGTH_LONG).show());
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.music_cardview, parent, false);
        return new MusicAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MusicAdapter.MyViewHolder holder, int position) {
        holder.textViewTitleOfTheMusic_cardView.setText(musicModelList.get(position).getTextViewTitleOfTheMusic_cardView());
        holder.textViewAlbumTitle_cardView.setText(musicModelList.get(position).getTextViewAlbumTitle_cardView());
        holder.textViewNameOfTheArtist_cardView.setText(musicModelList.get(position).getTextViewNameOfTheArtist_cardView());
        holder.textViewFirstNameOfTheArtist_cardView.setText(musicModelList.get(position).getTextViewFirstNameOfTheArtist_cardView());
        holder.textViewGenreOfMusic_cardView.setText(musicModelList.get(position).getTextViewGenreOfMusic_cardView());
        holder.textViewDurationOfTheMusic_cardView.setText(String.format("%s seconds", musicModelList.get(position).getTextViewDurationOfTheMusic_cardView()));
        holder.textViewNameOfTheLabel_cardView.setText(musicModelList.get(position).getTextViewNameOfTheLabel_cardView());
        holder.textViewMusicReleaseDate_cardView.setText(musicModelList.get(position).getTextViewMusicReleaseDate_cardView());
    }

    @Override
    public int getItemCount() {
        return musicModelList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        final TextView textViewTitleOfTheMusic_cardView;
        final TextView textViewAlbumTitle_cardView;
        final TextView textViewNameOfTheArtist_cardView;
        final TextView textViewFirstNameOfTheArtist_cardView;
        final TextView textViewGenreOfMusic_cardView;
        final TextView textViewDurationOfTheMusic_cardView;
        final TextView textViewNameOfTheLabel_cardView;
        final TextView textViewMusicReleaseDate_cardView;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            textViewTitleOfTheMusic_cardView = itemView.findViewById(R.id.textViewTitleOfTheMusic_cardView);
            textViewAlbumTitle_cardView = itemView.findViewById(R.id.textViewAlbumTitle_cardView);
            textViewNameOfTheArtist_cardView = itemView.findViewById(R.id.textViewNameOfTheArtist_cardView);
            textViewFirstNameOfTheArtist_cardView = itemView.findViewById(R.id.textViewFirstNameOfTheArtist_cardView);
            textViewGenreOfMusic_cardView = itemView.findViewById(R.id.textViewGenreOfMusic_cardView);
            textViewDurationOfTheMusic_cardView = itemView.findViewById(R.id.textViewDurationOfTheMusic_cardView);
            textViewNameOfTheLabel_cardView = itemView.findViewById(R.id.textViewNameOfTheLabel_cardView);
            textViewMusicReleaseDate_cardView = itemView.findViewById(R.id.textViewMusicReleaseDate_cardView);
        }
    }

}
