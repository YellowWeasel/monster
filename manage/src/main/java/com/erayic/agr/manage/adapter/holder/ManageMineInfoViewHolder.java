package com.erayic.agr.manage.adapter.holder;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.common.view.CircleImageView;
import com.erayic.agr.manage.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ManageMineInfoViewHolder extends BaseViewHolder {

    @BindView(R2.id.manage_mine_info_icon)
    public CircleImageView manageMineInfoIcon;
    @BindView(R2.id.manage_mine_info_name)
    public TextView manageMineInfoName;
    @BindView(R2.id.manage_mine_info_role)
    public TextView manageMineInfoRole;

    public ManageMineInfoViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
