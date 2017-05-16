package com.erayic.agr.unit.adapter.holder;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.unit.R;
import com.erayic.agr.unit.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：控制设备
 */

public class UnitListItemByControlByEquViewHolder extends BaseViewHolder {


    @BindView(R2.id.unit_control_equ_name)
    public TextView unitControlEquName;
    @BindView(R2.id.unit_control_equ_status)
    public TextView unitControlEquStatus;
    @BindView(R2.id.unit_control_equ_mode)
    public TextView unitControlEquMode;
    @BindView(R2.id.unit_control_equ_tem)
    public TextView unitControlEquTem;


    public UnitListItemByControlByEquViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
