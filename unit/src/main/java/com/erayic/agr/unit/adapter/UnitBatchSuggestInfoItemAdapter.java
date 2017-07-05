package com.erayic.agr.unit.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Matrix;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchInfoBean;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchSuggestBean;
import com.erayic.agr.common.util.ErayicNetDate;
import com.erayic.agr.unit.R;
import com.erayic.agr.unit.adapter.entity.UnitBatchSuggestInfoEntity;
import com.erayic.agr.unit.adapter.holder.UnitBatchSuggestByContentViewHolder;
import com.erayic.agr.unit.adapter.holder.UnitBatchSuggestByEnvironmentViewHolder;
import com.erayic.agr.unit.adapter.holder.UnitBatchSuggestByTitleViewHolder;
import com.erayic.agr.unit.adapter.holder.UnitBatchSuggestByWeatherViewHolder;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import butterknife.BindView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class UnitBatchSuggestInfoItemAdapter extends BaseMultiItemQuickAdapter<UnitBatchSuggestInfoEntity, BaseViewHolder> {

    private Context context;


    public UnitBatchSuggestInfoItemAdapter(Context context, List<UnitBatchSuggestInfoEntity> data) {
        super(data);
        this.context = context;
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case UnitBatchSuggestInfoEntity.TYPE_TITLE:
                return new UnitBatchSuggestByTitleViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_unit_batch_suggest_title, parent, false));
            case UnitBatchSuggestInfoEntity.TYPE_SUGGEST:
                return new UnitBatchSuggestByContentViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_unit_batch_suggest_content, parent, false));
            case UnitBatchSuggestInfoEntity.TYPE_WEATHER:
                return new UnitBatchSuggestByWeatherViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_unit_batch_suggest_weather, parent, false));
            case UnitBatchSuggestInfoEntity.TYPE_ENVIRONMENT:
                return new UnitBatchSuggestByEnvironmentViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_unit_batch_suggest_environment, parent, false));
        }
        return super.onCreateDefViewHolder(parent, viewType);
    }

    @Override
    protected void convert(BaseViewHolder helper, UnitBatchSuggestInfoEntity item) {
        switch (helper.getItemViewType()) {
            case UnitBatchSuggestInfoEntity.TYPE_TITLE:
                if (helper instanceof UnitBatchSuggestByTitleViewHolder) {
                    ((UnitBatchSuggestByTitleViewHolder) helper).unitContentName.setText(TextUtils.isEmpty(item.getName()) ? "" : item.getName());
                    ((UnitBatchSuggestByTitleViewHolder) helper).unitContentSubName.setText("");
                }
                break;
            case UnitBatchSuggestInfoEntity.TYPE_SUGGEST:
                if (helper instanceof UnitBatchSuggestByContentViewHolder) {
                    CommonUnitBatchSuggestBean.SuggestionResult result = (CommonUnitBatchSuggestBean.SuggestionResult) item.getData();
                    ((UnitBatchSuggestByContentViewHolder) helper).unitContentFraction.setText("" + result.getResultIndex() + "");
                    ((UnitBatchSuggestByContentViewHolder) helper).unitContentName.setText(CommonUnitBatchInfoBean.getDes(result.getKey()));
                    ((UnitBatchSuggestByContentViewHolder) helper).unitContentSubName.setText(result.getSuggestionContent());
                    if (result.getKey() != 4) {//非病虫害
                        if (result.getResultIndex() < 30) {
                            ((UnitBatchSuggestByContentViewHolder) helper).unitContentFraction.setBackgroundColor(Color.rgb(255, 0, 16));//红
                            ((UnitBatchSuggestByContentViewHolder) helper).unitContentUpdate.setText("不适宜");
                            ((UnitBatchSuggestByContentViewHolder) helper).unitContentUpdate.setTextColor(ContextCompat.getColor(context, R.color.app_base_text_status_red));
                        } else if (result.getResultIndex() < 60) {
                            ((UnitBatchSuggestByContentViewHolder) helper).unitContentFraction.setBackgroundColor(Color.rgb(255, 93, 0));//橙
                            ((UnitBatchSuggestByContentViewHolder) helper).unitContentUpdate.setText("不适宜");
                            ((UnitBatchSuggestByContentViewHolder) helper).unitContentUpdate.setTextColor(ContextCompat.getColor(context, R.color.app_base_text_status_red));
                        } else if (result.getResultIndex() <= 80) {
                            ((UnitBatchSuggestByContentViewHolder) helper).unitContentFraction.setBackgroundColor(Color.rgb(255, 230, 0));//黄
                            ((UnitBatchSuggestByContentViewHolder) helper).unitContentUpdate.setText("较适宜");
                            ((UnitBatchSuggestByContentViewHolder) helper).unitContentUpdate.setTextColor(ContextCompat.getColor(context, R.color.app_base_text_status_gray));
                        } else {
                            ((UnitBatchSuggestByContentViewHolder) helper).unitContentFraction.setBackgroundColor(Color.rgb(0, 82, 255));//蓝
                            ((UnitBatchSuggestByContentViewHolder) helper).unitContentUpdate.setText("适宜");
                            ((UnitBatchSuggestByContentViewHolder) helper).unitContentUpdate.setTextColor(ContextCompat.getColor(context, R.color.app_base_text_status_green));
                        }
                    } else {//病虫害
                        if (result.getResultIndex() < 30) {
                            ((UnitBatchSuggestByContentViewHolder) helper).unitContentFraction.setBackgroundColor(Color.rgb(0, 82, 255));//蓝
                            ((UnitBatchSuggestByContentViewHolder) helper).unitContentUpdate.setText("低");
                            ((UnitBatchSuggestByContentViewHolder) helper).unitContentUpdate.setTextColor(ContextCompat.getColor(context, R.color.app_base_text_status_green));
                        } else if (result.getResultIndex() < 60) {
                            ((UnitBatchSuggestByContentViewHolder) helper).unitContentFraction.setBackgroundColor(Color.rgb(255, 230, 0));//黄
                            ((UnitBatchSuggestByContentViewHolder) helper).unitContentUpdate.setText("低");
                            ((UnitBatchSuggestByContentViewHolder) helper).unitContentUpdate.setTextColor(ContextCompat.getColor(context, R.color.app_base_text_status_green));
                        } else if (result.getResultIndex() <= 80) {
                            ((UnitBatchSuggestByContentViewHolder) helper).unitContentFraction.setBackgroundColor(Color.rgb(255, 93, 0));//橙
                            ((UnitBatchSuggestByContentViewHolder) helper).unitContentUpdate.setText("中");
                            ((UnitBatchSuggestByContentViewHolder) helper).unitContentUpdate.setTextColor(ContextCompat.getColor(context, R.color.app_base_text_status_gray));
                        } else {
                            ((UnitBatchSuggestByContentViewHolder) helper).unitContentFraction.setBackgroundColor(Color.rgb(255, 0, 16));//红
                            ((UnitBatchSuggestByContentViewHolder) helper).unitContentUpdate.setText("高");
                            ((UnitBatchSuggestByContentViewHolder) helper).unitContentUpdate.setTextColor(ContextCompat.getColor(context, R.color.app_base_text_status_red));
                        }
                    }
                }
                break;
            case UnitBatchSuggestInfoEntity.TYPE_WEATHER:
                if (helper instanceof UnitBatchSuggestByWeatherViewHolder) {

                    List<CommonUnitBatchSuggestBean.FeaturesInfo> featuresList = (List<CommonUnitBatchSuggestBean.FeaturesInfo>) item.getData();
                    Collections.sort(featuresList, new SortByDate());//排序
                    List<CommonUnitBatchSuggestBean.FeaturesInfo> temList = new ArrayList<>();
                    List<CommonUnitBatchSuggestBean.FeaturesInfo> rainList = new ArrayList<>();
                    List<CommonUnitBatchSuggestBean.FeaturesInfo> windList = new ArrayList<>();
                    for (CommonUnitBatchSuggestBean.FeaturesInfo info : featuresList) {
                        switch (info.getKey()) {
                            case 0:
                                break;
                            case 1://rain
                                rainList.add(info);
                                break;
                            case 2://tem
                                temList.add(info);
                                break;
                            case 3://wind
                                windList.add(info);
                                break;
                        }
                    }

                    CommonUnitBatchSuggestBean.RealTimeInfo realTime = (CommonUnitBatchSuggestBean.RealTimeInfo) item.getSubData();


                    int[] array = {temList.size(), rainList.size(), windList.size()};
                    Arrays.sort(array);
                    int maxLength = array[array.length - 1];

                    String[] xName = new String[maxLength + 1];
                    float[] temValue = new float[temList.size() + 1];
                    float[] rainValue = new float[rainList.size() + 1];
                    float[] windValue = new float[windList.size() + 1];
                    xName[0] = "当前";
                    temValue[0] = (float) realTime.getTemp_Max();
                    rainValue[0] = (float) realTime.getRain_1H();
                    windValue[0] = (float) realTime.getWind_Max();

                    //温度
                    for (int i = 0; i < temList.size(); i++) {
                        if (temList.size() == maxLength)
                            xName[i + 1] = new DateTime(ErayicNetDate.getLongDates(temList.get(i).getForecastDateTime())).toString("HH");
                        temValue[i + 1] = (float) temList.get(i).getValue();
                    }
                    //雨量
                    for (int i = 0; i < rainList.size(); i++) {
                        if (temList.size() == maxLength)
                            xName[i + 1] = new DateTime(ErayicNetDate.getLongDates(rainList.get(i).getForecastDateTime())).toString("HH");
                        rainValue[i + 1] = (float) rainList.get(i).getValue();
                    }
                    //风速
                    for (int i = 0; i < windList.size(); i++) {
                        if (temList.size() == maxLength)
                            xName[i + 1] = new DateTime(ErayicNetDate.getLongDates(windList.get(i).getForecastDateTime())).toString("HH");
                        windValue[i + 1] = (float) windList.get(i).getValue();
                    }
                    final String[] finalXName = xName;

                    ((UnitBatchSuggestByWeatherViewHolder) helper).lineChartWeather.setNoDataText("未检测到数据，刷新试试");
//                    //描述相关设置
                    ((UnitBatchSuggestByWeatherViewHolder) helper).lineChartWeather.getDescription().setEnabled(false);//是否显示图名称
//                    //设置手势
//                    ((UnitBatchSuggestByWeatherViewHolder) helper).lineChartWeather.setTouchEnabled(false);//禁用所有手势
                    ((UnitBatchSuggestByWeatherViewHolder) helper).lineChartWeather.setScaleEnabled(false);//禁止缩放xy
                    ((UnitBatchSuggestByWeatherViewHolder) helper).lineChartWeather.setPinchZoom(false);//捏缩放功能
                    ((UnitBatchSuggestByWeatherViewHolder) helper).lineChartWeather.setDoubleTapToZoomEnabled(false);
                    ((UnitBatchSuggestByWeatherViewHolder) helper).lineChartWeather.setHighlightPerDragEnabled(false);
                    ((UnitBatchSuggestByWeatherViewHolder) helper).lineChartWeather.setHighlightPerTapEnabled(false);
                    ((UnitBatchSuggestByWeatherViewHolder) helper).lineChartWeather.setDragDecelerationEnabled(true);
                    ((UnitBatchSuggestByWeatherViewHolder) helper).lineChartWeather.setDragDecelerationFrictionCoef(0.5f);
                    ((UnitBatchSuggestByWeatherViewHolder) helper).lineChartWeather.setExtraBottomOffset(10);
//

                    Legend l = ((UnitBatchSuggestByWeatherViewHolder) helper).lineChartWeather.getLegend();
                    l.setWordWrapEnabled(true);
                    l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
                    l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
                    l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
                    l.setDrawInside(false);

//                    // 把X坐标轴放置到底部。默认的是在顶部。
                    XAxis xAxis = ((UnitBatchSuggestByWeatherViewHolder) helper).lineChartWeather.getXAxis();
                    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                    // X轴坐标线的颜色
                    xAxis.setAxisLineColor(Color.LTGRAY);

                    // false将不显示图表网格中的x轴标线
                    // xAxis.setEnabled(false);
                    xAxis.setAvoidFirstLastClipping(true);//绘制时会避免“剪掉”在x轴上的图表或屏幕边缘的第一个和最后一个坐标轴标签项。
                    xAxis.setValueFormatter(null);
                    xAxis.setDrawGridLines(false);//是否显示网格
                    xAxis.setDrawLabels(true);//是否显示轴值
                    xAxis.setTextSize(12f);
                    xAxis.setYOffset(5f);
                    xAxis.setXOffset(5f);
                    xAxis.setValueFormatter(new IAxisValueFormatter() {
                        @Override
                        public String getFormattedValue(float value, AxisBase axis) {
                            return finalXName[(int) value % finalXName.length];
                        }
                    });
                    xAxis.setTextColor(Color.rgb(164, 169, 194));
//
//                    //Y轴设置
                    YAxis yAxisLeft = ((UnitBatchSuggestByWeatherViewHolder) helper).lineChartWeather.getAxisLeft();
                    yAxisLeft.setAxisMaximum(60f);
                    yAxisLeft.setAxisMinimum(-50f);
                    yAxisLeft.setEnabled(false);
                    YAxis yAxisRight = ((UnitBatchSuggestByWeatherViewHolder) helper).lineChartWeather.getAxisRight();
                    yAxisRight.setAxisMaximum(300f);
                    yAxisRight.setAxisMinimum(0f);
                    yAxisRight.setEnabled(false);

                    //数据
                    CombinedData data = new CombinedData();
                    //温度风力数据
                    {
                        LineData d = new LineData();

                        ArrayList<Entry> entriesTem = new ArrayList<>();
                        ArrayList<Entry> entriesWind = new ArrayList<>();

                        for (int index = 0; index < temValue.length; index++)
                            entriesTem.add(new Entry(index, temValue[index]));

                        LineDataSet setTem = new LineDataSet(entriesTem, "温度(℃)");
                        setTem.setColor(Color.rgb(0, 187, 201));
                        setTem.setLineWidth(1f);
                        setTem.setCircleColor(Color.rgb(0, 187, 201));
                        setTem.setCircleRadius(3f);
                        setTem.setFillColor(Color.rgb(0, 187, 201));
                        setTem.setMode(LineDataSet.Mode.CUBIC_BEZIER);
                        setTem.setDrawValues(true);
                        setTem.setValueTextSize(10f);
                        setTem.setValueTextColor(Color.rgb(0, 187, 201));
                        setTem.setAxisDependency(YAxis.AxisDependency.LEFT);

                        for (int index = 0; index < windValue.length; index++)
                            entriesWind.add(new Entry(index, windValue[index]));

                        LineDataSet setWind = new LineDataSet(entriesWind, "风力(m/s)");
                        setWind.setColor(Color.rgb(80, 199, 55));
                        setWind.setLineWidth(1f);
                        setWind.setCircleColor(Color.rgb(80, 199, 55));
                        setWind.setCircleRadius(3f);
                        setWind.setFillColor(Color.rgb(80, 199, 55));
                        setWind.setMode(LineDataSet.Mode.CUBIC_BEZIER);
                        setWind.setDrawValues(true);
                        setWind.setValueTextSize(10f);
                        setWind.setValueTextColor(Color.rgb(80, 199, 55));

                        setWind.setAxisDependency(YAxis.AxisDependency.LEFT);

                        d.addDataSet(setTem);
                        d.addDataSet(setWind);
                        data.setData(d);
                    }
                    //降雨数据
                    {
                        ArrayList<BarEntry> entries = new ArrayList<>();

                        for (int index = 0; index < rainValue.length; index++) {
                            entries.add(new BarEntry(index, rainValue[index]));
                        }

                        BarDataSet set1 = new BarDataSet(entries, "降雨量(ml)");
                        set1.setColor(Color.rgb(254, 204, 134));
                        set1.setValueTextColor(Color.rgb(254, 204, 134));
                        set1.setValueTextSize(10f);
                        set1.setBarBorderWidth(0f);
                        set1.setAxisDependency(YAxis.AxisDependency.RIGHT);

                        ArrayList<IBarDataSet> sets = new ArrayList<>();
                        sets.add(set1);

                        BarData cd = new BarData(sets);
                        cd.setBarWidth(0.8f);
                        data.setData(cd);

                        // make this BarData object grouped
//                        d.groupBars(0, groupSpace, barSpace); // start at x = 0

                    }
                    //左右滑动
                    Matrix m = new Matrix();
                    m.postScale(2.5f, 1f);//两个参数分别是x,y轴的缩放比例。例如：将x轴的数据放大为之前的1.5倍
                    ((UnitBatchSuggestByWeatherViewHolder) helper).lineChartWeather.getViewPortHandler().refresh(m, ((UnitBatchSuggestByWeatherViewHolder) helper).lineChartWeather, false);//将图表动画显示之前进行缩放

                    ((UnitBatchSuggestByWeatherViewHolder) helper).lineChartWeather.setData(data);
                    ((UnitBatchSuggestByWeatherViewHolder) helper).lineChartWeather.invalidate();
                }
                break;
            case UnitBatchSuggestInfoEntity.TYPE_ENVIRONMENT:
                break;
        }
    }

    /**
     * 按照时间排序
     */
    private class SortByDate implements Comparator {
        public int compare(Object o1, Object o2) {
            CommonUnitBatchSuggestBean.FeaturesInfo info1 = (CommonUnitBatchSuggestBean.FeaturesInfo) o1;
            CommonUnitBatchSuggestBean.FeaturesInfo info2 = (CommonUnitBatchSuggestBean.FeaturesInfo) o2;
            DateTime dateTime1 = new DateTime(ErayicNetDate.getLongDates(info1.getForecastDateTime()));
            DateTime dateTime2 = new DateTime(ErayicNetDate.getLongDates(info2.getForecastDateTime()));
            return Long.valueOf(dateTime1.getMillis()).compareTo(dateTime2.getMillis());
        }
    }
}
