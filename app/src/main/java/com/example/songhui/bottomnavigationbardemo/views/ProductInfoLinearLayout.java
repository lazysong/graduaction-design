package com.example.songhui.bottomnavigationbardemo.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by songhui on 2016/5/31.
 */
public class ProductInfoLinearLayout extends LinearLayout {
    private int position;

    public ProductInfoLinearLayout(Context context) {
        super(context);
    }

    public ProductInfoLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProductInfoLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
