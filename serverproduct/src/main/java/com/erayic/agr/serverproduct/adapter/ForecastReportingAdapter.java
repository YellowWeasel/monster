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
import com.erayic.agr.serverproduct.adapter.entity.EnvironmentParamterDatas;

import java.util.List;

/**
 * Created by wxk on 2017/5/16.
 */

public class ForecastReportingAdapter extends BaseAdapter {
    EnvironmentParamterDatas.FeatureWeather featureWeather;
    Context context;

    public void setFeatureWeather(EnvironmentParamterDatas.FeatureWeather featureWeather) {
        this.featureWeather = featureWeather;
    }

    public ForecastReportingAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        //获取最大长度
        int maxSize=0;
        if (featureWeather == null)return  maxSize;
        if (featureWeather.getTmpForecastWeathers().size()>maxSize)maxSize=featureWeather.getTmpForecastWeathers().size();
        if (featureWeather.getWindForecastWeathers().size()>maxSize)maxSize=featureWeather.getWindForecastWeathers().size();
        if (featureWeather.getRainForecastWeathers().size()>maxSize)maxSize=featureWeather.getRainForecastWeathers().size();

        return  maxSize;
    }

    @Override
    public Object getItem(int position) {
        List<EnvironmentParamterDatas.WeatherMap> weatherMaps;
        if (featureWeather == null)return  null;
        weatherMaps=(featureWeather.getTmpForecastWeathers().size()>featureWeather.getWindForecastWeathers().size())
                ?featureWeather.getTmpForecastWeathers():featureWeather.getWindForecastWeathers();
        weatherMaps=(weatherMaps.size()>featureWeather.getRainForecastWeathers().size())?weatherMaps:featureWeather.getRainForecastWeathers();

        return weatherMaps.get(position);
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

        if (position%2==1){
            viewHolder.layout.setBackgroundColor(Color.parseColor("#efeff4"));
        }

            if (featureWeather.getRainForecastWeathers().size()>position){
                viewHolder.rainTextView.setText(String.valueOf(featureWeather.getRainForecastWeathers().get(position).getValue()));
            }else{
                viewHolder.rainTextView.setText("—");
            }
            if (featureWeather.getWindForecastWeathers().size()>position){
                viewHolder.windTextView.setText(getWindLvl(featureWeather.getWindForecastWeathers().get(position).getValue()));
            }else{
                viewHolder.windTextView.setText("—");
            }
            if (featureWeather.getTmpForecastWeathers().size()>position){
                viewHolder.tempTextView.setText(String.valueOf(featureWeather.getTmpForecastWeathers().get(position).getValue()));
            }else{
                viewHolder.tempTextView.setText("—");
            }

        if (featureWeather.getDateList().size()>position){
            viewHolder.dateTextView.setText(featureWeather.getDateList().get(position));
        }else{
            viewHolder.dateTextView.setText("—");
        }
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
