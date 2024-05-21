package com.example.c_book.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.c_book.Book.Book;
import com.example.c_book.Category.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryDatasource {

    private SQLiteDatabase database;
    private BookHelper bookHelper;
    private String[] allColumn = {BookHelper.TBL_ID_CATEGORY, BookHelper.TBL_NAME_CATEGORY};

    public CategoryDatasource(Context context){
        bookHelper = new BookHelper(context);
    }

    public void open() throws SQLException {
        database = bookHelper.getReadableDatabase();
    }

    public void close(){
        bookHelper.close();
    }

    public List<Category> loadCategories(){
        List<Category> categoryList = new ArrayList<Category>();

        Cursor cursor = database.query(BookHelper.TBL_CATEGORY, allColumn, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Category category = cursorToCategory(cursor);
            categoryList.add(category);
            cursor.moveToNext();
        }
        cursor.close();
        return categoryList;
    }


    private Category cursorToCategory(Cursor cursor){
        int categoryId = cursor.getInt(cursor.getColumnIndexOrThrow(BookHelper.TBL_ID_CATEGORY));
        String categoryName = cursor.getString(cursor.getColumnIndexOrThrow(BookHelper.TBL_NAME_CATEGORY));

        Category category = new Category(categoryId, categoryName, null);
        return category;
    }

}
