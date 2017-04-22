package com.erayic.agr.service.adapter.holder;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TableRow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.service.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ServiceTopicByEntItemViewHolder extends BaseViewHolder {


    @BindView(R2.id.service_topic_item_name)
    public TextView serviceTopicItemName;
    @BindView(R2.id.service_topic_item_buy)
    public TextView serviceTopicItemBuy;
    @BindView(R2.id.service_topic_item_check)
    public CheckBox serviceTopicItemCheck;
    @BindView(R2.id.service_topic_item_layout)
    public TableRow serviceTopicItemLayout;


    public ServiceTopicByEntItemViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
