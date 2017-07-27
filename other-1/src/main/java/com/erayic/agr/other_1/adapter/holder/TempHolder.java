package com.erayic.agr.other_1.adapter.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.other_1.R;

/**
 * 作者:LiJiang
 * 邮箱:1755036940@qq.com
 * 作用:监控温度
 */

public class TempHolder extends BaseViewHolder {
    public ImageView mImageViewicon;
    public TextView mTextViewfacility;
    public TextView mTextViewTemp;

    public TempHolder(View view) {
        super(view);
        mImageViewicon= (ImageView) view.findViewById(R.id.name_icon_state_id);
        mTextViewfacility= (TextView) view.findViewById(R.id.name_status_id);
        mTextViewTemp= (TextView) view.findViewById(R.id.name_temp_id);
    }
}
