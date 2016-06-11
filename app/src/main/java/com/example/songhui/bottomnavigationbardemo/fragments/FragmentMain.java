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
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.songhui.bottomnavigationbardemo.R;
import com.example.songhui.bottomnavigationbardemo.activities.ProductDetailsActivity;
import com.example.songhui.bottomnavigationbardemo.activities.SearchActivity;
import com.example.songhui.bottomnavigationbardemo.activities.WebinfoActivity;
import com.example.songhui.bottomnavigationbardemo.entities.Product;
import com.example.songhui.bottomnavigationbardemo.views.ProductInfoLinearLayout;

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
    private ViewPager viewPagerMain;
    private Button searchButton_fragment_main;
    private ImageView imageHot;
    private EditText editText_fragment_main;

    private boolean isRunning =false;
    private View rootView;
    private LayoutInflater inflater;
//    private Thread t = new LoadPicThread();
    //展示的推荐商品的个数
    private static final int PRODUCT_RECOMMAND_COUNT = 3;
    //推荐商品的实体列表
    List<Product> productRecommandList;
    //显示商品信息的布局
    private LinearLayout[] productLayoutList_recommand = new LinearLayout[PRODUCT_RECOMMAND_COUNT];
    private int[] productLayoutList_recommand_id = new int[] {
            R.id.product1_recommand_fragment_main,
            R.id.product2_recommand_fragment_main,
            R.id.product3_recommand_fragment_main,
    };
    //显示商品的图像
    private ImageView[] recommand_product_imageList_imageView = new ImageView[PRODUCT_RECOMMAND_COUNT];
    private int[] recommand_product_imageList_id = new int[] {
            R.id.product1_recommand_image_fragment_main,
            R.id.product2_recommand_image_fragment_main,
            R.id.product3_recommand_image_fragment_main
    };
    //显示商品的当前价格
    private TextView[] product_priceCurrentList_textView = new TextView[PRODUCT_RECOMMAND_COUNT];
    private int[] product_priceCurrentList_id = new int[] {
            R.id.product1_priceCurrent_fragment_main,
            R.id.product2_priceCurrent_fragment_main,
            R.id.product3_priceCurrent_fragment_main
    };
    //显示商品的之前价格
    private TextView[] product_priceLastList_textView = new TextView[PRODUCT_RECOMMAND_COUNT];
    private int[] product_priceLastList_id = new int[] {
            R.id.product1_priceLast_fragment_main,
            R.id.product2_priceLast_fragment_main,
            R.id.product3_priceLast_fragment_main
    };

    //热销的商品列表
    List<Product> productHotList;
    //显示热销的商品列表的GridLayout
    GridLayout gridLayout_productHot;
    //热销商品的图片资源列表
    List<Bitmap> productHot_bitmap_list = new ArrayList<Bitmap>();

    private Handler pic_hdl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        this.inflater = inflater;
        initSearchBar(rootView);
        initPromotion(rootView);
        //获取推荐商品的图片资源
        productRecommandList = getProductRecommandList();
        initProductRecommand(rootView);
        //获得热销的商品列表
        productHotList = getProductHotList();
        pic_hdl = new PicHandler();
        isRunning = true;
        Log.v("mythread", "method onCreateView() is called, isRunning == true");
        Thread t = new LoadPicThread();
        t.start();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initBanner(getView());
    }

    public void initSearchBar(View rootView) {
        editText_fragment_main = (EditText) rootView.findViewById(R.id.editText_fragment_main);
        searchButton_fragment_main = (Button) rootView.findViewById(R.id.searchButton_fragment_main);
        searchButton_fragment_main.setOnClickListener(this);
    }

    public void initBanner(View rootView) {
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
    }

    public void initPromotion(View rootView) {
        imageHot = (ImageView) rootView.findViewById(R.id.imageView_freshShop);
        imageHot.setOnClickListener(this);
    }

    public void initProductRecommand(View rootView) {
        /*//获得推荐的商品列表
        productRecommandList = getProductRecommandList();*/
        //对推荐商品的栏目进行初始化
        for(int i = 0; i < PRODUCT_RECOMMAND_COUNT; i ++) {
            //初始化推荐商品的ImageView控件
            recommand_product_imageList_imageView[i] = (ImageView) rootView.findViewById(recommand_product_imageList_id[i]);
            //初始化显示推荐商品的当前价格的TextView
            product_priceCurrentList_textView[i] = (TextView) rootView.findViewById(product_priceCurrentList_id[i]);
            //初始化显示推荐商品之前价格的TextView
            product_priceLastList_textView[i] = (TextView) rootView.findViewById(product_priceLastList_id[i]);
            //为之前价格的TextView设置中划线
            product_priceLastList_textView[i].getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );
            //初始化包含推荐商品所有信息的LinearLayout布局，并为之绑定监听器
            productLayoutList_recommand[i] = (LinearLayout) rootView.findViewById(productLayoutList_recommand_id[i]);
            productLayoutList_recommand[i].setOnClickListener(this);
        }
    }

    public void initProductHot(View rootView, LayoutInflater inflater) {

        //获得展示热销商品信息的GridLayout
        gridLayout_productHot = (GridLayout) rootView.findViewById(R.id.gridLayout_fragment_main);
        gridLayout_productHot.removeAllViews();
        ProductInfoLinearLayout linearLayout;
        ImageView productImage;
        TextView productSaleCount;
        ViewGroup.LayoutParams imagePara = new ViewGroup.LayoutParams(200, 200);
        for(int i = 0; i < productHotList.size(); i ++) {
            productImage = new ImageView(getContext());
            productImage.setLayoutParams(imagePara);
            productImage.setImageBitmap(productHot_bitmap_list.get(i));
            productSaleCount = new TextView(getContext());
            productSaleCount.setText(productHotList.get(i).getSale_account() + "");
            linearLayout = (ProductInfoLinearLayout) inflater.inflate(R.layout.item_product_short, null);
            linearLayout.addView(productImage);
            linearLayout.addView(productSaleCount);
            linearLayout.setPosition(i);
            linearLayout.setOnClickListener(this);
            gridLayout_productHot.addView(linearLayout);
        }
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
            case R.id.imageView_freshShop:
                intent.setClass(getContext(), WebinfoActivity.class);
                startActivity(intent);
                break;
            case R.id.searchButton_fragment_main:
                intent.putExtra("keywords", editText_fragment_main.getText().toString());
                intent.setClass(getContext(), SearchActivity.class);
                startActivity(intent);
                break;
        }
        for (int i = 0; i < PRODUCT_RECOMMAND_COUNT; i ++) {
            if(id == productLayoutList_recommand_id[i]) {
                intent.setClass(getContext(), ProductDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("product", productRecommandList.get(i));
                intent.putExtras(bundle);
                startActivity(intent);
                return;
            }
        }
        if(v.getClass().equals(ProductInfoLinearLayout.class)) {
            intent.setClass(getContext(), ProductDetailsActivity.class);
            int position = ((ProductInfoLinearLayout)v).getPosition();
            Bundle bundle = new Bundle();
            bundle.putSerializable("product", productHotList.get(position));
            intent.putExtras(bundle);
            startActivity(intent);
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
            while(isRunning) {
                isRunning = false;
                List<Bitmap> imgList = new ArrayList<Bitmap>();
                Bitmap img;
                for (int i = 0; i < PRODUCT_RECOMMAND_COUNT; i++) {
                    img = getUrlImage("http://www.lazysong.cn" + productRecommandList.get(i).getProduct_imgs());
                    imgList.add(img);
                }
                //获取热销商品的图片资源
                for (int i = 0; i < productHotList.size(); i++) {
                    img = getUrlImage("http://www.lazysong.cn" + productHotList.get(i).getProduct_imgs());
                    productHot_bitmap_list.add(img);
                }
                Message msg = pic_hdl.obtainMessage();
                msg.what = 0;
                msg.obj = imgList;
                pic_hdl.sendMessage(msg);
                Log.v("mythread", "method run() is called, isRunning is false");
            }
        }
    }

    class PicHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            // 为推荐商品设置图片资源以及文本
            List<Bitmap> imgList = (ArrayList<Bitmap>)msg.obj;
            for(int i = 0; i < PRODUCT_RECOMMAND_COUNT; i ++) {
                recommand_product_imageList_imageView[i].setImageBitmap(imgList.get(i));
                product_priceCurrentList_textView[i].setText(productRecommandList.get(i).getCurrent_price() + "");
                product_priceLastList_textView[i].setText(productRecommandList.get(i).getLast_price() + "");
            }
//            initProductHot(rootView, getLayoutInflater(null));
            initProductHot(rootView, inflater);

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

    List<Product> getProductHotList() {
        List<Product> productHotList = new ArrayList<Product>();
        Product product;
        for(int i = 4; i < 10; i ++) {
            product = new Product(i);
            product.setProduct_name("name" + i);
            product.setCurrent_price(10*i +10);
            product.setLast_price(10*i +20);
            product.setProduct_imgs("/images/" + (i+1) + ".jpg");
            product.setSale_account((i + 1) * 1000);
            productHotList.add(product);
        }
        return productHotList;
    }
}
