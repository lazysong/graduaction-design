package com.example.songhui.bottomnavigationbardemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.songhui.bottomnavigationbardemo.entities.Product;

public class ProductDetailsActivity extends AppCompatActivity {
    private Button backButton;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        backButton = (Button) findViewById(R.id.back_product_details);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        product = (Product) intent.getExtras().getSerializable("product");
        Toast.makeText(ProductDetailsActivity.this, "product_name = " + product.getProduct_name() , Toast.LENGTH_SHORT).show();
    }
}
