package com.example.mediatheque.ui.music;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.mediatheque.R;
import com.example.mediatheque.databinding.FragmentMusicBinding;

import java.util.Objects;

public class MusicFragment extends Fragment {

    private ImageButton btnAddMusic;
    private MusicViewModel musicViewModel;
    private FragmentMusicBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        musicViewModel = new ViewModelProvider(this).get(MusicViewModel.class);

        binding = FragmentMusicBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        btnAddMusic = root.findViewById(R.id.btnAddMusic);
        btnAddMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Music add work in progress", Toast.LENGTH_SHORT).show();
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