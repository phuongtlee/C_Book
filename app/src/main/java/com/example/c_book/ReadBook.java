package com.example.c_book;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.c_book.database.ContentDatasource;

public class ReadBook extends AppCompatActivity {

    ContentDatasource contentDatasource;
    int currentChapter = 1;
    Button btnPrev, btnNext;
    private int maxChapter;
    String nameBook, bookContent;
    int chapterNumber, bookId, lastReadChapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_book);

        TextView tvNameBook = findViewById(R.id.tv_name_book);
        TextView tvChapter = findViewById(R.id.tv_chapter);
        TextView tvChapterContent = findViewById(R.id.tv_content_book);
        btnPrev = findViewById(R.id.btn_prev);
        btnNext = findViewById(R.id.btn_next);
        ImageButton icBack= findViewById(R.id.ic_back);

        nameBook = getIntent().getStringExtra("selectedBook");
        chapterNumber = getIntent().getIntExtra("chapterNumber", 1);
        bookContent = getIntent().getStringExtra("bookContent");
        bookId = getIntent().getIntExtra("bookId", 1);
        lastReadChapter = getIntent().getIntExtra("chapterNumber", 1);
        tvChapterContent.setMovementMethod(new ScrollingMovementMethod());

        icBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveLastReadPage(bookId, currentChapter);
                finish();
            }
        });


        contentDatasource = new ContentDatasource(this);
        contentDatasource.open();

        tvNameBook.setText(nameBook);
        tvChapter.setText(String.valueOf(chapterNumber));
        tvChapterContent.setText(bookContent);
        maxChapter = contentDatasource.getChapterCount(bookId);

        ImageView btnSearch = findViewById(R.id.ic_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentChapter < maxChapter){
                    currentChapter++;
                    String newChapterContent = contentDatasource.getChapterContent(bookId, currentChapter);
                    tvChapter.setText(String.valueOf(currentChapter));
                    tvChapterContent.setText(newChapterContent);
                    updateButtonStates();
                }
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentChapter > 1) {
                    currentChapter--;
                    String newChapterContent = contentDatasource.getChapterContent(bookId, currentChapter);
                    tvChapter.setText(String.valueOf(currentChapter));
                    tvChapterContent.setText(newChapterContent);
                    updateButtonStates();
                }
            }
        });

        String chapterContent = contentDatasource.getChapterContent(bookId, lastReadChapter);
        currentChapter = lastReadChapter;
        tvChapter.setText(String.valueOf(currentChapter));
        tvChapterContent.setText(chapterContent);
        updateButtonStates();
    }
    private void updateButtonStates() {
        if (currentChapter == 1) {
            btnPrev.setEnabled(false);
        } else {
            btnPrev.setEnabled(true);
        }

        if (currentChapter == maxChapter) {
            btnNext.setEnabled(false);
        } else {
            btnNext.setEnabled(true);
        }
    }
    private void saveLastReadPage(int bookId, int lastPage) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyBookReader", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("lastReadPage_" + bookId, lastPage);
        editor.apply();
    }

    @Override
    public void onBackPressed() {
        saveLastReadPage(bookId, currentChapter);
        super.onBackPressed();
    }
}