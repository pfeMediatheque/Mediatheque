package com.example.mediatheque.ui.book;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.mediatheque.R;
import com.example.mediatheque.ShowActivity;
import com.example.mediatheque.databinding.FragmentBookBinding;

public class BookFragment extends Fragment {

    private FragmentBookBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentBookBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ImageButton btnAddBook = root.findViewById(R.id.btnAddBook);
        btnAddBook.setOnClickListener(view -> startActivity(new Intent(getActivity(), BookAdd.class)));

        startActivity(new Intent(getActivity(), ShowActivity.class));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}