package com.example.songhui.bottomnavigationbardemo.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.songhui.bottomnavigationbardemo.R;
import com.example.songhui.bottomnavigationbardemo.activities.SearchActivity;
import com.example.songhui.bottomnavigationbardemo.activities.WebinfoActivity;
import com.example.songhui.bottomnavigationbardemo.entities.Product;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentMain.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentMain#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMain extends Fragment implements View.OnClickListener {
    ViewPager viewPagerMain;
    Button searchButton_fragment_main;
    ImageView imageHot;

    private EditText editText_fragment_main;

    private LinearLayout product1_recommand;
    private LinearLayout product2_recommand;
    private LinearLayout product3_recommand;

    private static final int PRODUCT_RECOMMAND_COUNT = 3;

    private ImageView[] recommand_product_imageList_imageView = new ImageView[PRODUCT_RECOMMAND_COUNT];
    private int[] recommand_product_imageList_id = new int[] {
            R.id.product1_recommand_image_fragment_main,
            R.id.product2_recommand_image_fragment_main,
            R.id.product3_recommand_image_fragment_main
    };


    private TextView[] product_priceCurrentList_textView = new TextView[PRODUCT_RECOMMAND_COUNT];
    private int[] product_priceCurrentList_id = new int[] {
            R.id.product1_priceCurrent_fragment_main,
            R.id.product2_priceCurrent_fragment_main,
            R.id.product3_priceCurrent_fragment_main
    };

    private TextView[] product_priceLastList_textView = new TextView[PRODUCT_RECOMMAND_COUNT];
    private int[] product_priceLastList_id = new int[] {
            R.id.product1_priceLast_fragment_main,
            R.id.product2_priceLast_fragment_main,
            R.id.product3_priceLast_fragment_main
    };

    //推荐商品的实体列表
    List<Product> productRecommandList;

    private Handler pic_hdl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        innilize(getView());
    }

    private void innilize(View rootView) {
        if (rootView != null)
            viewPagerMain = (ViewPager)rootView.findViewById(R.id.viewpager_main);
        else {
            Log.v("viewPager", "viewPagerMain == null");
            return;
        }
        FragmentManager fm = getChildFragmentManager();
        if(viewPagerMain != null)
            viewPagerMain.setAdapter(new MyViewPagerAdapter(fm));
        else {
            Log.v("viewPager", "viewPagerMain == null");
            return;
        }

        imageHot = (ImageView) rootView.findViewById(R.id.image_hot);
        imageHot.setOnClickListener(this);
        editText_fragment_main = (EditText) rootView.findViewById(R.id.editText_fragment_main);
        searchButton_fragment_main = (Button) rootView.findViewById(R.id.searchButton_fragment_main);
        searchButton_fragment_main.setOnClickListener(this);

        //获取推荐商品栏的控件
        product1_recommand = (LinearLayout) rootView.findViewById(R.id.product1_recommand_fragment_main);
        product2_recommand = (LinearLayout) rootView.findViewById(R.id.product2_recommand_fragment_main);
        product3_recommand = (LinearLayout) rootView.findViewById(R.id.product3_recommand_fragment_main);

        for(int i = 0; i < PRODUCT_RECOMMAND_COUNT; i ++) {
            recommand_product_imageList_imageView[i] = (ImageView) rootView.findViewById(recommand_product_imageList_id[i]);
        }

        for(int i = 0; i < PRODUCT_RECOMMAND_COUNT; i ++) {
            product_priceCurrentList_textView[i] = (TextView) rootView.findViewById(product_priceCurrentList_id[i]);
        }

        for(int i = 0; i < PRODUCT_RECOMMAND_COUNT; i ++) {
            product_priceLastList_textView[i] = (TextView) rootView.findViewById(product_priceLastList_id[i]);
            product_priceLastList_textView[i].getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );
        }

        //为四个推荐商品设置图像
