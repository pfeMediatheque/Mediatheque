package com.example.mediatheque.ui.book;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mediatheque.MainActivity;
import com.example.mediatheque.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BookAddUpdate extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextNameOfTheAuthor_AddBook,editTextFirstNameOfTheAuthor_AddBook,
            editTextTitleOfTheBook_AddBook,editTextTypeOfTheBook_AddBook,editTextNumberOfPages_AddBook,
            editTextPublishersName_AddBook,editTextDateOfPublication_AddBook;

    private String updateIdBook;

    private FirebaseFirestore db;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        editTextTitleOfTheBook_AddBook = findViewById(R.id.editTextTitleOfTheBook_AddBook);

        editTextNameOfTheAuthor_AddBook = findViewById(R.id.editTextNameOfTheAuthor_AddBook);
        editTextFirstNameOfTheAuthor_AddBook = findViewById(R.id.editTextFirstNameOfTheAuthor_AddBook);

        editTextTypeOfTheBook_AddBook = findViewById(R.id.editTextTypeOfTheBook_AddBook);
        editTextNumberOfPages_AddBook = findViewById(R.id.editTextNumberOfPages_AddBook);

        editTextPublishersName_AddBook = findViewById(R.id.editTextPublishersName_AddBook);
        editTextDateOfPublication_AddBook = findViewById(R.id.editTextDateOfPublication_AddBook);

        Button buttonAdd_AddBook = findViewById(R.id.buttonAdd_AddBook);
        buttonAdd_AddBook.setOnClickListener(this);

        db = FirebaseFirestore.getInstance();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            buttonAdd_AddBook.setText("Update");

            updateIdBook = bundle.getString("updateIdBook");
            String updateTitleOfTheBook = bundle.getString("updateTitleOfTheBook");
            String updateNameOfTheAuthor = bundle.getString("updateNameOfTheAuthor");
            String updateFirstNameOfTheAuthor = bundle.getString("updateFirstNameOfTheAuthor");
            String updateTypeOfTheBook = bundle.getString("updateTypeOfTheBook");
            String updateNumberOfPages = bundle.getString("updateNumberOfPages");
            String updatePublishersName = bundle.getString("updatePublishersName");
            String updateDateOfPublication = bundle.getString("updateDateOfPublication");

            editTextTitleOfTheBook_AddBook.setText(updateTitleOfTheBook);
            editTextNameOfTheAuthor_AddBook.setText(updateNameOfTheAuthor);
            editTextFirstNameOfTheAuthor_AddBook.setText(updateFirstNameOfTheAuthor);
            editTextTypeOfTheBook_AddBook.setText(updateTypeOfTheBook);
            editTextNumberOfPages_AddBook.setText(updateNumberOfPages);
            editTextPublishersName_AddBook.setText(updatePublishersName);
            editTextDateOfPublication_AddBook.setText(updateDateOfPublication);
        }else{
            buttonAdd_AddBook.setText("Add");
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonAdd_AddBook) {
            buttonAddUpdateBookClicked();
        }
    }

    private void buttonAddUpdateBookClicked() {
        Bundle aBundle = getIntent().getExtras();

        String TitleOfTheBook = editTextTitleOfTheBook_AddBook.getText().toString().trim();

        String NameOfTheAuthor = editTextNameOfTheAuthor_AddBook.getText().toString().trim();
        String FirstNameOfTheAuthor = editTextFirstNameOfTheAuthor_AddBook.getText().toString().trim();

        String TypeOfTheBook = editTextTypeOfTheBook_AddBook.getText().toString().trim();
        String NumberOfPages = editTextNumberOfPages_AddBook.getText().toString().trim();

        String PublishersName = editTextPublishersName_AddBook.getText().toString().trim();
        String DateOfPublication = editTextDateOfPublication_AddBook.getText().toString().trim();

        // Update
        if (aBundle != null){

            String idBook = updateIdBook;

            db.collection("bookCollection").document(idBook).update("TitleOfTheBook",TitleOfTheBook,"NameOfTheAuthor",NameOfTheAuthor,"FirstNameOfTheAuthor",FirstNameOfTheAuthor,"TypeOfTheBook",TypeOfTheBook,"NumberOfPages",NumberOfPages,"PublishersName",PublishersName,"DateOfPublication",DateOfPublication,"search",TitleOfTheBook.toLowerCase())
                    .addOnCompleteListener(task -> {
                        Toast.makeText(BookAddUpdate.this, "The book has been successfully updated.", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(BookAddUpdate.this, MainActivity.class));
                    }).addOnFailureListener(e -> Toast.makeText(BookAddUpdate.this, "Updating book failed - Connection error with the database.", Toast.LENGTH_LONG).show());

        // Save
        }else{

            String idBook = UUID.randomUUID().toString().trim();

            if (TitleOfTheBook.isEmpty()){
                editTextTitleOfTheBook_AddBook.setError("Title of the book is required.");
                editTextTitleOfTheBook_AddBook.requestFocus();
            }

            else if (NameOfTheAuthor.isEmpty()){
                editTextNameOfTheAuthor_AddBook.setError("Name of the author is required.");
                editTextNameOfTheAuthor_AddBook.requestFocus();
            }

            else if (FirstNameOfTheAuthor.isEmpty()){
                editTextFirstNameOfTheAuthor_AddBook.setError("Firstname of the author is required.");
                editTextFirstNameOfTheAuthor_AddBook.requestFocus();
            }

            else if (TypeOfTheBook.isEmpty()){
                editTextTypeOfTheBook_AddBook.setError("Type of the book is required.");
                editTextTypeOfTheBook_AddBook.requestFocus();
            }

            else if (NumberOfPages.isEmpty()){
                editTextNumberOfPages_AddBook.setError("Number of pages is required.");
                editTextNumberOfPages_AddBook.requestFocus();
            }

            else if (PublishersName.isEmpty()){
                editTextPublishersName_AddBook.setError("Publisher's name is required.");
                editTextPublishersName_AddBook.requestFocus();
            }

            else if (DateOfPublication.isEmpty()){
                editTextDateOfPublication_AddBook.setError("Date of publication is required.");
                editTextDateOfPublication_AddBook.requestFocus();
            }

            else if (!DateOfPublication.matches("\\d{2}/\\d{2}/\\d{4}")) {
                editTextDateOfPublication_AddBook.setError("Please provide the date in DD/MM/YYYY format.");
                editTextDateOfPublication_AddBook.requestFocus();
            }

            else {
                Map<String,Object> mapBook = new HashMap<>();
                mapBook.put("idBook",idBook);
                mapBook.put("TitleOfTheBook",TitleOfTheBook);
                mapBook.put("NameOfTheAuthor",NameOfTheAuthor);
                mapBook.put("FirstNameOfTheAuthor",FirstNameOfTheAuthor);
                mapBook.put("TypeOfTheBook",TypeOfTheBook);
                mapBook.put("NumberOfPages",NumberOfPages);
                mapBook.put("PublishersName",PublishersName);
                mapBook.put("DateOfPublication",DateOfPublication);

                mapBook.put("search",TitleOfTheBook.toLowerCase());

                db.collection("bookCollection").document(idBook).set(mapBook)
                        .addOnSuccessListener(documentReference -> {
                            Toast.makeText(BookAddUpdate.this, "The book has been successfully added.", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(BookAddUpdate.this, MainActivity.class));
                        }).addOnFailureListener(e -> Toast.makeText(BookAddUpdate.this, "Adding book failed - Connection error with the database.", Toast.LENGTH_LONG).show());

            }
        }
    }
}