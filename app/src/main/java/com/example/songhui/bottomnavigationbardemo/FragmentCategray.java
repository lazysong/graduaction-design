package com.example.songhui.bottomnavigationbardemo;

import android.content.Intent;
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
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.songhui.bottomnavigationbardemo.ProductListActivity;
import com.example.songhui.bottomnavigationbardemo.R;
import com.example.songhui.bottomnavigationbardemo.SearchActivity;
import com.example.songhui.bottomnavigationbardemo.WebinfoActivity;
import com.example.songhui.bottomnavigationbardemo.entities.SubCategory;
import com.example.songhui.bottomnavigationbardemo.entities.TopCategory;
import com.example.songhui.bottomnavigationbardemo.views.MyLinearLayout;
import com.example.songhui.bottomnavigationbardemo.views.MyTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentCategray.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class FragmentCategray extends Fragment implements View.OnClickListener {
    ScrollView scrollViewLeft;
    ScrollView scrollViewRight;
    LinearLayout topCategoryLayout;
    GridLayout subCategoryLayout;

    private EditText editText_fragment_categray;
    private Button searchButton_fragment_categray;

    List<SubCategory> subCategories;
    List<TopCategory> topCategories;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_categray, container, false);
        initLeft(rootView, inflater);
        return rootView;
    }

    public void initLeft(View rootView, LayoutInflater inflater) {
        scrollViewLeft = (ScrollView) rootView.findViewById(R.id.scrollview_top_category);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        topCategoryLayout = new LinearLayout(getContext());
        topCategoryLayout.setOrientation(LinearLayout.VERTICAL);
        topCategoryLayout.setLayoutParams(layoutParams);
        List<View> viewList = new ArrayList<View>();
        ViewGroup.LayoutParams viewGroupLP = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                180);
        MyTextView textView;
        topCategories = getTopCategories();

        for (int i = 0; i < topCategories.size(); i ++) {
            textView = (MyTextView) inflater.inflate(R.layout.top_category_textview, null);
            textView.setLayoutParams(viewGroupLP);
            textView.setText(topCategories.get(i).getProduct_category_name());
            textView.setNumber(topCategories.get(i).getProduct_category_no());
            viewList.add(textView);
            textView.setOnClickListener(this);
            topCategoryLayout.addView(viewList.get(i));
        }
        scrollViewLeft.addView(topCategoryLayout);

        editText_fragment_categray = (EditText) rootView.findViewById(R.id.editText_fragment_categray);
        searchButton_fragment_categray = (Button) rootView.findViewById(R.id.searchButton_fragment_categray);
        searchButton_fragment_categray.setOnClickListener(this);
    }

    private void initRight(View rootView, LayoutInflater inflater, View v) {
        scrollViewRight = (ScrollView) rootView.findViewById(R.id.scrollview_sub_category);
        subCategoryLayout = new GridLayout(getContext());
        subCategoryLayout.setColumnCount(3);
//        subCategoryLayout.setRowCount(3);

        List<View> viewList = new ArrayList<View>();
        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(300, 300);
        MyLinearLayout myLinearLayout;
        subCategories = getSubCategories(    topCategories.get( (   (MyTextView)v).getNumber()  )      );

        scrollViewRight.removeAllViewsInLayout();
        for (int i = 0; i < subCategories.size(); i ++) {
            myLinearLayout = (MyLinearLayout) inflater.inflate(R.layout.sub_category_linear_layout, null);
            myLinearLayout.setLayoutParams(linearLayoutParams);
//            myLinearLayout.setText(subCategories.get(i).getProduct_subcategory_name());
            myLinearLayout.setProduct_subcategory_no(subCategories.get(i).getProduct_subcategory_no());
            myLinearLayout.setOnClickListener(this);
            viewList.add(myLinearLayout);
            subCategoryLayout.addView(myLinearLayout);
        }
        scrollViewRight.addView(subCategoryLayout);
    }
    List<TopCategory> getTopCategories() {
        List<TopCategory> topCategories = new ArrayList<TopCategory>();
        TopCategory topCategory;
        //获得一级种类的Json数据
        //将Json数据转换为ArrayList<TopCategory>
        for(int i = 0; i < 5; i ++) {
            topCategory = new TopCategory(i,"水果蔬菜");
            topCategories.add(topCategory);
        }
        return topCategories;
    }

    List<SubCategory> getSubCategories(TopCategory topCategory) {
        List<SubCategory> subCategories = new ArrayList<SubCategory>();
        SubCategory subCategory;
        //获取二级种类的Json数据
        //将Json数据转换为ArrayList<SunCategory>
        for(int i = 0; i < 9; i ++) {
            subCategory = new SubCategory(i, "苹果", topCategory, "/image");
            subCategories.add(subCategory);
        }
        return subCategories;
    }

    @Override
    public void onClick(View v) {
        //响应顶级分类的点击事件
        if (v.getClass().equals(MyTextView.class)) {
            Toast.makeText(getContext(),
                    "自定义的TextView被点击了！top_category_no = " + ((MyTextView)v).getNumber(),
                    Toast.LENGTH_SHORT).show();
            initRight(getView(), getLayoutInflater(null), v);
        }
        if(v.getClass().equals(MyLinearLayout.class)) {
            int product_subcategory_no = ((MyLinearLayout)v).getProduct_subcategory_no();
            Toast.makeText(getContext(),
                    "自定义的LinearLayout被点击了！sub_category_no = " + product_subcategory_no,
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(getContext(), ProductListActivity.class);
            intent.putExtra("product_subcategory_no", product_subcategory_no);
            startActivity(intent);
        }
        int id = v.getId();
        Intent intent = new Intent();
        switch (id) {
            case R.id.searchButton_fragment_categray:
                intent.putExtra("keywords", editText_fragment_categray.getText().toString());
                intent.setClass(getContext(), SearchActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * A simple {@link Fragment} subclass.
     * Activities that contain this fragment must implement the
     * {@link FragmentMain.OnFragmentInteractionListener} interface
     * to handle interaction events.
     * Use the {@link FragmentMain#newInstance} factory method to
     * create an instance of this fragment.
     */
    public static class FragmentMain extends Fragment implements View.OnClickListener {
        ViewPager viewPagerMain;
        Button searchButton;
        ImageView imageHot;
        EditText editText_fragment_main;

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

            //        viewPagerMain.setCurrentItem(0);
            imageHot = (ImageView) rootView.findViewById(R.id.image_hot);
            imageHot.setOnClickListener(this);
            editText_fragment_main = (EditText) rootView.findViewById(R.id.editText_fragment_main);
            searchButton = (Button) rootView.findViewById(R.id.searchButton_fragment_main);
            searchButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int id = v.getId();
            Intent intent = new Intent();
            switch (id) {
                case R.id.image_banner:
                    intent.setClass(getContext(), WebinfoActivity.class);
                    startActivity(intent);
                    break;
                case R.id.image_hot:
                    intent.setClass(getContext(), WebinfoActivity.class);
                    startActivity(intent);
                    break;
                case R.id.searchButton_fragment_main:
                    intent.putExtra("keywords", editText_fragment_main.getText().toString());
                    intent.setClass(getContext(), SearchActivity.class);
                    startActivity(intent);
            }
        }

        class BannerFragment extends Fragment {
            public ImageView image;
            public TextView textView;
            public final String ARG_SECTION_NUMBER = "section_number";

            @Nullable
            @Override
            public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
                View rootView = inflater.inflate(R.layout.fragment_banner, container, false);

                image = (ImageView) rootView.findViewById(R.id.image_banner);
                image.setOnClickListener(FragmentMain.this);

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

    /**
     * A simple {@link Fragment} subclass.
     * Activities that contain this fragment must implement the
     * {@link FragmentOrder.OnFragmentInteractionListener} interface
     * to handle interaction events.
     */
    public static class FragmentOrder extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_order, container, false);
            return rootView;
        }
    }

    /**
     * Created by songhui on 2016/5/8.
     */
    public static class FregmentAccount extends Fragment{
        public static final String ARG_SECTION_NUMBER = "section_number";

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_order, container, false);
            return  rootView;
        }
    }
}
