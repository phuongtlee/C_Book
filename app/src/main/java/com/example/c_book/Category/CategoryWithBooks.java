package com.example.c_book.Category;

import com.example.c_book.Book.Book;

import java.util.List;

public class CategoryWithBooks {
    private Category category;
    private List<Book> bookList;

    public CategoryWithBooks(Category category, List<Book> bookList) {
        this.category = category;
        this.bookList = bookList;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
