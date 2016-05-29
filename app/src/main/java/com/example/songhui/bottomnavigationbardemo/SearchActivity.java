package com.example.songhui.bottomnavigationbardemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

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
