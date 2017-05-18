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
import com.erayic.agr.serverproduct.adapter.entity.DynamicPricePrincipalMarketDatas;

/**
 * Created by wxk on 2017/5/13.
 */

public class DynamicPricesAdapter extends BaseAdapter {
    private DynamicPricePrincipalMarketDatas principalMarketDatasList;
    private Context context;

    public DynamicPricePrincipalMarketDatas getPrincipalMarketDatasList() {
        return principalMarketDatasList;
    }

    public void setPrincipalMarketDatasList(DynamicPricePrincipalMarketDatas principalMarketDatasList) {
        this.principalMarketDatasList = principalMarketDatasList;
    }

    public DynamicPricesAdapter(DynamicPricePrincipalMarketDatas principalMarketDatasList, Context context) {
        this.principalMarketDatasList = principalMarketDatasList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return (principalMarketDatasList==null||principalMarketDatasList.getMarketPriceDatasList()==null)?0:principalMarketDatasList.getMarketPriceDatasList().size();
    }

    @Override
    public Object getItem(int position) {
        return principalMarketDatasList.getMarketPriceDatasList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    class  ViewHolder{
        TextView marketNameTextView;
        TextView priceValueTextView;
        TextView dateTextView;
        LinearLayout serverproduct_dynamic_price_linearlayout;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.dynamic_prices_item,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.marketNameTextView= (TextView) convertView.findViewById(R.id.serverproduct_dynamic_price_item_market_textview);
            viewHolder.priceValueTextView= (TextView) convertView.findViewById(R.id.serverproduct_dynamic_price_item_pricevalue_textview);
            viewHolder.dateTextView= (TextView) convertView.findViewById(R.id.serverproduct_dynamic_price_item_date_textview);
            viewHolder.serverproduct_dynamic_price_linearlayout= (LinearLayout) convertView.findViewById(R.id.serverproduct_dynamic_price_linearlayout);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.serverproduct_dynamic_price_linearlayout.setBackgroundColor(Color.parseColor("#ffffff"));
        if (position%2==1){
            viewHolder.serverproduct_dynamic_price_linearlayout.setBackgroundColor(Color.parseColor("#efeff4"));
        }
        DynamicPricePrincipalMarketDatas.MarketPriceDatas marketPriceDatas = principalMarketDatasList.getMarketPriceDatasList().get(position);
        viewHolder.marketNameTextView.setText(marketPriceDatas.getMarketName());
        viewHolder.priceValueTextView.setText(marketPriceDatas.getValue());
        viewHolder.dateTextView.setText(marketPriceDatas.getDate());
        return convertView;
    }
}
