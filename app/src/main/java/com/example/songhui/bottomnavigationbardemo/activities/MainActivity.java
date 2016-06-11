package com.example.songhui.bottomnavigationbardemo.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.songhui.bottomnavigationbardemo.R;
import com.example.songhui.bottomnavigationbardemo.fragments.FragmentCategray;
import com.example.songhui.bottomnavigationbardemo.fragments.FragmentMain;
import com.example.songhui.bottomnavigationbardemo.fragments.FragmentCar;
import com.example.songhui.bottomnavigationbardemo.fragments.FregmentAccount;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {
    BottomNavigationBar bottomNavigationBar;
    BadgeItem numberBadgeItem;
    ViewPager mViewPager;
    AppSectionsPagerAdapter mAppSectionsPagerAdapter;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationBar = (BottomNavigationBar)findViewById(R.id.bottom_navigation_bar);
        mViewPager = (ViewPager)findViewById(R.id.pager);
        mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAppSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                Log.v("mythread", "method onPageSelected() is called, position == "  + position);
                bottomNavigationBar.selectTab(position);
            }
        });

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
                .addItem(new BottomNavigationItem(R.drawable.ic_home_white_24dp, "首页").setActiveColorResource(R.color.orange).setBadgeItem(numberBadgeItem))
                .addItem(new BottomNavigationItem(R.drawable.ic_categray_white_24dp,  "分类").setActiveColorResource(R.color.teal))
                .addItem(new BottomNavigationItem(R.drawable.ic_shopingbag_white_24dp, "购物车").setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.drawable.ic_account_white_24dp, "我的").setActiveColorResource(R.color.brown))
                .setFirstSelectedPosition(0)
                .initialise();
    }

    @Override
    public void onTabSelected(int position) {
//        Toast.makeText(MainActivity.this, "position " + position + "is selected", Toast.LENGTH_SHORT).show();
        mViewPager.setCurrentItem(position);
    }

    @Override
    public void onTabUnselected(int position) {
    }

    @Override
    public void onTabReselected(int position) {
    }


    //SessionPageAdapter类
    public static class AppSectionsPagerAdapter extends FragmentPagerAdapter {

        public AppSectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    Log.v("mythread", "new FragmentMain()");
                    return new FragmentMain();
                case 1:
                    Log.v("mythread", "new FragmentCategray()");
                    return new FragmentCategray();
                case 2:
                    Log.v("mythread", "new FragmentCar()");
                    return new FragmentCar();
                case 3:
                    Log.v("mythread", "new FregmentAccount()");
                    return new FregmentAccount();

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Section " + (position + 1);
        }
    }

}
