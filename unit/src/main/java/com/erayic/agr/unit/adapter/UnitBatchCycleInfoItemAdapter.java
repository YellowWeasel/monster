package com.erayic.agr.unit.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.common.net.back.CommonMapArrayBean;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchCycleBean;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchInfoBean;
import com.erayic.agr.common.util.ErayicNetDate;
import com.erayic.agr.unit.R;
import com.erayic.agr.unit.adapter.entity.UnitBatchCycleInfoEntity;
import com.erayic.agr.unit.adapter.holder.UnitBatchCycleByCycleViewHolder;
import com.erayic.agr.unit.adapter.holder.UnitBatchCycleByTemRainViewHolder;
import com.erayic.agr.unit.adapter.holder.UnitBatchCycleByTitleViewHolder;
import com.erayic.agr.unit.adapter.holder.UnitBatchInfoBySuggestViewHolder;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class UnitBatchCycleInfoItemAdapter extends BaseMultiItemQuickAdapter<UnitBatchCycleInfoEntity, BaseViewHolder> {

    private Context context;
    /**
     * 颜色初始化
     */
    List<Integer> places = Arrays.asList(
            Color.rgb(165, 165, 165),//过去
            Color.rgb(92, 184, 82),//当前
            Color.rgb(237, 125, 49));//未来


    public UnitBatchCycleInfoItemAdapter(Context context, List<UnitBatchCycleInfoEntity> data) {
        super(data);
        this.context = context;
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case UnitBatchCycleInfoEntity.TYPE_TITLE:
                return new UnitBatchCycleByTitleViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_unit_batch_cycle_title, parent, false));
            case UnitBatchCycleInfoEntity.TYPE_CYCLE:
                return new UnitBatchCycleByCycleViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_unit_batch_cycle_cycle, parent, false));
            case UnitBatchCycleInfoEntity.TYPE_ACCTEM:
                return new UnitBatchCycleByTemRainViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_unit_batch_cycle_ten_water, parent, false));
            case UnitBatchCycleInfoEntity.TYPE_ACCRAIN:
                return new UnitBatchCycleByTemRainViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_unit_batch_cycle_ten_water, parent, false));
        }
        return super.onCreateDefViewHolder(parent, viewType);
    }

    @Override
    protected void convert(BaseViewHolder helper, UnitBatchCycleInfoEntity item) {
        switch (helper.getItemViewType()) {
            case UnitBatchCycleInfoEntity.TYPE_TITLE:
                if (helper instanceof UnitBatchCycleByTitleViewHolder) {
                    ((UnitBatchCycleByTitleViewHolder) helper).unitContentName.setText(TextUtils.isEmpty(item.getName()) ? "" : item.getName());
                    ((UnitBatchCycleByTitleViewHolder) helper).unitContentSubName.setText(TextUtils.isEmpty(item.getSubName()) ? "" : item.getSubName());
                }
                break;
            case UnitBatchCycleInfoEntity.TYPE_CYCLE:
                if (helper instanceof UnitBatchCycleByCycleViewHolder) {
                    CommonUnitBatchInfoBean.CropCycle cycle = (CommonUnitBatchInfoBean.CropCycle) item.getData1();

                    ((UnitBatchCycleByCycleViewHolder) helper).unitContentFraction.setText("" + cycle.getSubCycle().getSubCycleNum() + "");
                    ((UnitBatchCycleByCycleViewHolder) helper).unitContentName.setText(cycle.getSubCycle().getSubCycleName());
                    DateTime dateTimeStart = new DateTime(ErayicNetDate.getLongDates(cycle.getStartDay()));
                    DateTime dateTimeEnd = new DateTime(ErayicNetDate.getLongDates(cycle.getEndDay()));
                    DateTime dateTimeThis = new DateTime();

                    if (dateTimeStart.getMillis() <= dateTimeThis.getMillis()) {
                        ((UnitBatchCycleByCycleViewHolder) helper).unitContentSubNameStart.setText("开始时间：" + dateTimeStart.toString("yyyy-MM-dd"));
                        ((UnitBatchCycleByCycleViewHolder) helper).unitContentSubNameStart.setTextColor(ContextCompat.getColor(context, R.color.app_base_text_title_2));
                    } else {
                        ((UnitBatchCycleByCycleViewHolder) helper).unitContentSubNameStart.setText("预计开始时间：" + dateTimeStart.toString("yyyy-MM-dd"));
                        ((UnitBatchCycleByCycleViewHolder) helper).unitContentSubNameStart.setTextColor(ContextCompat.getColor(context, R.color.app_base_text_price));
                    }

                    if (dateTimeEnd.getMillis() < dateTimeThis.getMillis()) {
                        ((UnitBatchCycleByCycleViewHolder) helper).unitContentSubNameEnd.setText("结束时间：" + dateTimeEnd.toString("yyyy-MM-dd"));
                        ((UnitBatchCycleByCycleViewHolder) helper).unitContentSubNameEnd.setTextColor(ContextCompat.getColor(context, R.color.app_base_text_title_2));
                    } else {
                        ((UnitBatchCycleByCycleViewHolder) helper).unitContentSubNameEnd.setText("预计结束时间：" + dateTimeEnd.toString("yyyy-MM-dd"));
                        ((UnitBatchCycleByCycleViewHolder) helper).unitContentSubNameEnd.setTextColor(ContextCompat.getColor(context, R.color.app_base_text_price));
                    }

                    if (dateTimeStart.getMillis() >= dateTimeThis.getMillis() || (dateTimeStart.getMillis() < dateTimeThis.getMillis() && dateTimeThis.getMillis() < dateTimeEnd.getMillis())) {
                        ((UnitBatchCycleByCycleViewHolder) helper).unitContentUpdate.setVisibility(View.GONE);
                        if (dateTimeStart.getMillis() < dateTimeThis.getMillis() && dateTimeThis.getMillis() < dateTimeEnd.getMillis())
                            ((UnitBatchCycleByCycleViewHolder) helper).unitContentFraction.setBackgroundColor(places.get(1));
                        else
                            ((UnitBatchCycleByCycleViewHolder) helper).unitContentFraction.setBackgroundColor(places.get(2));

                    } else {
                        ((UnitBatchCycleByCycleViewHolder) helper).unitContentUpdate.setVisibility(View.VISIBLE);
                        ((UnitBatchCycleByCycleViewHolder) helper).unitContentUpdate.setText("变更");
                        ((UnitBatchCycleByCycleViewHolder) helper).unitContentFraction.setBackgroundColor(places.get(0));
                    }

                }
                break;
            case UnitBatchCycleInfoEntity.TYPE_ACCTEM:
                if (helper instanceof UnitBatchCycleByTemRainViewHolder) {
                    final CommonUnitBatchCycleBean.TempInfo historyTemp = (CommonUnitBatchCycleBean.TempInfo) item.getData1();
                    final CommonUnitBatchCycleBean.TempInfo curAccTemp = (CommonUnitBatchCycleBean.TempInfo) item.getData2();

                    ((UnitBatchCycleByTemRainViewHolder) helper).batchCycleChart.setNoDataText("未检测到数据，刷新试试");
                    //描述相关设置
                    ((UnitBatchCycleByTemRainViewHolder) helper).batchCycleChart.getDescription().setEnabled(false);//是否显示
                    ((UnitBatchCycleByTemRainViewHolder) helper).batchCycleChart.getDescription().setText("生长周期图");//描述
                    ((UnitBatchCycleByTemRainViewHolder) helper).batchCycleChart.getDescription().setTextSize(14f);
                    //设置手势
                    ((UnitBatchCycleByTemRainViewHolder) helper).batchCycleChart.setTouchEnabled(false);//启用/禁用与图表的所有可能的触摸交互。
//                    ((UnitBatchCycleByTemRainViewHolder) helper).batchCycleChart.setScaleEnabled(false);//是否可缩放

                    //坐标轴
                    //X轴设置
                    XAxis xAxis = ((UnitBatchCycleByTemRainViewHolder) helper).batchCycleChart.getXAxis();
                    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                    xAxis.setAvoidFirstLastClipping(true);//绘制时会避免“剪掉”在x轴上的图表或屏幕边缘的第一个和最后一个坐标轴标签项。
                    xAxis.setValueFormatter(null);
                    xAxis.setDrawGridLines(false);//是否显示网格
                    xAxis.setDrawLabels(false);//是否显示轴值

                    //三个X坐标  6.23号
//                    xAxis.setValueFormatter(new IAxisValueFormatter() {
//                        @Override
//                        public String getFormattedValue(float value, AxisBase axis) {
//                            DateTime dateTime = new DateTime(ErayicNetDate.getLongDates(historyTemp.getTempSerial().get((int) value % historyTemp.getTempSerial().size()).getKey()));
//                            return dateTime.toString("MM-dd");
//                        }
//                    });
//                    xAxis.setSpaceMin(historyTemp.getTempSerial().size()-1);
//                    xAxis.setEnabled(false);

                    //Y轴设置
                    YAxis yAxis = ((UnitBatchCycleByTemRainViewHolder) helper).batchCycleChart.getAxisLeft();
                    yAxis.setLabelCount(10, false);
                    yAxis.setTextSize(12f);
                    yAxis.setAxisMaximum((float) (historyTemp.getTotalTemp() + 100));
                    yAxis.setAxisMinimum(0f);
                    yAxis.setDrawGridLines(false);//是否显示网格
                    yAxis.setDrawLabels(true);//是否显示轴值
                    YAxis yAxis1 = ((UnitBatchCycleByTemRainViewHolder) helper).batchCycleChart.getAxisRight();
                    yAxis1.setEnabled(false);

                    //数据设置
                    ArrayList<Entry> history = new ArrayList<Entry>();
                    ArrayList<Entry> curAcc = new ArrayList<Entry>();
                    for (int i = 0; i < historyTemp.getTempSerial().size(); i++) {
                        CommonMapArrayBean bean = historyTemp.getTempSerial().get(i);
                        if (i == 0)
                            history.add(i, new Entry(i, Float.valueOf(bean.getValue()).floatValue()));
                        else {
                            Entry entryLast = history.get(i - 1);
                            history.add(i, new Entry(i, entryLast.getY() + Float.valueOf(bean.getValue()).floatValue()));
                        }

                    }

                    LineDataSet d1 = new LineDataSet(history, "同期历史积温");
                    d1.setLineWidth(2.5f);
                    d1.setDrawCircles(false);
//                    d1.setCircleRadius(4.5f);
//                    d1.setHighLightColor(Color.rgb(244, 117, 117));
                    d1.setColor(places.get(0));
                    d1.setHighlightEnabled(false);
                    d1.setDrawValues(false);


                    for (int i = 0; i < curAccTemp.getTempSerial().size(); i++) {
                        CommonMapArrayBean bean = curAccTemp.getTempSerial().get(i);
                        if (i == 0)
                            curAcc.add(i, new Entry(i, Float.valueOf(bean.getValue()).floatValue()));
                        else {
                            Entry entryLast = curAcc.get(i - 1);
                            curAcc.add(i, new Entry(i, entryLast.getY() + Float.valueOf(bean.getValue()).floatValue()));
                        }

                    }

                    LineDataSet d2 = new LineDataSet(curAcc, "当前积温");
                    d2.setLineWidth(2.5f);
                    d2.setColor(places.get(1));
                    d2.setHighlightEnabled(false);
                    d2.setDrawCircles(false);
//                    d2.setCircleRadius(4.5f);
//                    d2.setHighLightColor(Color.rgb(244, 117, 117));
//                    d2.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);
//                    d2.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[0]);
                    d2.setDrawValues(false);

                    ArrayList<ILineDataSet> sets = new ArrayList<>();
                    if (history.size() > 0)
                        sets.add(d1);
                    if (curAcc.size() > 0)
                        sets.add(d2);

                    LineData cd = new LineData(sets);

                    ((UnitBatchCycleByTemRainViewHolder) helper).batchCycleChart.setData(cd);
                    ((UnitBatchCycleByTemRainViewHolder) helper).batchCycleChart.animateX(1500, Easing.EasingOption.EaseInCubic);
                    ((UnitBatchCycleByTemRainViewHolder) helper).batchCycleChart.invalidate();
                }
                break;
            case UnitBatchCycleInfoEntity.TYPE_ACCRAIN:
                if (helper instanceof UnitBatchCycleByTemRainViewHolder) {
                    CommonUnitBatchCycleBean.RainInfo historyRain = (CommonUnitBatchCycleBean.RainInfo) item.getData1();
                    CommonUnitBatchCycleBean.RainInfo curAccRain = (CommonUnitBatchCycleBean.RainInfo) item.getData2();

                    ((UnitBatchCycleByTemRainViewHolder) helper).batchCycleChart.setNoDataText("未检测到数据，刷新试试");
                    //描述相关设置
                    ((UnitBatchCycleByTemRainViewHolder) helper).batchCycleChart.getDescription().setEnabled(false);//是否显示
                    ((UnitBatchCycleByTemRainViewHolder) helper).batchCycleChart.getDescription().setText("生长周期图");//描述
                    ((UnitBatchCycleByTemRainViewHolder) helper).batchCycleChart.getDescription().setTextSize(14f);
                    //设置手势
                    ((UnitBatchCycleByTemRainViewHolder) helper).batchCycleChart.setTouchEnabled(false);//启用/禁用与图表的所有可能的触摸交互。
//                    ((UnitBatchCycleByTemRainViewHolder) helper).batchCycleChart.setScaleEnabled(false);//是否可缩放

                    //坐标轴
                    //X轴设置
                    XAxis xAxis = ((UnitBatchCycleByTemRainViewHolder) helper).batchCycleChart.getXAxis();
                    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                    xAxis.setAvoidFirstLastClipping(true);//绘制时会避免“剪掉”在x轴上的图表或屏幕边缘的第一个和最后一个坐标轴标签项。
                    xAxis.setValueFormatter(null);
                    xAxis.setDrawGridLines(false);//是否显示网格
                    xAxis.setDrawLabels(false);//是否显示轴值
//                    xAxis.setEnabled(false);

                    //Y轴设置
                    YAxis yAxis = ((UnitBatchCycleByTemRainViewHolder) helper).batchCycleChart.getAxisLeft();
                    yAxis.setLabelCount(10, false);
                    yAxis.setTextSize(12f);
                    yAxis.setAxisMaximum((float) (historyRain.getTotalRain() + 100));
                    yAxis.setAxisMinimum(0f);
                    yAxis.setDrawGridLines(false);//是否显示网格
                    yAxis.setDrawLabels(true);//是否显示轴值
                    YAxis yAxis1 = ((UnitBatchCycleByTemRainViewHolder) helper).batchCycleChart.getAxisRight();
                    yAxis1.setEnabled(false);

                    //数据设置
                    ArrayList<Entry> history = new ArrayList<Entry>();
                    ArrayList<Entry> curAcc = new ArrayList<Entry>();

                    for (int i = 0; i < historyRain.getRainSerial().size(); i++) {
                        CommonMapArrayBean bean = historyRain.getRainSerial().get(i);
                        if (i == 0)
                            history.add(i, new Entry(i, Float.valueOf(bean.getValue()).floatValue()));
                        else {
                            Entry entryLast = history.get(i - 1);
                            history.add(i, new Entry(i, entryLast.getY() + Float.valueOf(bean.getValue()).floatValue()));
                        }

                    }

                    LineDataSet d1 = new LineDataSet(history, "同期历史累积降雨量");
                    d1.setLineWidth(2.5f);
                    d1.setDrawCircles(false);
//                    d1.setCircleRadius(4.5f);
//                    d1.setHighLightColor(Color.rgb(244, 117, 117));
                    d1.setColor(places.get(0));
                    d1.setHighlightEnabled(false);
                    d1.setDrawValues(false);


                    for (int i = 0; i < curAccRain.getRainSerial().size(); i++) {
                        CommonMapArrayBean bean = curAccRain.getRainSerial().get(i);
                        if (i == 0)
                            curAcc.add(i, new Entry(i, Float.valueOf(bean.getValue()).floatValue()));
                        else {
                            Entry entryLast = curAcc.get(i - 1);
                            curAcc.add(i, new Entry(i, entryLast.getY() + Float.valueOf(bean.getValue()).floatValue()));
                        }

                    }

                    LineDataSet d2 = new LineDataSet(curAcc, "当前累积降雨量");
                    d2.setLineWidth(2.5f);
                    d2.setColor(places.get(1));
                    d2.setHighlightEnabled(false);
                    d2.setDrawCircles(false);
//                    d2.setCircleRadius(4.5f);
//                    d2.setHighLightColor(Color.rgb(244, 117, 117));
//                    d2.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);
//                    d2.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[0]);
                    d2.setDrawValues(false);

                    ArrayList<ILineDataSet> sets = new ArrayList<>();
                    if (history.size() > 0)
                        sets.add(d1);
                    if (curAcc.size() > 0)
                        sets.add(d2);

                    LineData cd = new LineData(sets);

                    ((UnitBatchCycleByTemRainViewHolder) helper).batchCycleChart.setData(cd);
                    ((UnitBatchCycleByTemRainViewHolder) helper).batchCycleChart.animateX(1500, Easing.EasingOption.EaseInCubic);
                    ((UnitBatchCycleByTemRainViewHolder) helper).batchCycleChart.invalidate();
                }
                break;
        }
    }
}
