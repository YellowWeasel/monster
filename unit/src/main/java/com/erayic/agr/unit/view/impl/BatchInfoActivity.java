package com.erayic.agr.unit.view.impl;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.erayic.agr.common.AgrConstant;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.net.back.enums.EnumBatchStatus;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchInfoBean;
import com.erayic.agr.common.util.ErayicNetDate;
import com.erayic.agr.common.util.ErayicStack;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.CircleImageView;
import com.erayic.agr.common.view.tooblbar.ErayicToolbar;
import com.erayic.agr.unit.R;
import com.erayic.agr.unit.R2;
import com.erayic.agr.unit.event.BatchInfoEvent;
import com.erayic.agr.unit.view.IBatchInfosView;
import com.jaeger.library.StatusBarUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.joda.time.DateTime;

import butterknife.BindView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

@Route(path = "/unit/activity/BatchInfoActivity", name = "批次详情")
public class BatchInfoActivity extends BaseActivity implements IBatchInfosView {

    @BindView(R2.id.toolbar)
    ErayicToolbar toolbar;
    @BindView(R2.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R2.id.unit_batch_info_tab)
    TabLayout unitBatchInfoTab;
    @BindView(R2.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R2.id.toolbar_content)
    LinearLayout toolbarContent;
    @BindView(R2.id.unit_batch_info_viewPager)
    ViewPager unitBatchInfoViewPager;

    @BindView(R2.id.unit_content_icon)
    CircleImageView unitContentIcon;//批次图片
    @BindView(R2.id.unit_content_name)
    TextView unitContentName;//批次名称
    @BindView(R2.id.unit_content_date)
    TextView unitContentDate;//种植时间
    @BindView(R2.id.unit_content_subName)
    TextView unitContentArea;//种植面积
    @BindView(R2.id.unit_content_twoName)
    TextView unitContentResp;//责任人

    @Autowired
    String unitID;
    @Autowired
    String batchID;
    @Autowired
    String batchName;
    @Autowired
    String imgUrl;//批次图片
    @Autowired(name = "history")
    boolean isHistory;

    private CommonUnitBatchInfoBean.Batch batchInfo;

    private BatchPagerAdapter adapter;
    private String[] strTitle = new String[]{"生长数据", "生产履历", "工作日志"};
    private Fragment[] fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_batch_infos);
    }

    @Override
    public void initView() {
        EventBus.getDefault().register(this);
        toolbarLayout.setTitle(TextUtils.isEmpty(batchName) ? "批次详情" : (batchName + ""));
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        fragments = new Fragment[]{(Fragment) ARouter.getInstance().build("/unit/fragment/BatchInfoByStatueFragment").withString("batchID", batchID).withString("unitID", unitID).navigation(),
                (Fragment) ARouter.getInstance().build("/unit/fragment/BatchInfoByResumeFragment").withString("batchID", batchID).navigation(),
                (Fragment) ARouter.getInstance().build("/unit/fragment/BatchInfoByLogFragment").withString("batchID", batchID).navigation()};

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset <= -toolbarLayout.getHeight() / 2) {
                    toolbarLayout.setTitle(TextUtils.isEmpty(batchName) ? "批次详情" : (batchName + ""));
                } else {
                    toolbarLayout.setTitle("");
                }
            }
        });

        //设置CollapsingToolbarLayout扩张时的标题颜色
        toolbarLayout.setExpandedTitleColor(Color.WHITE);
        //设置CollapsingToolbarLayout收缩时的标题颜色
        toolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        toolbarLayout.setCollapsedTitleGravity(Gravity.START);//设置收缩后标题的位置
        toolbarLayout.setExpandedTitleGravity(Gravity.TOP);////设置展开后标题的位置


        //设置 NestedScrollView 的内容是否拉伸填充整个视图，
        //这个设置是必须的，否者我们在里面设置的ViewPager会不可见
