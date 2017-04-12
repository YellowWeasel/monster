package com.erayic.agr.service.view.impl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.base.BaseFragment;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.config.PreferenceUtils;
import com.erayic.agr.common.util.ErayicLog;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.service.R;
import com.erayic.agr.service.R2;
import com.erayic.agr.service.presenter.IMineServicePresenter;
import com.erayic.agr.service.presenter.impl.MimeServicePresenterImpl;
import com.erayic.agr.service.view.IMineServiceView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */
@Route(path = "/service/fragment/MineServiceFragment", name = "我的服务")
public class MineServiceFragment extends BaseFragment implements IMineServiceView {

    //    /* 标题栏 */
    @Autowired
    String titleName;

    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.mine_service_RecyclerView)
    RecyclerView mineServiceRecyclerView;
    @BindView(R2.id.fake_status_bar)
    View fakeStatusBar;
    @BindView(R2.id.toolbar_title_name)
    TextView toolbarTitleName;
    @BindView(R2.id.toolbar_title_img)
    ImageView toolbarTitleImg;

    private IMineServicePresenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine_service;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("");
        toolbarTitleName.setText(titleName);
        if (PreferenceUtils.getParam("UserRole", 0) == 1) {
            toolbarTitleImg.setVisibility(View.VISIBLE);
        } else {
            toolbarTitleImg.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initData() {
        presenter = new MimeServicePresenterImpl(this);
        presenter.getAllServiceByUser();
    }

    @OnClick(R2.id.toolbar_title_img)
    public void onToolbarTitleImgClick() {
        ARouter.getInstance().build("/service/activity/ServiceMarketActivity").navigation();
    }


    @Override
    public void showToast(final String msg) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ErayicToast.TextToast(getActivity(), msg);
            }
        });
    }

}
