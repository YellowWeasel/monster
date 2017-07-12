package com.erayic.agr.service.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.erayic.agr.common.AgrConstant;
import com.erayic.agr.common.net.back.ServiceBuyByUserBean;
import com.erayic.agr.common.net.back.enums.EnumServiceType;
import com.erayic.agr.common.util.ErayicLog;
import com.erayic.agr.common.view.SectionedRecyclerViewAdapter;
import com.erayic.agr.service.R;
import com.erayic.agr.service.adapter.holder.ServiceManageChildViewHolder;
import com.erayic.agr.service.adapter.holder.ServiceManageGroupViewHolder;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ServiceManageAdapter extends SectionedRecyclerViewAdapter<ServiceManageGroupViewHolder, ServiceManageChildViewHolder, RecyclerView.ViewHolder> {

    private Context context;
    private OnSwitchStatueListener onSwitchStatueListener;
    private List<ServiceBuyByUserBean> list;
    private SparseBooleanArray mBooleanMap;

    public ServiceManageAdapter(Context context) {
        this.context = context;
        mBooleanMap = new SparseBooleanArray();
    }

    public void setList(List<ServiceBuyByUserBean> list) {
        this.list = list;
    }

    public List<ServiceBuyByUserBean> getList() {
        return list;
    }

    public void setOnSwitchStatueListener(OnSwitchStatueListener onSwitchStatueListener) {
        this.onSwitchStatueListener = onSwitchStatueListener;
    }

    @Override
    protected int getSectionCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    protected int getItemCountForSection(int section) {
        int count = list.get(section).getSpecifys() == null ? 0 : list.get(section).getSpecifys().size();
        if (count >= 0 && !mBooleanMap.get(section)) {
            count = 0;
        }
        return list.get(section).getSpecifys() == null ? 0 : count;
    }

    @Override
    protected boolean hasFooterInSection(int section) {
        return false;
    }

    @Override
    protected ServiceManageGroupViewHolder onCreateSectionHeaderViewHolder(ViewGroup parent, int viewType) {
        return new ServiceManageGroupViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_service_manage_group, parent, false));
    }

    @Override
    protected RecyclerView.ViewHolder onCreateSectionFooterViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected ServiceManageChildViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        return new ServiceManageChildViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_service_manage_child, parent, false));
    }

    @Override
    protected void onBindSectionHeaderViewHolder(ServiceManageGroupViewHolder holder, final int section) {
        Glide.with(context).load(AgrConstant.IMAGE_URL_PREFIX + list.get(section).getIcon()).apply(AgrConstant.iconOptions).into(holder.serviceManageItemIcon);
        holder.serviceManageItemName.setText(list.get(section).getServiceName());
        if (list.get(section).getType() == EnumServiceType.Subject) {
            holder.serviceManageItemSub.setVisibility(View.VISIBLE);
            holder.serviceManageItemSwitch.setVisibility(View.GONE);
        } else {
            holder.serviceManageItemSub.setVisibility(View.GONE);
            holder.serviceManageItemSwitch.setVisibility(View.VISIBLE);
            holder.serviceManageItemSwitch.setCheckedImmediately(list.get(section).isOwner());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(section).getType() == EnumServiceType.Subject) {
                    boolean isOpen = mBooleanMap.get(section);
                    mBooleanMap.put(section, !isOpen);
                    notifyDataSetChanged();
                } else {
                    onSwitchStatueListener.switchChecked(false, list.get(section).getServiceID(), null, section, -1, !list.get(section).isOwner());
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
    }

    @Override
    protected void onBindSectionFooterViewHolder(RecyclerView.ViewHolder holder, int section) {

    }

    @Override
    protected void onBindItemViewHolder(ServiceManageChildViewHolder holder, final int section, final int position) {
        holder.serviceManageItemChildName.setText(list.get(section).getSpecifys().get(position).getSepcify());
        holder.serviceManageItemChildIcon.setVisibility(View.INVISIBLE);
        holder.serviceManageItemChildSwitch.setCheckedImmediately(list.get(section).getSpecifys().get(position).isOwner());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSwitchStatueListener.switchChecked(true, list.get(section).getServiceID(), list.get(section).getSpecifys().get(position).getServiceID(), section, position,
                        !list.get(section).getSpecifys().get(position).isOwner());
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
    }

    public interface OnSwitchStatueListener {
        void switchChecked(boolean isSub, String serviceID, String subServiceID, int section, int position, boolean isChecked);
    }
}
