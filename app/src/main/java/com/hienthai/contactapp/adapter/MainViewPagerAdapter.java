package com.hienthai.contactapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.hienthai.contactapp.CallsFragment;
import com.hienthai.contactapp.ContactsFragment;
import com.hienthai.contactapp.FavoriteFragment;

public class MainViewPagerAdapter extends FragmentStateAdapter {


    public MainViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new ContactsFragment();
            case 2:
                return new FavoriteFragment();
            default:
                return new CallsFragment();
        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }

}
