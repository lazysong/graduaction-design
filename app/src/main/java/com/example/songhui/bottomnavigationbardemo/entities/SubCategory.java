package com.example.songhui.bottomnavigationbardemo.entities;

/**
 * Created by songhui on 2016/5/28.
 */
public class SubCategory {
    /*PRODUCT_SUBCATEGORY_NO	PRODUCT_SUBCATEGORY_NAME	PRODUCT_CATEGORY_NO*/
    private int product_subcategory_no;
    private String product_subcategory_name;
    private TopCategory topCategory;
    private String image;

    public SubCategory(int product_subcategory_no, String product_subcategory_name, TopCategory topCategory, String image) {
        this.product_subcategory_no = product_subcategory_no;
        this.product_subcategory_name = product_subcategory_name;
        this.topCategory = topCategory;
        this.image = image;
    }

    public int getProduct_subcategory_no() {
        return product_subcategory_no;
    }

    public void setProduct_subcategory_no(int product_subcategory_no) {
        this.product_subcategory_no = product_subcategory_no;
    }

    public String getProduct_subcategory_name() {
        return product_subcategory_name;
    }

    public void setProduct_subcategory_name(String product_subcategory_name) {
        this.product_subcategory_name = product_subcategory_name;
    }

    public TopCategory getTopCategory() {
        return topCategory;
    }

    public void setTopCategory(TopCategory topCategory) {
        this.topCategory = topCategory;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
