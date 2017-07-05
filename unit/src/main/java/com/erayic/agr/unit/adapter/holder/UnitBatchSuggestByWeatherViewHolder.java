package com.erayic.agr.unit.adapter.holder;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.unit.R;
import com.erayic.agr.unit.R2;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.LineChart;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class UnitBatchSuggestByWeatherViewHolder extends BaseViewHolder {

    @BindView(R2.id.line_chart_weather)
    public CombinedChart lineChartWeather;

    public UnitBatchSuggestByWeatherViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
