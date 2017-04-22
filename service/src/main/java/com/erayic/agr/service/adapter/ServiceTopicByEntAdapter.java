package com.erayic.agr.service.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.erayic.agr.common.net.back.CommonSubServiceBean;
import com.erayic.agr.common.net.back.enums.EnumOrderType;
import com.erayic.agr.common.util.ErayicNetDate;
import com.erayic.agr.service.R;
import com.erayic.agr.service.adapter.holder.ServiceTopicByEntItemViewHolder;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ServiceTopicByEntAdapter extends BaseQuickAdapter<CommonSubServiceBean, ServiceTopicByEntItemViewHolder> {

    private Context context;

    public ServiceTopicByEntAdapter(Context context, List<CommonSubServiceBean> data) {
        super(R.layout.adapter_service_topic_ent_item, data);
        this.context = context;
    }

    @Override
    protected void convert(final ServiceTopicByEntItemViewHolder helper, final CommonSubServiceBean item) {
        helper.serviceTopicItemName.setText(item.getCrop());
        helper.serviceTopicItemCheck.setChecked(item.isCheck());

        if (item.getStatus() == EnumOrderType.ORDER_SUCCESS) {
            helper.serviceTopicItemBuy.setText("已购（" + ErayicNetDate.getStringDate(item.getEndTime()) + "到期)");
        } else {
            helper.serviceTopicItemBuy.setText("");
        }
    }
}
