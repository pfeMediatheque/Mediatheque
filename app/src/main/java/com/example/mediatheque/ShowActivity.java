package com.example.mediatheque;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediatheque.ui.book.AddBook;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends AppCompatActivity {

    private RecyclerView sRecyclerViewBook;
    private FirebaseFirestore db;
    private ViewHolder viewHolder;
    private List<Model> list;

    protected void OnCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_book);

        sRecyclerViewBook = findViewById(R.id.recyclerViewBook);
        sRecyclerViewBook.setHasFixedSize(true);
        sRecyclerViewBook.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        list = new ArrayList<>();
        viewHolder = new ViewHolder(this, list);
        sRecyclerViewBook.setAdapter(viewHolder);

        showData();

    }

    private void showData(){
        db.collection("bookCollection").get()
                .addOnCompleteListener(task -> {
                    list.clear();
                    for (DocumentSnapshot snapshot : task.getResult()){
                        Model model = new Model(snapshot.getString("id"),snapshot.getString("mTitleOfTheBook"),
                                snapshot.getString("mNameOfTheAuthor"),snapshot.getString("mFirstNameOfTheAuthor"),
                                snapshot.getString("mTypeOfTheBook"),snapshot.getString("mNumberOfPages"),
                                snapshot.getString("mPublishersName"),snapshot.getString("mDateOfPublication"));

                        list.add(model);
                    }
                    viewHolder.notifyDataSetChanged();
                }).addOnFailureListener(e -> Toast.makeText(ShowActivity.this, "Display error.Something went wrong.", Toast.LENGTH_LONG).show());
    }
}
