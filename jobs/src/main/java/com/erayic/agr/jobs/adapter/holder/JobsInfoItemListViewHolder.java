package com.erayic.agr.jobs.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.jobs.R;
import com.erayic.agr.jobs.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class JobsInfoItemListViewHolder extends BaseViewHolder {

    @BindView(R2.id.jobs_content_name)
    public TextView jobsContentName;
    @BindView(R2.id.jobs_content_list)
    public RecyclerView jobsContentList;

    public JobsInfoItemListViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