//        unitBatchInfoNested.setFillViewport(true);

        unitBatchInfoTab.addTab(unitBatchInfoTab.newTab().setText(strTitle[0]));//添加tab选项卡
        unitBatchInfoTab.addTab(unitBatchInfoTab.newTab().setText(strTitle[1]));
        unitBatchInfoTab.addTab(unitBatchInfoTab.newTab().setText(strTitle[2]));
//        unitBatchInfoTab.addTab(unitBatchInfoTab.newTab().setText(strTitle[3]));

        adapter = new BatchPagerAdapter(this.getSupportFragmentManager());
        unitBatchInfoViewPager.setAdapter(adapter);
//        unitBatchInfoTab.setupWithViewPager(unitBatchInfoViewPager);ViewPager和TabLayout建立关联（不用这个）
//        ViewPager和TabLayout建立关联
        unitBatchInfoViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener
                (unitBatchInfoTab));
        unitBatchInfoTab.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener
                (unitBatchInfoViewPager));

    }

    @Override
    public void initData() {
        Glide.with(this)
                .load(TextUtils.isEmpty(imgUrl) ? "" : (AgrConstant.IMAGE_URL_IMAGE + imgUrl))
                .apply(AgrConstant.iconOptions)
                .into(unitContentIcon);
        unitContentName.setText(TextUtils.isEmpty(batchName) ? "未命名" : batchName);
    }

    @Override
    protected void setStatusBar() {
        StatusBarUtil.setTranslucentForCoordinatorLayout(this, StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBatchInfoMessage(BatchInfoEvent event) {
        batchInfo = event.getBatchInfo();
        unitContentName.setText(event.getBatchInfo().getProductName() + "(" + EnumBatchStatus.getStatueDes(event.getBatchInfo().getStatus()) + ")");
        if (event.getBatchInfo().getStatus() == EnumBatchStatus.TYPE_Doing) {
            unitContentDate.setText("种植时间：" + new DateTime(ErayicNetDate.getLongDates(event.getBatchInfo().getStartTime())).toString("yyyy-MM-dd") + "");
        } else {
            unitContentDate.setText(new DateTime(ErayicNetDate.getLongDates(event.getBatchInfo().getStartTime())).toString("yyyy-MM-dd") + "……" +
                    new DateTime(ErayicNetDate.getLongDates(event.getBatchInfo().getEndTime())).toString("yyyy-MM-dd"));
        }

        unitContentArea.setText("面积：" + event.getBatchInfo().getQuantity() + "亩");
        unitContentResp.setText("负责人：" + event.getBatchInfo().getOpeName() + "");
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
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!isHistory)
            getMenuInflater().inflate(R.menu.unit_batch_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//返回
            ErayicStack.getInstance().finishCurrentActivity();
        } else if (item.getItemId() == R.id.action_unit_batch_goto) {
            if (batchInfo != null)
                ARouter.getInstance()
                        .build("/unit/activity/AddUpdateBatchActivity")
                        .withBoolean("isAdd", false)
                        .withString("unitID", unitID)
                        .withString("batchID", batchID)
                        .withString("batchName", batchName)
                        .withSerializable("batchInfo", batchInfo)
                        .navigation();
            else
                showToast("正在获取数据，请稍后");

//            new ErayicTextDialog.Builder(BatchInfoActivity.this)
//                    .setMessage("完成批次代表此批次的生产管理已经结束\n之后不能对该批次做任何操作，且不可逆\n确定完成批次吗？", null)
//                    .setTitle("重要提示")
//                    .setButton1("取消", new ErayicTextDialog.OnClickListener() {
//                        @Override
//                        public void onClick(Dialog dialog, int which) {
//                            dialog.dismiss();
//                        }
//                    })
//                    .setButton2("确定", new ErayicTextDialog.OnClickListener() {
//                        @Override
//                        public void onClick(Dialog dialog, int which) {
//                            dialog.dismiss();
//                            showToast("暂未开放");
//                        }
//                    }).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public class BatchPagerAdapter extends FragmentPagerAdapter {

        public BatchPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return strTitle[position];
        }

        @Override
        public int getCount() {
            return strTitle.length;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
