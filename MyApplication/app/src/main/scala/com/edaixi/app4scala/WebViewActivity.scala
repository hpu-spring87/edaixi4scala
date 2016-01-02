package com.edaixi.app4scala

/**
 * Created by weichunsheng on 16/1/1.
 */

import java.util

import android.os.{Build, Bundle}
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.webkit.{WebView, WebViewClient}
import com.edaixi.app4scala.http.{Constants, XAuathUtil}
import com.edaixi.app4scala.utils.PrefUtils
import com.squareup.okhttp.Request
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.callback.StringCallback
import org.json.JSONObject

class WebViewActivity extends AppCompatActivity {
  var webview: WebView = null

  protected override def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_webview)

    val myclient: MyWebViewClient = new MyWebViewClient
    webview = findViewById(R.id.webview).asInstanceOf[WebView]
    webview.setWebViewClient(myclient)
    webview.getSettings().setJavaScriptEnabled(true)
    // make sure your pinch zoom is enabled
    webview.getSettings().setBuiltInZoomControls(true)
    // don't show the zoom controls
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
      webview.getSettings().setDisplayZoomControls(false)
    }
    webview.getSettings().setBuiltInZoomControls(true)
    webview.getSettings().setSupportZoom(true)
    webview.getSettings().setUseWideViewPort(true)
    webview.getSettings().setLoadWithOverviewMode(true)

    val flag: Int = getIntent.getIntExtra("WebFlag", 0)
    if (flag == 1) {
      getjifenUrl
    }


  }

  def getjifenUrl = {
    val params: util.HashMap[String, String] = new util.HashMap();
    val orderurl: String = XAuathUtil.signHttpUrl(getApplicationContext, Constants.GET_USER_INTEGRAL_SHOP, params)

    OkHttpUtils
      .get()
      .url(orderurl)
      .build()
      .execute(new StringCallback {
        override def onError(request: Request, e: Exception): Unit = {
          Log.e("integralShopUrl:", "9090");
        }

        override def onResponse(response: String): Unit = {
          val json: JSONObject = new JSONObject(response)
          if (json.getBoolean("ret")) {
            val datajson: String = json.getString("data")
            val integralShopUrl = new JSONObject(datajson).getString("url");
            if (integralShopUrl != null) webview.loadUrl(integralShopUrl)
          }
        }
      })

  }

  private class MyWebViewClient extends WebViewClient {
    override def shouldOverrideUrlLoading(view: WebView, url: String): Boolean = {
      view.loadUrl(url)
      return true
    }
  }

}
