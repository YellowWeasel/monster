package com.erayic.agr.serverproduct.view.impl;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.webkit.JavascriptInterface;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.erayic.agr.common.AgrConstant;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.net.back.api.CommonDynamicPriceBean;
import com.erayic.agr.common.util.ErayicStack;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.LoadingDialog;
import com.erayic.agr.serverproduct.R;
import com.erayic.agr.serverproduct.R2;
import com.erayic.agr.serverproduct.adapter.DynamicPricesAdapter;
import com.erayic.agr.serverproduct.adapter.entity.DynamicAveragePriceDatas;
import com.erayic.agr.serverproduct.adapter.entity.DynamicPricePrincipalMarketDatas;
import com.erayic.agr.serverproduct.adapter.entity.MarketInfoParamter;
import com.erayic.agr.serverproduct.presenter.IDynamicPricePresenter;
import com.erayic.agr.serverproduct.presenter.impl.DynamicPricePresenterImpl;
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
public class DynamicPriceActivity extends BaseActivity implements IDynamicPriceView, AdapterView.OnItemClickListener {
    @Autowired
    String serviceID;
    @Autowired
    int cropId;//7
    @Autowired
    String cropIconUrl;
    @Autowired
    String cropName;

    int dateInterval = 7;

    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    IDynamicPricePresenter dynamicPricePresenter;
    @BindView(R2.id.serverproduct_dynamic_price_averageprice_webview)
    WebView serverproductDynamicPriceAveragepriceWebview;
    DynamicAveragePriceDatas averagePriceDatas;
    DynamicPricePrincipalMarketDatas principalMarketDatas;
    DynamicPricesAdapter adapter;
    @BindView(R2.id.serverproduct_dynamic_price_averageprice_listview)
    ListView marketPriceListView;
    @BindView(R2.id.serverproduct_dynamic_price_type_imageview)
    ImageView iconImageView;
    @BindView(R2.id.serverproduct_dynamic_price_type_textview)
    TextView typeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_price);
    }

    @Override
    public void initView() {
        averagePriceDatas = new DynamicAveragePriceDatas();
        refreshWebView();
        dynamicPricePresenter = new DynamicPricePresenterImpl(this);
        toolbar.setTitle("价格动态");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        typeTextView.setText(TextUtils.isEmpty(cropName) ? "" : cropName);
        marketPriceListView.setOnItemClickListener(this);
        serverproductDynamicPriceAveragepriceWebview.getSettings().setJavaScriptEnabled(true);
        serverproductDynamicPriceAveragepriceWebview.getSettings().setDefaultTextEncodingName("utf-8");
        serverproductDynamicPriceAveragepriceWebview.addJavascriptInterface(new DynamicPriceJsInterface(), "Datas");
        Glide.with(DynamicPriceActivity.this).
                load(TextUtils.isEmpty(cropIconUrl) ? "" : (AgrConstant.IMAGE_URL_IMAGE + cropIconUrl)).
                apply(AgrConstant.iconOptions)
                .into(iconImageView);
    }

    public void refreshWebView() {
        if (serverproductDynamicPriceAveragepriceWebview == null || !serverproductDynamicPriceAveragepriceWebview.isAttachedToWindow())
            return;
        serverproductDynamicPriceAveragepriceWebview.loadUrl("file:///android_asset/DynamicPriceTool.html");
        serverproductDynamicPriceAveragepriceWebview.setWebViewClient(new WebViewClient() {
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

    public void getDynamicDatasByDate() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -dateInterval);
        dynamicPricePresenter.getDynamicPricedatas(cropId
                , new SimpleDateFormat("yyyy/MM/dd").format(calendar.getTime())
                , new SimpleDateFormat("yyyy/MM/dd").format(new Date()), serviceID);
    }

    @Override
    public void initData() {
        adapter = new DynamicPricesAdapter(principalMarketDatas, this);
        ViewStub stub = new ViewStub(this);
        marketPriceListView.addHeaderView(stub);
        marketPriceListView.setAdapter(adapter);
        getDynamicDatasByDate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_serverproduct_dynamicprices, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//返回
            finish();
        } else if (item.getItemId() == R.id.serverproduct_dynamicprices_week) {
            dateInterval = 7;
        } else if (item.getItemId() == R.id.serverproduct_dynamicprices_month) {
            dateInterval = 30;
        } else if (item.getItemId() == R.id.serverproduct_dynamicprices_season) {
            dateInterval = 90;
        }
        getDynamicDatasByDate();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void refreshDynamicPrice(final CommonDynamicPriceBean bean) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                principalMarketDatas = new DynamicPricePrincipalMarketDatas(bean);
                averagePriceDatas = new DynamicAveragePriceDatas(bean);
                refreshWebView();
                adapter.setPrincipalMarketDatasList(principalMarketDatas);
                adapter.notifyDataSetChanged();
                dismissLoading();
            }
        });
    }

    LoadingDialog dialog;

    @Override
    public void showLoading() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialog == null) dialog = new LoadingDialog(DynamicPriceActivity.this);
                if (!dialog.isShowing()) dialog.show();
            }
        });
    }

    @Override
    public void dismissLoading() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialog == null) return;
                if (dialog.isShowing()) {
                    dialog.dismiss();
                    dialog = null;
                }
            }
        });
    }

    @Override
    public void showToast(final String msg) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ErayicToast.TextToast(getApplicationContext(), msg);
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (serverproductDynamicPriceAveragepriceWebview != null) {
            final ViewGroup viewGroup = (ViewGroup) serverproductDynamicPriceAveragepriceWebview.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(serverproductDynamicPriceAveragepriceWebview);
            }
            serverproductDynamicPriceAveragepriceWebview.removeAllViews();
            WebStorage.getInstance().deleteAllData();
            serverproductDynamicPriceAveragepriceWebview.destroy();
        }
        super.onDestroy();
        dismissLoading();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -dateInterval);

        DynamicPricePrincipalMarketDatas.MarketPriceDatas marketPriceDatas = (DynamicPricePrincipalMarketDatas.MarketPriceDatas) parent.getItemAtPosition(position);
        ARouter.getInstance().build("/serverproduct/activity/DynamicPriceDetailActivity")
                .withParcelable("paramter", new MarketInfoParamter(cropId, marketPriceDatas.getMarketName(), new SimpleDateFormat("yyyy/MM/dd").format(calendar.getTime())
                        , new SimpleDateFormat("yyyy/MM/dd").format(new Date()))).withString("serviceID", serviceID).withString("cropName", cropName).navigation();
    }

    public class DynamicPriceJsInterface {
        @JavascriptInterface
        public void ShowMessage(Object e) {
        }

        @JavascriptInterface
        public String GetData() {

            return new Gson().toJson(averagePriceDatas);
        }
    }
}
