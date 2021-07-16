package com.hienthai.contactapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.hienthai.contactapp.adapter.MainViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private final int[] ICONS = {R.drawable.ic_baseline_call_24,
            R.drawable.ic_baseline_contacts_24,
            R.drawable.ic_baseline_favorite_24};

    private TabLayout tabLayout;
    private ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout_main);
        viewPager2 = findViewById(R.id.viewPager_main);

        viewPager2.setAdapter(new MainViewPagerAdapter(this));

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {

            switch (position) {
                case 0: {
                    tab.setText("Calls");
                    tab.setIcon(ICONS[0]);
                    break;
                }

                case 1: {
                    tab.setText("Contacts");
                    tab.setIcon(ICONS[1]);
                    break;
                }

                case 2: {
                    tab.setText("Favorite");
                    tab.setIcon(ICONS[2]);
                    break;
                }
            }
        });
        tabLayoutMediator.attach();

        askPermissions();

    }

    private void askPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALL_LOG}, 1);
        }
    }
}