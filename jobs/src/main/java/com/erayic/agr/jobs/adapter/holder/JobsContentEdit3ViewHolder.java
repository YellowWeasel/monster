package com.erayic.agr.jobs.adapter.holder;

import android.view.View;
import android.widget.EditText;

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

public class JobsContentEdit3ViewHolder extends BaseViewHolder {

    @BindView(R2.id.jobs_content_content)
    public EditText jobsContentContent;

    public JobsContentEdit3ViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
