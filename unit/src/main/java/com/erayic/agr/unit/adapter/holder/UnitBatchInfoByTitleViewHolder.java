package com.erayic.agr.unit.adapter.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.unit.R;
import com.erayic.agr.unit.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class UnitBatchInfoByTitleViewHolder extends BaseViewHolder{

    @BindView(R2.id.unit_item_list_icon)
    public ImageView unitItemListIcon;
    @BindView(R2.id.unit_item_list_name)
    public TextView unitItemListName;

    public UnitBatchInfoByTitleViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
