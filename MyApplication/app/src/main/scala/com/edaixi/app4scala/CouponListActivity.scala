package com.edaixi.app4scala

/**
 * Created by weichunsheng on 16/1/1.
 */

import java.util

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.{ListView, SimpleAdapter}
import com.alibaba.fastjson.JSON
import com.edaixi.app4scala.adapter.{CouponListAdapter, OrderListAdapter}
import com.edaixi.app4scala.http.{Constants, XAuathUtil}
import com.edaixi.app4scala.moudle.{CouponBean, OrderListBean}
import com.edaixi.app4scala.utils.PrefUtils
import com.squareup.okhttp.Request
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.callback.StringCallback
import org.json.JSONObject

/**
 * @author wei-springs
 *         2016/1/1
 */

class CouponListActivity extends AppCompatActivity with SwipeRefreshLayout.OnRefreshListener {

  //private var swipe_container: SwipeRefreshLayout = null
  private var coupon_list: ListView = null


  protected override def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_couponlist)

//    swipe_container = findViewById(R.id.swipe_container_coupon).asInstanceOf[SwipeRefreshLayout]
//    swipe_container.setOnRefreshListener(new OnRefreshListener {
//      override def onRefresh(): Unit = {
//        swipe_container.setRefreshing(false)
//        swipe_container.setEnabled(false)
//      }
//    })
    coupon_list = findViewById(R.id.coupon_list).asInstanceOf[ListView]
    getCouponList
  }

  override def onRefresh(): Unit = {
    //执行刷新的逻辑
    getCouponList
  }

  def getCouponList = {
    val params: util.HashMap[String, String] = new util.HashMap();
    val pre: PrefUtils = new PrefUtils()
    val orderurl: String = XAuathUtil.signHttpUrl(getApplicationContext, Constants.GET_COUPON_LIST, params)

    OkHttpUtils
      .get()
      .url(orderurl)
      .build()
      .execute(new StringCallback {
        override def onError(request: Request, e: Exception): Unit = {}

        override def onResponse(response: String): Unit = {
          val json: JSONObject = new JSONObject(response)
          if (json.getBoolean("ret")) {
            val datajson: String = json.getString("data")
            val loadMoreServing: util.List[CouponBean] = JSON.parseArray(datajson, classOf[CouponBean])
            Log.e("couponServing:", loadMoreServing.size() + "");
            val orderAdapter: CouponListAdapter = new CouponListAdapter(loadMoreServing, getApplicationContext)
            coupon_list.setAdapter(orderAdapter)
          }
        }
      })


  }
}
