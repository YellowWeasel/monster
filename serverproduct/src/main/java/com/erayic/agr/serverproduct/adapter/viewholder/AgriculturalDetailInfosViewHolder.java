package com.erayic.agr.serverproduct.adapter.viewholder;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.serverproduct.R;
import com.erayic.agr.serverproduct.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wxk on 2017/5/13.
 */

public class AgriculturalDetailInfosViewHolder extends BaseViewHolder {

    @BindView(R2.id.serverproduct_agricultural_detail_item_title_textview)
    public TextView serverproductAgriculturalDetailItemTitleTextview;
    @BindView(R2.id.serverproduct_agricultural_detail_item_source_textview)
    public TextView serverproductAgriculturalDetailItemSourceTextview;
    @BindView(R2.id.serverproduct_agricultural_detail_item_publishdate_textview)
    public TextView serverproductAgriculturalDetailItemPublishdateTextview;
    @BindView(R2.id.serverproduct_agricultural_detail_item_content_textview)
    public TextView serverproductAgriculturalDetailItemContentTextview;
    @BindView(R2.id.serverproduct_agricultural_detail_item_title_relativelayout)
    public RelativeLayout serverproductAgriculturalDetailItemTitleRelativelayout;

    public AgriculturalDetailInfosViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