//        product1_recommand_image.setImageBitmap();
        productRecommandList = getProductRecommandList();
        pic_hdl = new PicHandler();
        Thread t = new LoadPicThread();
        t.start();

        //为每个商品设置点击的监听器
        product1_recommand.setOnClickListener(this);
        product2_recommand.setOnClickListener(this);
        product3_recommand.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent intent = new Intent();
        switch (id) {
            case R.id.image_banner:
                intent.setClass(getContext(), WebinfoActivity.class);
                startActivity(intent);
                break;
            case R.id.image_hot:
                intent.setClass(getContext(), WebinfoActivity.class);
                startActivity(intent);
                break;
            case R.id.searchButton_fragment_main:
                intent.putExtra("keywords", editText_fragment_main.getText().toString());
                intent.setClass(getContext(), SearchActivity.class);
                startActivity(intent);
                break;
        }
    }

    class BannerFragment extends Fragment {
        public ImageView imageView;
        public TextView textView;
        public final String ARG_SECTION_NUMBER = "section_number";
        private int position = 0;

        public BannerFragment(int position) {
            this.position = position;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_banner, container, false);

            imageView = (ImageView) rootView.findViewById(R.id.image_banner);
            imageView.setOnClickListener(FragmentMain.this);
            setBannerImage(position);
            return rootView;
        }

        public void setBannerImage(int position) {
            switch (position) {
                case 0:
                    imageView.setBackgroundResource(R.drawable.pic_banner_0);
                    break;
                case 1:
                    imageView.setBackgroundResource(R.drawable.pic_banner_1);
                    break;
                case 2:
                    imageView.setBackgroundResource(R.drawable.pic_banner_2);
                    break;
                case 3:
                    imageView.setBackgroundResource(R.drawable.pic_banner_3);
                    break;
            }
        }
    }

    class LoadPicThread extends Thread{
        @Override
        public void run(){
            List<Bitmap> imgList = new ArrayList<Bitmap>();
            Bitmap img;
            productRecommandList = getProductRecommandList();
            for (int i = 0; i < PRODUCT_RECOMMAND_COUNT; i ++) {
                img = getUrlImage("http://www.lazysong.cn" + productRecommandList.get(i).getProduct_imgs());
                imgList.add(img);
            }
            Message msg = pic_hdl.obtainMessage();
            msg.what = 0;
            msg.obj = imgList;
            pic_hdl.sendMessage(msg);
        }
    }

    class PicHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            //String s = (String)msg.obj;
            //ptv.setText(s);
            List<Bitmap> imgList = (ArrayList<Bitmap>)msg.obj;
            for(int i = 0; i < PRODUCT_RECOMMAND_COUNT; i ++) {
                recommand_product_imageList_imageView[i].setImageBitmap(imgList.get(i));
                product_priceCurrentList_textView[i].setText(productRecommandList.get(i).getCurrent_price() + "");
                product_priceLastList_textView[i].setText(productRecommandList.get(i).getLast_price() + "");
            }
        }

    }

    //加载图片
    public Bitmap getUrlImage(String url) {
        Bitmap img = null;
        try {
            URL picurl = new URL(url);
            // 获得连接
            HttpURLConnection conn = (HttpURLConnection)picurl.openConnection();
            conn.setConnectTimeout(6000);//设置超时
            conn.setDoInput(true);
            conn.setUseCaches(false);//不缓存
            conn.connect();
            InputStream is = conn.getInputStream();//获得图片的数据流
            img = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return img;
    }

    /**
     * ViewPager的适配器
     */
    class MyViewPagerAdapter extends FragmentPagerAdapter {

        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Log.v("viewPager", "getItem()方法被调用");
            BannerFragment fragment = new BannerFragment(position % 4);
            return fragment;
        }

        @Override
        public int getCount() {
            return 500;
        }
    }

    List<Product> getProductRecommandList() {
        List<Product> productRecommandList = new ArrayList<Product>();
        Product product;
        for(int i = 0; i < 4; i ++) {
            product = new Product(i);
            product.setProduct_name("name" + i);
            product.setCurrent_price(10*i +10);
            product.setLast_price(10*i +20);
            product.setProduct_imgs("/images/" + (i+1) + ".jpg");
            productRecommandList.add(product);
        }
        return productRecommandList;
    }
}
