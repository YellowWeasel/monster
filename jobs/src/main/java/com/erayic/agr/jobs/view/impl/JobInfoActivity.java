package com.erayic.agr.jobs.view.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.CustomLinearLayoutManager;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.event.MainRefreshMessage;
import com.erayic.agr.common.net.back.CommonMapArrayBean;
import com.erayic.agr.common.net.back.enums.EnumRequestType;
import com.erayic.agr.common.net.back.work.CommonJobInfoBean;
import com.erayic.agr.common.util.DividerItemDecoration;
import com.erayic.agr.common.util.ErayicStack;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.LoadingDialog;
import com.erayic.agr.common.view.codbking.DatePickDialog;
import com.erayic.agr.common.view.codbking.OnSureLisener;
import com.erayic.agr.common.view.codbking.bean.DateType;
import com.erayic.agr.jobs.R;
import com.erayic.agr.jobs.R2;
import com.erayic.agr.jobs.adapter.JobInfoItemAdapter;
import com.erayic.agr.jobs.adapter.entity.JobInfoEntity;
import com.erayic.agr.jobs.presenter.IJobInfoPresenter;
import com.erayic.agr.jobs.presenter.impl.JobInfoPresenterImpl;
import com.erayic.agr.jobs.view.IJobInfoView;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */
@Route(path = "/jobs/activity/JobInfoActivity", name = "工作安排（增加、查询）")
public class JobInfoActivity extends BaseActivity implements IJobInfoView {

