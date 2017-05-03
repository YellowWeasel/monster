package com.erayic.agr.manage.view.impl;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.erayic.agr.common.base.BaseFragment;
import com.erayic.agr.manage.R;
import com.erayic.agr.manage.view.IFertilizerListView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */
@Route(path = "/manage/fragment/FertilizerListFragment", name = "化肥列表")
public class FertilizerListFragment extends BaseFragment implements IFertilizerListView {


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_manage_fertilizer;
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
