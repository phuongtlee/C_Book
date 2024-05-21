package com.example.c_book.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c_book.BookInfo;
import com.example.c_book.Category.Category;
import com.example.c_book.Favorite.Favorite;
import com.example.c_book.Favorite.FavoriteAdapter;
import com.example.c_book.R;
import com.example.c_book.database.FavoriteDatasource;

import java.util.List;

public class FavoriteFragment extends Fragment {

    RecyclerView rcvFavorite;
    FavoriteDatasource favoriteDatasource;

    FavoriteAdapter favoriteAdapter;

    private View v;
    Category category;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_favorite, container, false);

        rcvFavorite = v.findViewById(R.id.rcv_book_favorite);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(v.getContext(), 3);
        rcvFavorite.setLayoutManager(gridLayoutManager);

        favoriteDatasource = new FavoriteDatasource(getContext());
        favoriteDatasource.open();

        favoriteAdapter = new FavoriteAdapter(getContext());
        favoriteAdapter.setData(loadFavorite());
        favoriteAdapter.setOnItemClickListener(new FavoriteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Favorite selectedFavorite = favoriteAdapter.getFavoriteAt(position);
                if (selectedFavorite != null) {
                    FavoriteDatasource favoriteDatasource = new FavoriteDatasource(getContext());
                    favoriteDatasource.open();
                    String categoryName = favoriteDatasource.getCategoryName(selectedFavorite.getCategoryId());
                    favoriteDatasource.close();

                    Intent intent = new Intent(getContext(), BookInfo.class);
                    intent.putExtra("id", selectedFavorite.getId());
                    intent.putExtra("name", selectedFavorite.getName());
                    intent.putExtra("author", selectedFavorite.getAuthor());
                    intent.putExtra("categoryName", categoryName);
                    intent.putExtra("resourceImg", selectedFavorite.getResourceImg());
                    intent.putExtra("script", selectedFavorite.getScript());
                    startActivity(intent);
                }
            }
        });


        rcvFavorite.setAdapter(favoriteAdapter);
        loadFavorite();

        return v;
    }

    public String getUsernameFromSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        return username;
    }

    private List<Favorite> loadFavorite() {
        String username = getUsernameFromSharedPreferences(getContext());
        List<Favorite> favoriteBooks = favoriteDatasource.getFavoriteItems(username);
        return favoriteBooks;
    }
}
