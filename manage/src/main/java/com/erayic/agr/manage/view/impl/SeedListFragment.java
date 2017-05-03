package com.erayic.agr.manage.view.impl;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.erayic.agr.common.base.BaseFragment;
import com.erayic.agr.manage.R;
import com.erayic.agr.manage.view.ISeedListView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

@Route(path = "/manage/fragment/SeedListFragment", name = "种子列表")
public class SeedListFragment extends BaseFragment implements ISeedListView {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_manage_seed;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void showToast(String msg) {

    }

}
