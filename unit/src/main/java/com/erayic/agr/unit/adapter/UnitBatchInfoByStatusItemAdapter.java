package com.erayic.agr.unit.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchInfoBean;
import com.erayic.agr.common.util.ErayicLog;
import com.erayic.agr.common.util.ErayicNetDate;
import com.erayic.agr.unit.R;
import com.erayic.agr.unit.adapter.entity.UnitBatchItemEntity;
import com.erayic.agr.unit.adapter.holder.UnitBatchInfoByModelViewHolder;
import com.erayic.agr.unit.adapter.holder.UnitBatchInfoByProduceViewHolder;
import com.erayic.agr.unit.adapter.holder.UnitBatchInfoBySuggestViewHolder;
import com.erayic.agr.unit.adapter.holder.UnitBatchInfoByTitleViewHolder;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.utils.MPPointF;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class UnitBatchInfoByStatusItemAdapter extends BaseMultiItemQuickAdapter<UnitBatchItemEntity, BaseViewHolder> {

    private Context context;

    private OnItemChartClickListener onItemChartClickListener;

    /**
     * 颜色初始化
     */
    List<Integer> places = Arrays.asList(
            Color.rgb(55, 217, 170),
            Color.rgb(4, 169, 249),
            Color.rgb(80, 195, 252),
            Color.rgb(0, 215, 21),
            Color.rgb(249, 255, 1),
            Color.rgb(252, 210, 2),
            Color.rgb(255, 155, 13),
            Color.rgb(255, 99, 2));

    public UnitBatchInfoByStatusItemAdapter(Context context, List<UnitBatchItemEntity> data) {
        super(data);
        this.context = context;
    }

    public void setOnItemChartClickListener(OnItemChartClickListener onItemChartClickListener) {
        this.onItemChartClickListener = onItemChartClickListener;
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case UnitBatchItemEntity.TYPE_TITLE_MODEL:
            case UnitBatchItemEntity.TYPE_TITLE_SUGGEST:
            case UnitBatchItemEntity.TYPE_TITLE_PRODUCE:
                return new UnitBatchInfoByTitleViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_unit_batch_info_title, parent, false));
            case UnitBatchItemEntity.TYPE_MODEL:
                return new UnitBatchInfoByModelViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_unit_batch_info_model, parent, false));
            case UnitBatchItemEntity.TYPE_SUGGEST:
                return new UnitBatchInfoBySuggestViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_unit_batch_info_suggest, parent, false));
            case UnitBatchItemEntity.TYPE_PRODUCE:
                return new UnitBatchInfoByProduceViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_unit_batch_info_produce, parent, false));
            default:
                return super.onCreateDefViewHolder(parent, viewType);
        }
    }

    @Override
    protected void convert(final BaseViewHolder helper, final UnitBatchItemEntity item) {
        switch (helper.getItemViewType()) {
            case UnitBatchItemEntity.TYPE_TITLE_MODEL:
                if (helper instanceof UnitBatchInfoByTitleViewHolder) {
                    ((UnitBatchInfoByTitleViewHolder) helper).unitItemListName.setText(item.getName());
                    ((UnitBatchInfoByTitleViewHolder) helper).itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onItemChartClickListener != null)
                                onItemChartClickListener.onBind(UnitBatchItemEntity.TYPE_TITLE_MODEL);
                        }
                    });
                    ((UnitBatchInfoByTitleViewHolder) helper).itemView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            return true;
                        }
                    });

                }
                break;
            case UnitBatchItemEntity.TYPE_TITLE_SUGGEST:
                if (helper instanceof UnitBatchInfoByTitleViewHolder) {
                    ((UnitBatchInfoByTitleViewHolder) helper).unitItemListName.setText(item.getName());
                    ((UnitBatchInfoByTitleViewHolder) helper).itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onItemChartClickListener != null)
                                onItemChartClickListener.onBind(UnitBatchItemEntity.TYPE_TITLE_SUGGEST);
                        }
                    });
                    ((UnitBatchInfoByTitleViewHolder) helper).itemView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            return true;
                        }
                    });

                }
                break;
            case UnitBatchItemEntity.TYPE_TITLE_PRODUCE:
                if (helper instanceof UnitBatchInfoByTitleViewHolder) {
                    ((UnitBatchInfoByTitleViewHolder) helper).unitItemListName.setText(item.getName());
                    ((UnitBatchInfoByTitleViewHolder) helper).itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onItemChartClickListener != null)
                                onItemChartClickListener.onBind(UnitBatchItemEntity.TYPE_TITLE_PRODUCE);
                        }
                    });
                    ((UnitBatchInfoByTitleViewHolder) helper).itemView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            return true;
                        }
                    });

                }
                break;
            case UnitBatchItemEntity.TYPE_MODEL:
                if (helper instanceof UnitBatchInfoByModelViewHolder) {

//                    String[] x = new String[]{"发芽期", "生长期", "伸蔓期", "结果期"};
//                    float[] y = {80f, 45f, 60f, 15f};
                    List<CommonUnitBatchInfoBean.CropCycle> cycles = (List<CommonUnitBatchInfoBean.CropCycle>) item.getData();
                    String[] key = new String[cycles.size()];
                    float[] value = new float[cycles.size()];
                    int current = -1;//当前处于周期位置
                    int distanceBelow = 0;//距离周期结束还有多少天
                    for (CommonUnitBatchInfoBean.CropCycle cycleBean : cycles) {
                        key[cycleBean.getSubCycle().getSubCycleNum() - 1] = cycleBean.getSubCycle().getSubCycleName();

                        DateTime dateTimeStart = new DateTime(ErayicNetDate.getLongDates(cycleBean.getStartDay()));
                        DateTime dateTimeEnd = new DateTime(ErayicNetDate.getLongDates(cycleBean.getEndDay()));
                        DateTime dateTimeThis = new DateTime();
                        Period p = new Period(dateTimeStart, dateTimeEnd);
                        value[cycleBean.getSubCycle().getSubCycleNum() - 1] = p.getDays();
                        if (new Period(dateTimeThis, dateTimeEnd).getDays() == 0) {
                            current = cycleBean.getSubCycle().getSubCycleNum() - 1;
                            distanceBelow = new Period(dateTimeThis, dateTimeEnd).getDays();
                        } else if (new Interval(dateTimeStart, dateTimeEnd).contains(dateTimeThis)) {//dateTimeThis是否在区间里面
                            current = cycleBean.getSubCycle().getSubCycleNum() - 1;
                            distanceBelow = new Period(dateTimeThis, dateTimeEnd).getDays();
                        }
                    }

                    ((UnitBatchInfoByModelViewHolder) helper).batchInfoPieChart.setOnChartGestureListener(new OnChartGestureListener() {
                        @Override
                        public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

                        }

                        @Override
                        public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

                        }

                        @Override
                        public void onChartLongPressed(MotionEvent me) {

                        }

                        @Override
                        public void onChartDoubleTapped(MotionEvent me) {

                        }

                        @Override
                        public void onChartSingleTapped(MotionEvent me) {
                            //图表上单击回调
                            if (onItemChartClickListener != null) {
                                if (item.isBind())
                                    onItemChartClickListener.onToInfo(helper.getItemViewType(), item.getData());
                                else
                                    onItemChartClickListener.onBind(helper.getItemViewType());
                            }
                        }

                        @Override
                        public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {

                        }

                        @Override
                        public void onChartScale(MotionEvent me, float scaleX, float scaleY) {

                        }

                        @Override
                        public void onChartTranslate(MotionEvent me, float dX, float dY) {

                        }
                    });

                    if (item.isBind()) {//已绑定服务
                        //无数据是显示
                        ((UnitBatchInfoByModelViewHolder) helper).batchInfoPieChart.setNoDataText("未检测到数据");
                        ((UnitBatchInfoByModelViewHolder) helper).batchInfoPieChart.setNoDataTextColor(ContextCompat.getColor(context, R.color.app_base_text_status_gray));
                        //设置显示成比例
                        ((UnitBatchInfoByModelViewHolder) helper).batchInfoPieChart.setUsePercentValues(false);//
                        //描述相关设置
                        ((UnitBatchInfoByModelViewHolder) helper).batchInfoPieChart.getDescription().setEnabled(false);//是否显示
                        ((UnitBatchInfoByModelViewHolder) helper).batchInfoPieChart.getDescription().setText("生长周期图");//描述
                        ((UnitBatchInfoByModelViewHolder) helper).batchInfoPieChart.getDescription().setTextSize(14f);
                        //最外圈距离布局间隔
                        ((UnitBatchInfoByModelViewHolder) helper).batchInfoPieChart.setExtraOffsets(5, 5, 30, 5);

                        ((UnitBatchInfoByModelViewHolder) helper).batchInfoPieChart.setDragDecelerationFrictionCoef(0.95f);

                        //中心文本设置）
//                    ((UnitBatchInfoByModelViewHolder) helper).batchInfoPieChart.setCenterTextTypeface(mTfLight);
                        ((UnitBatchInfoByModelViewHolder) helper).batchInfoPieChart.setDrawCenterText(true);//是否显示中心文字
                        //切割线设置
                        ((UnitBatchInfoByModelViewHolder) helper).batchInfoPieChart.setDrawHoleEnabled(true);//是否启用切割线
                        ((UnitBatchInfoByModelViewHolder) helper).batchInfoPieChart.setHoleColor(Color.WHITE);//切割线颜色
                        //半透明圈设置
                        ((UnitBatchInfoByModelViewHolder) helper).batchInfoPieChart.setTransparentCircleColor(Color.WHITE);//设置半透明颜色
                        ((UnitBatchInfoByModelViewHolder) helper).batchInfoPieChart.setTransparentCircleAlpha(110);//半透明度
                        //各部分半径设置
                        ((UnitBatchInfoByModelViewHolder) helper).batchInfoPieChart.setHoleRadius(50f);//半径（0为实心圆）
                        ((UnitBatchInfoByModelViewHolder) helper).batchInfoPieChart.setTransparentCircleRadius(55f);//半透明圈

                        ((UnitBatchInfoByModelViewHolder) helper).batchInfoPieChart.setRotationAngle(0);//初始旋转角度
                        ((UnitBatchInfoByModelViewHolder) helper).batchInfoPieChart.setRotationEnabled(false);//是否可以手动旋转
                        ((UnitBatchInfoByModelViewHolder) helper).batchInfoPieChart.setHighlightPerTapEnabled(false);//是否显示突出显示点击

                        // 选择监听器
//                    ((UnitBatchInfoByModelViewHolder) helper).batchInfoPieChart.setOnChartValueSelectedListener(this);

                        ((UnitBatchInfoByModelViewHolder) helper).batchInfoPieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);//设置动画

                        //设置比例图
                        Legend l = ((UnitBatchInfoByModelViewHolder) helper).batchInfoPieChart.getLegend();
