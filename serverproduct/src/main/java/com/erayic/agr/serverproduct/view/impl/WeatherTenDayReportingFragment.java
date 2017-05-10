package com.erayic.agr.serverproduct.view.impl;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.erayic.agr.common.base.BaseFragment;
import com.erayic.agr.serverproduct.Constants;
import com.erayic.agr.serverproduct.R;
import com.erayic.agr.serverproduct.R2;
import com.erayic.agr.serverproduct.adapter.entity.WeatherTendayReportingData;

import butterknife.BindView;

/**
 * Created by wxk on 2017/5/9.
 */

@Route(path = "/serverproduct/fragment/WeatherTenDayReportingFragment", name = "旬报")
public class WeatherTenDayReportingFragment extends BaseFragment {
    @Autowired
    WeatherTendayReportingData reportingData;
    @BindView(R2.id.serverproduct_tendayreporting_images_listview)
    ListView reportingPics;
    @BindView(R2.id.serverproduct_tendayreporting_title_textview)
    TextView titleTextView;
    @BindView(R2.id.serverproduct_tendayreporting_content_textview)
    TextView contentTextView;

    @Override
    protected int getLayoutId() {

        return R.layout.fragment_weather_ten_day;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        if (reportingData==null)return;
        titleTextView.setText(reportingData.getTitle());
        contentTextView.setText("[摘要]\n\t\t\t" + Html.fromHtml("<b><tt>" + reportingData.getSummaryTxt() + "</tt></b>")
                + "\n\n" + reportingData.getConentTxt());
        SpannableStringBuilder builder = new SpannableStringBuilder(contentTextView.getText());
        ForegroundColorSpan blackSpan = new ForegroundColorSpan(Color.parseColor("#000000"));
        builder.setSpan(blackSpan, 0, ("[摘要]\n\t\t\t" + Html.fromHtml("<b><tt>" + reportingData.getSummaryTxt() + "</tt></b>")).length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        contentTextView.setText(builder);
        reportingPics.setAdapter(new PicAdapter());
    }

    public class PicAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return (reportingData == null) ? 0
                    : reportingData.
                    getReportImg().size();
        }

        @Override
        public Object getItem(int position) {
            return reportingData.getReportImg().get(position).getImgUrl();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public class ViewHolder {
            TextView textView;
            ImageView reportingView;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.weather_reporting_item, parent, false);
                viewHolder.reportingView = (ImageView) convertView.findViewById(R.id.serverproduct_tendayreporting_image_imageview);
                viewHolder.textView = (TextView) convertView.findViewById(R.id.serverproduct_tendayreporting_imagetitle_textview);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            String url = Constants.REPORTINGIMGURL + reportingData.getReportImg()
                    .get(position).getImgUrl();
            Glide.with(getActivity())
                    .load(url)
                    .into(viewHolder.reportingView);
            viewHolder.textView.setText(reportingData.getReportImg().get(position).getImgTitle());
            return convertView;
        }
    }
}
