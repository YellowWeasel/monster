package com.erayic.agr.jobs.view.impl;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.text.method.KeyListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.CustomLinearLayoutManager;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.event.MainRefreshMessage;
import com.erayic.agr.common.event.ManageRefreshMessage;
import com.erayic.agr.common.net.back.enums.EnumOperationType;
import com.erayic.agr.common.net.back.enums.EnumRequestType;
import com.erayic.agr.common.net.back.work.CommonWorkInfoBean;
import com.erayic.agr.common.util.DividerItemDecoration;
import com.erayic.agr.common.util.ErayicStack;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.ErayicEditDialog;
import com.erayic.agr.common.view.LoadingDialog;
import com.erayic.agr.jobs.R;
import com.erayic.agr.jobs.R2;
import com.erayic.agr.jobs.adapter.WorkInfoItemAdapter;
import com.erayic.agr.jobs.adapter.entity.WorkInfoEntity;
import com.erayic.agr.jobs.presenter.IWorkInfoPresenter;
import com.erayic.agr.jobs.presenter.impl.WorkInfoPresenterImpl;
import com.erayic.agr.jobs.view.IWorkInfoView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

@Route(path = "/jobs/activity/WorkInfoActivity", name = "预设作业详情（增加、查询）")
public class WorkInfoActivity extends BaseActivity implements IWorkInfoView {

