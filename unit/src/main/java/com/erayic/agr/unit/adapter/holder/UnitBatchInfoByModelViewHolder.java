package com.erayic.agr.unit.adapter.holder;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.unit.R2;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class UnitBatchInfoByModelViewHolder extends BaseViewHolder {

    @BindView(R2.id.batch_info_pie_chart)
    public PieChart batchInfoPieChart;

    public UnitBatchInfoByModelViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
