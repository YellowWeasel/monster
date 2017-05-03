package com.erayic.agr.manage.adapter.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.common.view.CircleImageView;
import com.erayic.agr.manage.R;
import com.erayic.agr.manage.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ManageContentText2ViewHolder extends BaseViewHolder {

    @BindView(R2.id.manage_content_icon)
    public CircleImageView manageContentIcon;
    @BindView(R2.id.manage_content_name)
    public TextView manageContentName;
    @BindView(R2.id.manage_content_subName)
    public TextView manageContentSubName;
    @BindView(R2.id.manage_content_goto)
    public ImageView manageContentGoto;

    public ManageContentText2ViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
