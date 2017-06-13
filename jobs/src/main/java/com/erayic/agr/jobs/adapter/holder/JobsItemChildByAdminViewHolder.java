package com.erayic.agr.jobs.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.jobs.R;
import com.erayic.agr.jobs.R2;
import com.erayic.agr.jobs.adapter.JobsListGridImageAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class JobsItemChildByAdminViewHolder extends BaseViewHolder {

    @BindView(R2.id.jobs_content_name)
    public TextView jobsContentName;
    @BindView(R2.id.jobs_content_subName)
    public TextView jobsContentSubName;
    @BindView(R2.id.jobs_content_status)
    public TextView jobsContentStatus;
    @BindView(R2.id.jobs_content_img)
    public RecyclerView jobsContentImg;
    @BindView(R2.id.jobs_content_goto)
    public ImageView jobsContentGoto;

    public JobsItemChildByAdminViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }


}
