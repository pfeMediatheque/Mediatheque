package com.example.mediatheque.ui.book;

public class BookModel {

    String idBook,textViewTitleOfTheBook_cardView, textViewNameOfTheAuthor_cardView, textViewFirstNameOfTheAuthor_cardView, textViewTypeOfTheBook_cardView, textViewNumberOfPages_cardView, textViewPublishersName_cardView, textViewDateOfPublication_cardView;

    public BookModel() { }

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

    public void setIdBook(String idBook) {
        this.idBook = idBook;
    }

    public String getTextViewTitleOfTheBook_cardView() {
        return textViewTitleOfTheBook_cardView;
    }

    public void setTextViewTitleOfTheBook_cardView(String textViewTitleOfTheBook_cardView) {
        this.textViewTitleOfTheBook_cardView = textViewTitleOfTheBook_cardView;
    }

    public String getTextViewNameOfTheAuthor_cardView() {
        return textViewNameOfTheAuthor_cardView;
    }

    public void setTextViewNameOfTheAuthor_cardView(String textViewNameOfTheAuthor_cardView) {
        this.textViewNameOfTheAuthor_cardView = textViewNameOfTheAuthor_cardView;
    }

    public String getTextViewFirstNameOfTheAuthor_cardView() {
        return textViewFirstNameOfTheAuthor_cardView;
    }

    public void setTextViewFirstNameOfTheAuthor_cardView(String textViewFirstNameOfTheAuthor_cardView) {
        this.textViewFirstNameOfTheAuthor_cardView = textViewFirstNameOfTheAuthor_cardView;
    }

    public String getTextViewTypeOfTheBook_cardView() {
        return textViewTypeOfTheBook_cardView;
    }

    public void setTextViewTypeOfTheBook_cardView(String textViewTypeOfTheBook_cardView) {
        this.textViewTypeOfTheBook_cardView = textViewTypeOfTheBook_cardView;
    }

    public String getTextViewNumberOfPages_cardView() {
        return textViewNumberOfPages_cardView;
    }

    public void setTextViewNumberOfPages_cardView(String textViewNumberOfPages_cardView) {
        this.textViewNumberOfPages_cardView = textViewNumberOfPages_cardView;
    }

    public String getTextViewPublishersName_cardView() {
        return textViewPublishersName_cardView;
    }

    public void setTextViewPublishersName_cardView(String textViewPublishersName_cardView) {
        this.textViewPublishersName_cardView = textViewPublishersName_cardView;
    }

    public String getTextViewDateOfPublication_cardView() {
        return textViewDateOfPublication_cardView;
    }

    public void setTextViewDateOfPublication_cardView(String textViewDateOfPublication_cardView) {
        this.textViewDateOfPublication_cardView = textViewDateOfPublication_cardView;
    }
}
