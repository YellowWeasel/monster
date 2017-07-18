package com.erayic.agr.service.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.common.view.SlideSwitch;
import com.erayic.agr.service.R;
import com.erayic.agr.service.R2;
import com.kyleduo.switchbutton.SwitchButton;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ServiceManageGroupViewHolder extends RecyclerView.ViewHolder {

    @BindView(R2.id.service_manage_item_icon)
    public ImageView serviceManageItemIcon;
    @BindView(R2.id.service_manage_item_name)
    public TextView serviceManageItemName;
    @BindView(R2.id.service_manage_item_switch)
    public SwitchButton serviceManageItemSwitch;
    @BindView(R2.id.service_manage_item_sub)
    public ImageView serviceManageItemSub;
    @BindView(R2.id.service_manage_item_layout)
    public TableRow serviceManageItemLayout;

    public ServiceManageGroupViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
