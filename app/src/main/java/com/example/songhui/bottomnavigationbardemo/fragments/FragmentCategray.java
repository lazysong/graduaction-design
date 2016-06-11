package com.example.songhui.bottomnavigationbardemo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.songhui.bottomnavigationbardemo.activities.ProductListActivity;
import com.example.songhui.bottomnavigationbardemo.R;
import com.example.songhui.bottomnavigationbardemo.activities.SearchActivity;
import com.example.songhui.bottomnavigationbardemo.entities.SubCategory;
import com.example.songhui.bottomnavigationbardemo.entities.TopCategory;
import com.example.songhui.bottomnavigationbardemo.views.MyLinearLayout;
import com.example.songhui.bottomnavigationbardemo.views.MyTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentCategray.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class FragmentCategray extends Fragment implements View.OnClickListener {
    ScrollView scrollViewLeft;
    ScrollView scrollViewRight;
    LinearLayout topCategoryLayout;
    GridLayout subCategoryLayout;

    private EditText editText_fragment_categray;
    private Button searchButton_fragment_categray;

    List<SubCategory> subCategories;
    List<TopCategory> topCategories;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_categray, container, false);
        initLeft(rootView, inflater);
        return rootView;
    }

    public void initLeft(View rootView, LayoutInflater inflater) {
        scrollViewLeft = (ScrollView) rootView.findViewById(R.id.scrollview_top_category);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        topCategoryLayout = new LinearLayout(getContext());
        topCategoryLayout.setOrientation(LinearLayout.VERTICAL);
        topCategoryLayout.setLayoutParams(layoutParams);
        List<View> viewList = new ArrayList<View>();
        ViewGroup.LayoutParams viewGroupLP = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                180);
        MyTextView textView;
        topCategories = getTopCategories();

        for (int i = 0; i < topCategories.size(); i ++) {
            textView = (MyTextView) inflater.inflate(R.layout.top_category_textview, null);
            textView.setLayoutParams(viewGroupLP);
            textView.setText(topCategories.get(i).getProduct_category_name());
            textView.setNumber(topCategories.get(i).getProduct_category_no());
            viewList.add(textView);
            textView.setOnClickListener(this);
            topCategoryLayout.addView(viewList.get(i));
        }
        scrollViewLeft.addView(topCategoryLayout);

        editText_fragment_categray = (EditText) rootView.findViewById(R.id.editText_fragment_categray);
        searchButton_fragment_categray = (Button) rootView.findViewById(R.id.searchButton_fragment_categray);
        searchButton_fragment_categray.setOnClickListener(this);
    }

    private void initRight(View rootView, LayoutInflater inflater, View v) {
        scrollViewRight = (ScrollView) rootView.findViewById(R.id.scrollview_sub_category);
        subCategoryLayout = new GridLayout(getContext());
        subCategoryLayout.setColumnCount(3);
//        subCategoryLayout.setRowCount(3);

        List<View> viewList = new ArrayList<View>();
        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(200, 200);
        MyLinearLayout myLinearLayout;
        subCategories = getSubCategories(    topCategories.get( (   (MyTextView)v).getNumber()  )      );

        scrollViewRight.removeAllViewsInLayout();
        TextView textView;
        for (int i = 0; i < subCategories.size(); i ++) {
            myLinearLayout = (MyLinearLayout) inflater.inflate(R.layout.sub_category_linear_layout, null);
            textView = (TextView) myLinearLayout.findViewById(R.id.subcategrayName_subcategory_linear_layout);
            textView.setText(subCategories.get(i).getProduct_subcategory_name());
            myLinearLayout.setLayoutParams(linearLayoutParams);
//            myLinearLayout.setText(subCategories.get(i).getProduct_subcategory_name());
            myLinearLayout.setProduct_subcategory_no(subCategories.get(i).getProduct_subcategory_no());
            myLinearLayout.setOnClickListener(this);
            viewList.add(myLinearLayout);
            subCategoryLayout.addView(myLinearLayout);
        }
        scrollViewRight.addView(subCategoryLayout);
    }
    List<TopCategory> getTopCategories() {
        List<TopCategory> topCategories = new ArrayList<TopCategory>();
        TopCategory topCategory;
        //获得一级种类的Json数据
        //将Json数据转换为ArrayList<TopCategory>
        String[] categryName = new String[]{"水果", "蔬菜", "禽畜", "水产", "食用油", "谷类", "调味品", "酒茶冲饮", "肉类水产加工", "蔬果加工", "肉蛋类", "零食特产"};
        for(int i = 0; i < categryName.length; i ++) {
            topCategory = new TopCategory(i,categryName[i]);
            topCategories.add(topCategory);
        }
        return topCategories;
    }

    List<SubCategory> getSubCategories(TopCategory topCategory) {
        List<SubCategory> subCategories = new ArrayList<SubCategory>();
        SubCategory subCategory;
        //获取二级种类的Json数据
        //将Json数据转换为ArrayList<SunCategory>
        String[][] subCategryNames = {
                {"苹果", "梨子", "香蕉", "菠萝", "西瓜", "葡萄", "提子"},
                {"大白菜", "小白菜", "生菜", "包菜", "韭菜", "香菜", "西蓝花", "胡萝卜", "南瓜", "丝瓜", "冬瓜", "黄瓜", "苦瓜", "辣椒", "西红柿", "秋葵", "莲藕", "红薯", "紫薯", "土豆", "芋头", "淮山", "葛根", "魔芋", "香菇", "黑木耳", "杏鲍菇", "茶树菇", "竹荪", "蘑菇", "姜", "大蒜球", "大葱", "小葱"},
                {"鸡", "鸭", "鹅", "鸽子", "牛", "羊", "猪", "马", "驴", "骡", "兔"},
                {"鲈鱼", "鲤鱼", "泥鳅", "草鱼", "黄鳝", "青鱼", "鲫鱼", "雄鱼", "鲢鱼"},
                {"大豆油", "山茶油", "花生油", "橄榄油", "玉米油", "菜籽油", "葵花籽油", "色拉油"},
                {"红豆", "大豆", "绿豆", "芸豆", "蚕豆", "黑豆", "赤小豆", "水稻", "大米", "小米", "玉米", "大麦"},
                {"盐", "味精", "鸡精", "糖", "醋", "酱油", "芝麻油", "蚝油干", "辣椒", "辣椒粉", "胡椒粉", "孜然粉"},
                {"绿茶", "普洱", "乌龙茶", "毛尖", "黑茶", "蓝茶", "红茶", "保健茶", "黄茶", "白茶", "酒花茶"},
                {"香肠", "火腿", "腊制品", "丸子制品", "腊肉"},
                {"干香菇", "干菌子", "百合干", "脱水蔬菜", "干笋", "葡萄干", "地瓜干", "黑加仑果", "干桂圆", "干柿饼", "干木耳"},
                {"鸡蛋", "鸭蛋", "鹌鹑蛋", "鹅蛋", "鸽子蛋", "皮蛋", "咸鸭蛋"},
                {"蜂蜜", "红枣", "枸杞", "莲子饼干", "糕点", "麻辣食品", "核桃", "坚果", "干果", "瓜子"}
        };
        for(int i = 0; i < subCategryNames[topCategory.getProduct_category_no()].length; i ++) {
            subCategory = new SubCategory(i, subCategryNames[topCategory.getProduct_category_no()][i], topCategory, "/image");
            subCategories.add(subCategory);
        }
        return subCategories;
    }

    @Override
    public void onClick(View v) {
        //响应顶级分类的点击事件
        if (v.getClass().equals(MyTextView.class)) {
            Toast.makeText(getContext(),
                    "自定义的TextView被点击了！top_category_no = " + ((MyTextView)v).getNumber(),
                    Toast.LENGTH_SHORT).show();
            initRight(getView(), getLayoutInflater(null), v);
//            v.setBackgroundResource(R.color.colorPrimary);
        }
        if(v.getClass().equals(MyLinearLayout.class)) {
            int product_subcategory_no = ((MyLinearLayout)v).getProduct_subcategory_no();
            Toast.makeText(getContext(),
                    "自定义的LinearLayout被点击了！sub_category_no = " + product_subcategory_no,
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(getContext(), ProductListActivity.class);
            intent.putExtra("product_subcategory_no", product_subcategory_no);
            startActivity(intent);
        }
        int id = v.getId();
        Intent intent = new Intent();
        switch (id) {
            case R.id.searchButton_fragment_categray:
                intent.putExtra("keywords", editText_fragment_categray.getText().toString());
                intent.setClass(getContext(), SearchActivity.class);
                startActivity(intent);
                break;
        }
    }

}
