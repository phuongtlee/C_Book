package com.example.c_book.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class LoginDatasource {
    private SQLiteDatabase database;
    private BookHelper bookHelper;
    private String[] allColumn = {BookHelper.TBL_USERNAME, BookHelper.TBL_PASSWORD};

    public LoginDatasource(Context context){
        bookHelper = new BookHelper(context);
    }

    public void open() throws SQLException {
        database = bookHelper.getReadableDatabase();
        database.execSQL("PRAGMA foreign_keys=ON;");
    }

    public void close(){
        bookHelper.close();
    }

    public boolean checkLogin(String username, String password) {
        String[] columns = {BookHelper.TBL_USER_ID};
        String selection = BookHelper.TBL_USERNAME + " = ?" + " AND " + BookHelper.TBL_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};
        Cursor cursor = database.query(BookHelper.TBL_USER, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        database.close();

        return count > 0;
    }

    public boolean register(String username, String password) {
        ContentValues values = new ContentValues();
        values.put(BookHelper.TBL_USERNAME, username);
        values.put(BookHelper.TBL_PASSWORD, password);

        if (userExists(username)) {
            return false;
        } else {
            long newRowId = database.insert(BookHelper.TBL_USER, null, values);
            return newRowId != -1;
        }
    }

    private boolean userExists(String username) {
        String[] columns = {BookHelper.TBL_USER_ID};
        String selection = BookHelper.TBL_USERNAME + " = ?";
        String[] selectionArgs = {username};
        Cursor cursor = database.query(BookHelper.TBL_USER, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }
}
