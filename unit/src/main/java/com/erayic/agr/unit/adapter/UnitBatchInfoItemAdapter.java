package com.erayic.agr.unit.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.unit.R;
import com.erayic.agr.unit.adapter.entity.UnitBatchItemEntity;
import com.erayic.agr.unit.adapter.holder.UnitBatchInfoByBatchViewHolder;
import com.erayic.agr.unit.adapter.holder.UnitBatchInfoByModelViewHolder;
import com.erayic.agr.unit.adapter.holder.UnitBatchInfoBySuggestViewHolder;
import com.erayic.agr.unit.adapter.holder.UnitBatchInfoByTitleViewHolder;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class UnitBatchInfoItemAdapter extends BaseMultiItemQuickAdapter<UnitBatchItemEntity, BaseViewHolder> {

    private Context context;

    public UnitBatchInfoItemAdapter(Context context, List<UnitBatchItemEntity> data) {
        super(data);
        this.context = context;
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case UnitBatchItemEntity.TYPE_TITLE:
                return new UnitBatchInfoByTitleViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_unit_batch_info_title, parent, false));
            case UnitBatchItemEntity.TYPE_BATCH:
                return new UnitBatchInfoByBatchViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_unit_batch_info_batch, parent, false));
            case UnitBatchItemEntity.TYPE_MODEL:
                return new UnitBatchInfoByModelViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_unit_batch_info_model, parent, false));
            case UnitBatchItemEntity.TYPE_SUGGEST:
                return new UnitBatchInfoBySuggestViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_unit_batch_info_suggest, parent, false));
            default:
                return super.onCreateDefViewHolder(parent, viewType);
        }
    }

    @Override
    protected void convert(BaseViewHolder helper, UnitBatchItemEntity item) {
        switch (helper.getItemViewType()) {
            case UnitBatchItemEntity.TYPE_TITLE:
                break;
            case UnitBatchItemEntity.TYPE_BATCH:
                break;
            case UnitBatchItemEntity.TYPE_MODEL:
                if (helper instanceof UnitBatchInfoByModelViewHolder) {

                    String[] x = new String[]{"发芽期", "生长期", "伸蔓期", "结果期"};
                    float[] y = {80f, 45f, 60f, 15f};
                    //初始
                    //无数据是显示
                    ((UnitBatchInfoByModelViewHolder) helper).batchInfoPieChart.setNoDataText("未检测到数据");
                    //设置显示成比例
                    ((UnitBatchInfoByModelViewHolder) helper).batchInfoPieChart.setUsePercentValues(false);//
                    //描述相关设置
                    ((UnitBatchInfoByModelViewHolder) helper).batchInfoPieChart.getDescription().setEnabled(true);//是否显示
                    ((UnitBatchInfoByModelViewHolder) helper).batchInfoPieChart.getDescription().setText("生长周期图");//描述
                    ((UnitBatchInfoByModelViewHolder) helper).batchInfoPieChart.getDescription().setTextSize(14f);
                    //最外圈距离布局间隔
                    ((UnitBatchInfoByModelViewHolder) helper).batchInfoPieChart.setExtraOffsets(5, 5, 5, 5);

                    ((UnitBatchInfoByModelViewHolder) helper).batchInfoPieChart.setDragDecelerationFrictionCoef(0.95f);

                    //中心文本设置）
//                    ((UnitBatchInfoByModelViewHolder) helper).batchInfoPieChart.setCenterTextTypeface(mTfLight);
                    ((UnitBatchInfoByModelViewHolder) helper).batchInfoPieChart.setCenterText("当前处于生长期\n距离伸蔓期还有28天");
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
                    ((UnitBatchInfoByModelViewHolder) helper).batchInfoPieChart.setRotationEnabled(true);//是否可以手动旋转
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

                    //数据 添加顺序即为显示顺序
                    ArrayList<PieEntry> entries = new ArrayList<>();
                    for (int i = 0; i < 4; i++) {
                        if (i == 2)
                            entries.add(new PieEntry(y[i],
                                    x[i % x.length],
                                    ContextCompat.getDrawable(context, R.drawable.ic_work_day)));
                        else
                            entries.add(new PieEntry(y[i],
                                    x[i % x.length],
                                    null));
                    }

                    PieDataSet dataSet = new PieDataSet(entries, "");
                    dataSet.setDrawIcons(true);
                    dataSet.setSliceSpace(3f);
                    dataSet.setIconsOffset(new MPPointF(0, 40));
                    dataSet.setSelectionShift(5f);

                    //添加颜色
                    ArrayList<Integer> colors = new ArrayList<>();
                    colors.add(Color.rgb(39, 203, 38));
                    colors.add(Color.rgb(23, 33, 226));
                    colors.add(Color.rgb(126, 50, 204));
                    colors.add(Color.rgb(204, 50, 104));

                    dataSet.setColors(colors);
                    //dataSet.setSelectionShift(0f);

                    PieData data = new PieData(dataSet);
                    data.setValueFormatter(new PercentFormatter());
                    data.setValueTextSize(11f);
                    data.setValueTextColor(Color.WHITE);
//                    data.setValueTypeface(mTfLight);
                    ((UnitBatchInfoByModelViewHolder) helper).batchInfoPieChart.setData(data);

                    // 撤销所有亮点
                    ((UnitBatchInfoByModelViewHolder) helper).batchInfoPieChart.highlightValues(null);
                    //执行刷新
                    ((UnitBatchInfoByModelViewHolder) helper).batchInfoPieChart.invalidate();


                }
                break;
            case UnitBatchItemEntity.TYPE_SUGGEST:
                break;
            default:
                break;
        }
    }
}
