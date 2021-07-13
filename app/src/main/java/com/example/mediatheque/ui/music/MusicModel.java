package com.example.mediatheque.ui.music;

public class MusicModel {

    final String idMusic;
    final String textViewTitleOfTheMusic_cardView;
    final String textViewAlbumTitle_cardView;
    final String textViewNameOfTheArtist_cardView;
    final String textViewFirstNameOfTheArtist_cardView;
    final String textViewGenreOfMusic_cardView;
    final String textViewDurationOfTheMusic_cardView;
    final String textViewNameOfTheLabel_cardView;
    final String textViewMusicReleaseDate_cardView;


    public MusicModel(String idMusic, String textViewTitleOfTheMusic_cardView, String textViewAlbumTitle_cardView, String textViewNameOfTheArtist_cardView, String textViewFirstNameOfTheArtist_cardView, String textViewGenreOfMusic_cardView, String textViewDurationOfTheMusic_cardView, String textViewNameOfTheLabel_cardView, String textViewMusicReleaseDate_cardView) {
        this.idMusic = idMusic;
        this.textViewTitleOfTheMusic_cardView = textViewTitleOfTheMusic_cardView;
        this.textViewAlbumTitle_cardView = textViewAlbumTitle_cardView;
        this.textViewNameOfTheArtist_cardView = textViewNameOfTheArtist_cardView;
        this.textViewFirstNameOfTheArtist_cardView = textViewFirstNameOfTheArtist_cardView;
        this.textViewGenreOfMusic_cardView = textViewGenreOfMusic_cardView;
        this.textViewDurationOfTheMusic_cardView = textViewDurationOfTheMusic_cardView;
        this.textViewNameOfTheLabel_cardView = textViewNameOfTheLabel_cardView;
        this.textViewMusicReleaseDate_cardView = textViewMusicReleaseDate_cardView;
    }

    public String getIdMusic() { return idMusic; }

    public String getTextViewTitleOfTheMusic_cardView() { return textViewTitleOfTheMusic_cardView; }

    public String getTextViewAlbumTitle_cardView() { return textViewAlbumTitle_cardView; }

    public String getTextViewNameOfTheArtist_cardView() { return textViewNameOfTheArtist_cardView; }

    public String getTextViewFirstNameOfTheArtist_cardView() { return textViewFirstNameOfTheArtist_cardView; }

    public String getTextViewGenreOfMusic_cardView() { return textViewGenreOfMusic_cardView; }

    public String getTextViewDurationOfTheMusic_cardView() { return textViewDurationOfTheMusic_cardView; }

    public String getTextViewNameOfTheLabel_cardView() { return textViewNameOfTheLabel_cardView; }

    public String getTextViewMusicReleaseDate_cardView() { return textViewMusicReleaseDate_cardView; }
}
