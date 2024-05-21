package com.example.c_book;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.c_book.Book.Book;
import com.example.c_book.Book.BookAdapter;
import com.example.c_book.database.BookLoadDatasource;
import com.example.c_book.database.FavoriteDatasource;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements TextWatcher {

    private ImageButton imageBack, imageSearch;
    AutoCompleteTextView edtSearch;
    BookAdapter bookAdapter;
    BookLoadDatasource bookLoadDatasource;
    RecyclerView rcvSearch;
    ArrayList<Book> arrayList = new ArrayList<Book>();

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        imageSearch = findViewById(R.id.ic_search);
        imageBack = findViewById(R.id.ic_back);
        edtSearch = findViewById(R.id.edt_search);
        rcvSearch = findViewById(R.id.rcv_book_search);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        rcvSearch.setLayoutManager(gridLayoutManager);

        bookAdapter = new BookAdapter(this);
        rcvSearch.setAdapter(bookAdapter);

        ArrayList<String> listName = new ArrayList<>();
        for (Book i : arrayList){
            String name = i.getName();
            String author = i.getAuthor();
            listName.add(name);
            listName.add(author);
        }

        edtSearch.addTextChangedListener( this);
        edtSearch.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listName));

        bookLoadDatasource = new BookLoadDatasource(this);
        bookLoadDatasource.open();

        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imageSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameOrAuthor = edtSearch.getText().toString();
                performSearch(nameOrAuthor);
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        String nameOrAuthor = edtSearch.getText().toString();
        List<Book> searchResults = new ArrayList<>();

        if (nameOrAuthor.isEmpty()) {
            bookAdapter.setData(searchResults);
            rcvSearch.setAdapter(bookAdapter);
            bookAdapter.notifyDataSetChanged();
        } else{
            performSearch(nameOrAuthor);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    private void performSearch(String nameOrAuthor) {
        List<Book> searchResults = bookLoadDatasource.searchBooks(nameOrAuthor);
        bookAdapter.setData(searchResults);
        bookAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Book selectedBook = searchResults.get(position);

                if (selectedBook != null) {

                    FavoriteDatasource favoriteDatasource = new FavoriteDatasource(context);
                    favoriteDatasource.open();
                    String categoryName = favoriteDatasource.getCategoryName(selectedBook.getCategoryId());
                    favoriteDatasource.close();

                    Intent intent = new Intent(SearchActivity.this, BookInfo.class);
                    intent.putExtra("id", selectedBook.getId());
                    intent.putExtra("name", selectedBook.getName());
                    intent.putExtra("author", selectedBook.getAuthor());
                    intent.putExtra("categoryName", categoryName);
                    intent.putExtra("resourceImg", selectedBook.getResourceImg());
                    intent.putExtra("script", selectedBook.getScript());

                    startActivity(intent);
                }
            }
        });
        bookAdapter.notifyDataSetChanged();
    }

}