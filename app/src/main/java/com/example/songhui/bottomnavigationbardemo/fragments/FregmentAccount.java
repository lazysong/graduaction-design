package com.example.songhui.bottomnavigationbardemo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.songhui.bottomnavigationbardemo.R;

/**
 * Created by songhui on 2016/5/8.
 */
public class FregmentAccount extends Fragment{
    public static final String ARG_SECTION_NUMBER = "section_number";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_account, container, false);
        return  rootView;
    }
}
