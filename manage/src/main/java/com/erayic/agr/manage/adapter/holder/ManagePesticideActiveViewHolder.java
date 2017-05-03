package com.erayic.agr.manage.adapter.holder;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class ManagePesticideActiveViewHolder extends BaseViewHolder {

    @BindView(R2.id.manage_content_icon_1)
    public ImageView manageContentIcon1;
    @BindView(R2.id.manage_content_name_1)
    public TextView manageContentName1;
    @BindView(R2.id.manage_content_subName_1)
    public TextView manageContentSubName1;
    @BindView(R2.id.manage_content_icon_2)
    public ImageView manageContentIcon2;
    @BindView(R2.id.manage_content_name_2)
    public TextView manageContentName2;
    @BindView(R2.id.manage_content_subName_2)
    public TextView manageContentSubName2;

    public ManagePesticideActiveViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
