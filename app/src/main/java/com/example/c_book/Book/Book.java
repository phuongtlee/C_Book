package com.example.c_book.Book;

import java.io.Serializable;

public class Book implements Serializable {

    private int id;
    private String name;
    private String author;
    private int categoryId;

    public int getCategoryId() {
        return categoryId;
    }

    public void setIdCategory(int categoryId) {
        this.categoryId = categoryId;
    }

    private int resourceImg;
    private String script;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public int getResourceImg() {
        return resourceImg;
    }

    public void setResourceImg(int resourceImg) {
        this.resourceImg = resourceImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Book(int id, String name, String author, int categoryId, int resourceImg, String script) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.categoryId = categoryId;
        this.resourceImg = resourceImg;
        this.script = script;
    }

}
