package com.erayic.agr.jobs.view.impl;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.base.BaseFragment;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.jobs.R;
import com.erayic.agr.jobs.R2;
import com.erayic.agr.jobs.view.IJobsListView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */
@Route(path = "/jobs/fragment/JobsListFragment", name = "作业列表")
public class JobsListFragment extends BaseFragment implements IJobsListView, SwipeRefreshLayout.OnRefreshListener {

    //    /* 标题栏 */
    @Autowired
    String titleName;
    @BindView(R2.id.fake_status_bar)
    View fakeStatusBar;
    @BindView(R2.id.toolbar_title_name)
    TextView toolbarTitleName;
    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.jobs_list_RecyclerView)
    RecyclerView jobsListRecyclerView;
    @BindView(R2.id.jobs_list_swipe)
    SwipeRefreshLayout jobsListSwipe;
    @BindView(R2.id.toolbar_title_img)
    ImageView toolbarTitleImg;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_jobs_list;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        toolbar.setTitle("");
        toolbarTitleName.setText(titleName);
        jobsListSwipe.setOnRefreshListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onRefresh() {

    }

    @OnClick(R2.id.toolbar_title_img)
    public void onViewClicked() {
        showPopupMenu(toolbarTitleImg);
    }

    private void showPopupMenu(View view) {
        // View当前PopupMenu显示的相对View的位置
        PopupMenu popupMenu = new PopupMenu(getActivity(), view);
        // menu布局
        popupMenu.getMenuInflater().inflate(R.menu.menu_jobs_list_view, popupMenu.getMenu());
        // menu的item点击事件
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_bar_jobs_work) {//工作安排
                    ARouter.getInstance().build("/jobs/activity/ArrangeJobActivity").navigation();
                } else if (item.getItemId() == R.id.action_bar_jobs_advance) {//作业预设
                    ARouter.getInstance().build("/jobs/activity/AdvanceWorkActivity").navigation();
                } else if (item.getItemId() == R.id.action_bar_jobs_history) {//历史查询

                }
                return false;
            }
        });
        // PopupMenu关闭事件
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
//                Toast.makeText(getApplicationContext(), "关闭PopupMenu", Toast.LENGTH_SHORT).show();
            }
        });

        popupMenu.show();
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
}
