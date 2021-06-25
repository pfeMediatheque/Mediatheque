package com.example.mediatheque.ui.movie;

public class MovieModel {

    String idMovie, textViewTitleOfTheMovie_cardView, textViewNameOfTheDirector_cardView, textViewFirstNameOfTheDirector_cardView, textViewTypeOfTheMovie_cardView, textViewDurationOfTheMovie_cardView, textViewProductionCompanies_cardView, textViewReleaseDate_cardView;

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

    public String getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(String idMovie) {
        this.idMovie = idMovie;
    }

    public String getTextViewTitleOfTheMovie_cardView() {
        return textViewTitleOfTheMovie_cardView;
    }

    public void setTextViewTitleOfTheMovie_cardView(String textViewTitleOfTheMovie_cardView) {
        this.textViewTitleOfTheMovie_cardView = textViewTitleOfTheMovie_cardView;
    }

    public String getTextViewNameOfTheDirector_cardView() {
        return textViewNameOfTheDirector_cardView;
    }

    public void setTextViewNameOfTheDirector_cardView(String textViewNameOfTheDirector_cardView) {
        this.textViewNameOfTheDirector_cardView = textViewNameOfTheDirector_cardView;
    }

    public String getTextViewFirstNameOfTheDirector_cardView() {
        return textViewFirstNameOfTheDirector_cardView;
    }

    public void setTextViewFirstNameOfTheDirector_cardView(String textViewFirstNameOfTheDirector_cardView) {
        this.textViewFirstNameOfTheDirector_cardView = textViewFirstNameOfTheDirector_cardView;
    }

    public String getTextViewTypeOfTheMovie_cardView() {
        return textViewTypeOfTheMovie_cardView;
    }

    public void setTextViewTypeOfTheMovie_cardView(String textViewTypeOfTheMovie_cardView) {
        this.textViewTypeOfTheMovie_cardView = textViewTypeOfTheMovie_cardView;
    }

    public String getTextViewDurationOfTheMovie_cardView() {
        return textViewDurationOfTheMovie_cardView;
    }

    public void setTextViewDurationOfTheMovie_cardView(String textViewDurationOfTheMovie_cardView) {
        this.textViewDurationOfTheMovie_cardView = textViewDurationOfTheMovie_cardView;
    }

    public String getTextViewProductionCompanies_cardView() {
        return textViewProductionCompanies_cardView;
    }

    public void setTextViewProductionCompanies_cardView(String textViewProductionCompanies_cardView) {
        this.textViewProductionCompanies_cardView = textViewProductionCompanies_cardView;
    }

    public String getTextViewReleaseDate_cardView() {
        return textViewReleaseDate_cardView;
    }

    public void setTextViewReleaseDate_cardView(String textViewReleaseDate_cardView) {
        this.textViewReleaseDate_cardView = textViewReleaseDate_cardView;
    }
}
