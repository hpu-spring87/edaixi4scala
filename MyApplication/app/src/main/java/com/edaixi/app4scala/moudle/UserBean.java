package com.edaixi.app4scala.moudle;

import java.io.Serializable;

/**
 * Created by weichunsheng on 16/1/1.
 */
public class UserBean implements Serializable {

    private String user_id;
    private String userpassword;
    private String username;
    private String city;
    private String user_token;
    private String tel;
    private String province;
    private String area;
    private String address;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUser_token() {
        return user_token;
    }

    public void setUser_token(String user_token) {
        this.user_token = user_token;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "user_id='" + user_id + '\'' +
                ", userpassword='" + userpassword + '\'' +
                ", username='" + username + '\'' +
                ", city='" + city + '\'' +
                ", user_token='" + user_token + '\'' +
                ", tel='" + tel + '\'' +
                ", province='" + province + '\'' +
                ", area='" + area + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
