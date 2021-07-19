package com.example.mediatheque.ui.movie;

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

public class MovieAddUpdate extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextNameOfTheDirector_AddMovie,editTextFirstNameOfTheDirector_AddMovie,
            editTextMovieTitle_AddMovie,editTextTypeOfMovie_AddMovie,editTextDurationOfTheMovie_AddMovie,
            editTextProductionCompanies_AddMovie,editTextReleaseDate_AddMovie;

    private String updateIdMovie;

    private FirebaseFirestore db;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        editTextMovieTitle_AddMovie = findViewById(R.id.editTextMovieTitle_AddMovie);

        editTextNameOfTheDirector_AddMovie = findViewById(R.id.editTextNameOfTheDirector_AddMovie);
        editTextFirstNameOfTheDirector_AddMovie = findViewById(R.id.editTextFirstNameOfTheDirector_AddMovie);

        editTextTypeOfMovie_AddMovie = findViewById(R.id.editTextTypeOfMovie_AddMovie);
        editTextDurationOfTheMovie_AddMovie = findViewById(R.id.editTextDurationOfTheMovie_AddMovie);

        editTextProductionCompanies_AddMovie = findViewById(R.id.editTextProductionCompanies_AddMovie);
        editTextReleaseDate_AddMovie = findViewById(R.id.editTextReleaseDate_AddMovie);

        Button buttonAdd_AddMovie = findViewById(R.id.buttonAdd_AddMovie);
        buttonAdd_AddMovie.setOnClickListener(this);

        db = FirebaseFirestore.getInstance();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            buttonAdd_AddMovie.setText("Update");

            updateIdMovie = bundle.getString("updateIdMovie");
            String updateTitleOfTheMovie = bundle.getString("updateTitleOfTheMovie");
            String updateNameOfTheDirector = bundle.getString("updateNameOfTheDirector");
            String updateFirstNameOfTheDirector = bundle.getString("updateFirstNameOfTheDirector");
            String updateTypeOfTheMovie = bundle.getString("updateTypeOfTheMovie");
            String updateDurationOfTheMovie = bundle.getString("updateDurationOfTheMovie");
            String updateProductionCompanies = bundle.getString("updateProductionCompanies");
            String updateReleaseDate = bundle.getString("updateReleaseDate");

            editTextMovieTitle_AddMovie.setText(updateTitleOfTheMovie);
            editTextNameOfTheDirector_AddMovie.setText(updateNameOfTheDirector);
            editTextFirstNameOfTheDirector_AddMovie.setText(updateFirstNameOfTheDirector);
            editTextTypeOfMovie_AddMovie.setText(updateTypeOfTheMovie);
            editTextDurationOfTheMovie_AddMovie.setText(updateDurationOfTheMovie);
            editTextProductionCompanies_AddMovie.setText(updateProductionCompanies);
            editTextReleaseDate_AddMovie.setText(updateReleaseDate);
        }else{
            buttonAdd_AddMovie.setText("Add");
        }

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonAdd_AddMovie) {
            buttonAddMovieClicked();
        }
    }

    private void buttonAddMovieClicked() {
        Bundle aBundle = getIntent().getExtras();

        String MovieTitle = editTextMovieTitle_AddMovie.getText().toString().trim();

        String NameOfTheDirector = editTextNameOfTheDirector_AddMovie.getText().toString().trim();
        String FirstNameOfTheDirector = editTextFirstNameOfTheDirector_AddMovie.getText().toString().trim();

        String TypeOfMovie = editTextTypeOfMovie_AddMovie.getText().toString().trim();
        String DurationOfTheMovie = editTextDurationOfTheMovie_AddMovie.getText().toString().trim();

        String ProductionCompanies = editTextProductionCompanies_AddMovie.getText().toString().trim();
        String ReleaseDate = editTextReleaseDate_AddMovie.getText().toString().trim();

        // Update
        if (aBundle != null){

            String idMovie = updateIdMovie;

            db.collection("movieCollection").document(idMovie).update("MovieTitle",MovieTitle,"NameOfTheDirector",NameOfTheDirector,"FirstNameOfTheDirector",FirstNameOfTheDirector,"TypeOfMovie",TypeOfMovie,"DurationOfTheMovie",DurationOfTheMovie,"ProductionCompanies",ProductionCompanies,"ReleaseDate",ReleaseDate)
                    .addOnCompleteListener(task -> {
                        Toast.makeText(MovieAddUpdate.this, "The movie has been successfully updated.", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(MovieAddUpdate.this, MainActivity.class));
                    }).addOnFailureListener(e -> Toast.makeText(MovieAddUpdate.this, "Updating movie failed - Connection error with the database.", Toast.LENGTH_LONG).show());

        // Save
        }else{

            String idMovie = UUID.randomUUID().toString().trim();

            if (MovieTitle.isEmpty()){
                editTextMovieTitle_AddMovie.setError("Title of the movie is required.");
                editTextMovieTitle_AddMovie.requestFocus();
            }

            else if (NameOfTheDirector.isEmpty()){
                editTextNameOfTheDirector_AddMovie.setError("Name of the director is required.");
                editTextNameOfTheDirector_AddMovie.requestFocus();
            }

            else if (FirstNameOfTheDirector.isEmpty()){
                editTextFirstNameOfTheDirector_AddMovie.setError("Firstname of the director is required.");
                editTextFirstNameOfTheDirector_AddMovie.requestFocus();
            }

            else if (TypeOfMovie.isEmpty()){
                editTextTypeOfMovie_AddMovie.setError("Type of the movie is required.");
                editTextTypeOfMovie_AddMovie.requestFocus();
            }

            else if (DurationOfTheMovie.isEmpty()){
                editTextDurationOfTheMovie_AddMovie.setError("Duration of the movie is required.");
                editTextDurationOfTheMovie_AddMovie.requestFocus();
            }

            else if (ProductionCompanies.isEmpty()){
                editTextProductionCompanies_AddMovie.setError("Production companies is required.");
                editTextProductionCompanies_AddMovie.requestFocus();
            }

            else if (ReleaseDate.isEmpty()){
                editTextReleaseDate_AddMovie.setError("Release date is required.");
                editTextReleaseDate_AddMovie.requestFocus();
            }

            else if (!ReleaseDate.matches("\\d{2}/\\d{2}/\\d{4}")) {
                editTextReleaseDate_AddMovie.setError("Please provide the date in DD/MM/YYYY format.");
                editTextReleaseDate_AddMovie.requestFocus();
            }

            else{
                Map<String,Object> mapMovie = new HashMap<>();
                mapMovie.put("idMovie",idMovie);
                mapMovie.put("MovieTitle",MovieTitle);
                mapMovie.put("NameOfTheDirector",NameOfTheDirector);
                mapMovie.put("FirstNameOfTheDirector",FirstNameOfTheDirector);
                mapMovie.put("TypeOfMovie",TypeOfMovie);
                mapMovie.put("DurationOfTheMovie",DurationOfTheMovie);
                mapMovie.put("ProductionCompanies",ProductionCompanies);
                mapMovie.put("ReleaseDate",ReleaseDate);

                db.collection("movieCollection").document(idMovie).set(mapMovie)
                        .addOnSuccessListener(documentReference -> {
                            Toast.makeText(MovieAddUpdate.this, "The movie has been successfully added.", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(MovieAddUpdate.this, MainActivity.class));
                        }).addOnFailureListener(e -> Toast.makeText(MovieAddUpdate.this, "Adding movie failed - Connection error with the database.", Toast.LENGTH_LONG).show());
            }
        }
    }
}