//                    l.setEnabled(false);//设置禁用比例块
                        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);//显示位置
                        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);//居中
                        l.setOrientation(Legend.LegendOrientation.VERTICAL);
                        l.setDrawInside(false);
                        l.setXEntrySpace(7f);//设置距离饼图的距离，防止与饼图重合
                        l.setYEntrySpace(2f);//设置距离饼图的距离，防止与饼图重合
                        l.setYOffset(0f);

                        // entry label styling
                        ((UnitBatchInfoByModelViewHolder) helper).batchInfoPieChart.setEntryLabelColor(Color.WHITE);
//                    mChart.setEntryLabelTypeface(mTfRegular);
                        ((UnitBatchInfoByModelViewHolder) helper).batchInfoPieChart.setEntryLabelTextSize(12f);
                        ((UnitBatchInfoByModelViewHolder) helper).batchInfoPieChart.setDrawEntryLabels(true);
                        ((UnitBatchInfoByModelViewHolder) helper).batchInfoPieChart.setCenterText(Html.fromHtml("<font color='#FF0000'><big>已成熟</big></font>"));
                        //数据 添加顺序即为显示顺序
                        ArrayList<PieEntry> entries = new ArrayList<>();
                        for (int i = 0; i < key.length; i++) {
                            if (i == current) {
                                entries.add(new PieEntry(value[i],
                                        key[i % key.length],
                                        ContextCompat.getDrawable(context, R.drawable.image_unit_batch_info_today)));
                                ((UnitBatchInfoByModelViewHolder) helper).batchInfoPieChart.setCenterText(Html.fromHtml("<big>" + key[i % key.length] +
                                        "</big><br/>距" + (i == key.length ? "周期结束" : key[(i + 1) % key.length]) + "还有<font color='#FF0000'><big>" + (distanceBelow + 1) + "</big></font>天"));//中心文字
                            } else
                                entries.add(new PieEntry(value[i],
                                        key[i % key.length],
                                        null));
                        }
                        PieDataSet dataSet = new PieDataSet(entries, "");
                        dataSet.setDrawIcons(false);//设置图片
                        dataSet.setSliceSpace(3f);
                        dataSet.setIconsOffset(new MPPointF(0, 40));
                        dataSet.setSelectionShift(5f);

                        //添加颜色
                        dataSet.setColors(places);
                        //dataSet.setSelectionShift(0f);

                        PieData data = new PieData(dataSet);
                        data.setValueFormatter(new PercentFormatter());
                        data.setValueTextSize(11f);
                        data.setValueTextColor(Color.WHITE);
