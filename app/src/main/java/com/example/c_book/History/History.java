package com.example.c_book.History;

import com.example.c_book.Category.Category;

public class History {
    private int id;
    private String name;
    private String author;
    private int categoryId;
    private int resourceImg;
    private String script;
    private String timestamp;

    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public History(int id, String name, String author, int categoryId, int resourceImg, String script, String timestamp) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.categoryId = categoryId;
        this.resourceImg = resourceImg;
        this.script = script;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getResourceImg() {
        return resourceImg;
    }

    public void setResourceImg(int resourceImg) {
        this.resourceImg = resourceImg;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
