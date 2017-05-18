package com.erayic.agr.serverproduct.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.erayic.agr.serverproduct.R;
import com.erayic.agr.serverproduct.adapter.entity.DesignatedMarketDatas;
import com.erayic.agr.serverproduct.adapter.entity.DynamicPricePrincipalMarketDatas;
import com.erayic.agr.serverproduct.adapter.entity.MarketDynamicPriceDatas;

import java.util.List;

/**
 * Created by wxk on 2017/5/13.
 */

public class DynamicPricesDetailAdapter extends BaseAdapter {
    private MarketDynamicPriceDatas marketDynamicPriceDatas;
    private Context context;

    public MarketDynamicPriceDatas getMarketDynamicPriceDatas() {
        return marketDynamicPriceDatas;
    }

    public void setMarketDynamicPriceDatas(MarketDynamicPriceDatas marketDynamicPriceDatas) {
        this.marketDynamicPriceDatas = marketDynamicPriceDatas;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public DynamicPricesDetailAdapter(MarketDynamicPriceDatas mMarketDynamicPriceDatas, Context context) {
        this.marketDynamicPriceDatas = mMarketDynamicPriceDatas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return (marketDynamicPriceDatas!=null&&marketDynamicPriceDatas.getPriceDatasList()!=null)?marketDynamicPriceDatas.getPriceDatasList().size():0;
    }

    @Override
    public Object getItem(int position) {
        return marketDynamicPriceDatas.getPriceDatasList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    class  ViewHolder{
        TextView priceValueTextView;
        TextView dateTextView;
        LinearLayout serverproduct_dynamic_price_detail_linearlayout;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.dynamic_prices_detail_item,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.priceValueTextView= (TextView) convertView.findViewById(R.id.serverproduct_dynamic_price_detail_item_pricevalue_textview);
            viewHolder.dateTextView= (TextView) convertView.findViewById(R.id.serverproduct_dynamic_price_detail_item_date_textview);
            viewHolder.serverproduct_dynamic_price_detail_linearlayout= (LinearLayout) convertView.findViewById(R.id.serverproduct_dynamic_price_detail_item_linearlayout);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.serverproduct_dynamic_price_detail_linearlayout.setBackgroundColor(Color.parseColor("#ffffff"));
        if (position%2==1){
            viewHolder.serverproduct_dynamic_price_detail_linearlayout.setBackgroundColor(Color.parseColor("#efeff4"));
        }
        MarketDynamicPriceDatas.priceDatas priceDatas = marketDynamicPriceDatas.getPriceDatasList().get(position);
        viewHolder.priceValueTextView.setText(priceDatas.getValue());
        viewHolder.dateTextView.setText(priceDatas.getKey());
        return convertView;
    }
}
