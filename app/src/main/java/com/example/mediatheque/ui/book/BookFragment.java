package com.example.mediatheque.ui.book;

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
import com.example.mediatheque.databinding.FragmentBookBinding;

public class BookFragment extends Fragment {

    private ImageButton btnAddBook;
    private GalleryViewModel galleryViewModel;
    private FragmentBookBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel = new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentBookBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        btnAddBook = root.findViewById(R.id.btnAddBook);
        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Book add work in progress", Toast.LENGTH_SHORT).show();
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