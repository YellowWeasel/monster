package com.erayic.agr.view.impl;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.erayic.agr.R;
import com.erayic.agr.common.base.BaseFragment;
import com.erayic.agr.view.IMineView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

@Route(path = "/main/fragment/MineFragment", name = "我的")
public class MineFragment extends BaseFragment implements IMineView {

    private View view;

    //    /* 标题栏 */
    @Autowired
    String titleName;


    @Override
    public void showToast(String msg) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }

}
