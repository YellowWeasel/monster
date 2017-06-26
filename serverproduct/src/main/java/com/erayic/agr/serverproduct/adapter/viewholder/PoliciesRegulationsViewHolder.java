package com.erayic.agr.serverproduct.adapter.viewholder;

import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.serverproduct.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wxk on 2017/5/13.
 */

public class PoliciesRegulationsViewHolder extends BaseViewHolder {


    @BindView(R2.id.serverproduct_polices_regulations_title_textview)
    public TextView serverproductPolicesRegulationsTitleTextview;
    @BindView(R2.id.serverproduct_polices_regulations_title_relativelayout)
    public RelativeLayout serverproductPolicesRegulationsTitleRelativelayout;

    public PoliciesRegulationsViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
