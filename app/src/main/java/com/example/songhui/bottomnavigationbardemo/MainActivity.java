package com.example.songhui.bottomnavigationbardemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {
    BottomNavigationBar bottomNavigationBar;
    BadgeItem numberBadgeItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationBar = (BottomNavigationBar)findViewById(R.id.bottom_navigation_bar);
        numberBadgeItem = new BadgeItem()
                .setBorderWidth(4)
                .setBackgroundColorResource(R.color.blue)
                .setText("" + 0)
                .setHideOnSelect(true);
        bottomNavigationBar.setTabSelectedListener(this);
        refresh();
    }

    void refresh() {
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_home_white_24dp, "Home").setActiveColorResource(R.color.orange).setBadgeItem(numberBadgeItem))
                .addItem(new BottomNavigationItem(R.drawable.ic_book_white_24dp, "Books").setActiveColorResource(R.color.teal))
                .addItem(new BottomNavigationItem(R.drawable.ic_music_note_white_24dp, "Music").setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.drawable.ic_tv_white_24dp, "Movies & TV").setActiveColorResource(R.color.brown))
                .setFirstSelectedPosition(0)
                .initialise();
    }

    @Override
    public void onTabSelected(int position) {
        Toast.makeText(MainActivity.this, "position " + position + "is selected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTabUnselected(int position) {
    }

    @Override
    public void onTabReselected(int position) {
        Toast.makeText(MainActivity.this, "position " + position + "is reselected", Toast.LENGTH_SHORT).show();
    }
}
