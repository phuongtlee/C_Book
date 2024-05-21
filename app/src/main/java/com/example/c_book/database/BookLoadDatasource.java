package com.example.c_book.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.c_book.Book.Book;
import com.example.c_book.Category.Category;
import com.example.c_book.Favorite.Favorite;

import java.util.ArrayList;
import java.util.List;

public class BookLoadDatasource {

    private SQLiteDatabase database;
    private BookHelper bookHelper;
    private String[] allColumn = {BookHelper.TBL_ID_BOOK, BookHelper.TBL_NAME_BOOK, BookHelper.TBL_AUTHOR, BookHelper.TBL_BOOK_CATEGORY_ID
                                , BookHelper.TBL_IMAGE_PATH, BookHelper.TBL_SCRIPT};
    private List<Book> bookList;

    public BookLoadDatasource(Context context){
        bookHelper = new BookHelper(context);
    }

    public void open() throws SQLException {
        database = bookHelper.getReadableDatabase();
        database.execSQL("PRAGMA foreign_keys=ON;");
    }

    public void close(){
        bookHelper.close();
    }

    public List<Book> loadBooksForCategory(int categoryId) {
        List<Book> bookList = new ArrayList<>();

        String selection = BookHelper.TBL_BOOK_CATEGORY_ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(categoryId) };

        Cursor cursor = database.query(BookHelper.TBL_BOOK, allColumn, selection, selectionArgs, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Book book = cursorToBook(cursor);
            bookList.add(book);
            cursor.moveToNext();
        }

        cursor.close();
        return bookList;
    }

    public int getId(String bookName) {
        int bookId = -1;

        String selection = BookHelper.TBL_NAME_BOOK + "=?";
        String[] selectionArgs = {bookName};

        Cursor cursor = database.query(BookHelper.TBL_BOOK, new String[]{BookHelper.TBL_ID_BOOK}, selection, selectionArgs, null, null, null);;

        if (cursor.moveToFirst()) {
            bookId = cursor.getInt(cursor.getColumnIndex(BookHelper.TBL_ID_BOOK));
        }
        cursor.close();

        return bookId;
    }

    public String getImagePathForBookId(int bookId) {
        SQLiteDatabase database = bookHelper.getReadableDatabase();
        String[] columns = {BookHelper.TBL_IMAGE_PATH};
        String selection = BookHelper.TBL_ID_BOOK + " = ?";
        String[] selectionArgs = {String.valueOf(bookId)};

        Cursor cursor = database.query(BookHelper.TBL_BOOK, columns, selection, selectionArgs, null, null, null);
        String imagePath = null;

        if (cursor.moveToFirst()) {
            imagePath = cursor.getString(cursor.getColumnIndex(BookHelper.TBL_IMAGE_PATH));
        }

        cursor.close();
        return imagePath;
    }

    private Book cursorToBook(Cursor cursor){
        Book book = new Book(cursor.getInt(0), cursor.getString(1), cursor.getString(2),cursor.getInt(3), cursor.getInt(4) ,cursor.getString(5));
        return book;
    }

    public List<Book> searchBooks(String query) {
        List<Book> bookList = new ArrayList<>();

        String selection = BookHelper.TBL_NAME_BOOK + " LIKE ? OR " + BookHelper.TBL_AUTHOR + " LIKE ?";
        String[] selectionArgs = { "%" + query + "%", "%" + query + "%" };

        Cursor cursor = database.query(BookHelper.TBL_BOOK, allColumn, selection, selectionArgs, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Book book = cursorToBook(cursor);
            bookList.add(book);
            cursor.moveToNext();
        }

        cursor.close();
        return bookList;
    }
}
