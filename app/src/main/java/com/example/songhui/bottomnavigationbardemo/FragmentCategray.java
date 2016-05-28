package com.example.songhui.bottomnavigationbardemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.songhui.bottomnavigationbardemo.entities.SubCategory;
import com.example.songhui.bottomnavigationbardemo.entities.TopCategory;
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
    ScrollView scrollView;
    LinearLayout topCategoryLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_categray, container, false);

        scrollView = (ScrollView) rootView.findViewById(R.id.scrollview_top_category);
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
        List<TopCategory> topCategories = getTopCategories();

        for (int i = 0; i < topCategories.size(); i ++) {
            textView = (MyTextView) inflater.inflate(R.layout.top_category_textview, null);
            textView.setLayoutParams(viewGroupLP);
            textView.setText(topCategories.get(i).getProduct_category_name());
            textView.setNumber(topCategories.get(i).getProduct_category_no());
            viewList.add(textView);
            textView.setOnClickListener(this);
            topCategoryLayout.addView(viewList.get(i));
        }
        scrollView.addView(topCategoryLayout);

        return rootView;
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
        for(int i = 0; i < 10; i ++) {
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
        }
    }
}
