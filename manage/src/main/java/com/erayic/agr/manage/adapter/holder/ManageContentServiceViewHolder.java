package com.erayic.agr.manage.adapter.holder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.manage.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ManageContentServiceViewHolder extends BaseViewHolder {

    @BindView(R2.id.manage_content_service_icon)
    public ImageView manageContentServiceIcon;
    @BindView(R2.id.manage_content_service_name)
    public TextView manageContentServiceName;
    @BindView(R2.id.manage_content_service_sub)
    public TextView manageContentServiceSub;
    @BindView(R2.id.manage_content_service_buy)
    public Button manageContentServiceBuy;

    public ManageContentServiceViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
