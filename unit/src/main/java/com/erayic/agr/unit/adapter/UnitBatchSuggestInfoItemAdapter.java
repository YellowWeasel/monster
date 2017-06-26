package com.erayic.agr.unit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.unit.R;
import com.erayic.agr.unit.adapter.entity.UnitBatchSuggestInfoEntity;
import com.erayic.agr.unit.adapter.holder.UnitBatchSuggestByBatchViewHolder;
import com.erayic.agr.unit.adapter.holder.UnitBatchSuggestByContentViewHolder;
import com.erayic.agr.unit.adapter.holder.UnitBatchSuggestByEnvironmentViewHolder;
import com.erayic.agr.unit.adapter.holder.UnitBatchSuggestByTitleViewHolder;
import com.erayic.agr.unit.adapter.holder.UnitBatchSuggestByWeatherViewHolder;

import java.util.List;

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
            case UnitBatchSuggestInfoEntity.TYPE_BATCH:
                return new UnitBatchSuggestByBatchViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_unit_batch_suggest_batch, parent, false));
            case UnitBatchSuggestInfoEntity.TYPE_TITLE:
                return new UnitBatchSuggestByTitleViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_unit_batch_suggest_title, parent, false));
            case UnitBatchSuggestInfoEntity.TYPE_GIVE:
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
            case UnitBatchSuggestInfoEntity.TYPE_BATCH:
                break;
            case UnitBatchSuggestInfoEntity.TYPE_TITLE:
                break;
            case UnitBatchSuggestInfoEntity.TYPE_GIVE:
                break;
            case UnitBatchSuggestInfoEntity.TYPE_WEATHER:
                break;
            case UnitBatchSuggestInfoEntity.TYPE_ENVIRONMENT:
                break;
        }
    }
}
