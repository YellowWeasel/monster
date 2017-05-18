package com.erayic.agr.serverproduct.view.impl;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.config.PreferenceUtils;
import com.erayic.agr.common.net.back.api.CommonFutureWeatherBean;
import com.erayic.agr.common.net.back.api.CommonRealTimeWeatherBean;
import com.erayic.agr.common.util.ErayicNetDate;
import com.erayic.agr.common.util.ErayicTextUtil;
import com.erayic.agr.common.view.LoadingDialog;
import com.erayic.agr.serverproduct.Constants;
import com.erayic.agr.serverproduct.DateFormatUtils;
import com.erayic.agr.serverproduct.R;
import com.erayic.agr.serverproduct.R2;
import com.erayic.agr.serverproduct.TextUtils;
import com.erayic.agr.serverproduct.adapter.ForecastReportingAdapter;
import com.erayic.agr.serverproduct.adapter.entity.BaseForecastInfo;
import com.erayic.agr.serverproduct.adapter.entity.FutureForecastDatas;
import com.erayic.agr.serverproduct.adapter.entity.RealTimeForecastInfo;
import com.erayic.agr.serverproduct.adapter.entity.ReportingInfo;
import com.erayic.agr.serverproduct.presenter.IReportingPresenter;
import com.erayic.agr.serverproduct.presenter.impl.ReportingPresenterImpl;
import com.erayic.agr.serverproduct.view.IReportingInfoView;
import com.erayic.agr.serverproduct.view.IShowClockView;
import com.erayic.agr.serverproduct.view.custom.NoScrollWebView;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.util.List;
import java.util.Timer;

import butterknife.BindView;

/**
 * Created by wxk on 2017/5/4.
 */

@Route(path = "/serverproduct/activity/ReportingActivity", name = "农事气象")
public class ReportingActivity extends BaseActivity implements IReportingInfoView,IShowClockView {
    @Autowired
    String serviceID;

