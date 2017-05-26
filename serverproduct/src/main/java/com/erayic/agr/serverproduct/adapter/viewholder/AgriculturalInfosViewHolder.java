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

public class AgriculturalInfosViewHolder extends BaseViewHolder {

    @BindView(R2.id.serverproduct_agricultural_info_title_textview)
    public TextView serverproductAgriculturalInfoTitleTextview;
    @BindView(R2.id.serverproduct_agricultural_info_title_relativelayout)
    public RelativeLayout serverproductAgriculturalInfoTitleRelativelayout;

    public AgriculturalInfosViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
