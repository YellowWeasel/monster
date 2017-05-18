package com.erayic.agr.unit.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.common.net.back.enums.EnumCategoryType;
import com.erayic.agr.common.net.back.enums.EnumControlRelayStatus;
import com.erayic.agr.unit.R;
import com.erayic.agr.unit.adapter.entity.UnitListItemByControlEntity;
import com.erayic.agr.unit.adapter.holder.UnitListItemByControlByEquViewHolder;
import com.erayic.agr.unit.adapter.holder.UnitListItemByControlByPNViewHolder;
import com.erayic.agr.unit.adapter.holder.UnitListItemByControlBySTViewHolder;
import com.erayic.agr.unit.adapter.holder.UnitListItemByNoDataViewHolder;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：控制列表
 */

public class UnitListItemByControlAdapter extends BaseMultiItemQuickAdapter<UnitListItemByControlEntity, BaseViewHolder> {

    private Context context;

    public UnitListItemByControlAdapter(Context context, List<UnitListItemByControlEntity> data) {
        super(data);
        this.context = context;
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case UnitListItemByControlEntity.TYPE_ITEM_EQU:
                return new UnitListItemByControlByEquViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_unit_list_item_by_control_equ, parent, false));
            case UnitListItemByControlEntity.TYPE_ITEM_ST:
                return new UnitListItemByControlBySTViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_unit_list_item_by_control_st, parent, false));
            case UnitListItemByControlEntity.TYPE_ITEM_PN:
                return new UnitListItemByControlByPNViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_unit_list_item_by_control_pn, parent, false));
            case UnitListItemByControlEntity.TYPE_NO_EQU:
                return new UnitListItemByNoDataViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_unit_list_item_no_data, parent, false));
        }
        return super.onCreateDefViewHolder(parent, viewType);
    }

    @Override
    protected void convert(BaseViewHolder helper, UnitListItemByControlEntity item) {
        switch (helper.getItemViewType()) {
            case UnitListItemByControlEntity.TYPE_NO_EQU:
                if (helper instanceof UnitListItemByNoDataViewHolder) {
                    ((UnitListItemByNoDataViewHolder) helper).unitContentName.setText(item.getName());
                }
                break;
            case UnitListItemByControlEntity.TYPE_ITEM_EQU:
                if (helper instanceof UnitListItemByControlByEquViewHolder) {
                    ((UnitListItemByControlByEquViewHolder) helper).unitControlEquName.setText(item.getMap().get("Name").toString());
                    ((UnitListItemByControlByEquViewHolder) helper).unitControlEquMode.setText("模式:" + item.getMap().get("Mode").toString() + "");
                    ((UnitListItemByControlByEquViewHolder) helper).unitControlEquStatus.setText(item.getMap().get("Status").toString());
                    ((UnitListItemByControlByEquViewHolder) helper).unitControlEquTem.setText("机柜温度:" + item.getMap().get("Tempeture").toString() + "");
                }
                break;
            case UnitListItemByControlEntity.TYPE_ITEM_ST:
                if (helper instanceof UnitListItemByControlBySTViewHolder) {
                    switch (Integer.parseInt(item.getMap().get("Category").toString())) {
                        case EnumCategoryType.TYPE_Irrigation://灌溉
                            ((UnitListItemByControlBySTViewHolder) helper).unitContentIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_unit_control_irrigation));
                            break;
                        case EnumCategoryType.TYPE_Fan://风机
                            ((UnitListItemByControlBySTViewHolder) helper).unitContentIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_unit_control_fan));
                            break;
                        case EnumCategoryType.TYPE_Spray://雨帘
                            ((UnitListItemByControlBySTViewHolder) helper).unitContentIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_unit_control_spray));
                            break;
                        case EnumCategoryType.TYPE_Sunshade://遮阳
                            ((UnitListItemByControlBySTViewHolder) helper).unitContentIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_unit_control_sunshade));
                            break;
                        case EnumCategoryType.TYPE_Ventilate://通风
                            ((UnitListItemByControlBySTViewHolder) helper).unitContentIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_unit_control_ventilate));
                            break;
                        case EnumCategoryType.TYPE_Auxiliary://辅助
                            ((UnitListItemByControlBySTViewHolder) helper).unitContentIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_unit_control_auxiliary));
                            break;
                    }
                    ((UnitListItemByControlBySTViewHolder) helper).unitContentName.setText(item.getName());
                    ((UnitListItemByControlBySTViewHolder) helper).unitContentSubName.setText(item.getSubName());
                    switch (Integer.parseInt(item.getMap().get("Status").toString())) {
                        case EnumControlRelayStatus.TYPE_TurnOn://启动
                            ((UnitListItemByControlBySTViewHolder) helper).unitContentStart.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_unit_conitor_open_press));
                            ((UnitListItemByControlBySTViewHolder) helper).unitContentStop.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_unit_conitor_stop_nomal));
                            ((UnitListItemByControlBySTViewHolder) helper).unitContentStart.setClickable(false);//屏蔽点击事件
                            ((UnitListItemByControlBySTViewHolder) helper).unitContentStop.setClickable(true);//屏蔽点击事件
                            break;
                        case EnumControlRelayStatus.TYPE_TurnOff://停止
                            ((UnitListItemByControlBySTViewHolder) helper).unitContentStart.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_unit_conitor_open_nomal));
                            ((UnitListItemByControlBySTViewHolder) helper).unitContentStop.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_unit_conitor_stop_press));
                            ((UnitListItemByControlBySTViewHolder) helper).unitContentStart.setClickable(true);
                            ((UnitListItemByControlBySTViewHolder) helper).unitContentStop.setClickable(false);
                            break;
                        default://其他
                            ((UnitListItemByControlBySTViewHolder) helper).unitContentStart.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_unit_conitor_open_press));
                            ((UnitListItemByControlBySTViewHolder) helper).unitContentStop.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_unit_conitor_stop_press));
                            ((UnitListItemByControlBySTViewHolder) helper).unitContentStart.setClickable(false);
                            ((UnitListItemByControlBySTViewHolder) helper).unitContentStop.setClickable(false);
                            break;
                    }
                }
                break;
            case UnitListItemByControlEntity.TYPE_ITEM_PN:
                if (helper instanceof UnitListItemByControlByPNViewHolder) {
                    switch (Integer.parseInt(item.getMap().get("Category").toString())) {
                        case EnumCategoryType.TYPE_Irrigation://灌溉
                            ((UnitListItemByControlByPNViewHolder) helper).unitContentIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_unit_control_irrigation));
                            break;
                        case EnumCategoryType.TYPE_Fan://风机
                            ((UnitListItemByControlByPNViewHolder) helper).unitContentIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_unit_control_fan));
                            break;
                        case EnumCategoryType.TYPE_Spray://雨帘
                            ((UnitListItemByControlByPNViewHolder) helper).unitContentIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_unit_control_spray));
                            break;
                        case EnumCategoryType.TYPE_Sunshade://遮阳
                            ((UnitListItemByControlByPNViewHolder) helper).unitContentIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_unit_control_sunshade));
                            break;
                        case EnumCategoryType.TYPE_Ventilate://通风
                            ((UnitListItemByControlByPNViewHolder) helper).unitContentIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_unit_control_ventilate));
                            break;
                        case EnumCategoryType.TYPE_Auxiliary://辅助
                            ((UnitListItemByControlByPNViewHolder) helper).unitContentIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_unit_control_auxiliary));
                            break;
                    }
                    ((UnitListItemByControlByPNViewHolder) helper).unitContentName.setText(item.getName());
                    ((UnitListItemByControlByPNViewHolder) helper).unitContentSubName.setText(item.getSubName());
                    switch (Integer.parseInt(item.getMap().get("Status").toString())) {
                        case EnumControlRelayStatus.TYPE_TurnOff://停止
                            ((UnitListItemByControlByPNViewHolder) helper).unitContentOpen.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_unit_conitor_open_nomal));
                            ((UnitListItemByControlByPNViewHolder) helper).unitContentStop.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_unit_conitor_stop_press));
                            ((UnitListItemByControlByPNViewHolder) helper).unitContentClose.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_unit_conitor_close_nomal));
                            ((UnitListItemByControlByPNViewHolder) helper).unitContentOpen.setClickable(true);
                            ((UnitListItemByControlByPNViewHolder) helper).unitContentStop.setClickable(false);
                            ((UnitListItemByControlByPNViewHolder) helper).unitContentClose.setClickable(true);
                            break;
                        case EnumControlRelayStatus.TYPE_DirectTurnOn://正在展开
                            ((UnitListItemByControlByPNViewHolder) helper).unitContentOpen.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_unit_conitor_open_press));
                            ((UnitListItemByControlByPNViewHolder) helper).unitContentStop.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_unit_conitor_stop_nomal));
                            ((UnitListItemByControlByPNViewHolder) helper).unitContentClose.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_unit_conitor_close_nomal));
                            ((UnitListItemByControlByPNViewHolder) helper).unitContentOpen.setClickable(false);//屏蔽点击事件
                            ((UnitListItemByControlByPNViewHolder) helper).unitContentStop.setClickable(true);//屏蔽点击事件
                            ((UnitListItemByControlByPNViewHolder) helper).unitContentClose.setClickable(true);//屏蔽点击事件
                            break;
                        case EnumControlRelayStatus.TYPE_DirectOff://展开到头
                            ((UnitListItemByControlByPNViewHolder) helper).unitContentOpen.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_unit_conitor_open_press));
                            ((UnitListItemByControlByPNViewHolder) helper).unitContentStop.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_unit_conitor_stop_press));
                            ((UnitListItemByControlByPNViewHolder) helper).unitContentClose.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_unit_conitor_close_nomal));
                            ((UnitListItemByControlByPNViewHolder) helper).unitContentOpen.setClickable(false);
                            ((UnitListItemByControlByPNViewHolder) helper).unitContentStop.setClickable(false);
                            ((UnitListItemByControlByPNViewHolder) helper).unitContentClose.setClickable(true);
                            break;
                        case EnumControlRelayStatus.TYPE_ReverseTurnOn://正在收拢
                            ((UnitListItemByControlByPNViewHolder) helper).unitContentOpen.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_unit_conitor_open_nomal));
                            ((UnitListItemByControlByPNViewHolder) helper).unitContentStop.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_unit_conitor_stop_nomal));
                            ((UnitListItemByControlByPNViewHolder) helper).unitContentClose.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_unit_conitor_close_press));
                            ((UnitListItemByControlByPNViewHolder) helper).unitContentOpen.setClickable(true);
                            ((UnitListItemByControlByPNViewHolder) helper).unitContentStop.setClickable(true);
                            ((UnitListItemByControlByPNViewHolder) helper).unitContentClose.setClickable(false);
                            break;
                        case EnumControlRelayStatus.TYPE_ReverseTurnOff://收拢到头
                            ((UnitListItemByControlByPNViewHolder) helper).unitContentOpen.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_unit_conitor_open_nomal));
                            ((UnitListItemByControlByPNViewHolder) helper).unitContentStop.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_unit_conitor_stop_press));
                            ((UnitListItemByControlByPNViewHolder) helper).unitContentClose.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_unit_conitor_close_press));
                            ((UnitListItemByControlByPNViewHolder) helper).unitContentOpen.setClickable(true);
                            ((UnitListItemByControlByPNViewHolder) helper).unitContentStop.setClickable(false);
                            ((UnitListItemByControlByPNViewHolder) helper).unitContentClose.setClickable(false);
                            break;
                        default://其他
                            ((UnitListItemByControlByPNViewHolder) helper).unitContentOpen.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_unit_conitor_open_press));
                            ((UnitListItemByControlByPNViewHolder) helper).unitContentStop.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_unit_conitor_stop_press));
                            ((UnitListItemByControlByPNViewHolder) helper).unitContentClose.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_unit_conitor_close_press));
                            ((UnitListItemByControlByPNViewHolder) helper).unitContentOpen.setClickable(false);
                            ((UnitListItemByControlByPNViewHolder) helper).unitContentStop.setClickable(false);
                            ((UnitListItemByControlByPNViewHolder) helper).unitContentClose.setClickable(false);
                            break;
                    }
                }
                break;
            default:
                break;
        }
    }
}
