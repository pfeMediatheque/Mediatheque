package com.example.mediatheque;

public class Model {

    String id, mTitleOfTheBook, mNameOfTheAuthor, mFirstNameOfTheAuthor, mTypeOfTheBook, mNumberOfPages, mPublishersName, mDateOfPublication;

    public Model() { }

    public Model(String id, String mTitleOfTheBook, String mNameOfTheAuthor, String mFirstNameOfTheAuthor, String mTypeOfTheBook, String mNumberOfPages, String mPublishersName, String mDateOfPublication) {
        this.id = id;
        this.mTitleOfTheBook = mTitleOfTheBook;
        this.mNameOfTheAuthor = mNameOfTheAuthor;
        this.mFirstNameOfTheAuthor = mFirstNameOfTheAuthor;
        this.mTypeOfTheBook = mTypeOfTheBook;
        this.mNumberOfPages = mNumberOfPages;
        this.mPublishersName = mPublishersName;
        this.mDateOfPublication = mDateOfPublication;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getmTitleOfTheBook() {
        return mTitleOfTheBook;
    }

    public void setmTitleOfTheBook(String mTitleOfTheBook) {
        this.mTitleOfTheBook = mTitleOfTheBook;
    }

    public String getmNameOfTheAuthor() {
        return mNameOfTheAuthor;
    }

    public void setmNameOfTheAuthor(String mNameOfTheAuthor) {
        this.mNameOfTheAuthor = mNameOfTheAuthor;
    }

    public String getmFirstNameOfTheAuthor() {
        return mFirstNameOfTheAuthor;
    }

    public void setmFirstNameOfTheAuthor(String mFirstNameOfTheAuthor) {
        this.mFirstNameOfTheAuthor = mFirstNameOfTheAuthor;
    }

    public String getmTypeOfTheBook() {
        return mTypeOfTheBook;
    }

    public void setmTypeOfTheBook(String mTypeOfTheBook) {
        this.mTypeOfTheBook = mTypeOfTheBook;
    }

    public String getmNumberOfPages() {
        return mNumberOfPages;
    }

    public void setmNumberOfPages(String mNumberOfPages) {
        this.mNumberOfPages = mNumberOfPages;
    }

    public String getmPublishersName() {
        return mPublishersName;
    }

    public void setmPublishersName(String mPublishersName) {
        this.mPublishersName = mPublishersName;
    }

    public String getmDateOfPublication() {
        return mDateOfPublication;
    }

    public void setmDateOfPublication(String mDateOfPublication) {
        this.mDateOfPublication = mDateOfPublication;
    }
}