    private final static int ACTIVITY_REQUEST = 9000;

    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.job_info_list_RecyclerView)
    RecyclerView jobInfoListRecyclerView;

    private LoadingDialog dialog;

    @Autowired
    String strTitle;//标题
    @Autowired
    boolean isAdd;//是否增加

    private JobInfoItemAdapter adapter;
    private IJobInfoPresenter presenter;
    private CommonJobInfoBean infoBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs_job_info);
    }

    @Override
    public void initView() {
        toolbar.setTitle(TextUtils.isEmpty(strTitle) ? "" : strTitle);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        adapter = new JobInfoItemAdapter(JobInfoActivity.this, null);
        adapter.setOnJobItemClickListener(new JobInfoItemAdapter.OnJobItemClickListener() {
            @Override
            public void onClick(View view, JobInfoEntity entity, final int position) {
                switch (entity.getItemType()) {
                    case JobInfoEntity.TYPE_JOB_WORK://选择工作
                        ARouter.getInstance().build("/manage/activity/SelectActivity")
                                .withBoolean("isRadio", true)
                                .withInt("selectType", EnumRequestType.TYPE_RETURN_WORK)
                                .navigation(JobInfoActivity.this, ACTIVITY_REQUEST);
                        break;
                    case JobInfoEntity.TYPE_JOB_UNIT_ADD://增加工作范围
                        ARouter.getInstance().build("/manage/activity/SelectActivity")
                                .withBoolean("isRadio", false)
                                .withInt("selectType", EnumRequestType.TYPE_RETURN_UNIT)
                                .navigation(JobInfoActivity.this, ACTIVITY_REQUEST);
                        break;
                    case JobInfoEntity.TYPE_JOB_DATE://建议时间
                    {
                        DatePickDialog dialog = new DatePickDialog(JobInfoActivity.this);
                        //设置上下年分限制
                        dialog.setYearLimt(5);
                        //设置标题
                        dialog.setTitle("选择时间");
                        //设置类型
                        dialog.setType(DateType.TYPE_YMDHM);
                        //设置消息体的显示格式，日期格式
                        dialog.setMessageFormat("yyyy-MM-dd HH:mm");
                        //设置选择回调
                        dialog.setOnChangeLisener(null);
                        //设置点击确定按钮回调
                        dialog.setOnSureLisener(new OnSureLisener() {
                            @Override
                            public void onSure(Date date) {
                                String ctime = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
                                adapter.getData().get(position).setSubName(ctime);
                                adapter.getData().get(position).setID(ctime);
                                adapter.notifyItemChanged(position);
                            }
                        });
                        dialog.show();
                    }
                    break;
                    case JobInfoEntity.TYPE_JOB_NOTICE:
                        ARouter.getInstance().build("/manage/activity/SelectActivity")
                                .withBoolean("isRadio", false)
                                .withInt("selectType", EnumRequestType.TYPE_RETURN_NOTICE)
                                .navigation(JobInfoActivity.this, ACTIVITY_REQUEST);
                        break;
                    case JobInfoEntity.TYPE_JOB_NOTICE_DATE:
                        new AlertView("通知时间", null, "取消", null,
                                new String[]{"立即通知", "提前1小时", "提前2小时", "提前3小时"},
                                JobInfoActivity.this, AlertView.Style.ActionSheet, new OnItemClickListener() {
                            public void onItemClick(Object o, int section) {
                                switch (section) {
                                    case 0://立即
                                        adapter.getData().get(position).setSubName("立即通知");
                                        adapter.getData().get(position).setID(0);
                                        adapter.notifyItemChanged(position);
                                        break;
                                    case 1://提前1小时
                                        adapter.getData().get(position).setSubName("提前1小时");
                                        adapter.getData().get(position).setID(1);
                                        adapter.notifyItemChanged(position);
                                        break;
                                    case 2://提前2小时
                                        adapter.getData().get(position).setSubName("提前2小时");
                                        adapter.getData().get(position).setID(2);
                                        adapter.notifyItemChanged(position);
                                        break;
                                    case 3://提前3小时
                                        adapter.getData().get(position).setSubName("提前3小时");
                                        adapter.getData().get(position).setID(3);
                                        adapter.notifyItemChanged(position);
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }).show();
                        break;
                }
            }
        });
        //使用线性布局管理器
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(JobInfoActivity.this);
        manager.setScrollEnabled(true);//滑动监听
        jobInfoListRecyclerView.setLayoutManager(manager);
        jobInfoListRecyclerView.setAdapter(adapter);
        jobInfoListRecyclerView.addItemDecoration(new DividerItemDecoration(JobInfoActivity.this, DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    public void initData() {
        presenter = new JobInfoPresenterImpl(this);
        infoBean = new CommonJobInfoBean();
        if (isAdd) {
            initAddView();
        } else {

        }
    }

    private void initAddView() {
        List<JobInfoEntity> list = new ArrayList<>();
        //分割线
        JobInfoEntity entityDivider = new JobInfoEntity();
        entityDivider.setItemType(JobInfoEntity.TYPE_JOB_DIVIDER);
        list.add(entityDivider);

        //预设作业
        {
            JobInfoEntity entity = new JobInfoEntity();
            entity.setItemType(JobInfoEntity.TYPE_JOB_WORK);
            entity.setName("作业名称");
            list.add(entity);
        }

        //分割线
        list.add(entityDivider);

        //工作范围
        {
            JobInfoEntity entity = new JobInfoEntity();
            entity.setItemType(JobInfoEntity.TYPE_JOB_UNIT_ADD);
            entity.setName("工作范围");
            list.add(entity);
        }

        //建议时间
        {
            JobInfoEntity entity = new JobInfoEntity();
            entity.setItemType(JobInfoEntity.TYPE_JOB_DATE);
            entity.setName("建议时间");
            list.add(entity);
        }

        //通知方式
        {
            JobInfoEntity entity = new JobInfoEntity();
            entity.setItemType(JobInfoEntity.TYPE_JOB_NOTICE);
            entity.setName("通知方式");
            list.add(entity);
        }

        //通知时间
        {
            JobInfoEntity entity = new JobInfoEntity();
            entity.setItemType(JobInfoEntity.TYPE_JOB_NOTICE_DATE);
            entity.setName("通知时间");
            list.add(entity);
        }
        adapter.setShowNoticeDate(false);
        adapter.setNewData(list);
    }

    private void saveData() {
        infoBean = new CommonJobInfoBean();
        List<String> listUnitIDs = new ArrayList<>();
        for (JobInfoEntity entity : adapter.getData()) {
            switch (entity.getItemType()) {
                case JobInfoEntity.TYPE_JOB_WORK:
                    if (TextUtils.isEmpty(entity.getSubName())) {
                        showToast("请选择作业");
                        return;
                    }
                    infoBean.setJobID(entity.getID().toString());
                    break;
                case JobInfoEntity.TYPE_JOB_UNIT:
                    listUnitIDs.add(entity.getID().toString());
                    break;
                case JobInfoEntity.TYPE_JOB_DATE:
                    if (TextUtils.isEmpty(entity.getSubName())) {
                        showToast("请选择执行时间");
                        return;
                    }
                    infoBean.setDemandTime(entity.getID().toString());
                    break;
                case JobInfoEntity.TYPE_JOB_NOTICE:
                    if (TextUtils.isEmpty(entity.getSubName())) {
                        showToast("请选择通知方式");
                        return;
                    }
                    if (entity.getID() instanceof Integer) {
                        infoBean.setTips(Integer.valueOf(entity.getID().toString()));
                    }
                    break;
                case JobInfoEntity.TYPE_JOB_NOTICE_DATE:
                    if (infoBean.getTips() != 0) {
                        if (TextUtils.isEmpty(entity.getSubName())) {
                            showToast("请选择提醒时间");
                            return;
                        }
                        if (entity.getID() instanceof Integer) {
                            infoBean.setNoticeTime(Integer.valueOf(entity.getID().toString()));
                        }
                    } else {
                        infoBean.setNoticeTime(0);
                    }
                    break;
            }
        }
        if (listUnitIDs.size() == 0) {
            showToast("请至少添加一个管理单元");
            return;
        }
        presenter.saveSchedule(infoBean, listUnitIDs);
    }

    @Override
    public void showLoading() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialog == null)
                    dialog = new LoadingDialog(JobInfoActivity.this);
                dialog.show();
            }
        });
    }

    @Override
    public void dismissLoading() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialog == null)
                    dialog = new LoadingDialog(JobInfoActivity.this);
                dialog.dismiss();
            }
        });
    }

    @Override
    public void updateOrDeleteSure() {
        MainRefreshMessage message = new MainRefreshMessage();
        message.setMsgType(MainRefreshMessage.MAIN_MASTER_JOB);
        message.setData(infoBean.getDemandTime());
        EventBus.getDefault().post(message);//通知刷新
        ErayicStack.getInstance().finishCurrentActivity();
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
        getMenuInflater().inflate(R.menu.menu_jobs_job_info, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem save = menu.findItem(R.id.action_bar_jobs_job_save);
        MenuItem delete = menu.findItem(R.id.action_bar_jobs_job_delete);
        if (isAdd) {
            save.setVisible(true);
            delete.setVisible(false);
        } else {
            save.setVisible(false);
            delete.setVisible(true);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//返回
            ErayicStack.getInstance().finishCurrentActivity();
        } else if (item.getItemId() == R.id.action_bar_jobs_job_save) {//保存
            saveData();
        } else if (item.getItemId() == R.id.action_bar_jobs_job_delete) {

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTIVITY_REQUEST) {
            switch (resultCode) {
                case EnumRequestType.TYPE_RETURN_WORK: {
                    HashMap<String, Object> map = (HashMap<String, Object>) data.getSerializableExtra("map");
                    //工作
                    {
                        for (Map.Entry<String, Object> entry : map.entrySet()) {
                            for (int i = 0; i < adapter.getData().size(); i++) {
                                JobInfoEntity entity = adapter.getData().get(i);
                                if (entity.getItemType() == JobInfoEntity.TYPE_JOB_WORK) {
                                    entity.setSubName(entry.getValue().toString());
                                    entity.setID(entry.getKey());
                                }
                                adapter.notifyItemChanged(i);
                            }
                        }
                    }
                }
                break;
                case EnumRequestType.TYPE_RETURN_UNIT: {
                    HashMap<String, Object> map = (HashMap<String, Object>) data.getSerializableExtra("map");
                    for (Map.Entry<String, Object> entry : map.entrySet()) {

                        for (int i = 0; i < adapter.getData().size(); i++) {
                            JobInfoEntity entity = adapter.getData().get(i);
                            if (entity.getItemType() == JobInfoEntity.TYPE_JOB_UNIT_ADD) {
                                JobInfoEntity entityADD = new JobInfoEntity();
                                entityADD.setItemType(JobInfoEntity.TYPE_JOB_UNIT);
                                entityADD.setName(entry.getValue().toString());
//                                entityADD.setSubName();
                                entityADD.setID(entry.getKey());
                                adapter.addData(i + 1, entityADD);
                            }
//                            adapter.notifyItemChanged(i + 1);
                        }
                    }
                }
                break;
                case EnumRequestType.TYPE_RETURN_NOTICE: {
                    HashMap<String, Object> map = (HashMap<String, Object>) data.getSerializableExtra("map");
                    //通知
                    {
                        for (Map.Entry<String, Object> entry : map.entrySet()) {

                            for (int i = 0; i < adapter.getData().size(); i++) {
                                JobInfoEntity entity = adapter.getData().get(i);
                                if (entity.getItemType() == JobInfoEntity.TYPE_JOB_NOTICE) {
                                    entity.setSubName(entry.getValue().toString());
                                    entity.setID(Integer.valueOf(entry.getKey().toString()).intValue());
                                    if (Integer.valueOf(entry.getKey().toString()).intValue() == 0)
                                        adapter.setShowNoticeDate(false);
                                    else
                                        adapter.setShowNoticeDate(true);
                                }
                                adapter.notifyItemChanged(i);
                            }
                        }
                    }
                    break;
                }
            }
        }
    }
}
