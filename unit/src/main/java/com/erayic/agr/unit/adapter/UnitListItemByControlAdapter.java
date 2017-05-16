package com.erayic.agr.unit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.unit.R;
import com.erayic.agr.unit.adapter.entity.UnitListItemByControlEntity;
import com.erayic.agr.unit.adapter.holder.UnitListItemByControlByEquViewHolder;

import java.util.List;

import butterknife.BindView;

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
                return null;
            case UnitListItemByControlEntity.TYPE_ITEM_PN:
                return null;
            case UnitListItemByControlEntity.TYPE_NO_EQU:
                return null;
        }
        return super.onCreateDefViewHolder(parent, viewType);
    }

    @Override
    protected void convert(BaseViewHolder helper, UnitListItemByControlEntity item) {

    }
}
