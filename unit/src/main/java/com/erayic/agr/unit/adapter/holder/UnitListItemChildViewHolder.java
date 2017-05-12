package com.erayic.agr.unit.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.unit.R2;
import com.jpeng.jptabbar.JPTabBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class UnitListItemChildViewHolder extends BaseViewHolder{

    @BindView(R2.id.unit_list_item_tab)
    public JPTabBar unitListItemTab;
    @BindView(R2.id.unit_list_item_recyclerView)
    public RecyclerView unitListItemRecyclerView;

    public UnitListItemChildViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);

    }


}
