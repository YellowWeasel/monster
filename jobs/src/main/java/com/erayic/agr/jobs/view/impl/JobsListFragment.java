package com.erayic.agr.jobs.view.impl;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.base.BaseFragment;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.config.PreferenceUtils;
import com.erayic.agr.common.event.MainRefreshMessage;
import com.erayic.agr.common.net.back.enums.EnumUserRole;
import com.erayic.agr.common.net.back.work.CommonJobsListManagerBean;
import com.erayic.agr.common.net.back.work.CommonJobsListUserBean;
import com.erayic.agr.common.util.ErayicLog;
import com.erayic.agr.common.util.ErayicNetDate;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.SectionedSpanSizeLookup;
import com.erayic.agr.common.view.calendar.OnCalendarClickListener;
import com.erayic.agr.common.view.calendar.schedule.ScheduleLayout;
import com.erayic.agr.common.view.calendar.schedule.ScheduleRecyclerView;
import com.erayic.agr.common.view.tooblbar.ErayicToolbar;
import com.erayic.agr.jobs.R;
import com.erayic.agr.jobs.R2;
import com.erayic.agr.jobs.adapter.JobsListItemAdapter;
import com.erayic.agr.jobs.presenter.IJobsListPresenter;
import com.erayic.agr.jobs.presenter.impl.JobsListPresenterImpl;
import com.erayic.agr.jobs.view.IJobsListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;

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
    @BindView(R2.id.toolbar)
    ErayicToolbar toolbar;

    ScheduleRecyclerView jobsListRecyclerView;
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
        EventBus.getDefault().register(this);
        setHasOptionsMenu(true);
        toolbar.setTitle(titleName);
        if (PreferenceUtils.getParam("UserRole", -1) == EnumUserRole.Role_Manager || PreferenceUtils.getParam("UserRole", -1) == EnumUserRole.Role_Admin) {
            toolbar.inflateMenu(R.menu.menu_jobs_list_add); //加载菜单
        }
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                //点击事件
                if (item.getItemId() == R.id.action_bar_jobs_list_add) {
                    ARouter.getInstance().build("/jobs/activity/JobInfoActivity").withString("strTitle", "增加工作安排").withBoolean("isAdd", true).navigation();
                }
                return true;
            }
        });

        slSchedule.setOnCalendarClickListener(new OnCalendarClickListener() {
            @Override
            public void onClickDate(int year, int month, int day) {
                DateTime dateTime = new DateTime(year, month + 1, day, 0, 0);
                toolbar.setTitle(dateTime.toString("yyyy-MM-dd"));
                requestDayWork(dateTime.toString("yyyy-MM-dd"));
            }

            @Override
            public void onPageChange(int year, int month, int day) {
                DateTime dateTime = new DateTime(year, month + 1, day, 0, 0);
                toolbar.setTitle(dateTime.toString("yyyy-MM-dd"));
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
        toolbar.setTitle(new DateTime().toString("yyyy-MM-dd"));
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

    @Subscribe
    public void onMessageEvent(MainRefreshMessage event) {
        if (event.getMsgType() == MainRefreshMessage.MAIN_MASTER_JOB) {
//            // 根据指定格式,将时间字符串转换成DateTime对象
//            DateTime dt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime("2012-12-26 03:27:39");
            if (event.getSubType() == -1) {
                requestDayWork(toolbar.getTitle().toString());
            } else {
                Period p = new Period(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm").parseDateTime(event.getData().toString()),
                        DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime(toolbar.getTitle().toString()));
                if (p.getDays() == 0) {
//                ErayicLog.i("event-refresh", "/jobs/fragment/JobsListFragment");
                    requestDayWork(toolbar.getTitle().toString());
                }
            }
        }
    }

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

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
