package com.example.c_book.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.c_book.Book.Book;
import com.example.c_book.Category.Category;
import com.example.c_book.Favorite.Favorite;

import java.util.ArrayList;
import java.util.List;

public class FavoriteDatasource {

    private final Context context;
    private SQLiteDatabase database;
    private BookHelper bookHelper;
    private String[] allColumn = {BookHelper.TBL_ID_BOOK_FAVORITE,BookHelper.TBL_NAME_BOOK_FAVORITE, BookHelper.TBL_AUTHOR_FAVORITE, BookHelper.TBL_BOOK_CATEGORY_ID_FAVORITE
            , BookHelper.TBL_IMAGE_PATH_FAVORITE, BookHelper.TBL_SCRIPT_FAVORITE};

    public FavoriteDatasource(Context context){
        this.context = context;
        bookHelper = new BookHelper(context);
    }

    public void open() throws SQLException {
        database = bookHelper.getReadableDatabase();
        database.execSQL("PRAGMA foreign_keys=ON;");
    }

    public void close(){
        bookHelper.close();
    }

    public List<Favorite> getFavoriteItems(String username) {
        List<Favorite> favoriteItems = new ArrayList<>();
        Cursor cursor = database.query(BookHelper.TBL_FAVORITE, allColumn, BookHelper.TBL_USERNAME_FAVORITE + " = ?", new String[]{username}, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Favorite favorite = cursorToFavorite(cursor);
            favoriteItems.add(favorite);
            cursor.moveToNext();
        }
        cursor.close();
        return favoriteItems;
    }


    public boolean isFavorite(String username, long bookId) {
        Cursor cursor = database.query(
                BookHelper.TBL_FAVORITE,
                null,
                "username = ? AND id = ?",
                new String[]{username, String.valueOf(bookId)},
                null,
                null,
                null
        );
        boolean isFavorite = cursor.getCount() > 0;
        cursor.close();
        return isFavorite;
    }


    public void addFavorite(String username, Favorite favorite) {
        ContentValues values = new ContentValues();
        values.put(BookHelper.TBL_USERNAME_FAVORITE, username);
        values.put(BookHelper.TBL_ID_BOOK_FAVORITE, favorite.getId());
        values.put(BookHelper.TBL_NAME_BOOK_FAVORITE, favorite.getName());
        values.put(BookHelper.TBL_AUTHOR_FAVORITE, favorite.getAuthor());
        values.put(BookHelper.TBL_BOOK_CATEGORY_ID_FAVORITE, favorite.getCategoryId());
        values.put(BookHelper.TBL_IMAGE_PATH_FAVORITE, favorite.getResourceImg());
        values.put(BookHelper.TBL_SCRIPT_FAVORITE, favorite.getScript());
        database.insert(BookHelper.TBL_FAVORITE, null, values);
    }


    public void removeFavorite(String username, long bookId) {
        database.delete(bookHelper.TBL_FAVORITE, "username = ? AND id = ?", new String[]{username, String.valueOf(bookId)});
    }


    private Favorite cursorToFavorite(Cursor cursor){
        Favorite favorite = new Favorite(cursor.getInt(0), cursor.getString(1), cursor.getString(2),cursor.getInt(3), cursor.getInt(4) ,cursor.getString(5));
        return favorite;
    }

    public int getCategoryId(String categoryName) {
        String query = "SELECT " + BookHelper.TBL_ID_CATEGORY +
                " FROM " + BookHelper.TBL_CATEGORY +
                " WHERE " + BookHelper.TBL_NAME_CATEGORY + " = ?";
        Cursor cursor = database.rawQuery(query, new String[] { categoryName });
        int categoryId = -1; // Giá trị mặc định nếu không tìm thấy
        if (cursor.moveToFirst()) {
            categoryId = cursor.getInt(0);
        }
        cursor.close();
        return categoryId;
    }

    public String getCategoryName(int categoryId) {
        String categoryName = null;
        Cursor cursor = null;
        try {
            cursor = database.rawQuery("SELECT " + BookHelper.TBL_NAME_CATEGORY + " FROM " + BookHelper.TBL_CATEGORY + " WHERE " + BookHelper.TBL_ID_CATEGORY + " = ?", new String[]{String.valueOf(categoryId)});
            if (cursor.moveToFirst()) {
                categoryName = cursor.getString(cursor.getColumnIndex(BookHelper.TBL_NAME_CATEGORY));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return categoryName;
    }

}
