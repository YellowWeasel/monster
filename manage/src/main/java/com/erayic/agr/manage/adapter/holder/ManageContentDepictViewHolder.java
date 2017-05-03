package com.erayic.agr.manage.adapter.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.manage.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ManageContentDepictViewHolder extends BaseViewHolder {

    @BindView(R2.id.manage_content_depict_icon)
    public ImageView manageContentDepictIcon;
    @BindView(R2.id.manage_content_depict_name)
    public TextView manageContentDepictName;
    @BindView(R2.id.manage_content_depict_sub)
    public TextView manageContentDepictSub;
    @BindView(R2.id.manage_content_depict_goto)
    public ImageView manageContentDepictGoto;

    public ManageContentDepictViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
