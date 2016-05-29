package com.example.songhui.bottomnavigationbardemo;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.ListViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.songhui.bottomnavigationbardemo.entities.Product;
import com.example.songhui.bottomnavigationbardemo.entities.SubCategory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.zip.Inflater;

public class ProductListActivity extends AppCompatActivity implements View.OnClickListener {
    ListView listView;
    int product_subcategory_no;
    List<Product> productList;

    private EditText search_product_list;
    private Button button_search_product_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        listView = (ListView) findViewById(R.id.listview_product_list);
        Intent intent = getIntent();
        product_subcategory_no = intent.getIntExtra("product_subcategory_no", 0);
        Toast.makeText(
                ProductListActivity.this,
                "product_subcategory_no = " + product_subcategory_no,
                Toast.LENGTH_SHORT).show();
        SimpleAdapter adapter = new SimpleAdapter(
                ProductListActivity.this,
                getDataFromProducts(),
                R.layout.list_item_product,
                new String[]{"product_imgs", "product_name", "pruduct_price", "comment_and_sale"},
                new int[] { R.id.product_image_list_item,
                        R.id.product_name_list_item,
                        R.id.product_current_price_list_item,
                        R.id.prduct_raito_sale_list_item});
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("product", productList.get(position));
                intent.putExtras(bundle);
                intent.setClass(ProductListActivity.this, ProductDetailsActivity.class);
                startActivity(intent);
            }
        });

        search_product_list = (EditText) findViewById(R.id.search_product_list);
        button_search_product_list = (Button) findViewById(R.id.button_search_product_list);
        button_search_product_list.setOnClickListener(this);
    }

    /**
     * 根据product_subcategory_no来获得某个二级产品种类下面的所有产品种类的实体
     * product_no	store_no	product_name	product_subcategory_no
     * product_imgs	description	price_type	last_price	current_price
     * comment_ratio	sale_account	create_date
     */
    List<Product> getProducts(SubCategory subCategory) {
        List<Product> productList = new ArrayList<Product>();
        Product product;
        for(int i = 0; i < 10; i ++) {
            product = new Product(i);
            product.setProduct_name("苹果");
            productList.add(product);
        }
        return productList;
    }

    List<Map<String, Object>> getDataFromProducts() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map;
        productList = getProducts(new SubCategory(product_subcategory_no));
        for(int i = 0; i <productList.size(); i ++) {
            map = new HashMap<String, Object>();
            map.put("product_imgs", R.drawable.ic_launcher_48dp);
            map.put("product_name", productList.get(i).getProduct_name());
            map.put("pruduct_price","￥30/斤 降价" );
            map.put("comment_and_sale", "99% 销量：2123234");
            list.add(map);
        }
        Toast.makeText(ProductListActivity.this, "productList的大小为 " + productList.size(), Toast.LENGTH_SHORT).show();
        return list;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        int id = v.getId();
        switch (id) {
            case R.id.button_search_product_list:
                intent.putExtra("keywords", search_product_list.getText().toString());
                intent.setClass(ProductListActivity.this, SearchActivity.class);
                startActivity(intent);
        }
    }
}