//                    data.setValueTypeface(mTfLight);
                        if (entries.size() == 0)
                            ((UnitBatchInfoByModelViewHolder) helper).batchInfoPieChart.setData(null);//设置数据
                        else
                            ((UnitBatchInfoByModelViewHolder) helper).batchInfoPieChart.setData(data);//设置数据
                    } else {
                        ((UnitBatchInfoByModelViewHolder) helper).batchInfoPieChart.setNoDataText("去绑定");
                        ((UnitBatchInfoByModelViewHolder) helper).batchInfoPieChart.setNoDataTextColor(ContextCompat.getColor(context, R.color.app_base_text_status_red));
                        ((UnitBatchInfoByModelViewHolder) helper).batchInfoPieChart.setData(null);//设置数据
                    }
                    // 撤销所有亮点
                    ((UnitBatchInfoByModelViewHolder) helper).batchInfoPieChart.highlightValues(null);
                    //执行刷新
                    ((UnitBatchInfoByModelViewHolder) helper).batchInfoPieChart.invalidate();
                }
                break;
            case UnitBatchItemEntity.TYPE_SUGGEST:
                if (helper instanceof UnitBatchInfoBySuggestViewHolder) {

                    ((UnitBatchInfoBySuggestViewHolder) helper).batchInfoRadarChart.setOnChartGestureListener(new OnChartGestureListener() {
                        @Override
                        public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

                        }

                        @Override
                        public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

                        }

                        @Override
                        public void onChartLongPressed(MotionEvent me) {

                        }

                        @Override
                        public void onChartDoubleTapped(MotionEvent me) {

                        }

                        @Override
                        public void onChartSingleTapped(MotionEvent me) {
                            //图表上单击回调
                            if (onItemChartClickListener != null) {
                                if (item.isBind())
                                    onItemChartClickListener.onToInfo(helper.getItemViewType(), item.getData());
                                else
                                    onItemChartClickListener.onBind(helper.getItemViewType());
                            }
                        }

                        @Override
                        public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {

                        }

                        @Override
                        public void onChartScale(MotionEvent me, float scaleX, float scaleY) {

                        }

                        @Override
                        public void onChartTranslate(MotionEvent me, float dX, float dY) {

                        }
                    });


                    CommonUnitBatchInfoBean.FarmingSuggestionResult resultList = (CommonUnitBatchInfoBean.FarmingSuggestionResult) item.getData();

                    if (resultList.getSuggestionResultList() == null)
                        resultList.setSuggestionResultList(new ArrayList<CommonUnitBatchInfoBean.SuggestionResult>());

                    String[] strName = new String[resultList.getSuggestionResultList().size()];//Key
                    ArrayList<RadarEntry> entries = new ArrayList<>();//Value
                    for (int i = 0; i < resultList.getSuggestionResultList().size(); i++) {
                        CommonUnitBatchInfoBean.SuggestionResult result = resultList.getSuggestionResultList().get(i);
                        strName[i] = CommonUnitBatchInfoBean.getDes(result.getKey());
                        entries.add(new RadarEntry(result.getResultIndex()));
                    }
                    final String mActivities[] = strName;

                    if (item.isBind()) {
                        //设置无数据
                        ((UnitBatchInfoBySuggestViewHolder) helper).batchInfoRadarChart.setNoDataText("未检测到数据");
                        ((UnitBatchInfoBySuggestViewHolder) helper).batchInfoRadarChart.setNoDataTextColor(ContextCompat.getColor(context, R.color.app_base_text_status_gray));
                        //图形描述
                        ((UnitBatchInfoBySuggestViewHolder) helper).batchInfoRadarChart.getDescription().setEnabled(false);//是否显示
                        ((UnitBatchInfoBySuggestViewHolder) helper).batchInfoRadarChart.getDescription().setText("生产适应性图");//描述
                        ((UnitBatchInfoBySuggestViewHolder) helper).batchInfoRadarChart.getDescription().setTextSize(14f);
                        ((UnitBatchInfoBySuggestViewHolder) helper).batchInfoRadarChart.setRotationEnabled(false);//是否可以手动旋转

                        //雷达底部图设置
                        ((UnitBatchInfoBySuggestViewHolder) helper).batchInfoRadarChart.setWebLineWidth(1f);
                        ((UnitBatchInfoBySuggestViewHolder) helper).batchInfoRadarChart.setWebColor(Color.rgb(196, 196, 196));
                        ((UnitBatchInfoBySuggestViewHolder) helper).batchInfoRadarChart.setWebLineWidthInner(1f);
                        ((UnitBatchInfoBySuggestViewHolder) helper).batchInfoRadarChart.setWebColorInner(Color.rgb(196, 196, 196));
                        ((UnitBatchInfoBySuggestViewHolder) helper).batchInfoRadarChart.setWebAlpha(100);

                        //数据动画
                        ((UnitBatchInfoBySuggestViewHolder) helper).batchInfoRadarChart.animateXY(
                                0, 1400,
                                Easing.EasingOption.EaseInOutQuad,
                                Easing.EasingOption.EaseInOutQuad);

                        //X轴设置
                        XAxis xAxis = ((UnitBatchInfoBySuggestViewHolder) helper).batchInfoRadarChart.getXAxis();
//                    xAxis.setTypeface(mTfLight);
                        xAxis.setTextSize(14f);
                        xAxis.setYOffset(5f);
                        xAxis.setXOffset(5f);
                        xAxis.setValueFormatter(new IAxisValueFormatter() {
                            @Override
                            public String getFormattedValue(float value, AxisBase axis) {
                                return mActivities[(int) value % mActivities.length];
                            }
                        });
                        xAxis.setTextColor(Color.rgb(164, 169, 194));

                        //Y轴设置
                        YAxis yAxis = ((UnitBatchInfoBySuggestViewHolder) helper).batchInfoRadarChart.getYAxis();
//                    yAxis.setTypeface(mTfLight);
                        yAxis.setLabelCount(10, true);
                        yAxis.setTextSize(12f);
                        yAxis.setAxisMaximum(100f);
                        yAxis.setAxisMinimum(0f);
                        yAxis.setDrawLabels(false);//是否显示Y轴的分割值

                        Legend l = ((UnitBatchInfoBySuggestViewHolder) helper).batchInfoRadarChart.getLegend();
                        l.setEnabled(false);//隐藏图例

                        RadarDataSet set1 = new RadarDataSet(entries, "");
                        set1.setColor(Color.rgb(60, 144, 221));//线颜色
                        set1.setFillColor(Color.rgb(165, 200, 232));//充填颜色
                        set1.setDrawFilled(true);//是否实心填充
                        set1.setFillAlpha(180);//填充透明度
                        set1.setLineWidth(1f);//数据线宽度
                        set1.setDrawHighlightCircleEnabled(true);
                        set1.setDrawHighlightIndicators(false);
                        ArrayList<IRadarDataSet> sets = new ArrayList<>();
                        sets.add(set1);

                        RadarData data = new RadarData(sets);
//                    data.setValueTypeface(mTfLight);
                        data.setValueTextSize(10f);//数据文本大小
                        data.setDrawValues(true);//是否显示数据
                        data.setValueTextColor(Color.rgb(153, 179, 201));//数据文本颜色
                        if (resultList.getSuggestionResultList().size() == 0)
                            ((UnitBatchInfoBySuggestViewHolder) helper).batchInfoRadarChart.setData(null);
                        else
                            ((UnitBatchInfoBySuggestViewHolder) helper).batchInfoRadarChart.setData(data);
                    } else {
                        ((UnitBatchInfoBySuggestViewHolder) helper).batchInfoRadarChart.setNoDataText("去绑定");
                        ((UnitBatchInfoBySuggestViewHolder) helper).batchInfoRadarChart.setNoDataTextColor(ContextCompat.getColor(context, R.color.app_base_text_status_red));
                        ((UnitBatchInfoBySuggestViewHolder) helper).batchInfoRadarChart.setData(null);
                    }
                    //刷新数据
                    ((UnitBatchInfoBySuggestViewHolder) helper).batchInfoRadarChart.invalidate();
                }
                break;
            case UnitBatchItemEntity.TYPE_PRODUCE:
                if (helper instanceof UnitBatchInfoByProduceViewHolder) {

                    ((UnitBatchInfoByProduceViewHolder) helper).batchInfoBarChart.setOnChartGestureListener(new OnChartGestureListener() {
                        @Override
                        public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

                        }

                        @Override
                        public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

                        }

                        @Override
                        public void onChartLongPressed(MotionEvent me) {

                        }

                        @Override
                        public void onChartDoubleTapped(MotionEvent me) {

                        }

                        @Override
                        public void onChartSingleTapped(MotionEvent me) {
                            //图表上单击回调
                            if (onItemChartClickListener != null) {
                                if (item.isBind())
                                    onItemChartClickListener.onToInfo(helper.getItemViewType(), item.getData());
                                else
                                    onItemChartClickListener.onBind(helper.getItemViewType());
                            }
                        }

                        @Override
                        public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {

                        }

                        @Override
                        public void onChartScale(MotionEvent me, float scaleX, float scaleY) {

                        }

                        @Override
                        public void onChartTranslate(MotionEvent me, float dX, float dY) {

                        }
                    });

                    ((UnitBatchInfoByProduceViewHolder) helper).batchInfoBarChart.setNoDataText("暂未开放，敬请期待");
                    ((UnitBatchInfoByProduceViewHolder) helper).batchInfoBarChart.setNoDataTextColor(ContextCompat.getColor(context, R.color.app_base_text_status_red));

