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
import com.erayic.agr.common.view.SectionedRecyclerViewAdapter;
import com.erayic.agr.service.R;
import com.erayic.agr.service.adapter.holder.ServiceEntranceChildViewHolder;
import com.erayic.agr.service.adapter.holder.ServiceEntranceGroupViewHolder;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ServiceEntranceAdapter extends SectionedRecyclerViewAdapter<ServiceEntranceGroupViewHolder, ServiceEntranceChildViewHolder, RecyclerView.ViewHolder> {

    private Context context;
    private List<ServiceBuyByUserBean> list;
    private SparseBooleanArray mBooleanMap;
    private OnItemClickListener onItemClickListener;

    public ServiceEntranceAdapter(Context context) {
        this.context = context;
        mBooleanMap = new SparseBooleanArray();
    }

    public void setList(List<ServiceBuyByUserBean> list) {
        this.list = list;
    }

    public List<ServiceBuyByUserBean> getList() {
        return list;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
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
    protected ServiceEntranceGroupViewHolder onCreateSectionHeaderViewHolder(ViewGroup parent, int viewType) {
        return new ServiceEntranceGroupViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_service_entrance_group, parent, false));
    }

    @Override
    protected RecyclerView.ViewHolder onCreateSectionFooterViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected ServiceEntranceChildViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        return new ServiceEntranceChildViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_service_entrance_child, parent, false));
    }

    @Override
    protected void onBindSectionHeaderViewHolder(ServiceEntranceGroupViewHolder holder, final int section) {
        if (list.get(section).isOwner()) {
            holder.setVisibility(true);
        } else {
            holder.setVisibility(false);
        }
        Glide.with(context).load(AgrConstant.IMAGE_URL_PREFIX + list.get(section).getIcon()).error(R.drawable.image_service_test)
                .into(holder.serviceEntranceItemIcon);
        holder.serviceEntranceItemName.setText(list.get(section).getServiceName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(section).getType() == EnumServiceType.Subject) {
                    boolean isOpen = mBooleanMap.get(section);
                    mBooleanMap.put(section, !isOpen);
                    notifyDataSetChanged();
                } else {
                    if (onItemClickListener != null) {
                        onItemClickListener.onClick(v, list.get(section).getServiceID(), null);
                    }
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
    protected void onBindItemViewHolder(ServiceEntranceChildViewHolder holder, final int section, final int position) {
        if (list.get(section).getSpecifys().get(position).isOwner()) {
            holder.setVisibility(true);
        } else {
            holder.setVisibility(false);
        }
        holder.serviceEntranceItemChildIcon.setVisibility(View.INVISIBLE);
        holder.serviceEntranceItemChildName.setText(list.get(section).getSpecifys().get(position).getSepcify());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(v, list.get(section).getServiceID(), list.get(section).getSpecifys().get(position).getServiceID());
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

    public interface OnItemClickListener {
        void onClick(View v, String serviceID, String subServiceID);
    }
}
