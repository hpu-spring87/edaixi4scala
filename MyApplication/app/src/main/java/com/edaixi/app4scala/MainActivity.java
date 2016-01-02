package com.edaixi.app4scala;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.edaixi.app4scala.likebutton.LikeButtonView;
import com.edaixi.app4scala.utils.PrefUtils;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    PrefUtils prefUtils = new PrefUtils();
    String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "亲,侧滑联系我们客服哦", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        result = prefUtils.getFromPrefs(getApplicationContext(), "Is_Logined", "'false");
        if (result.equals("true")) {
            navigationView.getMenu().findItem(R.id.nav_send).setTitle("退出账户");
        } else {
            navigationView.getMenu().findItem(R.id.nav_send).setTitle("登录账户");
        }

        TextView textView = (TextView) navigationView.getHeaderView(0).findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "4008187171"));
                startActivity(intent);
            }
        });

        LikeButtonView LikeButtonViews = (LikeButtonView) findViewById(R.id.main_btn_washing);
        LikeButtonViews.setLikeBUttonClickListener(new LikeButtonView.LikeButtonClickListener() {
            @Override
            public void getVuttonClickListener(boolean isClickButton) {
                if (isClickButton)
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            //execute the task
                            if (result.equals("true")) {
                                startActivity(new Intent(MainActivity.this, TradingActivity.class));
                            } else {
                                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                            }
                        }
                    }, 1000);
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            if (result.equals("true")) {
                startActivity(new Intent(MainActivity.this, OrderListActivity.class));
            } else {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        } else if (id == R.id.nav_gallery) {
            if (result.equals("true")) {
                startActivity(new Intent(MainActivity.this, CouponListActivity.class));
            } else {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }

        } else if (id == R.id.nav_slideshow) {

            if (result.equals("true")) {
                startActivity(new Intent(MainActivity.this, WebViewActivity.class).putExtra("WebFlag", 1));
            } else {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,
                    "机智如我,新洗衣神器: http://android.myapp.com/myapp/detail.htm?apkName=com.edaixi.activity");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);

        } else if (id == R.id.nav_send) {
            if (result.equals("true")) {
                item.setTitle("登录账户");
                prefUtils.saveToPrefs(getApplicationContext(), "Is_Logined", "false");
            } else {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
