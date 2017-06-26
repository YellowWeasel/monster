package com.erayic.agr.unit.adapter.holder;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.unit.R2;
import com.github.mikephil.charting.charts.HorizontalBarChart;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class UnitBatchInfoByProduceViewHolder extends BaseViewHolder {

    @BindView(R2.id.batch_info_bar_chart)
    public HorizontalBarChart batchInfoBarChart;

    public UnitBatchInfoByProduceViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
