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
import com.edaixi.app4scala.moudle.OrderListBean;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class OrderListAdapter extends BaseAdapter {

    public List<OrderListBean> orderList;
    private Context context;
    @SuppressWarnings("unused")
    private int index;

    public OrderListAdapter(List<OrderListBean> orderList,
                            Context context) {
        this.orderList = orderList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return orderList.size();
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
        final OrderListBean orderItem = orderList.get(position);
        View view;
        ViewHolder viHolder = null;
        if (convertView != null) {
            view = convertView;
            viHolder = (ViewHolder) view.getTag();
        } else {
            view = View.inflate(context, R.layout.item_order_list, null);
            viHolder = new ViewHolder();
            viHolder.tv_order_sn = (TextView) view
                    .findViewById(R.id.tv_order_sn);
            viHolder.tv_order_time = (TextView) view
                    .findViewById(R.id.tv_order_time);
            viHolder.tv_order_address = (TextView) view
                    .findViewById(R.id.tv_order_address);
            viHolder.tv_order_cate = (TextView) view
                    .findViewById(R.id.tv_order_cate);
            view.setTag(viHolder);
        }
        viHolder.tv_order_sn.setText("订单编号: " + orderItem.getOrder_sn());
        viHolder.tv_order_time.setText("取件时间: " + getServingDate(orderItem.getYuyue_qujian_time()));
        viHolder.tv_order_address.setText("取件地址: " + orderItem.getAddress_qu());
        viHolder.tv_order_cate.setText("品类:" + orderItem.getGood());

        return view;
    }

    public static class ViewHolder {
        private TextView tv_order_sn;
        private TextView tv_order_time;
        private TextView tv_order_address;
        private TextView tv_order_cate;
    }

    public static String getServingDate(String dateString) {
        if (dateString.length() > 10) {
            StringBuilder sBuffer = new StringBuilder(dateString.substring(0,
                    10));
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            if (df.format(cal.getTime()).equals(dateString.subSequence(0, 10))) {
                sBuffer = sBuffer.insert(10, "(今天)  ");
            }
            cal.roll(Calendar.DAY_OF_YEAR, 1);
            if (df.format(cal.getTime()).equals(dateString.subSequence(0, 10))) {
                sBuffer = sBuffer.insert(10, "(明天)  ");
            }
            cal.roll(Calendar.DAY_OF_YEAR, 1);
            if (df.format(cal.getTime()).equals(dateString.subSequence(0, 10))) {
                sBuffer = sBuffer.insert(10, "(后天)  ");
            }
            if (!sBuffer.toString().contains("(")) {
                sBuffer = sBuffer.insert(10, "  ");
            }
            String replace = sBuffer.toString().replace("-", ".");
            replace = replace
                    + (dateString.subSequence(10, dateString.length())
                    .toString().replace(" ", ""));
            return replace;
        } else {
            return null;
        }
    }
}