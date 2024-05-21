package com.example.c_book.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c_book.Book.Book;
import com.example.c_book.Book.BookAdapter;
import com.example.c_book.BookInfo;
import com.example.c_book.Category.Category;
import com.example.c_book.Category.CategoryAdapter;
import com.example.c_book.Category.CategoryWithBooks;
import com.example.c_book.Favorite.Favorite;
import com.example.c_book.OnItemClickListener;
import com.example.c_book.R;
import com.example.c_book.database.BookLoadDatasource;
import com.example.c_book.database.CategoryDatasource;
import com.example.c_book.database.FavoriteDatasource;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private View v;
    private RecyclerView rcvCategory;
    private BookLoadDatasource bookLoadDatasource;

    private CategoryAdapter categoryAdapter;
    private List<Category> categoryList;
    private CategoryDatasource categoryDatasource;

    private List<CategoryWithBooks> categoryWithBooksList = new ArrayList<>();
    Spinner spinner;
    BookAdapter bookAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_home, container, false);

        rcvCategory = v.findViewById(R.id.rcv_book_category);
        spinner = v.findViewById(R.id.spinner);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(v.getContext(), RecyclerView.VERTICAL, false);
        rcvCategory.setLayoutManager(linearLayoutManager);

        categoryDatasource = new CategoryDatasource(v.getContext());
        categoryDatasource.open();

        List<Category> categories = categoryDatasource.loadCategories();
        ArrayList<String> categoryNames = new ArrayList<>();
        categoryNames.add("Mời chọn thể loại");
        for (Category category : categories) {
            categoryNames.add(category.getNameCategory());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, categoryNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        bookAdapter = new BookAdapter(getContext());
        rcvCategory.setAdapter(bookAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(v.getContext(), RecyclerView.VERTICAL, false);
                    rcvCategory.setLayoutManager(linearLayoutManager);
                    categoryAdapter.setData(categories);
                    rcvCategory.setAdapter(categoryAdapter);
                    loadCategories();
                } else {
                    int categoryId = categoryList.get(position - 1).getIdCategory();
                    List<Book> books = bookLoadDatasource.loadBooksForCategory(categoryId);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(v.getContext(), 3);
                    rcvCategory.setLayoutManager(gridLayoutManager);
                    bookAdapter.setData(books);
                    rcvCategory.setAdapter(bookAdapter);
                    bookAdapter.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            Book selectedBook = books.get(position);
                            if (selectedBook != null) {
                                FavoriteDatasource favoriteDatasource = new FavoriteDatasource(getContext());
                                favoriteDatasource.open();
                                String categoryName = favoriteDatasource.getCategoryName(selectedBook.getCategoryId());
                                favoriteDatasource.close();

                                Intent intent = new Intent(getContext(), BookInfo.class);
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

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bookLoadDatasource = new BookLoadDatasource(v.getContext());
        bookLoadDatasource.open();

        for (Category category : categories) {
            List<Book> books = bookLoadDatasource.loadBooksForCategory(category.getIdCategory());

            CategoryWithBooks categoryWithBooks = new CategoryWithBooks(category, books);
            categoryWithBooksList.add(categoryWithBooks);
        }

        categoryAdapter = new CategoryAdapter(getContext());
        rcvCategory.setAdapter(categoryAdapter);

        loadCategories();

        return v;
    }

    private void loadCategories() {
        categoryList = categoryDatasource.loadCategories();
        categoryAdapter.setData(categoryList);

        for (CategoryWithBooks categoryWithBooks : categoryWithBooksList) {
            int categoryId = categoryWithBooks.getCategory().getIdCategory();
            List<Book> books = categoryWithBooks.getBookList();
            categoryAdapter.setBooksForCategory(categoryId, books);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bookLoadDatasource.close();
    }
}
