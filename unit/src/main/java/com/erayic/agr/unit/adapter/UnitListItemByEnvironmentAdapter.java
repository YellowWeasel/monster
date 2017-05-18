package com.erayic.agr.unit.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.unit.R;
import com.erayic.agr.unit.adapter.entity.UnitListItemByEnvironmentEntity;
import com.erayic.agr.unit.adapter.holder.UnitListItemByEnviInfoViewHolder;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：环境列表
 */

public class UnitListItemByEnvironmentAdapter extends BaseMultiItemQuickAdapter<UnitListItemByEnvironmentEntity, BaseViewHolder> {

    private Context context;

    public UnitListItemByEnvironmentAdapter(Context context, List<UnitListItemByEnvironmentEntity> data) {
        super(data);
        this.context = context;
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case UnitListItemByEnvironmentEntity.TYPE_AIR_TEM:
                return new UnitListItemByEnviInfoViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_unit_list_item_by_envi_info, parent, false));
            case UnitListItemByEnvironmentEntity.TYPE_AIR_HUM:
                return new UnitListItemByEnviInfoViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_unit_list_item_by_envi_info, parent, false));
            case UnitListItemByEnvironmentEntity.TYPE_SOIL_TEM:
                return new UnitListItemByEnviInfoViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_unit_list_item_by_envi_info, parent, false));
            case UnitListItemByEnvironmentEntity.TYPE_SOIL_HUM:
                return new UnitListItemByEnviInfoViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_unit_list_item_by_envi_info, parent, false));
            case UnitListItemByEnvironmentEntity.TYPE_ILL:
                return new UnitListItemByEnviInfoViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_unit_list_item_by_envi_info, parent, false));
            case UnitListItemByEnvironmentEntity.TYPE_WATER:
                return new UnitListItemByEnviInfoViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_unit_list_item_by_envi_info, parent, false));
            case UnitListItemByEnvironmentEntity.TYPE_WIND:
                return new UnitListItemByEnviInfoViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_unit_list_item_by_envi_info, parent, false));
            default:
                return super.onCreateDefViewHolder(parent, viewType);
        }
    }

    @Override
    protected void convert(BaseViewHolder helper, UnitListItemByEnvironmentEntity item) {
        switch (helper.getItemViewType()) {
            case UnitListItemByEnvironmentEntity.TYPE_AIR_TEM:
                if (helper instanceof UnitListItemByEnviInfoViewHolder) {
                    ((UnitListItemByEnviInfoViewHolder) helper).unitContentIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_unit_air_tem));
                    ((UnitListItemByEnviInfoViewHolder) helper).unitContentName.setText(item.getName());
                    ((UnitListItemByEnviInfoViewHolder) helper).unitContentSub.setText(item.getSubName());
                }
                break;
            case UnitListItemByEnvironmentEntity.TYPE_AIR_HUM:
                if (helper instanceof UnitListItemByEnviInfoViewHolder) {
                    ((UnitListItemByEnviInfoViewHolder) helper).unitContentIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_unit_air_hum));
                    ((UnitListItemByEnviInfoViewHolder) helper).unitContentName.setText(item.getName());
                    ((UnitListItemByEnviInfoViewHolder) helper).unitContentSub.setText(item.getSubName());
                }
                break;
            case UnitListItemByEnvironmentEntity.TYPE_SOIL_TEM:
                if (helper instanceof UnitListItemByEnviInfoViewHolder) {
                    ((UnitListItemByEnviInfoViewHolder) helper).unitContentIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_unit_soil_tem));
                    ((UnitListItemByEnviInfoViewHolder) helper).unitContentName.setText(item.getName());
                    ((UnitListItemByEnviInfoViewHolder) helper).unitContentSub.setText(item.getSubName());
                }
                break;
            case UnitListItemByEnvironmentEntity.TYPE_SOIL_HUM:
                if (helper instanceof UnitListItemByEnviInfoViewHolder) {
                    ((UnitListItemByEnviInfoViewHolder) helper).unitContentIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_unit_soil_hum));
                    ((UnitListItemByEnviInfoViewHolder) helper).unitContentName.setText(item.getName());
                    ((UnitListItemByEnviInfoViewHolder) helper).unitContentSub.setText(item.getSubName());
                }
                break;
            case UnitListItemByEnvironmentEntity.TYPE_ILL:
                if (helper instanceof UnitListItemByEnviInfoViewHolder) {
                    ((UnitListItemByEnviInfoViewHolder) helper).unitContentIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_unit_ill));
                    ((UnitListItemByEnviInfoViewHolder) helper).unitContentName.setText(item.getName());
                    ((UnitListItemByEnviInfoViewHolder) helper).unitContentSub.setText(item.getSubName());
                }
                break;
            case UnitListItemByEnvironmentEntity.TYPE_WATER:
                if (helper instanceof UnitListItemByEnviInfoViewHolder) {
                    ((UnitListItemByEnviInfoViewHolder) helper).unitContentIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_unit_water));
                    ((UnitListItemByEnviInfoViewHolder) helper).unitContentName.setText(item.getName());
                    ((UnitListItemByEnviInfoViewHolder) helper).unitContentSub.setText(item.getSubName());
                }
                break;
            case UnitListItemByEnvironmentEntity.TYPE_WIND:
                if (helper instanceof UnitListItemByEnviInfoViewHolder) {
                    ((UnitListItemByEnviInfoViewHolder) helper).unitContentIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_unit_wind));
                    ((UnitListItemByEnviInfoViewHolder) helper).unitContentName.setText(item.getName());
                    ((UnitListItemByEnviInfoViewHolder) helper).unitContentSub.setText(item.getSubName());
                }
                break;
            default:
                break;
        }
    }


}