    private final static int ACTIVITY_REQUEST = 9000;

    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.work_info_list_RecyclerView)
    RecyclerView workInfoListRecyclerView;

    @Autowired
    String strTitle;//标题
    @Autowired
    boolean isAdd;//是否增加
    @Autowired
    String JobID;//工作ID

    boolean isUpdater;//是否在修改状态

    private LoadingDialog dialog;

    private CommonWorkInfoBean workInfoBean;//实体类
    private WorkInfoItemAdapter adapter;
    private IWorkInfoPresenter presenter;
    private KeyListener keyListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs_work_info);
    }

    @Override
    public void initView() {
        toolbar.setTitle(TextUtils.isEmpty(strTitle) ? "" : strTitle);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

//        workInfoListSwipe.setOnRefreshListener(this);
        //使用线性布局管理器
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(WorkInfoActivity.this);
        manager.setScrollEnabled(true);//滑动监听
        workInfoListRecyclerView.setLayoutManager(manager);
        adapter = new WorkInfoItemAdapter(WorkInfoActivity.this, null);
        adapter.setOnWorkClickListener(new WorkInfoItemAdapter.OnWorkClickListener() {
            @Override
            public void onAddWork(View view, int position) {
                if (isAdd)
                    new AlertView("工作类型", null, "取消", null,
                            new String[]{"施肥", "打药", "采摘", "其他"},
                            WorkInfoActivity.this, AlertView.Style.ActionSheet, new OnItemClickListener() {
                        public void onItemClick(Object o, int position) {
                            switch (position) {
                                case 0://施肥
                                    ARouter.getInstance().build("/manage/activity/SelectActivity")
                                            .withBoolean("isRadio", true)
                                            .withInt("selectType", EnumRequestType.TYPE_RETURN_FERTILIZER)
                                            .navigation(WorkInfoActivity.this, ACTIVITY_REQUEST);
                                    break;
                                case 1://打药
                                    ARouter.getInstance().build("/manage/activity/SelectActivity")
                                            .withBoolean("isRadio", true)
                                            .withInt("selectType", EnumRequestType.TYPE_RETURN_PESTICIDE)
                                            .navigation(WorkInfoActivity.this, ACTIVITY_REQUEST);
                                    break;
                                case 2://采摘
                                {
                                    WorkInfoEntity infoEntity = new WorkInfoEntity();
                                    infoEntity.setItemType(WorkInfoEntity.TYPE_WORK_CONTENT_PICK);
                                    infoEntity.setName("采摘");
                                    infoEntity.setSubName("");
                                    adapter.addData(4, infoEntity);
                                }
                                break;
                                case 3://其他
                                    new ErayicEditDialog.Builder(WorkInfoActivity.this)
                                            .setMessage("", null)
                                            .setTitle("设置作业内容")
                                            .setButton1("取消", new ErayicEditDialog.OnClickListener() {
                                                @Override
                                                public void onClick(Dialog dialog, int which, CharSequence s) {
                                                    dialog.dismiss();
                                                }
                                            })
                                            .setButton2("确定", new ErayicEditDialog.OnClickListener() {
                                                @Override
                                                public void onClick(Dialog dialog, int which, CharSequence s) {
                                                    if (!TextUtils.isEmpty(s)) {
                                                        WorkInfoEntity infoEntity = new WorkInfoEntity();
                                                        infoEntity.setItemType(WorkInfoEntity.TYPE_WORK_CONTENT_OTHER);
                                                        infoEntity.setName(s.toString());
                                                        infoEntity.setSubProp("");
                                                        adapter.addData(4, infoEntity);
                                                    } else {
                                                        showToast("名称不能为空");
                                                    }
                                                    dialog.dismiss();
                                                }
                                            }).show();
                                    break;
                                default:
                                    break;
                            }
                        }
                    }).show();

            }

            @Override
            public void onDeleteWork(View view, int position) {
                adapter.getData().remove(position);
                adapter.notifyDataSetChanged();
            }
        });
        workInfoListRecyclerView.setAdapter(adapter);
        workInfoListRecyclerView.addItemDecoration(new DividerItemDecoration(WorkInfoActivity.this, DividerItemDecoration.VERTICAL_LIST));

        keyListener = new EditText(WorkInfoActivity.this).getKeyListener();

    }

    @Override
    public void initData() {
        workInfoBean = new CommonWorkInfoBean();
        presenter = new WorkInfoPresenterImpl(this);
        if (isAdd) {
            initAddView();
            workInfoBean = new CommonWorkInfoBean();
        } else {
            presenter.getJobDetails(JobID);
        }
    }

    private void initAddView() {
        List<WorkInfoEntity> list = new ArrayList<>();

        //分割线
        WorkInfoEntity entity = new WorkInfoEntity();
        entity.setItemType(WorkInfoEntity.TYPE_WORK_DIVIDER);
        list.add(entity);

        //作业名称
        {
            WorkInfoEntity infoEntity = new WorkInfoEntity();
            infoEntity.setItemType(WorkInfoEntity.TYPE_WORK_NAME);
            infoEntity.setName("作业名称");
            list.add(infoEntity);
        }

        //工作内容Title
        {
            WorkInfoEntity infoEntity = new WorkInfoEntity();
            infoEntity.setItemType(WorkInfoEntity.TYPE_WORK_CONTENT_TITLE_ADD);
            infoEntity.setName("工作内容");
            infoEntity.setSubName("添加");
            list.add(infoEntity);
        }

        //分割线
        list.add(entity);

        //工作要求Title
        {
            WorkInfoEntity infoEntity = new WorkInfoEntity();
            infoEntity.setItemType(WorkInfoEntity.TYPE_WORK_CONTENT_TITLE_SHOW);
            infoEntity.setName("工作要求");
            infoEntity.setSubName("");
            list.add(infoEntity);
        }
        //工作要求
        {
            WorkInfoEntity infoEntity = new WorkInfoEntity();
            infoEntity.setItemType(WorkInfoEntity.TYPE_WORK_CONTENT_NORM);
            infoEntity.setName("");
            infoEntity.setSubName("");
            list.add(infoEntity);
        }
        adapter.setKeyListener(keyListener);
        adapter.setNewData(list);
    }

    private void saveData() {
        List<CommonWorkInfoBean.JobContent> list = new ArrayList<>();
        for (WorkInfoEntity entity : adapter.getData()) {
            switch (entity.getItemType()) {
                case WorkInfoEntity.TYPE_WORK_NAME:
                    if (TextUtils.isEmpty(entity.getSubName())) {
                        showToast("请输入作业名称");
                        return;
                    } else {
                        workInfoBean.setJobName(entity.getSubName());
                    }
                    break;
                case WorkInfoEntity.TYPE_WORK_CONTENT_FERTILIZE://施肥
                    if (TextUtils.isEmpty(entity.getSubProp())) {
                        showToast("施肥量");
                        return;
                    } else {
                        CommonWorkInfoBean.JobContent content = new CommonWorkInfoBean.JobContent();
                        content.setOpeType(EnumOperationType.TYPE_Fertilize);
                        content.setResID(entity.getID());
                        content.setResName(entity.getSubName());
                        content.setNorm(entity.getSubProp());
                        list.add(content);
                    }
                    break;
                case WorkInfoEntity.TYPE_WORK_CONTENT_PESTICIDE://打药
                    if (TextUtils.isEmpty(entity.getSubProp())) {
                        showToast("农药配比");
                        return;
                    } else {
                        CommonWorkInfoBean.JobContent content = new CommonWorkInfoBean.JobContent();
                        content.setOpeType(EnumOperationType.TYPE_Pesticide);
                        content.setResID(entity.getID());
                        content.setResName(entity.getSubName());
                        content.setNorm(entity.getSubProp());
                        list.add(content);
                    }
                    break;
                case WorkInfoEntity.TYPE_WORK_CONTENT_PICK://采摘
                {
                    CommonWorkInfoBean.JobContent content = new CommonWorkInfoBean.JobContent();
                    content.setOpeType(EnumOperationType.TYPE_Pick);
                    list.add(content);
                }
                break;
                case WorkInfoEntity.TYPE_WORK_CONTENT_OTHER://其他
                {
                    CommonWorkInfoBean.JobContent content = new CommonWorkInfoBean.JobContent();
                    content.setOpeType(EnumOperationType.TYPE_Other);
                    content.setResName(entity.getName());
                    list.add(content);
                }
                break;
                case WorkInfoEntity.TYPE_WORK_CONTENT_NORM://工作要求
                    workInfoBean.setDescript(entity.getSubName());
                    break;
            }
        }
        if (list.size() == 0) {
            showToast("请添加工作内容");
            return;
        } else {
            workInfoBean.setContent(list);
        }
        presenter.updateJob(workInfoBean);
    }

    @Override
    public void selectSure(CommonWorkInfoBean bean) {
        workInfoBean = bean;
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                List<WorkInfoEntity> list = new ArrayList<>();
                //分割线
                WorkInfoEntity entity = new WorkInfoEntity();
                entity.setItemType(WorkInfoEntity.TYPE_WORK_DIVIDER);
                list.add(entity);

                //作业名称
                {
                    WorkInfoEntity infoEntity = new WorkInfoEntity();
                    infoEntity.setItemType(WorkInfoEntity.TYPE_WORK_NAME);
                    infoEntity.setName("作业名称");
                    infoEntity.setSubName(workInfoBean.getJobName());
                    list.add(infoEntity);
                }

                //工作内容Title
                {
                    WorkInfoEntity infoEntity = new WorkInfoEntity();
                    infoEntity.setItemType(WorkInfoEntity.TYPE_WORK_CONTENT_TITLE_ADD);
                    infoEntity.setName("工作内容");
                    infoEntity.setSubName("");
                    list.add(infoEntity);
                }

                for (CommonWorkInfoBean.JobContent content : workInfoBean.getContent()) {
                    WorkInfoEntity infoEntity = new WorkInfoEntity();
                    switch (content.getOpeType()) {
                        case EnumOperationType.TYPE_Fertilize://施肥
                            infoEntity.setItemType(WorkInfoEntity.TYPE_WORK_CONTENT_FERTILIZE);
                            infoEntity.setName("施肥");
                            infoEntity.setSubName(content.getResName());
                            infoEntity.setSubProp(content.getNorm());
                            break;
                        case EnumOperationType.TYPE_Pesticide://打药
                            infoEntity.setItemType(WorkInfoEntity.TYPE_WORK_CONTENT_PESTICIDE);
                            infoEntity.setName("打药");
                            infoEntity.setSubName(content.getResName());
                            infoEntity.setSubProp(content.getNorm());
                            break;
                        case EnumOperationType.TYPE_Pick://采摘
                            infoEntity.setItemType(WorkInfoEntity.TYPE_WORK_CONTENT_PICK);
                            infoEntity.setName("采摘");
                            infoEntity.setSubName(content.getResName());
                            infoEntity.setSubProp(content.getNorm());
                            break;
                        case EnumOperationType.TYPE_Other://其他
                            infoEntity.setItemType(WorkInfoEntity.TYPE_WORK_CONTENT_OTHER);
                            infoEntity.setName(content.getResName());
//                            infoEntity.setSubName(content.getResName());
                            break;
                        default:
                            break;
                    }
                    list.add(infoEntity);
                }

                //工作要求Title
                {
                    WorkInfoEntity infoEntity = new WorkInfoEntity();
                    infoEntity.setItemType(WorkInfoEntity.TYPE_WORK_CONTENT_TITLE_SHOW);
                    infoEntity.setName("工作要求");
                    infoEntity.setSubName("");
                    list.add(infoEntity);
                }

                //工作要求
                {
                    WorkInfoEntity infoEntity = new WorkInfoEntity();
                    infoEntity.setItemType(WorkInfoEntity.TYPE_WORK_CONTENT_NORM);
                    infoEntity.setSubName(workInfoBean.getDescript());
                    list.add(infoEntity);
                }
                adapter.setKeyListener(null);
                adapter.setNewData(list);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_jobs_work_info, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem save = menu.findItem(R.id.action_bar_jobs_work_save);
        MenuItem modify = menu.findItem(R.id.action_bar_jobs_work_update);
        MenuItem delete = menu.findItem(R.id.action_bar_jobs_work_delete);
        if (isAdd) {
            save.setVisible(true);
            modify.setVisible(false);
            delete.setVisible(false);
        } else {
            save.setVisible(false);
            modify.setVisible(true);
            delete.setVisible(true);
            if (isUpdater) {
                adapter.setKeyListener(keyListener);
                adapter.notifyDataSetChanged();
                isUpdater = false;
                save.setVisible(true);
                modify.setVisible(true);
                modify.setTitle("取消编辑");
                delete.setVisible(false);
            } else {
                isUpdater = true;
                adapter.setKeyListener(null);
                if (workInfoBean != null)
                    selectSure(workInfoBean);
                save.setVisible(false);
                modify.setVisible(true);
                modify.setTitle("编辑");
                delete.setVisible(true);
            }
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//返回
            ErayicStack.getInstance().finishCurrentActivity();
        } else if (item.getItemId() == R.id.action_bar_jobs_work_save) {
            saveData();
        } else if (item.getItemId() == R.id.action_bar_jobs_work_delete) {
            presenter.deleteJob(JobID);
        } else if (item.getItemId() == R.id.action_bar_jobs_work_update) {
            supportInvalidateOptionsMenu();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTIVITY_REQUEST) {
            switch (resultCode) {
                case EnumRequestType.TYPE_RETURN_FERTILIZER: {
                    HashMap<String, Object> map = (HashMap<String, Object>) data.getSerializableExtra("map");
                    //化肥
                    {
                        for (Map.Entry<String, Object> entry : map.entrySet()) {
                            WorkInfoEntity infoEntity = new WorkInfoEntity();
                            infoEntity.setItemType(WorkInfoEntity.TYPE_WORK_CONTENT_FERTILIZE);
                            infoEntity.setName("施肥");
                            infoEntity.setSubName(entry.getValue().toString());
                            infoEntity.setID(entry.getKey());
                            adapter.addData(4, infoEntity);
                        }

                    }
                }
                break;
                case EnumRequestType.TYPE_RETURN_PESTICIDE: {
                    HashMap<String, Object> map = (HashMap<String, Object>) data.getSerializableExtra("map");
                    //农药
                    {
                        for (Map.Entry<String, Object> entry : map.entrySet()) {
                            WorkInfoEntity infoEntity = new WorkInfoEntity();
                            infoEntity.setItemType(WorkInfoEntity.TYPE_WORK_CONTENT_PESTICIDE);
                            infoEntity.setName("打药");
                            infoEntity.setSubName(entry.getValue().toString());
                            infoEntity.setID(entry.getKey());
                            adapter.addData(4, infoEntity);
                        }


                    }

                }
                break;
            }
        }
    }

    @Override
    public void showLoading() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialog == null)
                    dialog = new LoadingDialog(WorkInfoActivity.this);
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
                    dialog = new LoadingDialog(WorkInfoActivity.this);
                dialog.dismiss();
            }
        });
    }

    @Override
    public void updateOrDeleteSure() {
        ManageRefreshMessage message = new ManageRefreshMessage();
        message.setMsgType(ManageRefreshMessage.MANAGE_MASTER_WORK_LIST);
        EventBus.getDefault().post(message);
        ErayicStack.getInstance().finishCurrentActivity();
    }

}
