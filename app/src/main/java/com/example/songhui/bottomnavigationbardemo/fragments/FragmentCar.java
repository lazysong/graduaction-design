package com.example.songhui.bottomnavigationbardemo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.example.songhui.bottomnavigationbardemo.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentCar.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class FragmentCar extends Fragment {
    private ScrollView scrollView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_car, container, false);

        //根据读取到的购物车内容，动态添加控件
        scrollView = (ScrollView) rootView.findViewById(R.id.scrollview_fragment_car);

        LinearLayout linearLayout = new LinearLayout(getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
               800);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        RelativeLayout relativeLayout;
        LinearLayout linearLayoutProduct;

        int storeCount = 2;
        int productCount = 2;
        for(int i = 0; i < storeCount; i ++) {
            //对每个store动态添加控件
            //先动态添加店铺栏
            relativeLayout = (RelativeLayout) inflater.inflate(R.layout.list_item_store, null);
            linearLayout.addView(relativeLayout);
            //再对每个店铺动态添加商品栏，其中productCount是根据store实体中的product列表长度计算得出的
            for (int j = 0; j < productCount; j ++) {
                linearLayoutProduct = (LinearLayout) inflater.inflate(R.layout.list_item_product_car, null);
                linearLayout.addView(linearLayoutProduct);
            }
            //添加商店栏
        }
        //将linearLayout添加到scrollView中
        scrollView.addView(linearLayout);
        return rootView;
    }
}
