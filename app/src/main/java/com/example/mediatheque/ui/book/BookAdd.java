package com.example.mediatheque.ui.book;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.mediatheque.R;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class BookAdd extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextNameOfTheAuthor_AddBook,editTextFirstNameOfTheAuthor_AddBook,
            editTextTitleOfTheBook_AddBook,editTextTypeOfTheBook_AddBook,editTextNumberOfPages_AddBook,
            editTextPublishersName_AddBook,editTextDateOfPublication_AddBook;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        editTextNameOfTheAuthor_AddBook = findViewById(R.id.editTextNameOfTheAuthor_AddBook);
        editTextFirstNameOfTheAuthor_AddBook = findViewById(R.id.editTextFirstNameOfTheAuthor_AddBook);

        editTextTitleOfTheBook_AddBook = findViewById(R.id.editTextTitleOfTheBook_AddBook);
        editTextTypeOfTheBook_AddBook = findViewById(R.id.editTextTypeOfTheBook_AddBook);
        editTextNumberOfPages_AddBook = findViewById(R.id.editTextNumberOfPages_AddBook);

        editTextPublishersName_AddBook = findViewById(R.id.editTextPublishersName_AddBook);
        editTextDateOfPublication_AddBook = findViewById(R.id.editTextDateOfPublication_AddBook);

        Button buttonAdd_AddBook = findViewById(R.id.buttonAdd_AddBook);
        buttonAdd_AddBook.setOnClickListener(this);

        db = FirebaseFirestore.getInstance();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonAdd_AddBook) {
            buttonAddBookClicked();
        }
    }

    private void buttonAddBookClicked() {
        String NameOfTheAuthor = editTextNameOfTheAuthor_AddBook.getText().toString().trim();
        String FirstNameOfTheAuthor = editTextFirstNameOfTheAuthor_AddBook.getText().toString().trim();

        String TitleOfTheBook = editTextTitleOfTheBook_AddBook.getText().toString().trim();
        String TypeOfTheBook = editTextTypeOfTheBook_AddBook.getText().toString().trim();
        String NumberOfPages = editTextNumberOfPages_AddBook.getText().toString().trim();

        String PublishersName = editTextPublishersName_AddBook.getText().toString().trim();
        String DateOfPublication = editTextDateOfPublication_AddBook.getText().toString().trim();

        if (NameOfTheAuthor.isEmpty()){
            editTextNameOfTheAuthor_AddBook.setError("Name of the author is required.");
            editTextNameOfTheAuthor_AddBook.requestFocus();
            return;
        }

        else if (FirstNameOfTheAuthor.isEmpty()){
            editTextFirstNameOfTheAuthor_AddBook.setError("Firstname of the author is required.");
            editTextFirstNameOfTheAuthor_AddBook.requestFocus();
            return;
        }

         if (TitleOfTheBook.isEmpty()){
            editTextTitleOfTheBook_AddBook.setError("Title of the book is required.");
            editTextTitleOfTheBook_AddBook.requestFocus();
            return;
        }

         if (TypeOfTheBook.isEmpty()){
            editTextTypeOfTheBook_AddBook.setError("Type of the book is required.");
            editTextTypeOfTheBook_AddBook.requestFocus();
            return;
        }

         if (NumberOfPages.isEmpty()){
            editTextNumberOfPages_AddBook.setError("Number of pages is required.");
            editTextNumberOfPages_AddBook.requestFocus();
            return;
        }

         if (PublishersName.isEmpty()){
            editTextPublishersName_AddBook.setError("Publisher's name is required.");
            editTextPublishersName_AddBook.requestFocus();
            return;
        }

         if (DateOfPublication.isEmpty()){
            editTextDateOfPublication_AddBook.setError("Date of publication is required.");
            editTextDateOfPublication_AddBook.requestFocus();
            return;
        }

         if (!DateOfPublication.matches("\\d{2}/\\d{2}/\\d{4}")) {
            editTextDateOfPublication_AddBook.setError("Please provide the date in DD/MM/YYYY format.");
            editTextDateOfPublication_AddBook.requestFocus();
        }

        Map<String,Object> mapBook = new HashMap<>();
        mapBook.put("NameOfTheAuthor",NameOfTheAuthor);
        mapBook.put("FirstNameOfTheAuthor",FirstNameOfTheAuthor);
        mapBook.put("TitleOfTheBook",TitleOfTheBook);
        mapBook.put("TypeOfTheBook",TypeOfTheBook);
        mapBook.put("NumberOfPages",NumberOfPages);
        mapBook.put("PublishersName",PublishersName);
        mapBook.put("DateOfPublication",DateOfPublication);

        db.collection("bookCollection").add(mapBook)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(BookAdd.this, "The book has been successfully added.", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(BookAdd.this, BookFragment.class));
                }).addOnFailureListener(e -> Toast.makeText(BookAdd.this, "Adding book failed - Connection error with the database.", Toast.LENGTH_LONG).show());

    }
}