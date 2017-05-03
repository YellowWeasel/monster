package com.erayic.agr.manage.adapter.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.common.view.CircleImageView;
import com.erayic.agr.manage.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ManageContentImageViewHolder extends BaseViewHolder {

    @BindView(R2.id.manage_content_icon)
    public ImageView manageContentIcon;
    @BindView(R2.id.manage_content_name)
    public TextView manageContentName;
    @BindView(R2.id.manage_content_head)
    public CircleImageView manageContentHead;

    public ManageContentImageViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
