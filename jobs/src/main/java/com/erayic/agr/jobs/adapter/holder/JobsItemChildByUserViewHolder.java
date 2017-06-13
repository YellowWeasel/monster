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

public class JobsItemChildByUserViewHolder extends BaseViewHolder {

    @BindView(R2.id.jobs_content_name)
    public TextView jobsContentName;
    @BindView(R2.id.imageView)
    public ImageView imageView;
    @BindView(R2.id.jobs_content_sub)
    public TextView jobsContentSub;
    @BindView(R2.id.jobs_content_goto)
    public ImageView jobsContentGoto;
    @BindView(R2.id.jobs_content_status)
    public TextView jobsContentStatus;
    @BindView(R2.id.jobs_content_unit)
    public TextView jobsContentUnit;

    public JobsItemChildByUserViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
