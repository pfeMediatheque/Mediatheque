package com.example.mediatheque.ui.music;

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
import com.example.mediatheque.databinding.FragmentMusicBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MusicFragment extends Fragment {

    FragmentMusicBinding binding;

    private FirebaseFirestore db;
    private MusicAdapter musicAdapter;
    private List<MusicModel> musicModelList;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMusicBinding.inflate(inflater, container, false);
        View root = inflater.inflate(R.layout.fragment_music, container, false);

        RecyclerView recyclerViewMusic = root.findViewById(R.id.recyclerviewMusic);
        recyclerViewMusic.setHasFixedSize(true);
        recyclerViewMusic.setLayoutManager(new LinearLayoutManager(getContext()));

        db = FirebaseFirestore.getInstance();
        musicModelList = new ArrayList<>();
        musicAdapter = new MusicAdapter(getContext(), musicModelList);
        recyclerViewMusic.setAdapter(musicAdapter);

        ItemTouchHelper touchHelper = new ItemTouchHelper(new MusicTouchHelper(musicAdapter));
        touchHelper.attachToRecyclerView(recyclerViewMusic);

        showDataMusic();

        ImageButton btnAddMusic = root.findViewById(R.id.btnAddMusic);
        btnAddMusic.setOnClickListener(view -> startActivity(new Intent(getActivity(), MusicAddUpdate.class)));

        return root;
    }

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
                }).addOnFailureListener(e -> Toast.makeText(getActivity(), "Display error.Something went wrong.", Toast.LENGTH_LONG).show());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}