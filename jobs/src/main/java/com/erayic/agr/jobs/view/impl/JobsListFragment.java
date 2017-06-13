package com.erayic.agr.jobs.view.impl;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.base.BaseFragment;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.config.PreferenceUtils;
import com.erayic.agr.common.net.back.enums.EnumUserRole;
import com.erayic.agr.common.net.back.work.CommonJobsListManagerBean;
import com.erayic.agr.common.net.back.work.CommonJobsListUserBean;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.SectionedSpanSizeLookup;
import com.erayic.agr.common.view.calendar.OnCalendarClickListener;
import com.erayic.agr.common.view.calendar.schedule.ScheduleLayout;
import com.erayic.agr.common.view.calendar.schedule.ScheduleRecyclerView;
import com.erayic.agr.jobs.R;
import com.erayic.agr.jobs.R2;
import com.erayic.agr.jobs.adapter.JobsListItemAdapter;
import com.erayic.agr.jobs.presenter.IJobsListPresenter;
import com.erayic.agr.jobs.presenter.impl.JobsListPresenterImpl;
import com.erayic.agr.jobs.view.IJobsListView;

import org.joda.time.DateTime;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */
@Route(path = "/jobs/fragment/JobsListFragment", name = "作业列表")
public class JobsListFragment extends BaseFragment implements IJobsListView {

    //    /* 标题栏 */
    @Autowired
    String titleName;
    @BindView(R2.id.fake_status_bar)
    View fakeStatusBar;
    @BindView(R2.id.toolbar_title_name)
    TextView toolbarTitleName;
    @BindView(R2.id.toolbar)
    Toolbar toolbar;

    ScheduleRecyclerView jobsListRecyclerView;
    @BindView(R2.id.toolbar_title_img)
    TextView toolbarTitleImg;
    @BindView(R2.id.slSchedule)
    ScheduleLayout slSchedule;

    private IJobsListPresenter presenter;
    private JobsListItemAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_jobs_list;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        toolbar.setTitle("");
        toolbarTitleName.setText(titleName);
        slSchedule.setOnCalendarClickListener(new OnCalendarClickListener() {
            @Override
            public void onClickDate(int year, int month, int day) {
                DateTime dateTime = new DateTime(year, month + 1, day, 0, 0);
                toolbarTitleName.setText(dateTime.toString("yyyy-MM-dd"));
                requestDayWork(dateTime.toString("yyyy-MM-dd"));
            }

            @Override
            public void onPageChange(int year, int month, int day) {
                DateTime dateTime = new DateTime(year, month + 1, day, 0, 0);
                toolbarTitleName.setText(dateTime.toString("yyyy-MM-dd"));
                requestDayWork(dateTime.toString("yyyy-MM-dd"));
            }
        });
        jobsListRecyclerView = slSchedule.getSchedulerRecyclerView();
        slSchedule.getMonthCalendar().setTodayToView();//跳转到今天
        adapter = new JobsListItemAdapter(getActivity(), null, PreferenceUtils.getParam("UserRole", 0));
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 1);
        manager.setSpanSizeLookup(new SectionedSpanSizeLookup(adapter, manager));
        adapter.setOnItemScrollToPositionWithOffset(new JobsListItemAdapter.OnItemScrollToPositionWithOffset() {
            @Override
            public void scrollToPositionWithOffset(int position) {
                jobsListRecyclerView.smoothScrollToPosition(position);//指定头部显示
            }
        });
        adapter.setOnItemInfoClickListener(new JobsListItemAdapter.OnItemInfoClickListener() {
            @Override
            public void onClick(View view, String schID, String unitID, String JobName, boolean isFinish) {
                ARouter.getInstance().build("/jobs/activity/JobsInfoActivity")
                        .withString("schID", schID).withString("unitID", unitID).withString("strTitle", JobName).withBoolean("isFinish", isFinish).navigation();
            }
        });
        jobsListRecyclerView.setLayoutManager(manager);
        jobsListRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        toolbarTitleName.setText(new DateTime().toString("yyyy-MM-dd"));
        presenter = new JobsListPresenterImpl(this);
        requestDayWork(new DateTime().toString("yyyy-MM-dd"));
    }

    private void requestDayWork(String specifyDay) {
        switch (PreferenceUtils.getParam("UserRole", 0)) {
            case EnumUserRole.Role_Manager://管理员
            {
                presenter.getDayWorkJobByManager(specifyDay);
            }
            break;
            case EnumUserRole.Role_Usage://用户
            {
                presenter.getDayWorkJobByUser(specifyDay);
            }
            break;
        }
    }

    @OnClick(R2.id.toolbar_title_img)
    public void onViewClicked() {
        ARouter.getInstance().build("/jobs/activity/JobInfoActivity").withString("strTitle", "增加工作安排").withBoolean("isAdd", true).navigation();
    }


//    private void showPopupMenu(View view) {
//        // View当前PopupMenu显示的相对View的位置
//        PopupMenu popupMenu = new PopupMenu(getActivity(), view);
//        // menu布局
//        popupMenu.getMenuInflater().inflate(R.menu.menu_jobs_list_view, popupMenu.getMenu());
//        // menu的item点击事件
//        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                if (item.getItemId() == R.id.action_bar_jobs_work) {//工作安排
//                    ARouter.getInstance().build("/jobs/activity/JobInfoActivity").withString("strTitle", "增加工作安排").withBoolean("isAdd", true).navigation();
//                }
//                return false;
//            }
//        });
//        // PopupMenu关闭事件
//        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
//            @Override
//            public void onDismiss(PopupMenu menu) {
////                Toast.makeText(getApplicationContext(), "关闭PopupMenu", Toast.LENGTH_SHORT).show();
//            }
//        });
//        popupMenu.show();
//    }

    @Override
    public void showToast(final String msg) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ErayicToast.TextToast(getActivity().getApplicationContext(), msg);
            }
        });
    }

    @Override
    public void selectUserSure(final CommonJobsListUserBean bean) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.setUserList(bean.getJobs());
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void selectManagerSure(final CommonJobsListManagerBean bean) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.setManagerList(bean.getJobs());
                adapter.notifyDataSetChanged();
            }
        });
    }
}
