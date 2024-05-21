package com.example.c_book.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ContentDatasource {

    private SQLiteDatabase database;
    private BookHelper bookHelper;
    private String[] allColumn = { BookHelper.TBL_BOOK_ID, BookHelper.TBL_BOOK_CONTENT_CHAP, BookHelper.TBL_BOOK_PAGE_CONTENT};

    public ContentDatasource(Context context){
        bookHelper = new BookHelper(context);
    }

    public void open() throws SQLException {
        database = bookHelper.getReadableDatabase();
        database.execSQL("PRAGMA foreign_keys=ON;");
    }

    public void close(){
        bookHelper.close();
    }


    public String loadContent(int bookId) {
        String query = "SELECT " + BookHelper.TBL_BOOK_PAGE_CONTENT + " FROM " + BookHelper.TBL_BOOK_CONTENT +
                " WHERE " + BookHelper.TBL_BOOK_ID + " = ?";
        String[] selectionArgs = {String.valueOf(bookId)};

        Cursor cursor = database.rawQuery(query, selectionArgs);
        String bookContent = "";

        if (cursor.moveToFirst()) {
            bookContent = cursor.getString(cursor.getColumnIndex(BookHelper.TBL_BOOK_PAGE_CONTENT));
        }

        cursor.close();

        return bookContent;
    }

    public String getChapterContent(int bookId, int chapterNumber) {
        String content = null;
        String[] columns = {BookHelper.TBL_BOOK_PAGE_CONTENT};
        String selection = BookHelper.TBL_BOOK_ID + " = ? AND " + BookHelper.TBL_BOOK_CONTENT_CHAP + " = ?";
        String[] selectionArgs = {String.valueOf(bookId), String.valueOf(chapterNumber)};

        Cursor cursor = database.query(
                BookHelper.TBL_BOOK_CONTENT,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            content = cursor.getString(cursor.getColumnIndex(BookHelper.TBL_BOOK_PAGE_CONTENT));
            cursor.close();
        }

        return content;
    }

    public int getChapterCount(int bookId) {
        int chapterCount = 0;
        String query = "SELECT COUNT(*) FROM " + BookHelper.TBL_BOOK_CONTENT +
                " WHERE " + BookHelper.TBL_BOOK_ID + " = ?";
        String[] selectionArgs = { String.valueOf(bookId) };

        Cursor cursor = database.rawQuery(query, selectionArgs);

        if (cursor.moveToFirst()) {
            chapterCount = cursor.getInt(0);
        }

        cursor.close();
        return chapterCount;
    }

}