//                    ((UnitBatchInfoByProduceViewHolder) helper).batchInfoBarChart.setNoDataText("未检测到数据");
//
//                    ((UnitBatchInfoByProduceViewHolder) helper).batchInfoBarChart.setDrawBarShadow(false);
//
//                    ((UnitBatchInfoByProduceViewHolder) helper).batchInfoBarChart.setDrawValueAboveBar(true);
//
//                    ((UnitBatchInfoByProduceViewHolder) helper).batchInfoBarChart.getDescription().setEnabled(false);
//                    ((UnitBatchInfoByProduceViewHolder) helper).batchInfoBarChart.setMaxVisibleValueCount(3);//最大条数（超过不绘制）
//                    ((UnitBatchInfoByProduceViewHolder) helper).batchInfoBarChart.setScaleEnabled(false);//启用/禁用缩放图表上的两个轴
//                    ((UnitBatchInfoByProduceViewHolder) helper).batchInfoBarChart.setDragEnabled(false);//启用/禁用拖动（平移）图表
//                    ((UnitBatchInfoByProduceViewHolder) helper).batchInfoBarChart.setDrawGridBackground(false);
//
//                    XAxis xl = ((UnitBatchInfoByProduceViewHolder) helper).batchInfoBarChart.getXAxis();
//                    xl.setPosition(XAxis.XAxisPosition.BOTTOM);
////                    xl.setTypeface(mTfLight);
//                    xl.setDrawAxisLine(true);
//                    xl.setDrawGridLines(false);//绘制网格
//                    xl.setDrawLabels(true);//绘制标签
//                    xl.setGranularity(1f); // only intervals of 1 day
//                    xl.setLabelCount(3);
//                    xl.setValueFormatter(new IAxisValueFormatter() {
//
//                        private String[] mActivities = new String[]{"预估", "历史", "行业"};
//
//                        @Override
//                        public String getFormattedValue(float value, AxisBase axis) {
//                            return mActivities[(int) value % mActivities.length];
//                        }
//                    });
////                    xl.setGranularity(10f);
//
//                    YAxis yl = ((UnitBatchInfoByProduceViewHolder) helper).batchInfoBarChart.getAxisLeft();
////                    yl.setTypeface(mTfLight);
//                    yl.setEnabled(false);
//
//                    YAxis yr = ((UnitBatchInfoByProduceViewHolder) helper).batchInfoBarChart.getAxisRight();
////                    yr.setTypeface(mTfLight);
//                    yr.setDrawAxisLine(true);
//                    yr.setDrawGridLines(false);
//                    yr.setAxisMinimum(0f); // this replaces setStartAtZero(true)
////        yr.setInverted(true);
//
//
//                    ((UnitBatchInfoByProduceViewHolder) helper).batchInfoBarChart.setFitBars(true);
//                    ((UnitBatchInfoByProduceViewHolder) helper).batchInfoBarChart.animateY(1500);
//
//                    Legend l = ((UnitBatchInfoByProduceViewHolder) helper).batchInfoBarChart.getLegend();
//                    l.setEnabled(false);
//
//                    ArrayList<BarEntry> list = new ArrayList<>();
//                    list.add(new BarEntry(0f, 59f, ""));
//                    list.add(new BarEntry(1f, 36f, ""));
//                    list.add(new BarEntry(2f, 150f, ""));
//
//                    BarDataSet set1 = new BarDataSet(list, "产量数据");
//                    set1.setDrawIcons(false);
//
//                    ArrayList<IBarDataSet> dataSets = new ArrayList<>();
//                    dataSets.add(set1);
//                    BarData data = new BarData(dataSets);
//                    data.setHighlightEnabled(false);//隐藏高亮
//                    data.setDrawValues(true);//是否显示数据
//                    data.setValueTextSize(10f);//数据文本大小
//                    data.setValueTextColor(Color.GRAY);
//
//                    ((UnitBatchInfoByProduceViewHolder) helper).batchInfoBarChart.setData(data);
//                    ((UnitBatchInfoByProduceViewHolder) helper).batchInfoBarChart.invalidate();
                }
                break;
            default:
                break;
        }
    }

    public interface OnItemChartClickListener {

        void onBind(int type);

        void onToInfo(int type, Object object);
    }
}
