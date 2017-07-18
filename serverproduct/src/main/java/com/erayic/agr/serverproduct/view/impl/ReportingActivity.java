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
import android.view.ViewParent;
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
import com.erayic.agr.common.util.ErayicStack;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.LoadingDialog;
import com.erayic.agr.serverproduct.Constants;
import com.erayic.agr.serverproduct.DateFormatUtils;
import com.erayic.agr.serverproduct.R;
import com.erayic.agr.serverproduct.R2;
import com.erayic.agr.serverproduct.adapter.ForecastReportingAdapter;
import com.erayic.agr.serverproduct.adapter.entity.EnvironmentParamterDatas;
import com.erayic.agr.serverproduct.presenter.IReportingPresenter;
import com.erayic.agr.serverproduct.presenter.impl.ReportingPresenterImpl;
import com.erayic.agr.serverproduct.view.IReportingInfoView;
import com.erayic.agr.serverproduct.view.IShowClockView;
import com.erayic.agr.serverproduct.view.custom.NoScrollWebView;
import com.google.gson.Gson;

import butterknife.BindView;

/**
 * Created by wxk on 2017/5/4.
 */

@Route(path = "/serverproduct/activity/ReportingActivity", name = "农事气象")
public class ReportingActivity extends BaseActivity implements IReportingInfoView, IShowClockView {
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
    public EnvironmentParamterDatas environmentParamterDatas;
    public IReportingPresenter reportingPresenter;
    private ForecastReportingAdapter adapter;
    //下拉刷新

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast_reporting);
        setLayoutHeight();
    }

    @Override
    public void initView() {
        toolbar.setTitle("农业气象实况");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        baseTextView.setText(PreferenceUtils.getParam("BaseName"));
        DateFormatUtils.showClock(100, "yyyy-MM-dd HH:mm", this);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDefaultTextEncodingName("utf-8");
        webView.addJavascriptInterface(new DataForJsInterface(), "Datas");
        refreshWebView();
    }

    public void setLayoutHeight() {
        int stateHeight = -1;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            stateHeight = getResources().getDimensionPixelSize(resourceId);
        }
        WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display defaultDisplay = manager.getDefaultDisplay();
        ViewGroup.LayoutParams layoutParams = parentLinearLayout.getLayoutParams();
        layoutParams.height = defaultDisplay.getHeight() - toolbar.getLayoutParams().height - stateHeight;
        parentLinearLayout.setLayoutParams(layoutParams);
    }

    @Override
    public void initData() {
        adapter = new ForecastReportingAdapter(this);
        reportingListView.setAdapter(adapter);

        reportingPresenter = new ReportingPresenterImpl(this);
        reportingPresenter.getRealTimeWeather();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            ErayicStack.getInstance().finishCurrentActivity();
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
        @JavascriptInterface
        public String GetData() {
            String json = "";
            Gson gson = new Gson();
            if (environmentParamterDatas != null)
                json = gson.toJson(environmentParamterDatas.getFeatures());
            return json;
        }
    }

    @Override
    protected void onDestroy() {
        if (webView != null) {
            ViewParent parent = webView.getParent();
            if (parent != null) ((ViewGroup) parent).removeAllViews();
            webView.stopLoading();
            //防止webview内存泄漏
            webView.getSettings().setJavaScriptEnabled(false);
            webView.clearHistory();
            webView.clearView();
            try {
                webView.destroy();
                webView = null;
            } catch (Exception ex) {
            }
        }
        super.onDestroy();
        DateFormatUtils.release();
    }

    @Override
    public void showToast(final String msg) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ErayicToast.TextToast(ReportingActivity.this, msg);
            }
        });
    }

    private LoadingDialog dialog;


    public void setTextViewContent(TextView tv, int score) {
        String strSocre = "";
        if (score <= 0 || score > 100) {
            strSocre = "—";
            tv.setTextColor(tv.getResources().getColor(R.color.pinred));
        } else if (score < 60) {//不适宜
            strSocre = String.valueOf(score) + "\n" + "(不适宜)";
            tv.setTextColor(tv.getResources().getColor(R.color.pinred));
        } else if (60 <= score && score < 80) {//一般、不建议
            strSocre = String.valueOf(score) + "\n" + "(适宜)";
            tv.setTextColor(tv.getResources().getColor(R.color.pinblue));
        } else if (80 <= score && score <= 100) {//适宜
            strSocre = String.valueOf(score) + "\n" + "(非常适宜)";
            tv.setTextColor(tv.getResources().getColor(R.color.green));
        }
        int start = strSocre.indexOf("(");
        int end = strSocre.length();
        Spannable textSpan = new SpannableString(strSocre);
        textSpan.setSpan(new AbsoluteSizeSpan(sp2px(25)), 0, start, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        textSpan.setSpan(new AbsoluteSizeSpan(sp2px(15)), start, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        tv.setText(textSpan);
    }

    public int sp2px(float spValue) {
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
    public void refreshReportingInfoView(EnvironmentParamterDatas datas) {

        if (datas == null || datas.getGps() == null || datas.getFeatures() == null
                || datas.getSuggest() == null || datas.getRealTimeInfo() == null) return;
        //基地信息
        baseLonTextView.setText(String.valueOf(datas.getGps().getLon()));
        baseLatTextView.setText(String.valueOf(datas.getGps().getLat()));
        baseTextView.setText(datas.getBaseName());
        //预测数据表格
        this.environmentParamterDatas = datas;
        refreshWebView();
        //预测数据列表
        adapter.setFeatureWeather(datas.getFeatures());
        adapter.notifyDataSetChanged();
        //实况数据
        EnvironmentParamterDatas.RealTime realTime = datas.getRealTimeInfo();
        rainLvlTextView.setText(Constants.getRainLvlTitle(realTime.getRain_10M()));
        if (!Constants.getRainLvlTitle(realTime.getRain_10M()).equals("晴")) {
            rainTextView.setText(String.valueOf(realTime.getRain_10M()) + "mm/10min");
        }
        tmpTextView.setVisibility(View.VISIBLE);
        tmpTextView.setText(String.valueOf(realTime.getTemp_Max()) + "℃");
        windSpeedTextView.setText(String.valueOf(realTime.getWind_Max()) + "m/s");
        windLvlTextView.setText(realTime.getWindDesc());
        realtimeLinearlayout.setVisibility(View.VISIBLE);
        setTextViewContent(fertilizationTextView, datas.getSuggest()
                .getFertilizationSuggestion().getResultIndex());
        setTextViewContent(pickingTextView, datas.getSuggest()
                .getHarvestSuggestion().getResultIndex());
        setTextViewContent(sprayTextView, datas.getSuggest()
                .getSprayInsecticideSuggestion().getResultIndex());
        setTextViewContent(irrigationTextView, datas.getSuggest()
                .getIrrigationSuggestion().getResultIndex());
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
