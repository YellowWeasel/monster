package com.erayic.agr.serverproduct.view.impl;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.serverproduct.R;
import com.erayic.agr.serverproduct.R2;
import com.erayic.agr.serverproduct.entity.BaseForecastInfo;
import com.erayic.agr.serverproduct.entity.ForecastInfo;
import com.erayic.agr.serverproduct.entity.ReportingInfo;
import com.erayic.agr.serverproduct.view.NoScrollWebView;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * Created by wxk on 2017/5/4.
 */

@Route(path = "/serverproduct/activity/ReportingActivity", name = "我的服务")
public class ReportingActivity extends BaseActivity {
    @Autowired
    String serviceID;
    @BindView(R2.id.forecast_reporting_webview)
    NoScrollWebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast_reporting);
    }
    @Override
    public void initView() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDefaultTextEncodingName("utf-8");
        webView.addJavascriptInterface(new DataForJsInterface(),"Datas");
        webView.loadUrl("file:///android_asset/ReportingTool.html");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                webView.loadUrl("javascript:refershView()");
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

    }
    public class  DataForJsInterface{
        public BaseForecastInfo infos;
        public ReportingInfo reportingInfo;

        public void getDatas(){
            reportingInfo=new ReportingInfo();
            reportingInfo.rainDatas=new double[]{2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3};
            reportingInfo.tmpDatas=new double[]{2.0, 4.9, 4, 3, 5, 3.2, 3.3, 6, 5.2, 5, 4.1, 3.3};
            reportingInfo.windDatas=new double[]{2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2};
            reportingInfo.maxRainLabel=300;
            reportingInfo.minRainLabel=0;
            reportingInfo.rainYInterval=50;
        }
        public void InitDatas(){
            reportingInfo=new ReportingInfo();
            reportingInfo.tmpDatas=new double[infos.forecastInfos.size()];
            reportingInfo.rainDatas=new double[infos.forecastInfos.size()];
            reportingInfo.windDatas=new double[infos.forecastInfos.size()];
              for(int i=0;i<infos.forecastInfos.size();i++){
                  reportingInfo.tmpDatas[i]=infos.forecastInfos.get(i).tmp;
                  reportingInfo.rainDatas[i]=infos.forecastInfos.get(i).rain;
                  reportingInfo.windDatas[i]=infos.forecastInfos.get(i).wind;
              }
        }

        @JavascriptInterface
        public String GetData(){
//            initData();
            getDatas();
            String json="";
            Gson gson=new Gson();
            json=gson.toJson(reportingInfo);
            Log.v("wxk",json);
            return json;
        }
    }
}
