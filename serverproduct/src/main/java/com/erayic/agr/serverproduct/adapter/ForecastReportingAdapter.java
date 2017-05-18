package com.erayic.agr.serverproduct.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.erayic.agr.serverproduct.Constants;
import com.erayic.agr.serverproduct.R;
import com.erayic.agr.serverproduct.adapter.entity.FutureForecastDatas;

import java.text.SimpleDateFormat;

/**
 * Created by wxk on 2017/5/16.
 */

public class ForecastReportingAdapter extends BaseAdapter {
    FutureForecastDatas forecastDatas;
    Context context;

    public void setForecastDatas(FutureForecastDatas forecastDatas) {
        this.forecastDatas = forecastDatas;
    }

    public ForecastReportingAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return (forecastDatas != null) ? forecastDatas.getFeartureBeans().size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return forecastDatas.getFeartureBeans().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        TextView windTextView;
        TextView tempTextView;
        TextView dateTextView;
        TextView rainTextView;
        LinearLayout layout;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.forecast_reporting_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.rainTextView = (TextView) convertView.findViewById(R.id.serverproduct_reporting_rain_textview);
            viewHolder.windTextView = (TextView) convertView.findViewById(R.id.serverproduct_reporting_wind_textview);
            viewHolder.tempTextView = (TextView) convertView.findViewById(R.id.serverproduct_reporting_temp_textview);
            viewHolder.dateTextView = (TextView) convertView.findViewById(R.id.serverproduct_reporting_date_textview);
            viewHolder.layout= (LinearLayout) convertView.findViewById(R.id.serverproduct_reporting_linearlayout);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.layout.setBackgroundColor(Color.parseColor("#ffffff"));
        FutureForecastDatas.ForecastDatas forecastData = this.forecastDatas.getFeartureBeans().get(position);
        if (position%2==1){
            viewHolder.layout.setBackgroundColor(Color.parseColor("#efeff4"));
        }
        if (forecastData.getTemperature() == 0 && forecastData.getWindSpeed() == 0 && forecastData.getRain() == 0) {
            viewHolder.rainTextView.setText("—");
            viewHolder.windTextView.setText("—");
            viewHolder.tempTextView.setText("—");
        } else {
            viewHolder.rainTextView.setText(String.valueOf(forecastData.getRain()));
            viewHolder.windTextView.setText(getWindLvl(forecastData.getWindSpeed()));
            viewHolder.tempTextView.setText(String.valueOf(forecastData.getTemperature()));
        }
        viewHolder.dateTextView.setText(forecastData.getAppearTime());
        return convertView;
    }

    public String getWindLvl(double windSpeed) {
        for (int i = 0; i < Constants.WINDLVL.length; i++) {
            if (windSpeed <= Constants.WINDLVL[i]) {
                return String.valueOf(i);
            }
        }
        return "";
    }
}
