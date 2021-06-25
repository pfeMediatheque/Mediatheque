package com.example.mediatheque.ui.book;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediatheque.ui.book.BookAdapter;

import org.jetbrains.annotations.NotNull;

public class BookTouchHelper extends ItemTouchHelper.SimpleCallback {

    private BookAdapter bookAdapter;

    public BookTouchHelper(BookAdapter bookAdapter) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.bookAdapter = bookAdapter;
    }

    @Override
    public boolean onMove(@NonNull @NotNull RecyclerView recyclerView, @NonNull @NotNull RecyclerView.ViewHolder viewHolder, @NonNull @NotNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull @NotNull RecyclerView.ViewHolder viewHolder, int direction) {
        final int position = viewHolder.getAbsoluteAdapterPosition();

        if (direction == ItemTouchHelper.LEFT){
            bookAdapter.updateDataBook(position);
            bookAdapter.notifyDataSetChanged();
        }else{

        }

    }
}
