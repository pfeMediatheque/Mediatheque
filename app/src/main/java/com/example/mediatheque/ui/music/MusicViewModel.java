package com.example.mediatheque.ui.music;

import android.widget.Button;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MusicViewModel extends ViewModel {

    Button btnAddMusic;
    private MutableLiveData<String> mText;

    public MusicViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is music fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}