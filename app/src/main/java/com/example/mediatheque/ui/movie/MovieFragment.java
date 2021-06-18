package com.example.mediatheque.ui.movie;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.mediatheque.R;
import com.example.mediatheque.databinding.FragmentMovieBinding;

public class MovieFragment extends Fragment {

    private FragmentMovieBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMovieBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ImageButton btnAddMovie = root.findViewById(R.id.btnAddMovie);
        btnAddMovie.setOnClickListener(view -> {
            //Toast.makeText(getActivity(), "Movie add work in progress", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getActivity(), AddMovie.class));
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}