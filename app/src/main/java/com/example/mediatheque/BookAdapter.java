package com.example.mediatheque;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {

    private final ShowActivity activity;
    private final List<BookModel> mList;

    public BookAdapter(ShowActivity activity, List<BookModel> mList){
        this.activity = activity;
        this.mList = mList;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(R.layout.book_cardview, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {
        holder.textViewTitleOfTheBook_cardView.setText(mList.get(position).getTextViewTitleOfTheBook_cardView());
        holder.textViewNameOfTheAuthor_cardView.setText(mList.get(position).getTextViewNameOfTheAuthor_cardView());
        holder.textViewFirstNameOfTheAuthor_cardView.setText(mList.get(position).getTextViewFirstNameOfTheAuthor_cardView());
        holder.textViewTypeOfTheBook_cardView.setText(mList.get(position).getTextViewTypeOfTheBook_cardView());
        holder.textViewNumberOfPages_cardView.setText(String.format("%s pages", mList.get(position).getTextViewNumberOfPages_cardView()));
        holder.textViewPublishersName_cardView.setText(mList.get(position).getTextViewPublishersName_cardView());
        holder.textViewDateOfPublication_cardView.setText(mList.get(position).getTextViewDateOfPublication_cardView());
    }

    @Override
    public int getItemCount() {
        return mList.size();
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
