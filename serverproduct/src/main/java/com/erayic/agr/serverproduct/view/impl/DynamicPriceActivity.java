package com.erayic.agr.serverproduct.view.impl;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.config.PreferenceUtils;
import com.erayic.agr.common.net.back.api.CommonDynamicPriceBean;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.serverproduct.DateFormatUtils;
import com.erayic.agr.serverproduct.R;
import com.erayic.agr.serverproduct.R2;
import com.erayic.agr.serverproduct.adapter.entity.DynamicAveragePriceDatas;
import com.erayic.agr.serverproduct.adapter.entity.DynamicPrincipalMarketDatas;
import com.erayic.agr.serverproduct.presenter.IDynamicPricePresenter;
import com.erayic.agr.serverproduct.presenter.impl.DynamicPricePresenter;
import com.erayic.agr.serverproduct.view.IDynamicPriceView;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wxk on 2017/5/11.
 */
@Route(path = "/serverproduct/activity/DynamicPriceActivity", name = "价格动态")
public class DynamicPriceActivity extends BaseActivity implements IDynamicPriceView {
    @Autowired
    int cropId;//7

    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    IDynamicPricePresenter dynamicPricePresenter;
    @BindView(R2.id.serverproduct_dynamic_price_averageprice_webview)
    WebView serverproductDynamicPriceAveragepriceWebview;
    DynamicAveragePriceDatas averagePriceDatas;
    DynamicPrincipalMarketDatas principalMarketDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_price);
        ButterKnife.bind(this);
        refreshWebView();
    }

    @Override
    public void initView() {
        dynamicPricePresenter = new DynamicPricePresenter(this);
        toolbar.setTitle("市场价格");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        serverproductDynamicPriceAveragepriceWebview.getSettings().setJavaScriptEnabled(true);
        serverproductDynamicPriceAveragepriceWebview.getSettings().setDefaultTextEncodingName("utf-8");
        serverproductDynamicPriceAveragepriceWebview.addJavascriptInterface(new DynamicPriceJsInterface(),"Datas");

    }

    public void refreshWebView(){
        serverproductDynamicPriceAveragepriceWebview.loadUrl("file:///android_asset/DynamicPriceTool.html");
        serverproductDynamicPriceAveragepriceWebview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                serverproductDynamicPriceAveragepriceWebview.loadUrl("javascript:refreshView()");
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }



    @Override
    public void initData() {
        principalMarketDatas=new DynamicPrincipalMarketDatas();
        averagePriceDatas=new DynamicAveragePriceDatas();
        Date date=new Date();
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH,-7);
        dynamicPricePresenter.getDynamicPricePresenter(7
                ,new SimpleDateFormat("yyyy/MM/dd").format(calendar.getTime())
                ,new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//返回
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void refreshDynamicPrice(final CommonDynamicPriceBean bean) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                principalMarketDatas=new DynamicPrincipalMarketDatas(bean);
                averagePriceDatas=new DynamicAveragePriceDatas(bean);
                refreshWebView();
            }
        });

    }

    @Override
    public void showToast(final String msg) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ErayicToast.TextToast(getApplicationContext(),msg);
            }
        });
    }

    public class DynamicPriceJsInterface{
        @JavascriptInterface
        public void ShowMessage(){
            showToast("js异常");
        }
        @JavascriptInterface
        public String GetData(){

            return new Gson().toJson(averagePriceDatas);
        }
    }
}
