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
import com.erayic.agr.common.AgrConstant;
import com.erayic.agr.common.net.DataBack;
import com.erayic.agr.common.util.ErayicNetDate;
import com.erayic.agr.unit.R;
import com.erayic.agr.unit.adapter.entity.UnitListItemByBatchEntity;
import com.erayic.agr.unit.adapter.holder.UnitListItemByBatchAddViewHolder;
import com.erayic.agr.unit.adapter.holder.UnitListItemByBatchInfoViewHolder;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;

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
                            .apply(AgrConstant.iconOptions)
                            .into(((UnitListItemByBatchInfoViewHolder) helper).unitContentIcon);
                    ((UnitListItemByBatchInfoViewHolder) helper).unitContentName.setText(item.getName());
                    int day = new Period(new DateTime(), new DateTime(ErayicNetDate.getLongDates(item.getMap().get("Mature"))), PeriodType.days()).getDays();
                    if (day <= 0)
                        ((UnitListItemByBatchInfoViewHolder) helper).unitContentSubName.setText(
                                Html.fromHtml("<font color=red>已成熟</font>"));
                    else if (day > 1000)
                        ((UnitListItemByBatchInfoViewHolder) helper).unitContentSubName.setText("");
                    else
                        ((UnitListItemByBatchInfoViewHolder) helper).unitContentSubName.setText(
                                Html.fromHtml("距离采摘还有<font color=red>" + day + "</font>天"));
                    ((UnitListItemByBatchInfoViewHolder) helper).unitContentDate.setText(ErayicNetDate.getStringDate(item.getMap().get("StartTime")));
                    ((UnitListItemByBatchInfoViewHolder) helper).itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onItemBatchClickListener != null)
                                onItemBatchClickListener.onBatchInfo(unitID, item.getMap().get("BatchID"), item.getName(), item.getMap().get("Icon"));
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
        void onBatchInfo(String unitID, String batchID, String batchName, String imgUrl);

        void onAddBatch(String unitID);
    }
}
