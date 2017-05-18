package com.erayic.agr.unit.adapter.holder;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.common.view.CircleImageView;
import com.erayic.agr.unit.R;
import com.erayic.agr.unit.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：正反转
 */

public class UnitListItemByControlByPNViewHolder extends BaseViewHolder {

    @BindView(R2.id.unit_content_icon)
    public CircleImageView unitContentIcon;
    @BindView(R2.id.unit_content_name)
    public TextView unitContentName;
    @BindView(R2.id.unit_content_subName)
    public TextView unitContentSubName;
    @BindView(R2.id.unit_content_open)
    public CircleImageView unitContentOpen;
    @BindView(R2.id.unit_content_stop)
    public CircleImageView unitContentStop;
    @BindView(R2.id.unit_content_close)
    public CircleImageView unitContentClose;

    public UnitListItemByControlByPNViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
