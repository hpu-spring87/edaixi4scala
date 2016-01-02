/*
 * this adapter is for orderlist
 * by wei-spring
 */
package com.edaixi.app4scala.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.edaixi.app4scala.R;
import com.edaixi.app4scala.moudle.CouponBean;
import com.edaixi.app4scala.moudle.OrderListBean;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class CouponListAdapter extends BaseAdapter {

    public List<CouponBean> couponList;
    private Context context;
    @SuppressWarnings("unused")
    private int index;

    public CouponListAdapter(List<CouponBean> couponList,
                             Context context) {
        this.couponList = couponList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return couponList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        index = position;
        final CouponBean couponItem = couponList.get(position);
        View view;
        ViewHolder viHolder = null;
        if (convertView != null) {
            view = convertView;
            viHolder = (ViewHolder) view.getTag();
        } else {
            view = View.inflate(context, R.layout.item_coupon_list, null);
            viHolder = new ViewHolder();
            viHolder.tv_coupon_name = (TextView) view
                    .findViewById(R.id.tv_coupon_name);
            viHolder.tv_coupon_xianzhi = (TextView) view
                    .findViewById(R.id.tv_coupon_xianzhi);
            viHolder.tv_coupon_time = (TextView) view
                    .findViewById(R.id.tv_coupon_time);
            viHolder.tv_coupon_desc = (TextView) view
                    .findViewById(R.id.tv_coupon_desc);
            view.setTag(viHolder);
        }
        viHolder.tv_coupon_name.setText(": " + couponItem.getCoupon_title());
        if (couponItem.getCoupon_use_limit().length() > 1) {
            viHolder.tv_coupon_xianzhi.setText(": " + couponItem.getCoupon_use_limit());
        } else {
            viHolder.tv_coupon_xianzhi.setVisibility(View.GONE);
        }

        viHolder.tv_coupon_desc.setText(": " + couponItem.getCategory_display());
        viHolder.tv_coupon_time.setText(": " + couponItem.getCoupon_time());

        return view;
    }

    public static class ViewHolder {
        private TextView tv_coupon_name;
        private TextView tv_coupon_xianzhi;
        private TextView tv_coupon_time;
        private TextView tv_coupon_desc;
    }

}