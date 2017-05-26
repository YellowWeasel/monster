package com.erayic.agr.serverproduct.adapter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.erayic.agr.serverproduct.R;
import com.erayic.agr.serverproduct.adapter.entity.AgriculturalInfoDatas;
import com.erayic.agr.serverproduct.adapter.viewholder.AgriculturalInfosViewHolder;
import com.erayic.agr.serverproduct.adapter.viewholder.PoliciesRegulationsViewHolder;
import com.erayic.agr.serverproduct.view.custom.CustomOnclickListener;

import java.util.List;

import butterknife.BindView;

/**
 * Created by wxk on 2017/5/12.
 */

public class AgriculturalInfosAdapter extends BaseQuickAdapter<AgriculturalInfoDatas, AgriculturalInfosViewHolder> {
    private AgriculturalItemOnclickListener itemOnclickListener;

    public void setItemOnclickListener(AgriculturalItemOnclickListener itemOnclickListener) {
        this.itemOnclickListener = itemOnclickListener;
    }

    public AgriculturalInfosAdapter(List<AgriculturalInfoDatas> data) {
        super(R.layout.agricultural_info_item, data);
    }

    @Override
    protected void convert(final AgriculturalInfosViewHolder helper, final AgriculturalInfoDatas item) {
            helper.serverproductAgriculturalInfoTitleTextview.setText(item.getTitle());
            helper.serverproductAgriculturalInfoTitleRelativelayout.setOnClickListener(new CustomOnclickListener() {
                @Override
                protected void doWorkOnClick() {
                    itemOnclickListener.doItemOnclick(item, helper.serverproductAgriculturalInfoTitleRelativelayout);
                }
            });
    }

    public static  interface   AgriculturalItemOnclickListener{
        void doItemOnclick(AgriculturalInfoDatas item,View view);
    }
}
