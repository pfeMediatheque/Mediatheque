package com.example.mediatheque.ui.music;

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

public class MusicAddUpdate extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextNameOfTheArtist_AddMusic,editTextFirstNameOfTheArtist_AddMusic,
            editTextMusicTitle_AddMusic,editTextAlbumTitle_AddMusic,editTextGenreOfMusic_AddMusic,editTextDurationOfTheMusic_AddMusic,
            editTextNameOfTheLabel_AddMusic,editTextMusicReleaseDate_AddMusic;

    private String updateIdMusic;

    private FirebaseFirestore db;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_music);

        editTextMusicTitle_AddMusic = findViewById(R.id.editTextMusicTitle_AddMusic);
        editTextAlbumTitle_AddMusic = findViewById(R.id.editTextAlbumTitle_AddMusic);

        editTextNameOfTheArtist_AddMusic = findViewById(R.id.editTextNameOfTheArtist_AddMusic);
        editTextFirstNameOfTheArtist_AddMusic = findViewById(R.id.editTextFirstNameOfTheArtist_AddMusic);

        editTextGenreOfMusic_AddMusic = findViewById(R.id.editTextGenreOfMusic_AddMusic);
        editTextDurationOfTheMusic_AddMusic = findViewById(R.id.editTextDurationOfTheMusic_AddMusic);

        editTextNameOfTheLabel_AddMusic = findViewById(R.id.editTextNameOfTheLabel_AddMusic);
        editTextMusicReleaseDate_AddMusic = findViewById(R.id.editTextMusicReleaseDate_AddMusic);

        Button buttonAdd_AddMusic = findViewById(R.id.buttonAdd_AddMusic);
        buttonAdd_AddMusic.setOnClickListener(this);

        db = FirebaseFirestore.getInstance();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            buttonAdd_AddMusic.setText("Update");

            updateIdMusic = bundle.getString("updateIdMusic");
            String updateTitleOfTheMusic = bundle.getString("updateTitleOfTheMusic");
            String updateAlbumTitle = bundle.getString("updateAlbumTitle");
            String updateNameOfTheArtist = bundle.getString("updateNameOfTheArtist");
            String updateFirstNameOfTheArtist = bundle.getString("updateFirstNameOfTheArtist");
            String updateGenreOfMusic = bundle.getString("updateGenreOfMusic");
            String updateDurationOfTheMusic = bundle.getString("updateDurationOfTheMusic");
            String updateNameOfTheLabel = bundle.getString("updateNameOfTheLabel");
            String updateMusicReleaseDate = bundle.getString("updateMusicReleaseDate");

            editTextMusicTitle_AddMusic.setText(updateTitleOfTheMusic);
            editTextAlbumTitle_AddMusic.setText(updateAlbumTitle);
            editTextNameOfTheArtist_AddMusic.setText(updateNameOfTheArtist);
            editTextFirstNameOfTheArtist_AddMusic.setText(updateFirstNameOfTheArtist);
            editTextGenreOfMusic_AddMusic.setText(updateGenreOfMusic);
            editTextDurationOfTheMusic_AddMusic.setText(updateDurationOfTheMusic);
            editTextNameOfTheLabel_AddMusic.setText(updateNameOfTheLabel);
            editTextMusicReleaseDate_AddMusic.setText(updateMusicReleaseDate);
        }else{
            buttonAdd_AddMusic.setText("Add");
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonAdd_AddMusic) {
            buttonAddMusicClicked();
        }
    }

    private void buttonAddMusicClicked() {
        Bundle aBundle = getIntent().getExtras();

        String MusicTitle = editTextMusicTitle_AddMusic.getText().toString().trim();
        String AlbumTitle = editTextAlbumTitle_AddMusic.getText().toString().trim();

        String NameOfTheArtist = editTextNameOfTheArtist_AddMusic.getText().toString().trim();
        String FirstNameOfTheArtist = editTextFirstNameOfTheArtist_AddMusic.getText().toString().trim();

        String GenreOfMusic = editTextGenreOfMusic_AddMusic.getText().toString().trim();
        String DurationOfTheMusic = editTextDurationOfTheMusic_AddMusic.getText().toString().trim();

        String NameOfTheLabel = editTextNameOfTheLabel_AddMusic.getText().toString().trim();
        String MusicReleaseDate = editTextMusicReleaseDate_AddMusic.getText().toString().trim();

        // Update
        if (aBundle != null){

            String idMusic = updateIdMusic;

            db.collection("musicCollection").document(idMusic).update("MusicTitle",MusicTitle,"AlbumTitle",AlbumTitle,"NameOfTheArtist",NameOfTheArtist,"FirstNameOfTheArtist",FirstNameOfTheArtist,"GenreOfMusic",GenreOfMusic,"DurationOfTheMusic",DurationOfTheMusic,"NameOfTheLabel",NameOfTheLabel,"MusicReleaseDate",MusicReleaseDate)
                    .addOnCompleteListener(task -> {
                        Toast.makeText(MusicAddUpdate.this, "The music has been successfully updated.", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(MusicAddUpdate.this, MainActivity.class));
                    }).addOnFailureListener(e -> Toast.makeText(MusicAddUpdate.this, "Updating music failed - Connection error with the database.", Toast.LENGTH_LONG).show());

            // Save
        }else{

            String idMusic = UUID.randomUUID().toString().trim();

            if (MusicTitle.isEmpty()){
                editTextMusicTitle_AddMusic.setError("Title of the music is required.");
                editTextMusicTitle_AddMusic.requestFocus();
            }

            else if (AlbumTitle.isEmpty()){
                editTextAlbumTitle_AddMusic.setError("Name of the album is required.");
                editTextAlbumTitle_AddMusic.requestFocus();
            }

            else if (NameOfTheArtist.isEmpty()){
                editTextNameOfTheArtist_AddMusic.setError("Name of the artist is required.");
                editTextNameOfTheArtist_AddMusic.requestFocus();
            }

            else if (FirstNameOfTheArtist.isEmpty()){
                editTextFirstNameOfTheArtist_AddMusic.setError("Firstname of the artist is required.");
                editTextFirstNameOfTheArtist_AddMusic.requestFocus();
            }

            else if (GenreOfMusic.isEmpty()){
                editTextGenreOfMusic_AddMusic.setError("Genre of the music is required.");
                editTextGenreOfMusic_AddMusic.requestFocus();
            }

            else if (DurationOfTheMusic.isEmpty()){
                editTextDurationOfTheMusic_AddMusic.setError("Duration of the music is required.");
                editTextDurationOfTheMusic_AddMusic.requestFocus();
            }

            else if (NameOfTheLabel.isEmpty()){
                editTextNameOfTheLabel_AddMusic.setError("Name of the label is required.");
                editTextNameOfTheLabel_AddMusic.requestFocus();
            }

            else if (MusicReleaseDate.isEmpty()){
                editTextMusicReleaseDate_AddMusic.setError("Music release date is required.");
                editTextMusicReleaseDate_AddMusic.requestFocus();
            }

            else if (!MusicReleaseDate.matches("\\d{2}/\\d{2}/\\d{4}")) {
                editTextMusicReleaseDate_AddMusic.setError("Please provide the date in DD/MM/YYYY format.");
                editTextMusicReleaseDate_AddMusic.requestFocus();
            }

            else{
                Map<String,Object> mapMusic = new HashMap<>();
                mapMusic.put("idMusic",idMusic);
                mapMusic.put("MusicTitle",MusicTitle);
                mapMusic.put("AlbumTitle",AlbumTitle);
                mapMusic.put("NameOfTheArtist",NameOfTheArtist);
                mapMusic.put("FirstNameOfTheArtist",FirstNameOfTheArtist);
                mapMusic.put("GenreOfMusic",GenreOfMusic);
                mapMusic.put("DurationOfTheMusic",DurationOfTheMusic);
                mapMusic.put("NameOfTheLabel",NameOfTheLabel);
                mapMusic.put("MusicReleaseDate",MusicReleaseDate);

                db.collection("musicCollection").document(idMusic).set(mapMusic)
                        .addOnSuccessListener(documentReference -> {
                            Toast.makeText(MusicAddUpdate.this, "The music has been successfully added.", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(MusicAddUpdate.this, MainActivity.class));
                        }).addOnFailureListener(e -> Toast.makeText(MusicAddUpdate.this, "Adding music failed - Connection error with the database.", Toast.LENGTH_LONG).show());
            }
        }
    }
}