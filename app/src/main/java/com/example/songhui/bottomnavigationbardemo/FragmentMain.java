package com.example.songhui.bottomnavigationbardemo;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SearchViewCompat;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentMain.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentMain#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMain extends Fragment {
    ViewPager viewPagerMain;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        return rootView;
    }
    public static class BannerFragment extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_banner, container, false);
            Log.v("onCreateView()", "rootView初始化完成");
            return rootView;
        }
    }

    public void setting(View rootView) {
        viewPagerMain = (ViewPager)rootView.findViewById(R.id.viewpager_main);
        FragmentManager fm = getFragmentManager();
        viewPagerMain.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                BannerFragment bannerFragment = new BannerFragment();
                View view = bannerFragment.getView();
                ImageView imageBanner;
                if(view != null)
                     imageBanner = (ImageView)view.findViewById(R.id.image_banner);
                else {
                    Log.v("getView()", "view为null");
                    return bannerFragment;
                }
                switch (position) {
                    case 0:
                        imageBanner.setImageResource(R.drawable.pic_banner1);
                        break;
                    default:
                        imageBanner.setImageResource(R.drawable.pic_banner1);
                }
                return bannerFragment;
            }

            @Override
            public int getCount() {
                return 5;
            }
        });
    }

}
