package com.erayic.agr.serverproduct.view.impl;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextPaint;
import android.view.MenuItem;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.LoadingDialog;
import com.erayic.agr.serverproduct.R;
import com.erayic.agr.serverproduct.R2;
import com.erayic.agr.serverproduct.adapter.entity.PoliciesRegulationsDetailDatas;
import com.erayic.agr.serverproduct.presenter.IPoliciesRegulationsDetailPresenter;
import com.erayic.agr.serverproduct.presenter.impl.PoliciesRegulationsDetailPresenterImpl;
import com.erayic.agr.serverproduct.view.IPoliciesRegulationDetailView;

import butterknife.BindView;

/**
 * Created by wxk on 2017/5/11.
 */
@Route(path = "/serverproduct/activity/PoliciesRegulationsDetailActivity", name = "政策法规详情")
public class PoliciesRegulationsDetailActivity extends BaseActivity implements IPoliciesRegulationDetailView {
    @Autowired
    int Id;
    PoliciesRegulationsDetailDatas mPoliciesRegulationsData;
    @BindView(R2.id.serverproduct_policies_regulations_title_textview)
    TextView titleTextView;
    @BindView(R2.id.serverproduct_policies_regulations_source_textview)
    TextView sourceTextView;
    @BindView(R2.id.serverproduct_policies_regulations_publishdate_textview)
    TextView publishDateTextView;
    @BindView(R2.id.serverproduct_policies_regulations_content_textview)
    TextView contentTextView;
    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    IPoliciesRegulationsDetailPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policies_regulations_detail);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//返回
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void initView() {
        toolbar.setTitle("政策法规");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public void refreshView() {
        if (mPoliciesRegulationsData == null) {
            titleTextView.setText("暂无数据");
        }
        TextPaint paint = titleTextView.getPaint();
        paint.setFakeBoldText(true);
        titleTextView.setText(mPoliciesRegulationsData.getTitle());
        titleTextView.setTextColor(Color.parseColor("#000000"));
        sourceTextView.setText((mPoliciesRegulationsData.getInfoSource()!=null)?"来源:\t" + mPoliciesRegulationsData.getInfoSource():null);
        publishDateTextView.setText(mPoliciesRegulationsData.getPublishTime());
        contentTextView.setText(mPoliciesRegulationsData.getTxtContent());
    }
    LoadingDialog dialog;
    @Override
    public void initData() {
        presenter = new PoliciesRegulationsDetailPresenterImpl(this);
        presenter.getPoliciesRegulationDetailDatas(Id);
    }

    @Override
    public void showToast(final String msg) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ErayicToast.TextToast(PoliciesRegulationsDetailActivity.this,msg);
            }
        });
    }

    @Override
    public void refreshPoliciesRegulationDatas(PoliciesRegulationsDetailDatas datas) {
        mPoliciesRegulationsData = datas;
        refreshView();
    }

    @Override
    public void showLoading() {
        if (dialog==null)dialog=new LoadingDialog(this);
        if (!dialog.isShowing())dialog.show();
    }

    @Override
    public void dismissLoading() {
        if (dialog==null)return;
        if (dialog.isShowing())dialog.dismiss();
    }
}