    @BindView(R2.id.serverproduct_reporting_webview)
    NoScrollWebView webView;

    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.serverproduct_rain_lvl_textview)
    TextView rainLvlTextView;
    @BindView(R2.id.serverproduct_rain_valuetext)
    TextView rainTextView;
    @BindView(R2.id.serverproduct_hum_valuetext)
    TextView humTextView;

    @BindView(R2.id.serverproduct_windlvl_valuetext)
    TextView windLvlTextView;
    @BindView(R2.id.serverproduct_windspeed_valuetext)
    TextView windSpeedTextView;

    @BindView(R2.id.serverproduct_tmp_valuetext)
    TextView tmpTextView;
    @BindView(R2.id.serverproduct_base_valuetext)
    TextView baseTextView;
    @BindView(R2.id.serverproduct_fertilization_textview)
    TextView fertilizationTextView;
    @BindView(R2.id.serverproduct_picking_textview)
    TextView pickingTextView;
    @BindView(R2.id.serverproduct_clock_text)
    TextView clockTextview;
    @BindView(R2.id.serverproduct_reporting_lon_textview)
    TextView baseLonTextView;
    @BindView(R2.id.serverproduct_reporting_lat_textview)
    TextView baseLatTextView;
    @BindView(R2.id.serverproduct_spray_textview)
    TextView sprayTextView;
    @BindView(R2.id.serverproduct_irrigation_textview)
    TextView irrigationTextView;

    @BindView(R2.id.serverproduct_reporting_base_linearlayout)
    LinearLayout parentLinearLayout;
    @BindView(R2.id.serverproduct_reporting_listview)
    ListView reportingListView;
    @BindView(R2.id.serverproduct_reporting_realtime_linearlayout)
    LinearLayout realtimeLinearlayout;

    public BaseForecastInfo infos;
    public IReportingPresenter reportingPresenter;
    ForecastReportingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast_reporting);
        setLayoutHeight();
    }

    @Override
    public void initView() {
        toolbar.setTitle("实况天气");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        baseLonTextView.setText(TextUtils.FormatNumber(PreferenceUtils.getParam("BaseLon"),4));
        baseLatTextView.setText(TextUtils.FormatNumber(PreferenceUtils.getParam("BaseLat"),4));

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDefaultTextEncodingName("utf-8");
        webView.addJavascriptInterface(new DataForJsInterface(), "Datas");
        refreshWebView();
    }
     public void setLayoutHeight(){
         int stateHeight=-1;
         int resourceId=getResources().getIdentifier("status_bar_height","dimen","android");
         if (resourceId>0){
             stateHeight=getResources().getDimensionPixelSize(resourceId);
         }
         WindowManager manager= (WindowManager) getSystemService(Context.WINDOW_SERVICE);
         Display defaultDisplay = manager.getDefaultDisplay();
         ViewGroup.LayoutParams layoutParams = parentLinearLayout.getLayoutParams();
         layoutParams.height=defaultDisplay.getHeight()-toolbar.getLayoutParams().height-stateHeight;
         parentLinearLayout.setLayoutParams(layoutParams);
     }
    @Override
    public void initData() {
        DateFormatUtils.showClock(100,"yyyy-MM-dd HH:mm",this);
        adapter=new ForecastReportingAdapter(this);
        reportingListView.setAdapter(adapter);
        reportingPresenter = new ReportingPresenterImpl(this);
        infos = new BaseForecastInfo();
        infos.setBaseName(PreferenceUtils.getParam("BaseName"));
        baseTextView.setText(PreferenceUtils.getParam("BaseName"));
        reportingPresenter.getFeatureWeather();
        reportingPresenter.getRealTimeWeather();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//返回
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showClock(final String strDate) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                clockTextview.setText(strDate);
            }
        });
    }

    public class DataForJsInterface {

        public ReportingInfo reportingInfo;

        @JavascriptInterface
        public String GetData() {
            reportingInfo = new ReportingInfo(infos);
            String json = "";
            Gson gson = new Gson();
            json = gson.toJson(reportingInfo);
            return json;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DateFormatUtils.release();
    }

    @Override
    public void showToast(String msg) {

    }

    private LoadingDialog dialog;

    @Override
    public void refreshRealDataView(CommonRealTimeWeatherBean bean) {
        infos.setNowForecastInfo(new RealTimeForecastInfo(bean.getAppearTime(), bean.getRain_10M()
                , bean.getWind_Max(), bean.getWindDirect_Max(), bean.getTemp_Max()
                , bean.getTemp_Min(), bean.getHumi()));
        RealTimeForecastInfo nowInfo = infos.getNowForecastInfo();
        rainLvlTextView.setText(Constants.getRainLvlTitle(nowInfo.getRain_10M()));
        if (!Constants.getRainLvlTitle(nowInfo.getRain_10M()).equals("晴")){
            rainTextView.setText(String.valueOf(nowInfo.getRain_10M()) + "mm/10min");
        }
        tmpTextView.setVisibility(View.VISIBLE);
        tmpTextView.setText(String.valueOf(nowInfo.getTemp_Max()) + "℃");
        humTextView.setText(String.valueOf(nowInfo.getHumi()) + "%");

        String windLvl=Constants.getWindLvlTitle(nowInfo.getWind_Max());
        windLvlTextView.setText(windLvl);
        if (windLvl.equals("—")){
            windSpeedTextView.setText("—");
        }else{
            windSpeedTextView.setText(String.valueOf(nowInfo.getWind_Max()+"m/s"));
        }

        realtimeLinearlayout.setVisibility(View.VISIBLE);
        setTextViewContent(fertilizationTextView, 75);
        setTextViewContent(pickingTextView, 89);
        setTextViewContent(sprayTextView, 30);
        setTextViewContent(irrigationTextView, 20);
    }

    public void setTextViewContent(TextView tv, int score) {
        String strSocre="";
        if (score <=0 || score > 100) {
            strSocre="—";
            tv.setTextColor(tv.getResources().getColor(R.color.pinred));
        } else if (score < 60) {//不适宜
            strSocre=String.valueOf(score)+"\n"+"(不适宜)";
            tv.setTextColor(tv.getResources().getColor(R.color.pinred));
        } else if (60 <= score && score < 80) {//一般、不建议
            strSocre=String.valueOf(score)+"\n"+"(适宜)";
            tv.setTextColor(tv.getResources().getColor(R.color.pinblue));
        } else if (80 <= score && score <= 100) {//适宜
            strSocre=String.valueOf(score)+"\n"+"(很适宜)";
            tv.setTextColor(tv.getResources().getColor(R.color.green));
        }
        int start=strSocre.indexOf("(");
        int end=strSocre.length();
        Spannable textSpan=new SpannableString(strSocre);
        textSpan.setSpan(new AbsoluteSizeSpan(sp2px(25)),0,start,Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        textSpan.setSpan(new AbsoluteSizeSpan(sp2px(15)),start,end,Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        tv.setText(textSpan);
    }

    public  int sp2px(float spValue) {
        final float fontScale = getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public void refreshWebView() {
        webView.loadUrl("file:///android_asset/ReportingTool.html");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                webView.loadUrl("javascript:startView()");
                dismissLoading();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    public void refreshFeatureDataView(List<CommonFutureWeatherBean> bean) {
        FutureForecastDatas featureForecastDatas = new FutureForecastDatas();
        infos.setFeatureForecastDatas(featureForecastDatas.InitFeature(bean));
        adapter.setForecastDatas(featureForecastDatas);
        adapter.notifyDataSetChanged();
        refreshWebView();
    }

    int index = 0;

    @Override
    public void showLoading() {
        index++;
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialog == null)
                    dialog = new LoadingDialog(ReportingActivity.this);
                if (!dialog.isShowing()) {
                    dialog.show();
                }
            }
        });
    }

    @Override
    public void dismissLoading() {
        index--;
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialog == null)
                    dialog = new LoadingDialog(ReportingActivity.this);
                if (index == 0)
                    dialog.dismiss();
            }
        });
    }

}
