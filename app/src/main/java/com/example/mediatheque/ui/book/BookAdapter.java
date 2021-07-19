package com.example.mediatheque.ui.book;

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

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {

    private final Context context;
    private final List<BookModel> bookModelList;
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    public BookAdapter(Context context, List<BookModel> bookModelList){
        this.context = context;
        this.bookModelList = bookModelList;
    }

    public void updateDataBook (int position){
        BookModel getPositionListBook = bookModelList.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("updateIdBook",getPositionListBook.getIdBook());
        bundle.putString("updateTitleOfTheBook",getPositionListBook.getTextViewTitleOfTheBook_cardView());
        bundle.putString("updateNameOfTheAuthor",getPositionListBook.getTextViewNameOfTheAuthor_cardView());
        bundle.putString("updateFirstNameOfTheAuthor",getPositionListBook.getTextViewFirstNameOfTheAuthor_cardView());
        bundle.putString("updateTypeOfTheBook",getPositionListBook.getTextViewTypeOfTheBook_cardView());
        bundle.putString("updateNumberOfPages",getPositionListBook.getTextViewNumberOfPages_cardView());
        bundle.putString("updatePublishersName",getPositionListBook.getTextViewPublishersName_cardView());
        bundle.putString("updateDateOfPublication",getPositionListBook.getTextViewDateOfPublication_cardView());

        Intent intent = new Intent (context, BookAddUpdate.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public void deleteDataBook (int position){
        BookModel getPositionListBook = bookModelList.get(position);
        db.collection("bookCollection").document(getPositionListBook.getIdBook()).delete()
                .addOnCompleteListener(task -> {
                    Toast.makeText(context, "The book has been successfully deleted.", Toast.LENGTH_LONG).show();
                    bookModelList.remove(position);
                    this.notifyItemRemoved(position);
                    this.notifyItemRangeChanged(position,bookModelList.size());
                }).addOnFailureListener(e -> Toast.makeText(context, "Deleting book failed - Connection error with the database.", Toast.LENGTH_LONG).show());
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_cardview, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {
        holder.textViewTitleOfTheBook_cardView.setText(bookModelList.get(position).getTextViewTitleOfTheBook_cardView());
        holder.textViewNameOfTheAuthor_cardView.setText(bookModelList.get(position).getTextViewNameOfTheAuthor_cardView());
        holder.textViewFirstNameOfTheAuthor_cardView.setText(bookModelList.get(position).getTextViewFirstNameOfTheAuthor_cardView());
        holder.textViewTypeOfTheBook_cardView.setText(bookModelList.get(position).getTextViewTypeOfTheBook_cardView());
        holder.textViewNumberOfPages_cardView.setText(String.format("%s pages", bookModelList.get(position).getTextViewNumberOfPages_cardView()));
        holder.textViewPublishersName_cardView.setText(bookModelList.get(position).getTextViewPublishersName_cardView());
        holder.textViewDateOfPublication_cardView.setText(bookModelList.get(position).getTextViewDateOfPublication_cardView());
    }

    @Override
    public int getItemCount() {
        return bookModelList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        final TextView textViewTitleOfTheBook_cardView;
        final TextView textViewNameOfTheAuthor_cardView;
        final TextView textViewFirstNameOfTheAuthor_cardView;
        final TextView textViewTypeOfTheBook_cardView;
        final TextView textViewNumberOfPages_cardView;
        final TextView textViewPublishersName_cardView;
        final TextView textViewDateOfPublication_cardView;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            textViewTitleOfTheBook_cardView = itemView.findViewById(R.id.textViewTitleOfTheBook_cardView);
            textViewNameOfTheAuthor_cardView = itemView.findViewById(R.id.textViewNameOfTheAuthor_cardView);
            textViewFirstNameOfTheAuthor_cardView = itemView.findViewById(R.id.textViewFirstNameOfTheAuthor_cardView);
            textViewTypeOfTheBook_cardView = itemView.findViewById(R.id.textViewTypeOfTheBook_cardView);
            textViewNumberOfPages_cardView = itemView.findViewById(R.id.textViewNumberOfPages_cardView);
            textViewPublishersName_cardView = itemView.findViewById(R.id.textViewPublishersName_cardView);
            textViewDateOfPublication_cardView = itemView.findViewById(R.id.textViewDateOfPublication_cardView);
        }
    }
}
