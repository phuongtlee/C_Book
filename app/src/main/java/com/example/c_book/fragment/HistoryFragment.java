package com.example.c_book.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c_book.BookInfo;
import com.example.c_book.Favorite.Favorite;
import com.example.c_book.Favorite.FavoriteAdapter;
import com.example.c_book.History.History;
import com.example.c_book.History.HistoryAdapter;
import com.example.c_book.R;
import com.example.c_book.database.FavoriteDatasource;
import com.example.c_book.database.HistoryDatasource;

import java.util.List;

public class HistoryFragment extends Fragment {

    private View v;
    RecyclerView rcvHistory;
    HistoryDatasource historyDatasource;
    HistoryAdapter historyAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_history, container, false);

        rcvHistory = v.findViewById(R.id.rcv_book_history);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(v.getContext(), 3);
        rcvHistory.setLayoutManager(gridLayoutManager);

        historyDatasource = new HistoryDatasource(getContext());
        historyDatasource.open();

        historyAdapter = new HistoryAdapter(getContext());
        historyAdapter.setData(loadHistory());

        historyAdapter.setOnItemClickListener(new HistoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                History selectedHistory = historyAdapter.getHistoryAt(position);
                if (selectedHistory != null) {
                    FavoriteDatasource favoriteDatasource = new FavoriteDatasource(getContext());
                    favoriteDatasource.open();
                    String categoryName = favoriteDatasource.getCategoryName(selectedHistory.getCategoryId());
                    favoriteDatasource.close();

                    Intent intent = new Intent(getContext(), BookInfo.class);
                    intent.putExtra("id", selectedHistory.getId());
                    intent.putExtra("name", selectedHistory.getName());
                    intent.putExtra("author", selectedHistory.getAuthor());
                    intent.putExtra("categoryName", categoryName);
                    intent.putExtra("resourceImg", selectedHistory.getResourceImg());
                    intent.putExtra("script", selectedHistory.getScript());
                    startActivity(intent);
                }
            }
        });

        rcvHistory.setAdapter(historyAdapter);

        return v;
    }

    public String getUsernameFromSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        return username;
    }


    private List<History> loadHistory() {
        String username = getUsernameFromSharedPreferences(getContext());
        List<History> histories = historyDatasource.loadHistoryBook(username);
        return histories;
    }
}
