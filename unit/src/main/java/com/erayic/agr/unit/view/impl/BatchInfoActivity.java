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
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.WindowManager;
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
import com.erayic.agr.common.net.back.enums.EnumCategoryType;
import com.erayic.agr.common.util.ErayicNetDate;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.CircleImageView;
import com.erayic.agr.common.view.PagerSlidingTabStrip;
import com.erayic.agr.common.view.tooblbar.ErayicToolbar;
import com.erayic.agr.unit.R;
import com.erayic.agr.unit.R2;
import com.erayic.agr.unit.adapter.holder.UnitListItemByBatchInfoViewHolder;
import com.erayic.agr.unit.event.BatchInfoEvent;
import com.erayic.agr.unit.view.IBatchInfosView;
import com.jaeger.library.StatusBarUtil;
import com.jpeng.jptabbar.JPTabBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.joda.time.DateTime;

import butterknife.BindView;
import butterknife.ButterKnife;

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
                .load(imgUrl)
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
        unitContentName.setText(event.getProductName() + "(" + EnumBatchStatus.getStatueDes(event.getStatus()) + ")");
        unitContentDate.setText(new DateTime(ErayicNetDate.getLongDates(event.getStartTime())).toString("yyyy-MM-dd"));
        unitContentArea.setText("面积：" + event.getQuantity() + "亩");
        unitContentResp.setText("负责人：" + event.getOpeName() + "");

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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//返回
            finish();
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
