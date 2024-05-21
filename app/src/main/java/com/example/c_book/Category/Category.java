package com.example.c_book.Category;

import com.example.c_book.Book.Book;

import java.util.List;

public class Category {

    private int categoryId;

    public int getIdCategory() {
        return categoryId;
    }

    public void setIdCategory(int categoryId) {
        this.categoryId = categoryId;
    }

    private String nameCategory;
    private List<Book> bookList;

    public Category(int categoryId, String nameCategory, List<Book> bookList) {
        this.categoryId = categoryId;
        this.nameCategory = nameCategory;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }
}
