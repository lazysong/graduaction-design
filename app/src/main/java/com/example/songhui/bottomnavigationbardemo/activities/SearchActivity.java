package com.example.songhui.bottomnavigationbardemo.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.songhui.bottomnavigationbardemo.R;

public class SearchActivity extends AppCompatActivity {
    private String keywords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        keywords = getIntent().getStringExtra("keywords");
        Toast.makeText(SearchActivity.this, "keywords = " + keywords, Toast.LENGTH_SHORT).show();
    }
}
