package com.edaixi.app4scala.moudle;

import java.io.Serializable;

public class CouponBean implements Serializable {

    private int id;
    private int coupon_price;
    private int category_id;
    private String category_display;
    private String coupon_title;
    private String exclusive_channels;
    private String coupon_time;
    private String coupon_least_price;
    private String coupon_use_limit;
    private boolean coupon_will_expire;
    private boolean usable;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCoupon_price() {
        return coupon_price;
    }

    public void setCoupon_price(int coupon_price) {
        this.coupon_price = coupon_price;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategory_display() {
        return category_display;
    }

    public void setCategory_display(String category_display) {
        this.category_display = category_display;
    }

    public String getCoupon_title() {
        return coupon_title;
    }

    public void setCoupon_title(String coupon_title) {
        this.coupon_title = coupon_title;
    }

    public String getExclusive_channels() {
        return exclusive_channels;
    }

    public void setExclusive_channels(String exclusive_channels) {
        this.exclusive_channels = exclusive_channels;
    }

    public String getCoupon_time() {
        return coupon_time;
    }

    public void setCoupon_time(String coupon_time) {
        this.coupon_time = coupon_time;
    }

    public String getCoupon_least_price() {
        return coupon_least_price;
    }

    public void setCoupon_least_price(String coupon_least_price) {
        this.coupon_least_price = coupon_least_price;
    }

    public String getCoupon_use_limit() {
        return coupon_use_limit;
    }

    public void setCoupon_use_limit(String coupon_use_limit) {
        this.coupon_use_limit = coupon_use_limit;
    }

    public boolean isCoupon_will_expire() {
        return coupon_will_expire;
    }

    public void setCoupon_will_expire(boolean coupon_will_expire) {
        this.coupon_will_expire = coupon_will_expire;
    }

    public boolean isUsable() {
        return usable;
    }

    public void setUsable(boolean usable) {
        this.usable = usable;
    }

    @Override
    public String toString() {
        return "CouponBeanInfo{" +
                "id=" + id +
                ", coupon_price=" + coupon_price +
                ", category_id=" + category_id +
                ", category_display='" + category_display + '\'' +
                ", coupon_title='" + coupon_title + '\'' +
                ", exclusive_channels='" + exclusive_channels + '\'' +
                ", coupon_time='" + coupon_time + '\'' +
                ", coupon_least_price='" + coupon_least_price + '\'' +
                ", coupon_use_limit='" + coupon_use_limit + '\'' +
                ", coupon_will_expire=" + coupon_will_expire +
                ", usable=" + usable +
                '}';
    }

}
