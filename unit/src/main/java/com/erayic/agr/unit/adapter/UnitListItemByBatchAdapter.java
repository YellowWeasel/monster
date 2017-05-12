package com.erayic.agr.unit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.unit.R;
import com.erayic.agr.unit.adapter.entity.UnitListItemByBatchEntity;
import com.erayic.agr.unit.adapter.holder.UnitListItemByBatchViewHolder;

import java.util.List;

import butterknife.BindView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class UnitListItemByBatchAdapter extends BaseMultiItemQuickAdapter<UnitListItemByBatchEntity, BaseViewHolder> {

    private Context context;

    public UnitListItemByBatchAdapter(Context context, List<UnitListItemByBatchEntity> data) {
        super(data);
        this.context = context;
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case UnitListItemByBatchEntity.TYPE_BATCH_CONTENT:
                return new UnitListItemByBatchViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_unit_list_item_by_batch, parent, false));
            case UnitListItemByBatchEntity.TYPE_BATCH_ADD:
                return new UnitListItemByBatchViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_unit_list_item_by_batch, parent, false));
            default:
                return super.onCreateDefViewHolder(parent, viewType);
        }

    }

    @Override
    protected void convert(BaseViewHolder helper, UnitListItemByBatchEntity item) {
        switch (helper.getItemViewType()) {
            case UnitListItemByBatchEntity.TYPE_BATCH_CONTENT:
                if (helper instanceof UnitListItemByBatchViewHolder) {
                    ((UnitListItemByBatchViewHolder) helper).unitContentName.setText(item.getName());
                    ((UnitListItemByBatchViewHolder) helper).unitContentSub.setText(item.getSubName());
                }
                break;
            case UnitListItemByBatchEntity.TYPE_BATCH_ADD:
                if (helper instanceof UnitListItemByBatchViewHolder) {
                    ((UnitListItemByBatchViewHolder) helper).unitContentName.setText(item.getName());
                    ((UnitListItemByBatchViewHolder) helper).unitContentSub.setText(item.getSubName());
                }
                break;
            default:
                break;
        }
    }
}
