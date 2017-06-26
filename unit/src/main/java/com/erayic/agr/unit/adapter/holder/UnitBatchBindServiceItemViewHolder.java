package com.erayic.agr.unit.adapter.holder;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.unit.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class UnitBatchBindServiceItemViewHolder extends BaseViewHolder {

    @BindView(R2.id.unit_content_icon)
    public ImageView unitContentIcon;
    @BindView(R2.id.unit_content_name)
    public TextView unitContentName;
    @BindView(R2.id.unit_content_sub_name)
    public TextView unitContentSubName;
    @BindView(R2.id.unit_content_goto)
    public ImageView unitContentGoto;

    public UnitBatchBindServiceItemViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
