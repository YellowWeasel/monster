package com.erayic.agr.other_1.adapter.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.other_1.R;

/**
 * 作者:LiJiang
 * 邮箱:1755036940@qq.com
 * 作用:远程监控
 */

public class MonitoringHolder extends BaseViewHolder{
    public ImageView mImageView;
    public TextView  mTextview;
    public MonitoringHolder(View view) {
        super(view);
        mImageView= (ImageView) view.findViewById(R.id.name_icon_id);
        mTextview= (TextView) view.findViewById(R.id.name_control_id);

    }
}
