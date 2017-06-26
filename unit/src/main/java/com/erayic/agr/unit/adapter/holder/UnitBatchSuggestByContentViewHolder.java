package com.erayic.agr.unit.adapter.holder;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.unit.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class UnitBatchSuggestByContentViewHolder extends BaseViewHolder{

    @BindView(R2.id.unit_content_fraction)
    public TextView unitContentFraction;
    @BindView(R2.id.unit_content_name)
    public TextView unitContentName;
    @BindView(R2.id.unit_content_sub_name)
    public TextView unitContentSubName;
    @BindView(R2.id.unit_content_update)
    public TextView unitContentUpdate;

    public UnitBatchSuggestByContentViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
