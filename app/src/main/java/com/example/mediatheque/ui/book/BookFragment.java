package com.example.mediatheque.ui.book;

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
import com.example.mediatheque.databinding.FragmentBookBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookFragment extends Fragment {

    private FragmentBookBinding binding;

    private FirebaseFirestore db;
    private BookAdapter bookAdapter;
    private List<BookModel> bookModelList;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentBookBinding.inflate(inflater, container, false);
        View root = inflater.inflate(R.layout.fragment_book, container, false);

        RecyclerView recyclerViewBook = root.findViewById(R.id.recyclerviewBook);
        recyclerViewBook.setHasFixedSize(true);
        recyclerViewBook.setLayoutManager(new LinearLayoutManager(getContext()));

        db = FirebaseFirestore.getInstance();
        bookModelList = new ArrayList<>();
        bookAdapter = new BookAdapter(getContext(), bookModelList);
        recyclerViewBook.setAdapter(bookAdapter);

        ItemTouchHelper touchHelper = new ItemTouchHelper(new TouchHelperBook(bookAdapter));
        touchHelper.attachToRecyclerView(recyclerViewBook);

        showDataBook();

        ImageButton btnAddBook = root.findViewById(R.id.btnAddBook);
        btnAddBook.setOnClickListener(view -> startActivity(new Intent(getActivity(), BookAddUpdate.class)));

        return root;
    }

    private void showDataBook() {
        db.collection("bookCollection").get()
                .addOnCompleteListener(task -> {
                    bookModelList.clear();
                    for (DocumentSnapshot snapshot : Objects.requireNonNull(task.getResult())){
                        BookModel bookModel = new BookModel(snapshot.getString("idBook"),snapshot.getString("TitleOfTheBook"),
                                snapshot.getString("NameOfTheAuthor"),snapshot.getString("FirstNameOfTheAuthor"),
                                snapshot.getString("TypeOfTheBook"),snapshot.getString("NumberOfPages"),
                                snapshot.getString("PublishersName"),snapshot.getString("DateOfPublication"));

                        bookModelList.add(bookModel);
                    }
                    bookAdapter.notifyDataSetChanged();
                }).addOnFailureListener(e -> Toast.makeText(getActivity(), "Display error.Something went wrong.", Toast.LENGTH_LONG).show());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}