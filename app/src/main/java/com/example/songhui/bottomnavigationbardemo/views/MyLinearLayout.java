package com.example.songhui.bottomnavigationbardemo.views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by songhui on 2016/5/29.
 */
public class MyLinearLayout extends LinearLayout {
    private int product_subcategory_no;

    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public int getProduct_subcategory_no() {
        return product_subcategory_no;
    }

    public void setProduct_subcategory_no(int product_subcategory_no) {
        this.product_subcategory_no = product_subcategory_no;
    }

}
