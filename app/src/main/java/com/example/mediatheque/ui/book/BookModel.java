package com.example.mediatheque.ui.book;

public class BookModel {

    final String idBook;
    final String textViewTitleOfTheBook_cardView;
    final String textViewNameOfTheAuthor_cardView;
    final String textViewFirstNameOfTheAuthor_cardView;
    final String textViewTypeOfTheBook_cardView;
    final String textViewNumberOfPages_cardView;
    final String textViewPublishersName_cardView;
    final String textViewDateOfPublication_cardView;

    public BookModel(String idBook, String textViewTitleOfTheBook_cardView, String textViewNameOfTheAuthor_cardView, String textViewFirstNameOfTheAuthor_cardView, String textViewTypeOfTheBook_cardView, String textViewNumberOfPages_cardView, String textViewPublishersName_cardView, String textViewDateOfPublication_cardView) {
        this.idBook = idBook;
        this.textViewTitleOfTheBook_cardView = textViewTitleOfTheBook_cardView;
        this.textViewNameOfTheAuthor_cardView = textViewNameOfTheAuthor_cardView;
        this.textViewFirstNameOfTheAuthor_cardView = textViewFirstNameOfTheAuthor_cardView;
        this.textViewTypeOfTheBook_cardView = textViewTypeOfTheBook_cardView;
        this.textViewNumberOfPages_cardView = textViewNumberOfPages_cardView;
        this.textViewPublishersName_cardView = textViewPublishersName_cardView;
        this.textViewDateOfPublication_cardView = textViewDateOfPublication_cardView;
    }

    public String getIdBook() {
        return idBook;
    }

    public String getTextViewTitleOfTheBook_cardView() {
        return textViewTitleOfTheBook_cardView;
    }

    public String getTextViewNameOfTheAuthor_cardView() {
        return textViewNameOfTheAuthor_cardView;
    }

    public String getTextViewFirstNameOfTheAuthor_cardView() { return textViewFirstNameOfTheAuthor_cardView; }

    public String getTextViewTypeOfTheBook_cardView() {
        return textViewTypeOfTheBook_cardView;
    }

    public String getTextViewNumberOfPages_cardView() {
        return textViewNumberOfPages_cardView;
    }

    public String getTextViewPublishersName_cardView() {
        return textViewPublishersName_cardView;
    }

    public String getTextViewDateOfPublication_cardView() { return textViewDateOfPublication_cardView; }

}
