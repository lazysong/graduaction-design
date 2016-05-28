package com.example.songhui.bottomnavigationbardemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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
    Button searchButton;

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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        innilize(getView());
    }

    private void innilize(View rootView) {
        if (rootView != null)
            viewPagerMain = (ViewPager)rootView.findViewById(R.id.viewpager_main);
        else {
            Log.v("viewPager", "viewPagerMain == null");
            return;
        }
        FragmentManager fm = getChildFragmentManager();
        if(viewPagerMain != null)
            viewPagerMain.setAdapter(new MyViewPagerAdapter(fm));
        else {
            Log.v("viewPager", "viewPagerMain == null");
            return;
        }
        //为ViewPager绑定一个用于测试监听器
        viewPagerMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"viewPagerMain 被点击", Toast.LENGTH_SHORT);
            }
        });
//        viewPagerMain.setCurrentItem(0);
        searchButton = (Button) rootView.findViewById(R.id.search_button);
    }

    public static class BannerFragment extends Fragment {
        public ImageView image;
        public TextView textView;
        public static final String ARG_SECTION_NUMBER = "section_number";

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_banner, container, false);

            /*image = (ImageView) rootView.findViewById(R.id.image_banner);
            if(image != null)
                image.setImageResource(R.drawable.pic_banner1);
            else {
                Log.v("viewPager", "image == null");
                return rootView;
            }*/
            return rootView;
        }
    }


    /**
     * ViewPager的适配器
     */
    class MyViewPagerAdapter extends FragmentPagerAdapter {

        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Log.v("viewPager", "getItem()方法被调用");
            return new BannerFragment();
        }

        @Override
        public int getCount() {
            return 5;
        }
    }
}
