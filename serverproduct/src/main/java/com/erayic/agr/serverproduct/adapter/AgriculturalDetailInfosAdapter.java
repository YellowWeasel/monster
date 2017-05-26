package com.erayic.agr.serverproduct.adapter;

import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.erayic.agr.serverproduct.R;
import com.erayic.agr.serverproduct.adapter.entity.AgriculturalDetailInfoDatas;
import com.erayic.agr.serverproduct.adapter.viewholder.AgriculturalDetailInfosViewHolder;
import com.erayic.agr.serverproduct.adapter.viewholder.AgriculturalInfosViewHolder;

import java.util.List;

import butterknife.BindView;

/**
 * Created by wxk on 2017/5/12.
 */

public class AgriculturalDetailInfosAdapter extends BaseQuickAdapter<AgriculturalDetailInfoDatas, AgriculturalDetailInfosViewHolder> {
    public AgriculturalDetailInfosAdapter(List<AgriculturalDetailInfoDatas> data) {
        super(R.layout.agricultural_detail_info_item, data);
    }

    @Override
    protected void convert(final AgriculturalDetailInfosViewHolder helper, final AgriculturalDetailInfoDatas item) {
        helper.serverproductAgriculturalDetailItemTitleTextview.setText(item.getTitle());
        helper.serverproductAgriculturalDetailItemSourceTextview.setText(item.getInfoSource());
        helper.serverproductAgriculturalDetailItemPublishdateTextview.setText(item.getPublishTime());
        helper.serverproductAgriculturalDetailItemContentTextview.setText(item.getTxtContent());
    }
}
