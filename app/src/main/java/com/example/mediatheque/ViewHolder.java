package com.example.mediatheque;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class ViewHolder extends RecyclerView.Adapter<ViewHolder.MyViewHolder> {

    private ShowActivity activity;
    private List<Model> vList;

    public ViewHolder(ShowActivity activity, List<Model> vList){
        this.activity = activity;
        this.vList = vList;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.cardview_model_book, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {
        holder.vTitleOfTheBook.setText(vList.get(position).getmTitleOfTheBook());
        holder.vNameOfTheAuthor.setText(vList.get(position).getmNameOfTheAuthor());
        holder.vFirstNameOfTheAuthor.setText(vList.get(position).getmFirstNameOfTheAuthor());
        holder.vTypeOfTheBook.setText(vList.get(position).getmTypeOfTheBook());
        holder.vNumberOfPages.setText(vList.get(position).getmNumberOfPages());
        holder.vPublishersName.setText(vList.get(position).getmPublishersName());
        holder.vDateOfPublication.setText(vList.get(position).getmDateOfPublication());
    }

    @Override
    public int getItemCount() {
        return vList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView vTitleOfTheBook, vNameOfTheAuthor, vFirstNameOfTheAuthor, vTypeOfTheBook, vNumberOfPages, vPublishersName, vDateOfPublication;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            vTitleOfTheBook = itemView.findViewById(R.id.textViewTitleOfTheBook_modelLayout);
            vNameOfTheAuthor = itemView.findViewById(R.id.textViewNameOfTheAuthor_modelLayout);
            vFirstNameOfTheAuthor = itemView.findViewById(R.id.textViewFirstNameOfTheAuthor_modelLayout);
            vTypeOfTheBook = itemView.findViewById(R.id.textViewTypeOfTheBook_modelLayout);
            vNumberOfPages = itemView.findViewById(R.id.textViewNumberOfPages_modelLayout);
            vPublishersName = itemView.findViewById(R.id.textViewPublishersName_modelLayout);
            vDateOfPublication = itemView.findViewById(R.id.textViewDateOfPublication_modelLayout);
        }
    }
}
