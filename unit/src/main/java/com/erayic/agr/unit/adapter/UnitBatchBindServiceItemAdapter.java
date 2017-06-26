package com.erayic.agr.unit.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.unit.R;
import com.erayic.agr.unit.adapter.entity.UnitBatchBindServiceEntity;
import com.erayic.agr.unit.adapter.holder.UnitAddBatchItemViewHolder;
import com.erayic.agr.unit.adapter.holder.UnitBatchBindServiceItemViewHolder;
import com.erayic.agr.unit.adapter.holder.UnitBatchInfoByTitleViewHolder;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class UnitBatchBindServiceItemAdapter extends BaseMultiItemQuickAdapter<UnitBatchBindServiceEntity, BaseViewHolder> {

    private Context context;
    private OnItemClickListener onItemClickListener;

    public UnitBatchBindServiceItemAdapter(Context context, List<UnitBatchBindServiceEntity> data) {
        super(data);
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case UnitBatchBindServiceEntity.TYPE_SERVICE:
                return new UnitBatchBindServiceItemViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_unit_batch_bind_service, parent, false));
            case UnitBatchBindServiceEntity.TYPE_MORE:
                return new UnitBatchBindServiceItemViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_unit_batch_bind_service, parent, false));//同一种布局
        }
        return super.onCreateDefViewHolder(parent, viewType);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final UnitBatchBindServiceEntity item) {
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null)
                    onItemClickListener.onClick(v, helper.getItemViewType(), item.getServiceID());
            }
        });
        helper.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });

        switch (helper.getItemViewType()) {
            case UnitBatchBindServiceEntity.TYPE_SERVICE:
                if (helper instanceof UnitBatchBindServiceItemViewHolder) {
                    ((UnitBatchBindServiceItemViewHolder) helper).unitContentName.setText(TextUtils.isEmpty(item.getName()) ? "未命名" : item.getName());
                    ((UnitBatchBindServiceItemViewHolder) helper).unitContentSubName.setText(TextUtils.isEmpty(item.getSubName()) ? "" : item.getSubName());
                    ((UnitBatchBindServiceItemViewHolder) helper).unitContentGoto.setVisibility(View.INVISIBLE);
                }
                break;
            case UnitBatchBindServiceEntity.TYPE_MORE:
                if (helper instanceof UnitBatchBindServiceItemViewHolder) {
                    ((UnitBatchBindServiceItemViewHolder) helper).unitContentIcon.setVisibility(View.INVISIBLE);
                    ((UnitBatchBindServiceItemViewHolder) helper).unitContentName.setText(TextUtils.isEmpty(item.getName()) ? "" : item.getName());
                    ((UnitBatchBindServiceItemViewHolder) helper).unitContentSubName.setText(TextUtils.isEmpty(item.getSubName()) ? "" : item.getSubName());
                    ((UnitBatchBindServiceItemViewHolder) helper).unitContentGoto.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    public interface OnItemClickListener {
        void onClick(View v, int type, String serviceID);
    }
}
