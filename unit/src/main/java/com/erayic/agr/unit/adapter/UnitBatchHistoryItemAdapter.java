package com.erayic.agr.unit.adapter;

import android.content.Context;
import android.text.TextUtils;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.erayic.agr.common.AgrConstant;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchInfoBean;
import com.erayic.agr.common.util.ErayicNetDate;
import com.erayic.agr.unit.R;
import com.erayic.agr.unit.adapter.holder.UnitBatchHistoryItemViewHolder;

import org.joda.time.DateTime;

import java.util.List;


/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class UnitBatchHistoryItemAdapter extends BaseQuickAdapter<CommonUnitBatchInfoBean.Batch, UnitBatchHistoryItemViewHolder> {

    private Context context;

    public UnitBatchHistoryItemAdapter(Context context, List<CommonUnitBatchInfoBean.Batch> entityList) {
        super(R.layout.adapter_unit_batch_item_history, entityList);
        this.context = context;
    }

    @Override
    protected void convert(UnitBatchHistoryItemViewHolder helper, CommonUnitBatchInfoBean.Batch item) {
        Glide.with(context)
                .load(TextUtils.isEmpty(item.getProductIcon()) ? "" : (AgrConstant.IMAGE_URL_IMAGE + item.getProductIcon()))
                .apply(AgrConstant.iconOptions)
                .into(helper.unitContentIcon);
        helper.unitContentName.setText(TextUtils.isEmpty(item.getProductName()) ? "未命名" : item.getProductName());
        helper.unitContentDateStart.setText(new DateTime(ErayicNetDate.getLongDates(item.getStartTime())).toString("yyyy-MM-dd"));
        helper.unitContentDateEnd.setText(new DateTime(ErayicNetDate.getLongDates(item.getEndTime())).toString("yyyy-MM-dd"));
    }
}
