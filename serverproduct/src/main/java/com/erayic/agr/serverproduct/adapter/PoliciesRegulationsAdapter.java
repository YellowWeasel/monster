package com.erayic.agr.serverproduct.adapter;

import android.graphics.Rect;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.erayic.agr.serverproduct.R;
import com.erayic.agr.serverproduct.adapter.entity.PoliciesRegulationsTitleDatas;
import com.erayic.agr.serverproduct.adapter.viewholder.PoliciesRegulationsViewHolder;
import com.erayic.agr.serverproduct.view.custom.CustomOnclickListener;

import java.util.List;

import butterknife.BindView;

/**
 * Created by wxk on 2017/5/12.
 */

public class PoliciesRegulationsAdapter extends BaseQuickAdapter<PoliciesRegulationsTitleDatas, PoliciesRegulationsViewHolder> {
    PoliciesRegulationsItemClickListener regulationsItemClickListener;

    public PoliciesRegulationsAdapter(List<PoliciesRegulationsTitleDatas> data) {
        super(R.layout.policies_regulation_item, data);
    }

    public PoliciesRegulationsItemClickListener getRegulationsItemClickListener() {
        return regulationsItemClickListener;
    }

    public void setRegulationsItemClickListener(PoliciesRegulationsItemClickListener regulationsItemClickListener) {
        this.regulationsItemClickListener = regulationsItemClickListener;
    }

    @Override
    protected void convert(final PoliciesRegulationsViewHolder helper,final PoliciesRegulationsTitleDatas item) {
          helper.serverproductPolicesRegulationsTitleTextview.setText(item.getTitle());

          helper.serverproductPolicesRegulationsTitleRelativelayout.setOnClickListener(new CustomOnclickListener() {
              @Override
              protected void doWorkOnClick() {
                  regulationsItemClickListener.doOnClick( helper.serverproductPolicesRegulationsTitleRelativelayout,
                          item.getId());
              }
          });
    }
    public static interface PoliciesRegulationsItemClickListener{
        void doOnClick(View view,int Id);
    }
}
