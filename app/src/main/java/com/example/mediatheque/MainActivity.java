package com.example.mediatheque;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mediatheque.databinding.ActivityMainBinding;
import com.example.mediatheque.ui.connection.LoginActivity;
import com.example.mediatheque.ui.music.MusicAdapter;
import com.example.mediatheque.ui.music.MusicModel;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    private FirebaseFirestore db;

    /*Music*/
    private List<MusicModel> musicModelList;
    private MusicAdapter musicAdapter;
    /*Book*/
    /*Movie*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_music, R.id.nav_book, R.id.nav_movie)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        db = FirebaseFirestore.getInstance();

        musicModelList = new ArrayList<>();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.itemSearch_Main);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchDataMusic(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchDataMusic(s);
                return false;
            }
        });
        return true;
    }

    /*Music*/
    private void showDataMusic() {
        db.collection("musicCollection").get()
                .addOnCompleteListener(task -> {
                    musicModelList.clear();
                    for (DocumentSnapshot snapshot : Objects.requireNonNull(task.getResult())){
                        MusicModel musicModel = new MusicModel(snapshot.getString("idMusic"),snapshot.getString("MusicTitle"),snapshot.getString("AlbumTitle"),
                                snapshot.getString("NameOfTheArtist"),snapshot.getString("FirstNameOfTheArtist"),
                                snapshot.getString("GenreOfMusic"),snapshot.getString("DurationOfTheMusic"),
                                snapshot.getString("NameOfTheLabel"),snapshot.getString("MusicReleaseDate"));

                        musicModelList.add(musicModel);
                    }
                    musicAdapter.notifyDataSetChanged();
                }).addOnFailureListener(e -> Toast.makeText(MainActivity.this, "Display error.Something went wrong.", Toast.LENGTH_LONG).show());
    }
    private void searchDataMusic(String s) {

        if (s.toLowerCase().equals("") || s.toLowerCase() == null){ showDataMusic(); }

        db.collection("musicCollection")
                .whereEqualTo("AlbumTitle",s.toLowerCase())
                .get().addOnCompleteListener(task -> {
            musicModelList.clear();

            for (DocumentSnapshot snapshot : Objects.requireNonNull(task.getResult())){
                MusicModel musicModel = new MusicModel(snapshot.getString("idMusic"),snapshot.getString("MusicTitle"),snapshot.getString("AlbumTitle"),
                        snapshot.getString("NameOfTheArtist"),snapshot.getString("FirstNameOfTheArtist"),
                        snapshot.getString("GenreOfMusic"),snapshot.getString("DurationOfTheMusic"),
                        snapshot.getString("NameOfTheLabel"),snapshot.getString("MusicReleaseDate"));

                musicModelList.add(musicModel);
            }

            musicAdapter = new MusicAdapter(MainActivity.this,musicModelList);

            RecyclerView recyclerViewMusic = findViewById(R.id.recyclerviewMusic);
            recyclerViewMusic.setHasFixedSize(true);
            recyclerViewMusic.setLayoutManager(new LinearLayoutManager(MainActivity.this));

            recyclerViewMusic.setAdapter(musicAdapter);

        }).addOnFailureListener(e -> Toast.makeText(MainActivity.this, "Searching name album failed - Connection error with the database.", Toast.LENGTH_LONG).show());
    }

    /*Book*/
    /*Movie*/

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemLogout_Main){
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}