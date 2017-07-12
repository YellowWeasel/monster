package com.erayic.agr.jobs.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

    /**
     * 显示隐藏ITEM
     */
    public void setVisibility(boolean isVisible){
        RecyclerView.LayoutParams param = (RecyclerView.LayoutParams)itemView.getLayoutParams();
        if (isVisible){
            param.height = LinearLayout.LayoutParams.WRAP_CONTENT;
            param.width = LinearLayout.LayoutParams.MATCH_PARENT;
            itemView.setVisibility(View.VISIBLE);
        }else{
            itemView.setVisibility(View.GONE);
            param.height = 0;
            param.width = 0;
        }
        itemView.setLayoutParams(param);
    }
}
