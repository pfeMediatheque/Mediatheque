package com.example.mediatheque.ui.music;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.mediatheque.R;
import com.example.mediatheque.databinding.FragmentMusicBinding;

public class MusicFragment extends Fragment {

    FragmentMusicBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMusicBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ImageButton btnAddMusic = root.findViewById(R.id.btnAddMusic);
        btnAddMusic.setOnClickListener(view -> Toast.makeText(getActivity(), "Music add work in progress", Toast.LENGTH_SHORT).show());
        return root;


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}