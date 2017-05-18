package com.erayic.agr.serverproduct.view.impl;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.net.back.api.CommonMarketDynamicPriceBean;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.LoadingDialog;
import com.erayic.agr.serverproduct.R;
import com.erayic.agr.serverproduct.R2;
import com.erayic.agr.serverproduct.adapter.DynamicPricesDetailAdapter;
import com.erayic.agr.serverproduct.adapter.entity.DesignatedMarketDatas;
import com.erayic.agr.serverproduct.adapter.entity.MarketDynamicPriceDatas;
import com.erayic.agr.serverproduct.adapter.entity.MarketInfoParamter;
import com.erayic.agr.serverproduct.presenter.IDynamicPriceDetailPresenter;
import com.erayic.agr.serverproduct.presenter.IPoliciesRegulationsDetailPresenter;
import com.erayic.agr.serverproduct.presenter.impl.DynamicPriceDetailPresenterImpl;
import com.erayic.agr.serverproduct.presenter.impl.PoliciesRegulationsDetailPresenterImpl;
import com.erayic.agr.serverproduct.view.IDynamicPriceDetailView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by wxk on 2017/5/15.
 */
@Route(path = "/serverproduct/activity/DynamicPriceDetailActivity", name = "市场价格动态详情")
public class DynamicPriceDetailActivity extends BaseActivity implements IDynamicPriceDetailView {
    @Autowired
    MarketInfoParamter paramter;
    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.serverproduct_dynamic_price_detail_title_textview)
    TextView marketTitle;
    @BindView(R2.id.serverproduct_dynamic_price_detail_webview)
    WebView dynamicDetailPriceWebView;
    @BindView(R2.id.serverproduct_dynamic_price_detail_averageprice_listview)
    ListView detailListView;

    IDynamicPriceDetailPresenter presenter;
    DesignatedMarketDatas marketDatas;
    DynamicPricesDetailAdapter adapter;
    MarketDynamicPriceDatas marketDynamicPriceDatasList;

    @Override
    public void initData() {
        adapter = new DynamicPricesDetailAdapter(marketDynamicPriceDatasList, this);
        detailListView.setAdapter(adapter);

        if (paramter == null) return;
        marketTitle.setText(paramter.getMarketName());
        presenter.getMarketDatas(paramter.getCropId(),
                paramter.getMarketName(), paramter.getStart(), paramter.getEnd());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_price_detail);
    }

    @Override
    public void initView() {
        toolbar.setTitle("市场价格详情");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        dynamicDetailPriceWebView.getSettings().setJavaScriptEnabled(true);
        dynamicDetailPriceWebView.getSettings().setDefaultTextEncodingName("utf-8");
        dynamicDetailPriceWebView.addJavascriptInterface(new DynamicPriceDetailJsInterface(), "Datas");
        dynamicDetailPriceWebView.loadUrl("file:///android_asset/DynamicPriceDetailTool.html");
        refreshWebView();
        presenter = new DynamicPriceDetailPresenterImpl(this);
    }

    public void refreshWebView() {

        dynamicDetailPriceWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                dynamicDetailPriceWebView.loadUrl("javascript:refreshView()");
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    public void refreshMarketDynamicPrices(MarketDynamicPriceDatas beans) {
        this.marketDynamicPriceDatasList=beans;
        adapter.setMarketDynamicPriceDatas(this.marketDynamicPriceDatasList);
        adapter.notifyDataSetChanged();
        this.marketDatas = new DesignatedMarketDatas(beans,paramter.getMarketName());
        refreshWebView();
    }

    LoadingDialog dialog;

    @Override
    public void showLoading() {
        if (dialog == null) {
            dialog = new LoadingDialog(this);
        }
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }

    @Override
    public void dismissLoading() {
        if (dialog == null) return;
        dialog.dismiss();
        dialog = null;
    }

    @Override
    protected void onDestroy() {
        dismissLoading();
        super.onDestroy();

    }

    @Override
    public void showToast(String msg) {
        ErayicToast.TextToast(this, msg);
    }

    public class DynamicPriceDetailJsInterface {

        @JavascriptInterface
        public String GetData() {

            return new Gson().toJson(marketDatas);
        }
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//返回
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}