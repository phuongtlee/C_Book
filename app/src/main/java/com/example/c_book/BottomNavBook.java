package com.example.c_book;

import android.graphics.SweepGradient;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.c_book.fragment.FavoriteFragment;
import com.example.c_book.fragment.HistoryFragment;
import com.example.c_book.fragment.HomeFragment;

public class BottomNavBook  extends FragmentStateAdapter {

    public BottomNavBook(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
            default:
                return new HomeFragment();
            case 1:
                return new FavoriteFragment();
            case 2:
                return new HistoryFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
