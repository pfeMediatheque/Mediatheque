package com.example.mediatheque.ui.movie;

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

public class AddMovie extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextNameOfTheDirector_AddMovie,editTextFirstNameOfTheDirector_AddMovie,
            editTextMovieTitle_AddMovie,editTextTypeOfMovie_AddMovie,editTextDurationOfTheMovie_AddMovie,editTextDateOfFirstViewing_AddMovie,
            editTextProductionCompanies_AddMovie,editTextReleaseDate_AddMovie;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        editTextNameOfTheDirector_AddMovie = findViewById(R.id.editTextNameOfTheDirector_AddMovie);
        editTextFirstNameOfTheDirector_AddMovie = findViewById(R.id.editTextFirstNameOfTheDirector_AddMovie);

        editTextMovieTitle_AddMovie = findViewById(R.id.editTextMovieTitle_AddMovie);
        editTextTypeOfMovie_AddMovie = findViewById(R.id.editTextTypeOfMovie_AddMovie);
        editTextDurationOfTheMovie_AddMovie = findViewById(R.id.editTextDurationOfTheMovie_AddMovie);
        editTextDateOfFirstViewing_AddMovie = findViewById(R.id.editTextDateOfFirstViewing_AddMovie);

        editTextProductionCompanies_AddMovie = findViewById(R.id.editTextProductionCompanies_AddMovie);
        editTextReleaseDate_AddMovie = findViewById(R.id.editTextReleaseDate_AddMovie);

        Button buttonAdd_AddMovie = findViewById(R.id.buttonAdd_AddMovie);
        buttonAdd_AddMovie.setOnClickListener(this);

        db = FirebaseFirestore.getInstance();

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonAdd_AddMovie) {
            buttonAddMovieClicked();
        }
    }

    private void buttonAddMovieClicked() {
        String NameOfTheDirector = editTextNameOfTheDirector_AddMovie.getText().toString().trim();
        String FirstNameOfTheDirector = editTextFirstNameOfTheDirector_AddMovie.getText().toString().trim();

        String MovieTitle = editTextMovieTitle_AddMovie.getText().toString().trim();
        String TypeOfMovie = editTextTypeOfMovie_AddMovie.getText().toString().trim();
        String DurationOfTheMovie = editTextDurationOfTheMovie_AddMovie.getText().toString().trim();
        String DateOfFirstViewing = editTextDateOfFirstViewing_AddMovie.getText().toString().trim();

        String ProductionCompanies = editTextProductionCompanies_AddMovie.getText().toString().trim();
        String ReleaseDate = editTextReleaseDate_AddMovie.getText().toString().trim();

        if (NameOfTheDirector.isEmpty()){
            editTextNameOfTheDirector_AddMovie.setError("Name of the director is required.");
            editTextNameOfTheDirector_AddMovie.requestFocus();
            return;
        }

        if (FirstNameOfTheDirector.isEmpty()){
            editTextFirstNameOfTheDirector_AddMovie.setError("Firstname of the director is required.");
            editTextFirstNameOfTheDirector_AddMovie.requestFocus();
            return;
        }

        if (MovieTitle.isEmpty()){
            editTextMovieTitle_AddMovie.setError("Title of the movie is required.");
            editTextMovieTitle_AddMovie.requestFocus();
            return;
        }

        if (TypeOfMovie.isEmpty()){
            editTextTypeOfMovie_AddMovie.setError("Type of the movie is required.");
            editTextTypeOfMovie_AddMovie.requestFocus();
            return;
        }

        if (DurationOfTheMovie.isEmpty()){
            editTextDurationOfTheMovie_AddMovie.setError("Duration of the movie is required.");
            editTextDurationOfTheMovie_AddMovie.requestFocus();
            return;
        }

        if (DateOfFirstViewing.isEmpty()){
            editTextDateOfFirstViewing_AddMovie.setError("Date of first viewing is required.");
            editTextDateOfFirstViewing_AddMovie.requestFocus();
            return;
        }

        if (!DateOfFirstViewing.matches("\\d{2}/\\d{2}/\\d{4}")) {
            editTextDateOfFirstViewing_AddMovie.setError("Please provide the date in DD/MM/YYYY format.");
            editTextDateOfFirstViewing_AddMovie.requestFocus();
        }

        if (ProductionCompanies.isEmpty()){
            editTextProductionCompanies_AddMovie.setError("Production companies is required.");
            editTextProductionCompanies_AddMovie.requestFocus();
            return;
        }

        if (ReleaseDate.isEmpty()){
            editTextReleaseDate_AddMovie.setError("Release date is required.");
            editTextReleaseDate_AddMovie.requestFocus();
            return;
        }

        if (!ReleaseDate.matches("\\d{2}/\\d{2}/\\d{4}")) {
            editTextReleaseDate_AddMovie.setError("Please provide the date in DD/MM/YYYY format.");
            editTextReleaseDate_AddMovie.requestFocus();
        }

        Map<String,Object> mapMovie = new HashMap<>();
        mapMovie.put("NameOfTheDirector",NameOfTheDirector);
        mapMovie.put("FirstNameOfTheDirector",FirstNameOfTheDirector);
        mapMovie.put("MovieTitle",MovieTitle);
        mapMovie.put("TypeOfMovie",TypeOfMovie);
        mapMovie.put("DurationOfTheMovie",DurationOfTheMovie);
        mapMovie.put("DateOfFirstViewing",DateOfFirstViewing);
        mapMovie.put("ProductionCompanies",ProductionCompanies);
        mapMovie.put("ReleaseDate",ReleaseDate);

        db.collection("MovieCollection").add(mapMovie)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(AddMovie.this, "The movie has been successfully added.", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(AddMovie.this, MovieFragment.class));
                }).addOnFailureListener(e -> Toast.makeText(AddMovie.this, "Adding book failed - Connection error with the database.", Toast.LENGTH_LONG).show());
    }
}