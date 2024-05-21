package com.example.c_book;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.example.c_book.Book.Book;
import com.example.c_book.Book.BookAdapter;
import com.example.c_book.fragment.FavoriteFragment;
import com.example.c_book.fragment.HistoryFragment;
import com.example.c_book.fragment.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BottomNavBook mBottomNavBook;
    private BottomNavigationView bottomNavigationView;
    private final int HOME_FRAGMENT=0;
    private final int FAVORITE_FRAGMENT=1;
    private final int HISTORY_FRAGMENT=2;
    private int mCurrentFragment = HOME_FRAGMENT;

    private ImageView mImageViewSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        androidx.appcompat.widget.Toolbar toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initUI();

        mImageViewSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        ImageView icUser = findViewById(R.id.ic_user);
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", true);

        icUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLoggedIn) {
                    // Nếu đã đăng nhập, chuyển hướng tới Activity đăng xuất
                    Intent intent = new Intent(MainActivity.this, Logout.class);
                    startActivity(intent);
                } else {
                    // Nếu chưa đăng nhập, chuyển hướng tới Activity đăng nhập
                    Intent intent = new Intent(MainActivity.this, Login.class);
                    startActivity(intent);
                }
            }
        });

        mBottomNavBook = new BottomNavBook(this);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.btm_home) {
                    if (mCurrentFragment != HOME_FRAGMENT){
                        replaceFragment(new HomeFragment());
                        mCurrentFragment = HOME_FRAGMENT;
                    }
                } else if (id == R.id.btm_favorite) {
                    if (mCurrentFragment != FAVORITE_FRAGMENT){
                        replaceFragment(new FavoriteFragment());
                        mCurrentFragment = FAVORITE_FRAGMENT;
                    }
                } else if (id == R.id.btm_history) {
                    if (mCurrentFragment != HISTORY_FRAGMENT){
                        replaceFragment(new HistoryFragment());
                        mCurrentFragment = HISTORY_FRAGMENT;
                    }
                }
                return true;
            }
        });

        replaceFragment(new HomeFragment());
    }

    private void initUI() {
        bottomNavigationView = findViewById(R.id.btm_navigation);
        mImageViewSearch = findViewById(R.id.ic_search);
    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);
        fragmentTransaction.commit();
    }

}