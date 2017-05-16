package com.erayic.agr.unit.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
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

    public UnitListItemByBatchAdapter(Context context, List<UnitListItemByBatchEntity> data) {
        super(data);
        this.context = context;
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
    protected void convert(BaseViewHolder helper, UnitListItemByBatchEntity item) {
        switch (helper.getItemViewType()) {
            case UnitListItemByBatchEntity.TYPE_BATCH_CONTENT:
                if (helper instanceof UnitListItemByBatchInfoViewHolder) {
                    ((UnitListItemByBatchInfoViewHolder) helper).unitContentName.setText("上海青");
                    ((UnitListItemByBatchInfoViewHolder) helper).unitContentSubName.setText(
                            Html.fromHtml("距离采摘还有<font color=red>" + 88 + "</font>天")
                    );
                    ((UnitListItemByBatchInfoViewHolder) helper).unitContentDate.setText("2017-01-01");
                }
                break;
            case UnitListItemByBatchEntity.TYPE_BATCH_ADD:
                if (helper instanceof UnitListItemByBatchAddViewHolder) {
                    ((UnitListItemByBatchAddViewHolder) helper).unitContentName.setText("新建一个批次");
                    ((UnitListItemByBatchAddViewHolder) helper).unitContentSubName.setText("");
                }
                break;
            default:
                break;
        }
    }
}
