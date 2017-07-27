package com.erayic.agr.other_1.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.other_1.R;
import com.erayic.agr.other_1.adapter.holder.CommandHolder;
import com.erayic.agr.other_1.adapter.holder.HalvigHolder;
import com.erayic.agr.other_1.adapter.holder.MonitoringHolder;
import com.erayic.agr.other_1.adapter.holder.PatternHolder;
import com.erayic.agr.other_1.adapter.holder.TempHolder;
import com.erayic.agr.other_1.entity.MonitoringData;

import java.util.List;

/**
 * 作者:LiJiang
 * 邮箱:1755036940@qq.com
 * 作用:远程管理适配器
 */

public class DeviceInfoItemAdapter extends BaseMultiItemQuickAdapter<MonitoringData, BaseViewHolder> {

    public Context mContext;
    public OndeviceInfoItem mOndeviceInfoItem;

    public DeviceInfoItemAdapter(Context context, List<MonitoringData> data) {
        super(data);
        this.mContext = context;
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {

            case MonitoringData.TYPE_ONE:
                return new HalvigHolder(mLayoutInflater.inflate(R.layout.activity_other_halvig, parent, false));
            case MonitoringData.TYPE_TWO:
                return new MonitoringHolder(mLayoutInflater.inflate(R.layout.adapter_other_monitoring, parent, false));
            case MonitoringData.TYPE_THREE:
                return new PatternHolder(mLayoutInflater.inflate(R.layout.adapter_other_pattern, parent, false));
            case MonitoringData.TYPE_FOUR:
                return new TempHolder(mLayoutInflater.inflate(R.layout.adapter_other_temp, parent, false));
            case MonitoringData.TYPE_FIVE:
                return new HalvigHolder(mLayoutInflater.inflate(R.layout.activity_other_halvi,parent,false));
            case MonitoringData.TYPE_SIX:
                return new CommandHolder(mLayoutInflater.inflate(R.layout.adapter_other_command,parent,false));
        }
        return super.onCreateDefViewHolder(parent, viewType);
    }

    @Override
    protected void convert(BaseViewHolder helper, final MonitoringData item) {
        switch (helper.getItemViewType()) {
            /*分割线*/
            case MonitoringData.TYPE_ONE:
                if (helper instanceof HalvigHolder) {

                }
            case MonitoringData.TYPE_TWO:
                if (helper instanceof MonitoringHolder) {
                    ((MonitoringHolder) helper).mTextview.setText(item.getTheMoment());
                    ((MonitoringHolder)helper).itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mOndeviceInfoItem!=null){
                                mOndeviceInfoItem.onClick(v,item.getUri());
                            }
                        }
                    });
                }
            case MonitoringData.TYPE_THREE:
                if (helper instanceof PatternHolder) {
                    ((PatternHolder) helper).mTextViewname.setText(item.getOperation());
                    ((PatternHolder) helper).mTextViewfuname.setText(item.getMotion());
                    ((PatternHolder)helper).itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mOndeviceInfoItem!=null){
                                mOndeviceInfoItem.onClick(v,item.getUri());
                            }
                        }
                    });
                }

                case MonitoringData.TYPE_FOUR:
                    if (helper instanceof TempHolder){
                        ((TempHolder)helper).mTextViewfacility.setText(item.getMonitoring());
                        ((TempHolder)helper).mTextViewTemp.setText(item.getTemp());
                    }
                    case MonitoringData.TYPE_SIX:
                        if (helper instanceof CommandHolder){
                            ((CommandHolder)helper).mButton.setText(item.getReboot());

                        }

        }
    }
    public interface  OndeviceInfoItem{
        void onClick(View view,String uri);
    }
    public void  setmOndeviceInfoItem(DeviceInfoItemAdapter.OndeviceInfoItem ondeviceInfoItem){
        this.mOndeviceInfoItem=ondeviceInfoItem;
    }
}
