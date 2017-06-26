package com.erayic.agr.unit.adapter.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.unit.R2;
import com.jaeger.ninegridimageview.NineGridImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class UnitBatchInfoByLogViewHolder extends BaseViewHolder {

    @BindView(R2.id.jobs_content_date)
    public TextView jobsContentDate;
    @BindView(R2.id.jobs_content_type)
    public TextView jobsContentType;
    @BindView(R2.id.imageView)
    public ImageView imageView;
    @BindView(R2.id.jobs_content_name)
    public TextView jobsContentName;
    @BindView(R2.id.jobs_content_img)
    public NineGridImageView jobsContentImg;
    @BindView(R2.id.jobs_content_goto)
    public ImageView jobsContentGoto;

    public UnitBatchInfoByLogViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
