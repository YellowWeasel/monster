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
 * 注解：启停
 */

public class UnitListItemByControlBySTViewHolder extends BaseViewHolder {

    @BindView(R2.id.unit_content_icon)
    public CircleImageView unitContentIcon;
    @BindView(R2.id.unit_content_name)
    public TextView unitContentName;
    @BindView(R2.id.unit_content_subName)
    public  TextView unitContentSubName;
    @BindView(R2.id.unit_content_start)
    public CircleImageView unitContentStart;
    @BindView(R2.id.unit_content_stop)
    public CircleImageView unitContentStop;

    public UnitListItemByControlBySTViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
