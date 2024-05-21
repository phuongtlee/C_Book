package com.example.c_book.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.c_book.History.History;

import java.util.ArrayList;
import java.util.List;

public class HistoryDatasource {
    private SQLiteDatabase database;
    private BookHelper bookHelper;
    private String[] allColumn = {BookHelper.TBL_ID_BOOK_HISTORY,BookHelper.TBL_NAME_BOOK_HISTORY, BookHelper.TBL_AUTHOR_HISTORY, BookHelper.TBL_BOOK_CATEGORY_ID_HISTORY
            , BookHelper.TBL_IMAGE_PATH_HISTORY, BookHelper.TBL_SCRIPT_HISTORY, BookHelper.TBL_TIME_HISTORY};

    public HistoryDatasource(Context context){
        bookHelper = new BookHelper(context);
    }

    public void open() throws SQLException {
        database = bookHelper.getWritableDatabase();
        database.execSQL("PRAGMA foreign_keys=ON;");
    }

    public void close(){
        bookHelper.close();
    }

    public List<History> loadHistoryBook(String username){
        List<History> historyList = new ArrayList<History>();

        String query = "SELECT * FROM " + BookHelper.TBL_HISTORY +
                " WHERE " + BookHelper.TBL_USERNAME_HISTORY + " = ?" +
                " ORDER BY " + BookHelper.TBL_TIME_HISTORY + " DESC";

        Cursor cursor = database.rawQuery(query, new String[]{username});

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            History history = cursorToBook(cursor);
            historyList.add(history);
            cursor.moveToNext();
        }
        cursor.close();
        return historyList;
    }

    public void addHistory(String username, History history) {
        ContentValues values = new ContentValues();
        values.put(BookHelper.TBL_ID_BOOK_HISTORY, history.getId());
        values.put(BookHelper.TBL_NAME_BOOK_HISTORY, history.getName());
        values.put(BookHelper.TBL_AUTHOR_HISTORY, history.getAuthor());
        values.put(BookHelper.TBL_BOOK_CATEGORY_ID_HISTORY, history.getCategoryId());
        values.put(BookHelper.TBL_IMAGE_PATH_HISTORY, history.getResourceImg());
        values.put(BookHelper.TBL_SCRIPT_HISTORY, history.getScript());
        values.put(BookHelper.TBL_TIME_HISTORY, history.getTimestamp());
        values.put(BookHelper.TBL_USERNAME_HISTORY, username);

        database.insert(BookHelper.TBL_HISTORY, null, values);
    }

    private History cursorToBook(Cursor cursor){
        History history = new History(cursor.getInt(0), cursor.getString(1), cursor.getString(2),cursor.getInt(3), cursor.getInt(4) ,cursor.getString(5), cursor.getString(6));
        return history;
    }
}
