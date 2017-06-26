package com.erayic.agr.jobs.adapter.holder;

import android.view.View;
import android.widget.ImageView;
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

public class JobsContentText1ViewHolder extends BaseViewHolder {

    @BindView(R2.id.jobs_content_icon)
    public ImageView jobsContentIcon;
    @BindView(R2.id.jobs_content_name)
    public TextView jobsContentName;
    @BindView(R2.id.jobs_content_sub)
    public TextView jobsContentSub;
    @BindView(R2.id.jobs_content_goto)
    public ImageView jobsContentGoto;

    public JobsContentText1ViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}