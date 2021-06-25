package com.example.mediatheque.ui.movie;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mediatheque.R;
import com.example.mediatheque.databinding.FragmentMovieBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MovieFragment extends Fragment {

    private FragmentMovieBinding binding;

    private FirebaseFirestore db;
    private MovieAdapter movieAdapter;
    private List<MovieModel> movieModelList;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMovieBinding.inflate(inflater, container, false);
        View root = inflater.inflate(R.layout.fragment_movie, container, false);

        RecyclerView recyclerViewMovie = root.findViewById(R.id.recyclerviewMovie);
        recyclerViewMovie.setHasFixedSize(true);
        recyclerViewMovie.setLayoutManager(new LinearLayoutManager(getContext()));

        db = FirebaseFirestore.getInstance();
        movieModelList = new ArrayList<>();
        movieAdapter = new MovieAdapter(getContext(), movieModelList);
        recyclerViewMovie.setAdapter(movieAdapter);

        ItemTouchHelper touchHelper = new ItemTouchHelper(new MovieTouchHelper(movieAdapter));
        touchHelper.attachToRecyclerView(recyclerViewMovie);

        showDataMovie();

        ImageButton btnAddMovie = root.findViewById(R.id.btnAddMovie);
        btnAddMovie.setOnClickListener(view -> startActivity(new Intent(getActivity(), MovieAddUpdate.class)));

        return root;
    }

    private void showDataMovie() {
        db.collection("movieCollection").get()
                .addOnCompleteListener(task -> {
                    movieModelList.clear();
                    for (DocumentSnapshot snapshot : Objects.requireNonNull(task.getResult())){
                        MovieModel movieModel = new MovieModel(snapshot.getString("idMovie"),snapshot.getString("MovieTitle"),
                                snapshot.getString("NameOfTheDirector"),snapshot.getString("FirstNameOfTheDirector"),
                                snapshot.getString("TypeOfMovie"),snapshot.getString("DurationOfTheMovie"),
                                snapshot.getString("ProductionCompanies"),snapshot.getString("ReleaseDate"));

                        movieModelList.add(movieModel);
                    }
                    movieAdapter.notifyDataSetChanged();
                }).addOnFailureListener(e -> Toast.makeText(getActivity(), "Display error.Something went wrong.", Toast.LENGTH_LONG).show());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}