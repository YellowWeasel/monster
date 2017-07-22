package com.erayic.agr.service.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.erayic.agr.common.AgrConstant;
import com.erayic.agr.common.net.back.ServiceBuyByUserBean;
import com.erayic.agr.common.net.back.enums.EnumServiceType;
import com.erayic.agr.common.util.ErayicNetDate;
import com.erayic.agr.common.view.SectionedRecyclerViewAdapter;
import com.erayic.agr.service.R;
import com.erayic.agr.service.adapter.holder.ServiceEntranceChildViewHolder;
import com.erayic.agr.service.adapter.holder.ServiceEntranceGroupViewHolder;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;

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
    private Drawable draFree, draTry, draOffer;

    public ServiceEntranceAdapter(Context context) {
        this.context = context;
        mBooleanMap = new SparseBooleanArray();

        draFree = ContextCompat.getDrawable(context, R.drawable.app_base_default_service_free);
        draFree.setBounds(0, 0, draFree.getMinimumWidth(), draFree.getMinimumHeight());

        draTry = ContextCompat.getDrawable(context, R.drawable.app_base_default_service_try);
        draTry.setBounds(0, 0, draTry.getMinimumWidth(), draTry.getMinimumHeight());

        draOffer = ContextCompat.getDrawable(context, R.drawable.app_base_default_service_offer);
        draOffer.setBounds(0, 0, draOffer.getMinimumWidth(), draOffer.getMinimumHeight());
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
        holder.serviceEntranceItemName.setText(list.get(section).getServiceName());

        boolean isShow = false;
        if (list.get(section).getType() != EnumServiceType.Subject) {//非主题服务
            if (list.get(section).isFree()) {
                holder.serviceEntranceItemName.setCompoundDrawables(null, null, null, null);
                holder.serviceEntranceItemBuy.setVisibility(View.GONE);
            } else {
                if (list.get(section).isOrder()) {
                    holder.serviceEntranceItemName.setCompoundDrawables(null, null, null, null);
                    int day = new Period(new DateTime(), new DateTime(ErayicNetDate.getLongDates(list.get(section).getDueDate())), PeriodType.days()).getDays();
                    if (day > 10) {
                        holder.serviceEntranceItemBuy.setVisibility(View.GONE);
                    } else if (day == 0) {
                        holder.serviceEntranceItemBuy.setText("今天到期");
                        holder.serviceEntranceItemBuy.setVisibility(View.VISIBLE);
                    } else if (day < 0) {
                        holder.serviceEntranceItemBuy.setText("已过期");
                        holder.serviceEntranceItemBuy.setVisibility(View.VISIBLE);
                    } else {
                        holder.serviceEntranceItemBuy.setText("剩" + day + "天");
                        holder.serviceEntranceItemBuy.setVisibility(View.VISIBLE);
                    }
                } else {
                    if (list.get(section).isTry()) {
                        holder.serviceEntranceItemName.setCompoundDrawables(null, null, draTry, null);
                        int day = new Period(new DateTime(), new DateTime(ErayicNetDate.getLongDates(list.get(section).getDueDate())), PeriodType.days()).getDays();
                        if (day > 10) {
                            holder.serviceEntranceItemBuy.setVisibility(View.GONE);
                        } else if (day == 0) {
                            holder.serviceEntranceItemBuy.setText("今天到期");
                            holder.serviceEntranceItemBuy.setVisibility(View.VISIBLE);
                        } else if (day < 0) {
                            holder.serviceEntranceItemBuy.setText("已过期");
                            holder.serviceEntranceItemBuy.setVisibility(View.VISIBLE);
                        } else {
                            holder.serviceEntranceItemBuy.setText("剩" + day + "天");
                            holder.serviceEntranceItemBuy.setVisibility(View.VISIBLE);
                        }
                    } else {
                        holder.serviceEntranceItemName.setCompoundDrawables(null, null, null, null);
                        holder.serviceEntranceItemBuy.setVisibility(View.GONE);
                    }
                }
            }
            if (list.get(section).isOwner()) {
                holder.setVisibility(true);
            } else {
                holder.setVisibility(false);
            }
        } else {
            holder.serviceEntranceItemName.setCompoundDrawables(null, null, null, null);
            holder.serviceEntranceItemBuy.setVisibility(View.GONE);
            for (ServiceBuyByUserBean.SpecifysInfo info : list.get(section).getSpecifys()) {
                isShow = isShow || info.isOwner();
            }
            if (isShow) {
                holder.setVisibility(true);
            } else {
                holder.setVisibility(false);
            }
        }
        Glide.with(context).
                load(TextUtils.isEmpty(list.get(section).getIcon()) ? "" : (AgrConstant.IMAGE_URL_IMAGE + list.get(section).getIcon())).
                apply(AgrConstant.iconOptions)
                .into(holder.serviceEntranceItemIcon);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(section).getType() == EnumServiceType.Subject) {
                    boolean isOpen = mBooleanMap.get(section);
                    mBooleanMap.put(section, !isOpen);
                    notifyDataSetChanged();
                } else {
                    if (onItemClickListener != null) {
                        onItemClickListener.onClick(v, list.get(section).getServiceID(), null, 0,null);
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
        holder.serviceEntranceItemBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.toServiceInfo(list.get(section).getServiceName(), list.get(section).getServiceID(), list.get(section).getIcon(),
                            list.get(section).getType());
                }
            }
        });
        holder.serviceEntranceItemBuy.setOnLongClickListener(new View.OnLongClickListener() {
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
        holder.serviceEntranceItemChildIcon.setVisibility(View.INVISIBLE);
        holder.serviceEntranceItemChildName.setText(list.get(section).getSpecifys().get(position).getSepcify());

        if (list.get(section).getSpecifys().get(position).isFree()) {
            holder.serviceEntranceItemChildName.setCompoundDrawables(null, null, null, null);
            holder.serviceEntranceItemChildBuy.setVisibility(View.GONE);
        } else {
            if (list.get(section).getSpecifys().get(position).isOrder()) {
                holder.serviceEntranceItemChildName.setCompoundDrawables(null, null, null, null);
                int day = new Period(new DateTime(), new DateTime(ErayicNetDate.getLongDates(list.get(section).getSpecifys().get(position).getDueDate())), PeriodType.days()).getDays();
                if (day > 10) {
                    holder.serviceEntranceItemChildBuy.setVisibility(View.GONE);
                } else if (day == 0) {
                    holder.serviceEntranceItemChildBuy.setText("今天到期");
                    holder.serviceEntranceItemChildBuy.setVisibility(View.VISIBLE);
                } else if (day < 0) {
                    holder.serviceEntranceItemChildBuy.setText("已过期");
                    holder.serviceEntranceItemChildBuy.setVisibility(View.VISIBLE);
                } else {
                    holder.serviceEntranceItemChildBuy.setText("剩" + day + "天");
                    holder.serviceEntranceItemChildBuy.setVisibility(View.VISIBLE);
                }
            } else {
                if (list.get(section).getSpecifys().get(position).isTry()) {
                    holder.serviceEntranceItemChildName.setCompoundDrawables(null, null, draTry, null);
                    int day = new Period(new DateTime(), new DateTime(ErayicNetDate.getLongDates(list.get(section).getSpecifys().get(position).getDueDate())), PeriodType.days()).getDays();
                    if (day > 10) {
                        holder.serviceEntranceItemChildBuy.setVisibility(View.GONE);
                    } else if (day == 0) {
                        holder.serviceEntranceItemChildBuy.setText("今天到期");
                        holder.serviceEntranceItemChildBuy.setVisibility(View.VISIBLE);
                    } else if (day < 0) {
                        holder.serviceEntranceItemChildBuy.setText("已过期");
                        holder.serviceEntranceItemChildBuy.setVisibility(View.VISIBLE);
                    } else {
                        holder.serviceEntranceItemChildBuy.setText("剩" + day + "天");
                        holder.serviceEntranceItemChildBuy.setVisibility(View.VISIBLE);
                    }
                } else {
                    holder.serviceEntranceItemChildName.setCompoundDrawables(null, null, null, null);
                    holder.serviceEntranceItemChildBuy.setVisibility(View.GONE);
                }
            }
        }

        if (list.get(section).getSpecifys().get(position).isOwner()) {
            holder.setVisibility(true);
        } else {
            holder.setVisibility(false);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(v, list.get(section).getServiceID(), list.get(section).getSpecifys().get(position).getServiceID(),
                            list.get(section).getSpecifys().get(position).getCropID(),list.get(section).getSpecifys().get(position).getSepcify());
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });

        holder.serviceEntranceItemChildBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.toServiceInfo(list.get(section).getServiceName(), list.get(section).getServiceID(), list.get(section).getIcon(),
                            list.get(section).getType());
                }
            }
        });
        holder.serviceEntranceItemChildBuy.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
    }

    public interface OnItemClickListener {
        void onClick(View v, String serviceID, String subServiceID, int cropID,String cropName);

        void toServiceInfo(String serviceName, String serviceID, String serviceIcon, int serviceType);
    }
}
