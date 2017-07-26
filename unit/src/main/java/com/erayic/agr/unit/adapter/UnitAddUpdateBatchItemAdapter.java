package com.erayic.agr.unit.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.unit.R;
import com.erayic.agr.unit.adapter.entity.UnitAddUpdateBatchItemEntity;
import com.erayic.agr.unit.adapter.holder.UnitAddUpdateBatchItemButtonViewHolder;
import com.erayic.agr.unit.adapter.holder.UnitAddUpdateBatchItemViewHolder;
import com.erayic.agr.unit.adapter.holder.UnitBatchDividerViewHolder;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class UnitAddUpdateBatchItemAdapter extends BaseMultiItemQuickAdapter<UnitAddUpdateBatchItemEntity, BaseViewHolder> {

    private Context context;
    private boolean isAdd;//true 增加 false 修改

    private OnBatchItemClickListener onBatchClickListener;

    public UnitAddUpdateBatchItemAdapter(Context context, boolean isAdd, List<UnitAddUpdateBatchItemEntity> data) {
        super(data);
        this.context = context;
        this.isAdd = isAdd;
    }

    public void setOnBatchClickListener(OnBatchItemClickListener onBatchClickListener) {
        this.onBatchClickListener = onBatchClickListener;
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case UnitAddUpdateBatchItemEntity.TYPE_DIVIDER:
                return new UnitBatchDividerViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_unit_batch_divider, parent, false));
            case UnitAddUpdateBatchItemEntity.TYPE_DELETE:
                return new UnitAddUpdateBatchItemButtonViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_unit_batch_item_by_button, parent, false));
            case UnitAddUpdateBatchItemEntity.TYPE_FINISH:
                return new UnitAddUpdateBatchItemButtonViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_unit_batch_item_by_button, parent, false));
            default:
                return new UnitAddUpdateBatchItemViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_unit_batch_item_by_add, parent, false));//同一种布局
        }
    }

    @Override
    protected void convert(final BaseViewHolder helper, final UnitAddUpdateBatchItemEntity item) {
        switch (helper.getItemViewType()) {
            case UnitAddUpdateBatchItemEntity.TYPE_PRODUCT://产品
                if (helper instanceof UnitAddUpdateBatchItemViewHolder) {
                    ((UnitAddUpdateBatchItemViewHolder) helper).unitContentName.setText(TextUtils.isEmpty(item.getName()) ? "产品" : item.getName());
                    ((UnitAddUpdateBatchItemViewHolder) helper).unitContentSubName.setText(TextUtils.isEmpty(item.getSubName()) ? "" : item.getSubName());
                }
                break;
            case UnitAddUpdateBatchItemEntity.TYPE_SEED://种苗
                if (helper instanceof UnitAddUpdateBatchItemViewHolder) {
                    ((UnitAddUpdateBatchItemViewHolder) helper).unitContentName.setText(TextUtils.isEmpty(item.getName()) ? "种苗" : item.getName());
                    ((UnitAddUpdateBatchItemViewHolder) helper).unitContentSubName.setText(TextUtils.isEmpty(item.getSubName()) ? "" : item.getSubName());
                }
                break;
            case UnitAddUpdateBatchItemEntity.TYPE_AREA://面积
                if (helper instanceof UnitAddUpdateBatchItemViewHolder) {
                    ((UnitAddUpdateBatchItemViewHolder) helper).unitContentName.setText(TextUtils.isEmpty(item.getName()) ? "种植面积" : item.getName());
                    ((UnitAddUpdateBatchItemViewHolder) helper).unitContentSubName.setText("" + (TextUtils.isEmpty(item.getSubName()) ? "" : item.getSubName()) + "(亩)");
                }
                break;
            case UnitAddUpdateBatchItemEntity.TYPE_DATE://时间
                if (helper instanceof UnitAddUpdateBatchItemViewHolder) {
                    ((UnitAddUpdateBatchItemViewHolder) helper).unitContentName.setText(TextUtils.isEmpty(item.getName()) ? "种植时间" : item.getName());
                    ((UnitAddUpdateBatchItemViewHolder) helper).unitContentSubName.setText(TextUtils.isEmpty(item.getSubName()) ? "" : item.getSubName());
                }
                break;
            case UnitAddUpdateBatchItemEntity.TYPE_PERSONNEL://种植人员
                if (helper instanceof UnitAddUpdateBatchItemViewHolder) {
                    ((UnitAddUpdateBatchItemViewHolder) helper).unitContentName.setText(TextUtils.isEmpty(item.getName()) ? "种植人员" : item.getName());
                    ((UnitAddUpdateBatchItemViewHolder) helper).unitContentSubName.setText(TextUtils.isEmpty(item.getSubName()) ? "" : item.getSubName());
                }
                break;
            case UnitAddUpdateBatchItemEntity.TYPE_DELETE:
                if (helper instanceof UnitAddUpdateBatchItemButtonViewHolder) {
                    ((UnitAddUpdateBatchItemButtonViewHolder) helper).manageContentButton.setText("删除批次");
                    ((UnitAddUpdateBatchItemButtonViewHolder) helper).manageContentButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onBatchClickListener != null)
                                onBatchClickListener.onClick(v, item, helper.getAdapterPosition());
                        }
                    });
                    ((UnitAddUpdateBatchItemButtonViewHolder) helper).manageContentButton.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            return true;
                        }
                    });
                }
                break;
            case UnitAddUpdateBatchItemEntity.TYPE_FINISH:
                if (helper instanceof UnitAddUpdateBatchItemButtonViewHolder) {
                    ((UnitAddUpdateBatchItemButtonViewHolder) helper).manageContentButton.setText("批次结束");
                    ((UnitAddUpdateBatchItemButtonViewHolder) helper).manageContentButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onBatchClickListener != null)
                                onBatchClickListener.onClick(v, item, helper.getAdapterPosition());
                        }
                    });
                    ((UnitAddUpdateBatchItemButtonViewHolder) helper).manageContentButton.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            return true;
                        }
                    });
                }
                break;
            default:
                break;
        }
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onBatchClickListener != null && item.getItemType() != UnitAddUpdateBatchItemEntity.TYPE_DELETE && item.getItemType() != UnitAddUpdateBatchItemEntity.TYPE_FINISH)
                    onBatchClickListener.onClick(v, item, helper.getAdapterPosition());
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
        void onClick(View view, UnitAddUpdateBatchItemEntity entity, int position);
    }
}
