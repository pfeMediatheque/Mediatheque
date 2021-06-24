package com.example.mediatheque.ui.book;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediatheque.R;

import org.jetbrains.annotations.NotNull;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {

    private Context context;
    private final List<BookModel> bookModelList;

    public BookAdapter(Context context, List<BookModel> bookModelList){
        this.context = context;
        this.bookModelList = bookModelList;
    }

    public void updateDataBook (int position){
        BookModel getPositionList = bookModelList.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("updateIdBook",getPositionList.getIdBook());
        bundle.putString("updateTitleOfTheBook",getPositionList.getTextViewTitleOfTheBook_cardView());
        bundle.putString("updateNameOfTheAuthor",getPositionList.getTextViewNameOfTheAuthor_cardView());
        bundle.putString("updateFirstNameOfTheAuthor",getPositionList.getTextViewFirstNameOfTheAuthor_cardView());
        bundle.putString("updateTypeOfTheBook",getPositionList.getTextViewTypeOfTheBook_cardView());
        bundle.putString("updateNumberOfPages",getPositionList.getTextViewNumberOfPages_cardView());
        bundle.putString("updatePublishersName",getPositionList.getTextViewPublishersName_cardView());
        bundle.putString("updateDateOfPublication",getPositionList.getTextViewDateOfPublication_cardView());

        Intent intent = new Intent (context, BookAddUpdate.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
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

        TextView textViewTitleOfTheBook_cardView, textViewNameOfTheAuthor_cardView, textViewFirstNameOfTheAuthor_cardView, textViewTypeOfTheBook_cardView, textViewNumberOfPages_cardView, textViewPublishersName_cardView, textViewDateOfPublication_cardView;

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
