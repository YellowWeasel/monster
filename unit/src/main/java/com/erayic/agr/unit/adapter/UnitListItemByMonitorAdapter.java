package com.erayic.agr.unit.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.unit.R;
import com.erayic.agr.unit.adapter.entity.UnitListItemByMonitorEntity;
import com.erayic.agr.unit.adapter.holder.UnitListItemByMonitorViewHolder;
import com.erayic.agr.unit.adapter.holder.UnitListItemByNoDataViewHolder;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：视频列表
 */

public class UnitListItemByMonitorAdapter extends BaseMultiItemQuickAdapter<UnitListItemByMonitorEntity, BaseViewHolder> {


    private Context context;

    public UnitListItemByMonitorAdapter(Context context, List<UnitListItemByMonitorEntity> data) {
        super(data);
        this.context = context;
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case UnitListItemByMonitorEntity.TYPE_NO_DATA:
                return new UnitListItemByNoDataViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_unit_list_item_no_data, parent, false));
            case UnitListItemByMonitorEntity.TYPE_MONITOR:
                return new UnitListItemByMonitorViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_unit_list_item_by_monitor, parent, false));
        }
        return super.onCreateDefViewHolder(parent, viewType);
    }

    @Override
    protected void convert(BaseViewHolder helper, UnitListItemByMonitorEntity item) {
        switch (helper.getItemViewType()) {
            case UnitListItemByMonitorEntity.TYPE_NO_DATA:
                if (helper instanceof UnitListItemByNoDataViewHolder) {
                    ((UnitListItemByNoDataViewHolder) helper).unitContentName.setText(item.getName());
                }
                break;
            case UnitListItemByMonitorEntity.TYPE_MONITOR:
                if (helper instanceof UnitListItemByMonitorViewHolder) {
                    ((UnitListItemByMonitorViewHolder) helper).unitContentIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_unit_monitor_nomal));
                    ((UnitListItemByMonitorViewHolder) helper).unitContentName.setText(item.getName());
                    ((UnitListItemByMonitorViewHolder) helper).unitContentSubName.setText("");
                }
                break;
            default:
                break;
        }
    }
}
