package com.example.mediatheque.ui.movie;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

public class MovieTouchHelper extends ItemTouchHelper.SimpleCallback {

    private final MovieAdapter movieAdapter;

    public MovieTouchHelper(MovieAdapter movieAdapter) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.movieAdapter = movieAdapter;
    }

    @Override
    public boolean onMove(@NonNull @NotNull RecyclerView recyclerView, @NonNull @NotNull RecyclerView.ViewHolder viewHolder, @NonNull @NotNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull @NotNull RecyclerView.ViewHolder viewHolder, int direction) {
        final int position = viewHolder.getAbsoluteAdapterPosition();

        if (direction == ItemTouchHelper.LEFT){
            movieAdapter.updateDataMovie(position);
            movieAdapter.notifyDataSetChanged();
        }else{
            movieAdapter.deleteDataMovie(position);
        }

    }
}
