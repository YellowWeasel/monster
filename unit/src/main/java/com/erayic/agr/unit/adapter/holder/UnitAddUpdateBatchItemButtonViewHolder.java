package com.erayic.agr.unit.adapter.holder;

import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.unit.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class UnitAddUpdateBatchItemButtonViewHolder extends BaseViewHolder {

    @BindView(R2.id.manage_content_button)
    public Button manageContentButton;

    public UnitAddUpdateBatchItemButtonViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
