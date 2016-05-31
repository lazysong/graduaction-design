package com.example.songhui.bottomnavigationbardemo.entities;

import java.io.Serializable;

/**
 * Created by songhui on 2016/5/29.
 *
 * product_no	store_no	product_name	product_subcategory_no
 * product_imgs	description	price_type	last_price	current_price
 * comment_ratio	sale_account	create_date
 */
public class Product implements Serializable {
    private long product_no;
    private int store_no;
    private String product_name;
    private int product_subcategory_no;
    private String product_imgs;
    private String description;
    private int price_type;
    private float last_price;
    private float current_price;
    private float comment_ratio;
    private int sale_account;
    private String create_date;

    public Product(long product_no) {
        this.product_no = product_no;
    }

    public long getProduct_no() {
        return product_no;
    }

    public void setProduct_no(long product_no) {
        this.product_no = product_no;
    }

    public int getStore_no() {
        return store_no;
    }

    public void setStore_no(int store_no) {
        this.store_no = store_no;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getProduct_subcategory_no() {
        return product_subcategory_no;
    }

    public void setProduct_subcategory_no(int product_subcategory_no) {
        this.product_subcategory_no = product_subcategory_no;
    }

    public String getProduct_imgs() {
        return product_imgs;
    }

    public void setProduct_imgs(String product_imgs) {
        this.product_imgs = product_imgs;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice_type() {
        return price_type;
    }

    public void setPrice_type(int price_type) {
        this.price_type = price_type;
    }

    public float getLast_price() {
        return last_price;
    }

    public void setLast_price(float last_price) {
        this.last_price = last_price;
    }

    public float getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(float current_price) {
        this.current_price = current_price;
    }

    public float getComment_ratio() {
        return comment_ratio;
    }

    public void setComment_ratio(float comment_ratio) {
        this.comment_ratio = comment_ratio;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public int getSale_account() {
        return sale_account;
    }

    public void setSale_account(int sale_account) {
        this.sale_account = sale_account;
    }
}
