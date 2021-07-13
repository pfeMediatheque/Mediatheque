package com.example.mediatheque.ui.music;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

public class MusicTouchHelper extends ItemTouchHelper.SimpleCallback{

    private final MusicAdapter musicAdapter;

    public MusicTouchHelper(MusicAdapter musicAdapter) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.musicAdapter = musicAdapter;
    }

    @Override
    public boolean onMove(@NonNull @NotNull RecyclerView recyclerView, @NonNull @NotNull RecyclerView.ViewHolder viewHolder, @NonNull @NotNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull @NotNull RecyclerView.ViewHolder viewHolder, int direction) {
        final int position = viewHolder.getAbsoluteAdapterPosition();

        if (direction == ItemTouchHelper.LEFT){
            musicAdapter.updateDataMusic(position);
            musicAdapter.notifyDataSetChanged();
        }else{
            musicAdapter.deleteDataMusic(position);
        }

    }
}
