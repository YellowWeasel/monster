package com.erayic.agr.unit.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.common.util.ErayicNetDate;
import com.erayic.agr.unit.R;
import com.erayic.agr.unit.adapter.entity.UnitListItemByBatchEntity;
import com.erayic.agr.unit.adapter.holder.UnitListItemByBatchAddViewHolder;
import com.erayic.agr.unit.adapter.holder.UnitListItemByBatchInfoViewHolder;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：批次列表
 */

public class UnitListItemByBatchAdapter extends BaseMultiItemQuickAdapter<UnitListItemByBatchEntity, BaseViewHolder> {

    private Context context;
    private OnItemBatchClickListener onItemBatchClickListener;
    private String unitID;

    public UnitListItemByBatchAdapter(Context context, List<UnitListItemByBatchEntity> data, String unitID) {
        super(data);
        this.context = context;
        this.unitID = unitID;

    }

    public void setOnItemBatchClickListener(OnItemBatchClickListener onItemBatchClickListener) {
        this.onItemBatchClickListener = onItemBatchClickListener;
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case UnitListItemByBatchEntity.TYPE_BATCH_CONTENT:
                return new UnitListItemByBatchInfoViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_unit_list_item_by_batch_info, parent, false));
            case UnitListItemByBatchEntity.TYPE_BATCH_ADD:
                return new UnitListItemByBatchAddViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_unit_list_item_by_batch_add, parent, false));
            default:
                return super.onCreateDefViewHolder(parent, viewType);
        }
    }

    @Override
    protected void convert(BaseViewHolder helper, final UnitListItemByBatchEntity item) {
        switch (helper.getItemViewType()) {
            case UnitListItemByBatchEntity.TYPE_BATCH_CONTENT:
                if (helper instanceof UnitListItemByBatchInfoViewHolder) {
                    Glide.with(context)
                            .load(item.getMap().get("Icon"))
                            .placeholder(R.drawable.app_base_default_plant)//待加载时显示
                            .error(R.drawable.app_base_default_plant)//加载错误时显示
                            .into(((UnitListItemByBatchInfoViewHolder) helper).unitContentIcon);
                    ((UnitListItemByBatchInfoViewHolder) helper).unitContentName.setText(item.getName());
                    ((UnitListItemByBatchInfoViewHolder) helper).unitContentSubName.setText(
                            Html.fromHtml("距离采摘还有<font color=red>" + ErayicNetDate.getStringDate(item.getMap().get("Mature")) + "</font>天")
                    );
                    ((UnitListItemByBatchInfoViewHolder) helper).unitContentDate.setText(ErayicNetDate.getStringDate(item.getMap().get("StartTime")));
                    ((UnitListItemByBatchInfoViewHolder) helper).itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onItemBatchClickListener != null)
                                onItemBatchClickListener.onBatchInfo(item.getMap().get("BatchID"),item.getName());
                        }
                    });
                    ((UnitListItemByBatchInfoViewHolder) helper).itemView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            return true;
                        }
                    });
                }
                break;
            case UnitListItemByBatchEntity.TYPE_BATCH_ADD:
                if (helper instanceof UnitListItemByBatchAddViewHolder) {
                    ((UnitListItemByBatchAddViewHolder) helper).unitContentIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.app_base_default_plant_add));
                    ((UnitListItemByBatchAddViewHolder) helper).unitContentName.setText("新建一个批次");
                    ((UnitListItemByBatchAddViewHolder) helper).unitContentSubName.setText("");
                    ((UnitListItemByBatchAddViewHolder) helper).itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onItemBatchClickListener != null)
                                onItemBatchClickListener.onAddBatch(unitID);
                        }
                    });
                    ((UnitListItemByBatchAddViewHolder) helper).itemView.setOnLongClickListener(new View.OnLongClickListener() {
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
    }

    public interface OnItemBatchClickListener {
        void onBatchInfo(String batchID,String batchName);

        void onAddBatch(String unitID);
    }
}
