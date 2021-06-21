package com.example.mediatheque;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShowActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private BookAdapter adapter;
    private List<BookModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        list = new ArrayList<>();
        adapter = new BookAdapter(this,list);
        recyclerView.setAdapter(adapter);

        showData();
    }

    private void showData() {
        db.collection("bookCollection").get()
                .addOnCompleteListener(task -> {
                    list.clear();
                    for (DocumentSnapshot snapshot : Objects.requireNonNull(task.getResult())){
                        BookModel bookModel = new BookModel(snapshot.getString("NameOfTheAuthor"),
                                snapshot.getString("FirstNameOfTheAuthor"),snapshot.getString("TitleOfTheBook"),
                                snapshot.getString("TypeOfTheBook"),snapshot.getString("NumberOfPages"),
                                snapshot.getString("PublishersName"),snapshot.getString("DateOfPublication"));

                        list.add(bookModel);
                    }
                    adapter.notifyDataSetChanged();
                }).addOnFailureListener(e -> Toast.makeText(ShowActivity.this, "Display error.Something went wrong.", Toast.LENGTH_LONG).show());
    }
}