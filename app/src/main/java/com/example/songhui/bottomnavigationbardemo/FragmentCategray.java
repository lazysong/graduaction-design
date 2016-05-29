package com.example.songhui.bottomnavigationbardemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

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
    }
}
