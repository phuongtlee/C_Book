package com.example.c_book;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.c_book.Book.Book;
import com.example.c_book.Favorite.Favorite;
import com.example.c_book.History.History;
import com.example.c_book.database.BookLoadDatasource;
import com.example.c_book.database.ContentDatasource;
import com.example.c_book.database.FavoriteDatasource;
import com.example.c_book.database.HistoryDatasource;

import java.util.Date;
import java.util.Locale;

public class BookInfo extends AppCompatActivity {

    ContentDatasource contentDatasource;
    BookLoadDatasource bookLoadDatasource;
    FavoriteDatasource favoriteDataSource;
    HistoryDatasource historyDatasource;
    TextView tvName;
    private boolean isFavorite = false;
    Button btnFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);

        ImageView imageView = findViewById(R.id.img_book_info);
        tvName = findViewById(R.id.tv_name_book);
        TextView tvNameAuthor = findViewById(R.id.tv_name_tg);
        TextView tvNameCategory = findViewById(R.id.tv_name_tl);
        TextView tvSummary = findViewById(R.id.tv_name_info);


        ImageView btnBack = findViewById(R.id.ic_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageView mImageViewSearch = findViewById(R.id.ic_search);
        mImageViewSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        String bookName = intent.getStringExtra("name");
        String author = intent.getStringExtra("author");
        String category = intent.getStringExtra("categoryName");
        String summary = intent.getStringExtra("script");
        int imagePath = intent.getIntExtra("resourceImg", R.drawable.avt_img1);


        tvName.setText(bookName);
        tvNameAuthor.setText(author);
        tvNameCategory.setText(category);
        tvSummary.setText(summary);
        imageView.setImageResource(imagePath);

        Button btnReadFirst = findViewById(R.id.btn_read_from_scratch);
        contentDatasource = new ContentDatasource(this);
        contentDatasource.open();

        historyDatasource = new HistoryDatasource(this);
        historyDatasource.open();

        bookLoadDatasource = new BookLoadDatasource(this);
        bookLoadDatasource.open();
        btnReadFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedBookId = bookLoadDatasource.getId(tvName.getText().toString());
                String nameBook = tvName.getText().toString().trim();
                String author = tvNameAuthor.getText().toString();
                int categoryId = favoriteDataSource.getCategoryId(category);
                String imagePath = bookLoadDatasource.getImagePathForBookId(selectedBookId);
                String script = tvSummary.getText().toString();
                String timestamp = getCurrentDateTime();
                String username = getUsernameFromSharedPreferences(getApplicationContext());

                Intent intent = new Intent(getApplicationContext(), ReadBook.class);
                intent.putExtra("selectedBook", nameBook);
                intent.putExtra("chapterNumber", 1);
                String bookContent = contentDatasource.loadContent(selectedBookId);
                intent.putExtra("bookContent", bookContent);
                intent.putExtra("bookId", selectedBookId);

                if (categoryId != -1) {
                    History history = new History(selectedBookId, nameBook, author, categoryId, Integer.parseInt(imagePath), script, String.valueOf(timestamp));
                    history.setId(selectedBookId);
                    history.setName(nameBook);
                    history.setAuthor(author);
                    history.setCategoryId(categoryId);
                    history.setResourceImg(Integer.parseInt(imagePath));
                    history.setScript(script);
                    history.setTimestamp(timestamp);
                    historyDatasource.addHistory(username, history);
                }
                startActivity(intent);
            }
        });

        Button btnReadContinue = findViewById(R.id.btn_read_continue);
        btnReadContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameBook = tvName.getText().toString().trim();
                int selectedBookId = bookLoadDatasource.getId(nameBook);
                int lastReadChapter = getLastReadChapter(selectedBookId);
                String author = tvNameAuthor.getText().toString();
                int categoryId = favoriteDataSource.getCategoryId(category);
                String imagePath = bookLoadDatasource.getImagePathForBookId(selectedBookId);
                String script = tvSummary.getText().toString();
                String timestamp = getCurrentDateTime();
                String username = getUsernameFromSharedPreferences(getApplicationContext());

                Intent intent = new Intent(getApplicationContext(), ReadBook.class);
                intent.putExtra("selectedBook", nameBook);
                String bookContent = contentDatasource.loadContent(selectedBookId);
                intent.putExtra("chapterNumber", lastReadChapter);
                intent.putExtra("bookContent", bookContent);
                intent.putExtra("bookId", selectedBookId);

                if (categoryId != -1) {
                    History history = new History(selectedBookId, nameBook, author, categoryId, Integer.parseInt(imagePath), script, String.valueOf(timestamp));
                    history.setId(selectedBookId);
                    history.setName(nameBook);
                    history.setAuthor(author);
                    history.setCategoryId(categoryId);
                    history.setResourceImg(Integer.parseInt(imagePath));
                    history.setScript(script);
                    history.setTimestamp(timestamp);
                    historyDatasource.addHistory(username, history);
                }

                startActivity(intent);
            }
        });

        favoriteDataSource = new FavoriteDatasource(this);
        favoriteDataSource.open();

        btnFavorite = findViewById(R.id.btn_favorite);

        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameBook = tvName.getText().toString().trim();
                int selectedBookId = bookLoadDatasource.getId(nameBook);
                String author = tvNameAuthor.getText().toString();
                int categoryId = favoriteDataSource.getCategoryId(category);
                String imagePath = bookLoadDatasource.getImagePathForBookId(selectedBookId);
                String script = tvSummary.getText().toString();
                String username = getUsernameFromSharedPreferences(getApplicationContext());
                if (categoryId != -1){
                    isFavorite = favoriteDataSource.isFavorite(username, selectedBookId);
                    if (isFavorite) {
                        favoriteDataSource.removeFavorite(username, selectedBookId);
                        isFavorite = false;
                    } else {
                        Favorite favorite = new Favorite(selectedBookId, nameBook, author,categoryId,Integer.parseInt(imagePath), script);
                        favorite.setId(selectedBookId);
                        favorite.setName(nameBook);
                        favorite.setAuthor(author);
                        favorite.setCategoryId(categoryId);
                        favorite.setResourceImg(Integer.parseInt(imagePath));
                        favorite.setScript(script);
                        favoriteDataSource.addFavorite(username, favorite);
                        isFavorite = true;
                    }
                    updateFavoriteButton(btnFavorite);
                }
            }
        });
    }

    private int getLastReadChapter(int bookId) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyBookReader", Context.MODE_PRIVATE);
        return sharedPreferences.getInt("lastReadPage_" + bookId, 1);
    }

    private void updateFavoriteButton(Button button) {
        if (isFavorite) {
            Toast.makeText(getApplicationContext(), "Đã thêm vào yêu thích", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(getApplicationContext(), "Đã xóa khỏi yêu thích", Toast.LENGTH_SHORT).show();
    }
    private String getCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    public String getUsernameFromSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE);

        String username = sharedPreferences.getString("username", "");
        return username;
    }

}