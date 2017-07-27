package com.erayic.agr.other_1.adapter.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.other_1.R;

/**
 * 作者:LiJiang
 * 邮箱:1755036940@qq.com
 * 作用:多种监控模式
 */

public class PatternHolder extends BaseViewHolder {
    public ImageView mImageViewicon;
    public TextView mTextViewname;
    public TextView mTextViewfuname;
    public ImageView mImageViewfuicon;
    public PatternHolder(View view) {
        super(view);
        mImageViewicon= (ImageView) view.findViewById(R.id.name_icon_itm_id);
        mTextViewname= (TextView) view.findViewById(R.id.name_yunxing_id);
        mTextViewfuname= (TextView) view.findViewById(R.id.name_zhidong_id);
        mImageViewfuicon= (ImageView) view.findViewById(R.id.name_icon_itmn_id);
    }
}
