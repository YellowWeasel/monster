package com.erayic.agr.jobs.adapter.holder;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.jobs.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class JobsDividerViewHolder extends BaseViewHolder {

    @BindView(R2.id.adapter_divider_item_name)
    public TextView adapterDividerName;

    public JobsDividerViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
