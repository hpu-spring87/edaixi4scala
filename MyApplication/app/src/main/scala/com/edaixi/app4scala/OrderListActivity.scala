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
import android.widget.{SimpleAdapter, ListView}
import com.alibaba.fastjson.JSON
import com.edaixi.app4scala.adapter.OrderListAdapter
import com.edaixi.app4scala.http.{Constants, XAuathUtil}
import com.edaixi.app4scala.moudle.OrderListBean
import com.squareup.okhttp.Request
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.callback.StringCallback
import org.json.JSONObject

/**
 * @author wei-springs
 *         2016/1/1
 */

class OrderListActivity extends AppCompatActivity with SwipeRefreshLayout.OnRefreshListener {

  private var swipe_container: SwipeRefreshLayout = null
  private var order_list: ListView = null
  var mAdapter: SimpleAdapter = null


  protected override def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_orderlist)

    swipe_container = findViewById(R.id.swipe_container_order).asInstanceOf[SwipeRefreshLayout]
    swipe_container.setOnRefreshListener(new OnRefreshListener {
      override def onRefresh(): Unit = {
        swipe_container.setRefreshing(false)
        swipe_container.setEnabled(false)
      }
    })
    order_list = findViewById(R.id.order_list).asInstanceOf[ListView]
    getOrderList
  }

  override def onRefresh(): Unit = {
    //执行刷新的逻辑
    getOrderList
  }

  def getOrderList = {
    val params: util.HashMap[String, String] = new util.HashMap();
    params.put("order_type", "1")
    params.put("page", "1")
    params.put("per_page", "10")
    val orderurl: String = XAuathUtil.signHttpUrl(getApplicationContext, Constants.GET_ORDER_LIST, params)

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
            val loadMoreServing: util.List[OrderListBean] = JSON.parseArray(datajson, classOf[OrderListBean])
            Log.e("loadMoreServing:", loadMoreServing.size() + "");
            val orderAdapter: OrderListAdapter = new OrderListAdapter(loadMoreServing, getApplicationContext)
            order_list.setAdapter(orderAdapter)
          }
        }
      })


  }
}
