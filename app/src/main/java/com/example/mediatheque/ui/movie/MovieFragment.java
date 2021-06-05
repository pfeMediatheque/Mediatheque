package com.example.mediatheque.ui.movie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mediatheque.R;
import com.example.mediatheque.databinding.FragmentMovieBinding;

public class MovieFragment extends Fragment {

    private ImageButton btnAddMovie;
    private MovieViewModel movieViewModel;
    private FragmentMovieBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        binding = FragmentMovieBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        btnAddMovie = root.findViewById(R.id.btnAddMovie);
        btnAddMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Movie add work in progress", Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}