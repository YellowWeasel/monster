package com.erayic.agr.manage.adapter.holder;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.manage.R;
import com.erayic.agr.manage.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ManageShareOtherViewHolder extends BaseViewHolder {

    @BindView(R2.id.manage_content_weixin)
    public TextView manageContentWeixin;
    @BindView(R2.id.manage_content_friends)
    public TextView manageContentFriends;
    @BindView(R2.id.manage_content_qq)
    public TextView manageContentQq;

    public ManageShareOtherViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
