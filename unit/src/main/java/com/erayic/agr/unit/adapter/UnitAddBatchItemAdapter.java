package com.erayic.agr.unit.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.unit.R;
import com.erayic.agr.unit.adapter.entity.UnitAddBatchItemEntity;
import com.erayic.agr.unit.adapter.holder.UnitAddBatchItemViewHolder;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class UnitAddBatchItemAdapter extends BaseMultiItemQuickAdapter<UnitAddBatchItemEntity, BaseViewHolder> {

    private Context context;

    private OnBatchItemClickListener onBatchClickListener;

    public UnitAddBatchItemAdapter(Context context, List<UnitAddBatchItemEntity> data) {
        super(data);
        this.context = context;
    }

    public void setOnBatchClickListener(OnBatchItemClickListener onBatchClickListener) {
        this.onBatchClickListener = onBatchClickListener;
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            default:
                return new UnitAddBatchItemViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_unit_batch_item_by_add, parent, false));//同一种布局
        }
    }

    @Override
    protected void convert(final BaseViewHolder helper, UnitAddBatchItemEntity item) {
        switch (helper.getItemViewType()) {
            case UnitAddBatchItemEntity.TYPE_PRODUCT://产品
                if (helper instanceof UnitAddBatchItemViewHolder) {
                    ((UnitAddBatchItemViewHolder) helper).unitContentName.setText(TextUtils.isEmpty(item.getName()) ? "产品" : item.getName());
                    ((UnitAddBatchItemViewHolder) helper).unitContentSubName.setText(TextUtils.isEmpty(item.getSubName()) ? "" : item.getSubName());
                }
                break;
            case UnitAddBatchItemEntity.TYPE_SEED://种苗
                if (helper instanceof UnitAddBatchItemViewHolder) {
                    ((UnitAddBatchItemViewHolder) helper).unitContentName.setText(TextUtils.isEmpty(item.getName()) ? "种苗" : item.getName());
                    ((UnitAddBatchItemViewHolder) helper).unitContentSubName.setText(TextUtils.isEmpty(item.getSubName()) ? "" : item.getSubName());
                }
                break;
            case UnitAddBatchItemEntity.TYPE_AREA://面积
                if (helper instanceof UnitAddBatchItemViewHolder) {
                    ((UnitAddBatchItemViewHolder) helper).unitContentName.setText(TextUtils.isEmpty(item.getName()) ? "种植面积" : item.getName());
                    ((UnitAddBatchItemViewHolder) helper).unitContentSubName.setText(TextUtils.isEmpty(item.getSubName()) ? "" : item.getSubName());
                }
                break;
            case UnitAddBatchItemEntity.TYPE_DATE://时间
                if (helper instanceof UnitAddBatchItemViewHolder) {
                    ((UnitAddBatchItemViewHolder) helper).unitContentName.setText(TextUtils.isEmpty(item.getName()) ? "种植时间" : item.getName());
                    ((UnitAddBatchItemViewHolder) helper).unitContentSubName.setText("" + (TextUtils.isEmpty(item.getSubName()) ? "" : item.getSubName()) + "(亩)");
                }
                break;
            case UnitAddBatchItemEntity.TYPE_PERSONNEL://种植人员
                if (helper instanceof UnitAddBatchItemViewHolder) {
                    ((UnitAddBatchItemViewHolder) helper).unitContentName.setText(TextUtils.isEmpty(item.getName()) ? "种植人员" : item.getName());
                    ((UnitAddBatchItemViewHolder) helper).unitContentSubName.setText(TextUtils.isEmpty(item.getSubName()) ? "" : item.getSubName());
                }
                break;
            default:
                break;
        }
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onBatchClickListener != null)
                    onBatchClickListener.onClick(v, helper.getItemViewType(), helper.getAdapterPosition());
            }
        });
        helper.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
    }

    public interface OnBatchItemClickListener {
        void onClick(View view, int itemType, int position);
    }
}
