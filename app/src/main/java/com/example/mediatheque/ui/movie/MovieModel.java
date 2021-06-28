package com.example.mediatheque.ui.movie;

public class MovieModel {

    final String idMovie;
    final String textViewTitleOfTheMovie_cardView;
    final String textViewNameOfTheDirector_cardView;
    final String textViewFirstNameOfTheDirector_cardView;
    final String textViewTypeOfTheMovie_cardView;
    final String textViewDurationOfTheMovie_cardView;
    final String textViewProductionCompanies_cardView;
    final String textViewReleaseDate_cardView;

    public MovieModel(String idMovie, String textViewTitleOfTheMovie_cardView, String textViewNameOfTheDirector_cardView, String textViewFirstNameOfTheDirector_cardView, String textViewTypeOfTheMovie_cardView, String textViewDurationOfTheMovie_cardView, String textViewProductionCompanies_cardView, String textViewReleaseDate_cardView) {
        this.idMovie = idMovie;
        this.textViewTitleOfTheMovie_cardView = textViewTitleOfTheMovie_cardView;
        this.textViewNameOfTheDirector_cardView = textViewNameOfTheDirector_cardView;
        this.textViewFirstNameOfTheDirector_cardView = textViewFirstNameOfTheDirector_cardView;
        this.textViewTypeOfTheMovie_cardView = textViewTypeOfTheMovie_cardView;
        this.textViewDurationOfTheMovie_cardView = textViewDurationOfTheMovie_cardView;
        this.textViewProductionCompanies_cardView = textViewProductionCompanies_cardView;
        this.textViewReleaseDate_cardView = textViewReleaseDate_cardView;
    }

    public String getIdMovie() { return idMovie; }

    public String getTextViewTitleOfTheMovie_cardView() {
        return textViewTitleOfTheMovie_cardView;
    }

    public String getTextViewNameOfTheDirector_cardView() { return textViewNameOfTheDirector_cardView; }

    public String getTextViewFirstNameOfTheDirector_cardView() { return textViewFirstNameOfTheDirector_cardView; }

    public String getTextViewTypeOfTheMovie_cardView() {
        return textViewTypeOfTheMovie_cardView;
    }

    public String getTextViewDurationOfTheMovie_cardView() { return textViewDurationOfTheMovie_cardView; }

    public String getTextViewProductionCompanies_cardView() { return textViewProductionCompanies_cardView; }

    public String getTextViewReleaseDate_cardView() {
        return textViewReleaseDate_cardView;
    }

}
