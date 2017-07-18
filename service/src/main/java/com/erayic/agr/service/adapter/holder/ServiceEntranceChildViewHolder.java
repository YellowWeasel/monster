package com.erayic.agr.service.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.erayic.agr.service.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ServiceEntranceChildViewHolder extends RecyclerView.ViewHolder {

    @BindView(R2.id.service_entrance_item_child_icon)
    public ImageView serviceEntranceItemChildIcon;
    @BindView(R2.id.service_entrance_item_child_name)
    public TextView serviceEntranceItemChildName;
    @BindView(R2.id.service_entrance_item_child_layout)
    public LinearLayout serviceEntranceItemChildLayout;
    @BindView(R2.id.service_entrance_item_child_buy)
    public Button serviceEntranceItemChildBuy;

    public ServiceEntranceChildViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    /**
     * 显示隐藏ITEM
     */
    public void setVisibility(boolean isVisible) {
        RecyclerView.LayoutParams param = (RecyclerView.LayoutParams) itemView.getLayoutParams();
        if (isVisible) {
            param.height = LinearLayout.LayoutParams.WRAP_CONTENT;
            param.width = LinearLayout.LayoutParams.MATCH_PARENT;
            itemView.setVisibility(View.VISIBLE);
        } else {
            itemView.setVisibility(View.GONE);
            param.height = 0;
            param.width = 0;
        }
        itemView.setLayoutParams(param);
    }

}
