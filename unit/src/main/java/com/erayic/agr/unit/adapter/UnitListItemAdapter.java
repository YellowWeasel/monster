package com.erayic.agr.unit.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.erayic.agr.common.config.CustomLinearLayoutManager;
import com.erayic.agr.common.net.back.device.CommonMonitorInfoEntity;
import com.erayic.agr.common.net.back.unit.CommonUnitListBean;
import com.erayic.agr.common.util.DividerItemDecoration;
import com.erayic.agr.common.view.SectionedRecyclerViewAdapter;
import com.erayic.agr.unit.R;
import com.erayic.agr.unit.adapter.entity.UnitListItemByBatchEntity;
import com.erayic.agr.unit.adapter.entity.UnitListItemByControlEntity;
import com.erayic.agr.unit.adapter.entity.UnitListItemByEnvironmentEntity;
import com.erayic.agr.unit.adapter.entity.UnitListItemByMonitorEntity;
import com.erayic.agr.unit.adapter.holder.UnitListItemChildViewHolder;
import com.erayic.agr.unit.adapter.holder.UnitListItemGroupViewHolder;
import com.jpeng.jptabbar.BadgeDismissListener;
import com.jpeng.jptabbar.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class UnitListItemAdapter extends SectionedRecyclerViewAdapter<UnitListItemGroupViewHolder, UnitListItemChildViewHolder, RecyclerView.ViewHolder> {

    private final String[] titlesName = new String[]{"批次", "环境", "控制", "监控", "历史"};
    private final int[] titleNormalIcons = new int[]{R.drawable.image_unit_batch_nomal, R.drawable.image_unit_environment_nomal,
            R.drawable.image_unit_control_nomal, R.drawable.image_unit_monitor_nomal, R.drawable.image_unit_batch_nomal};
    private final int[] titleSelectedIcons = new int[]{R.drawable.image_unit_batch_press, R.drawable.image_unit_environment_press,
            R.drawable.image_unit_control_press, R.drawable.image_unit_monitor_press, R.drawable.image_unit_batch_press};

    private Context context;
    private SparseBooleanArray mBooleanMap;
    private int onItemPosition = -1;
    private int onindexPosition = -1;//（批次，环境，控制，监控 位置记录）

    private List<CommonUnitListBean> list;

    private OnItemScrollToPositionWithOffset onItemScrollToPositionWithOffset;

    private UnitListItemByBatchAdapter.OnItemBatchClickListener onItemBatchClickListener;

    private UnitListItemByMonitorAdapter.OnMonitorClickListener onMonitorClickListener;

    private UnitListItemByControlAdapter controlAdapter;

    public UnitListItemAdapter(Context context) {
        this.context = context;
        mBooleanMap = new SparseBooleanArray();
        mBooleanMap.append(0, true);//默认打开第一个
        onItemPosition = 0;//默认打开0个
        onindexPosition = 0;//默认打开0个
    }

    public void setList(List<CommonUnitListBean> list) {
        this.list = list;
    }

    public void setOnItemScrollToPositionWithOffset(OnItemScrollToPositionWithOffset onItemScrollToPositionWithOffset) {
        this.onItemScrollToPositionWithOffset = onItemScrollToPositionWithOffset;
    }

    public void setOnMonitorClickListener(UnitListItemByMonitorAdapter.OnMonitorClickListener onMonitorClickListener) {
        this.onMonitorClickListener = onMonitorClickListener;
    }

    public void setControlAdapter(UnitListItemByControlAdapter controlAdapter) {
        this.controlAdapter = controlAdapter;
    }

    @Override
    protected int getSectionCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    protected int getItemCountForSection(int section) {
        int count = 1;
        if (!mBooleanMap.get(section)) {
            count = 0;
        }
        return count;
    }

    public void setOnItemBatchClickListener(UnitListItemByBatchAdapter.OnItemBatchClickListener onItemBatchClickListener) {
        this.onItemBatchClickListener = onItemBatchClickListener;
    }

    @Override
    protected boolean hasFooterInSection(int section) {
        return false;
    }

    @Override
    protected UnitListItemGroupViewHolder onCreateSectionHeaderViewHolder(ViewGroup parent, int viewType) {
        return new UnitListItemGroupViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_unit_list_group_item, parent, false));
    }

    @Override
    protected RecyclerView.ViewHolder onCreateSectionFooterViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected UnitListItemChildViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        return new UnitListItemChildViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_unit_list_child_item, parent, false));
    }

    @Override
    protected void onBindSectionHeaderViewHolder(final UnitListItemGroupViewHolder holder, final int section) {
        holder.unitItemListIcon.setImageResource(mBooleanMap.get(section) ? R.drawable.image_unit_item_open : R.drawable.image_unit_item_close);
        holder.serviceManageItemLayout.setBackground(ContextCompat.getDrawable(context, mBooleanMap.get(section) ? R.color.unit_colors_item_green : R.color.app_base_item_background));
        holder.unitItemListName.setTextColor(ContextCompat.getColor(context, mBooleanMap.get(section) ? R.color.app_base_item_background : R.color.app_base_text_title_2));
        holder.unitItemListName.setGravity(mBooleanMap.get(section) ? Gravity.CENTER : Gravity.CENTER_VERTICAL | Gravity.START);
        holder.unitItemListName.setText(list.get(section).getUnitName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isOpen = mBooleanMap.get(section);
                holder.unitItemListIcon.setImageResource(isOpen ? R.drawable.image_unit_item_open : R.drawable.image_unit_item_close);
                holder.serviceManageItemLayout.setBackground(ContextCompat.getDrawable(context, isOpen ? R.color.unit_colors_item_green : R.color.app_base_item_background));
                holder.unitItemListName.setTextColor(ContextCompat.getColor(context, isOpen ? R.color.app_base_item_background : R.color.app_base_text_title_2));
                holder.unitItemListName.setGravity(isOpen ? Gravity.CENTER : Gravity.CENTER_VERTICAL | Gravity.START);
                mBooleanMap.put(section, !isOpen);
                if (onItemPosition >= 0 && onItemPosition != section) {
                    mBooleanMap.put(onItemPosition, false);
                }
                onItemPosition = section;
                notifyDataSetChanged();
                if (onItemScrollToPositionWithOffset != null) {
                    onItemScrollToPositionWithOffset.scrollToPositionWithOffset(section);
                }

            }
        });
    }

    @Override
    protected void onBindSectionFooterViewHolder(RecyclerView.ViewHolder holder, int section) {

    }

    @Override
    protected void onBindItemViewHolder(final UnitListItemChildViewHolder holder, final int section, int position) {
        //强制关闭复用
        holder.setIsRecyclable(false);
        holder.unitListItemTab.setTitles(titlesName)
                .setNormalIcons(titleNormalIcons)
                .setSelectedIcons(titleSelectedIcons)
                .generate();
        //使用线性布局管理器
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(context);
        manager.setScrollEnabled(true);//滑动监听
        holder.unitListItemRecyclerView.setLayoutManager(manager);
        holder.unitListItemRecyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));

        holder.unitListItemTab.setTabListener(new OnTabSelectListener() {//设置点击TabBar事件的观察者
            @Override
            public void onTabSelect(int index) {
                switch (index) {
                    case 0://批次
                    {
                        List<UnitListItemByBatchEntity> listBatch = new ArrayList<>();
                        for (CommonUnitListBean.UnitBatchListBean bean : list.get(section).getBatchs()) {
                            UnitListItemByBatchEntity entity = new UnitListItemByBatchEntity();
                            entity.setItemType(UnitListItemByBatchEntity.TYPE_BATCH_CONTENT);
                            entity.setName(TextUtils.isEmpty(bean.getProductName()) ? "未命名" : bean.getProductName());
                            Map<String, String> map = new ArrayMap<>();
                            map.put("BatchID", bean.getBatchID());
                            map.put("Icon", bean.getIcon());
                            map.put("StartTime", bean.getStartTime());
                            map.put("Mature", bean.getMature());
                            entity.setMap(map);
                            listBatch.add(entity);
                        }
                        {
                            UnitListItemByBatchEntity entity = new UnitListItemByBatchEntity();
                            entity.setItemType(UnitListItemByBatchEntity.TYPE_BATCH_ADD);
                            entity.setName("新建一个批次");
                            listBatch.add(entity);
                        }
//                        {
//                            UnitListItemByBatchEntity entity = new UnitListItemByBatchEntity();
//                            entity.setItemType(UnitListItemByBatchEntity.TYPE_BATCH_HISTORY);
//                            entity.setName("历史批次");
//                            listBatch.add(entity);
//                        }
                        UnitListItemByBatchAdapter adapter = new UnitListItemByBatchAdapter(context, null, list.get(section).getUnitID());
                        adapter.setOnItemBatchClickListener(onItemBatchClickListener);
                        holder.unitListItemRecyclerView.setAdapter(adapter);
                        adapter.setNewData(listBatch);
                    }
                    onindexPosition = index;
                    break;
                    case 1://环境
                    {
                        List<UnitListItemByEnvironmentEntity> listEnvi = new ArrayList<>();
                        for (int i = 0; i < 8; i++) {
                            UnitListItemByEnvironmentEntity entity = new UnitListItemByEnvironmentEntity();
                            switch (i) {
                                case 0://空气温度
                                {
                                    entity.setItemType(UnitListItemByEnvironmentEntity.TYPE_AIR_TEM);
                                    entity.setName("空气温度：");
                                    if (list.get(section).getEnvInfo().getTemp() == -99)
                                        entity.setSubName("未采集");
                                    else
                                        entity.setSubName("" + list.get(section).getEnvInfo().getTemp() + "℃");
                                    listEnvi.add(entity);
                                }
                                break;
                                case 1://空气湿度
                                {
                                    entity.setItemType(UnitListItemByEnvironmentEntity.TYPE_AIR_HUM);
                                    entity.setName("空气湿度：");
                                    if (list.get(section).getEnvInfo().getHumi() == -1)
                                        entity.setSubName("未采集");
                                    else
                                        entity.setSubName(list.get(section).getEnvInfo().getHumi() + "％");
                                    listEnvi.add(entity);
                                }
                                break;
                                case 2://土壤温度
                                {
                                    entity.setItemType(UnitListItemByEnvironmentEntity.TYPE_SOIL_TEM);
                                    entity.setName("土壤温度：");
                                    if (list.get(section).getEnvInfo().getTempSoil() == -99)
                                        entity.setSubName("未采集");
                                    else
                                        entity.setSubName(list.get(section).getEnvInfo().getTempSoil() + "℃");
                                    listEnvi.add(entity);
                                }
                                break;
                                case 3://土壤湿度
                                {
                                    entity.setItemType(UnitListItemByEnvironmentEntity.TYPE_SOIL_HUM);
                                    entity.setName("土壤湿度：");
                                    if (list.get(section).getEnvInfo().getHumiSoil() == -1)
                                        entity.setSubName("未采集");
                                    else
                                        entity.setSubName(list.get(section).getEnvInfo().getHumiSoil() + "％");
                                    listEnvi.add(entity);
                                }
                                break;
                                case 4://降水量
                                {
                                    entity.setItemType(UnitListItemByEnvironmentEntity.TYPE_WATER);
                                    entity.setName("降水量(1H)：");
                                    if (list.get(section).getEnvInfo().getRain_1H() == -1)
                                        entity.setSubName("未采集");
                                    else
                                        entity.setSubName(list.get(section).getEnvInfo().getRain_1H() + "ml");
                                    listEnvi.add(entity);
                                }
                                break;
                                case 5://光照强度
                                {
                                    entity.setItemType(UnitListItemByEnvironmentEntity.TYPE_ILL);
                                    entity.setName("光照强度：");
                                    if (list.get(section).getEnvInfo().getIllumination() == -1)
                                        entity.setSubName("未采集");
                                    else
                                        entity.setSubName(list.get(section).getEnvInfo().getIllumination() + "lux");
                                    listEnvi.add(entity);
                                }
                                break;
                                case 6://CO2
                                {
                                    entity.setItemType(UnitListItemByEnvironmentEntity.TYPE_CO2);
                                    entity.setName("二氧化碳：");
                                    if (list.get(section).getEnvInfo().getCo2() == -1)
                                        entity.setSubName("未采集");
                                    else
                                        entity.setSubName(list.get(section).getEnvInfo().getCo2() + "ppm");
                                    listEnvi.add(entity);
                                }
                                break;
                                case 7://风力
                                {
                                    entity.setItemType(UnitListItemByEnvironmentEntity.TYPE_WIND);
                                    entity.setName("风力：");
                                    if (list.get(section).getEnvInfo().getWind_Max() == -1)
                                        entity.setSubName("未采集");
                                    else
                                        entity.setSubName(list.get(section).getEnvInfo().getWind_Max() + "m/s");
                                    listEnvi.add(entity);
                                }
                                break;
                                default:
                                    break;
                            }
                        }
                        UnitListItemByEnvironmentAdapter adapter = new UnitListItemByEnvironmentAdapter(context, null);
                        holder.unitListItemRecyclerView.setAdapter(adapter);
                        adapter.setNewData(listEnvi);
                    }
                    onindexPosition = index;
                    break;
                    case 2://控制
                    {
                        List<UnitListItemByControlEntity> listControl = new ArrayList<>();
                        if (list.get(section).getRemoteCtrl() != null && !TextUtils.isEmpty(list.get(section).getRemoteCtrl().getSerialNum())) {
                            {
                                UnitListItemByControlEntity entity = new UnitListItemByControlEntity();
                                entity.setItemType(UnitListItemByControlEntity.TYPE_ITEM_EQU);
                                entity.setName(TextUtils.isEmpty(list.get(section).getRemoteCtrl().getSerialNum()) ? "" : list.get(section).getRemoteCtrl().getSerialNum());
                                Map<String, Object> map = new ArrayMap<>();
                                map.put("Name", TextUtils.isEmpty(list.get(section).getRemoteCtrl().getName()) ? "未命名" : list.get(section).getRemoteCtrl().getName());
                                map.put("Mode", TextUtils.isEmpty(list.get(section).getRemoteCtrl().getMode()) ? "未知" : list.get(section).getRemoteCtrl().getMode());
                                map.put("Tempeture", list.get(section).getRemoteCtrl().getTempeture() + "℃");
                                map.put("Status", TextUtils.isEmpty(list.get(section).getRemoteCtrl().getStatus()) ? "未知" : list.get(section).getRemoteCtrl().getStatus());
                                entity.setMap(map);
                                listControl.add(entity);
                            }
                            for (CommonUnitListBean.UnitListCtrlItemsBean bean : list.get(section).getRemoteCtrl().getCtrlItems()) {
                                switch (bean.getRelayType()) {
                                    case 1://启停
                                    {
                                        UnitListItemByControlEntity entity = new UnitListItemByControlEntity();
                                        entity.setItemType(UnitListItemByControlEntity.TYPE_ITEM_ST);
                                        entity.setData(bean);
                                        listControl.add(entity);
                                    }
                                    break;
                                    case 2://正反转
                                    {
                                        UnitListItemByControlEntity entity = new UnitListItemByControlEntity();
                                        entity.setItemType(UnitListItemByControlEntity.TYPE_ITEM_PN);
                                        entity.setData(bean);
                                        listControl.add(entity);
                                    }
                                    break;
                                    default:
                                        break;
                                }
                            }

                        } else {
                            UnitListItemByControlEntity entity = new UnitListItemByControlEntity();
                            entity.setItemType(UnitListItemByControlEntity.TYPE_NO_EQU);
                            entity.setName("系统检测到当前地块无控制设备，请联系我们");
                            listControl.add(entity);
                        }
                        holder.unitListItemRecyclerView.setAdapter(controlAdapter);
                        controlAdapter.setNewData(listControl);
                    }
                    onindexPosition = index;
                    break;
                    case 3://监控
                    {
                        List<UnitListItemByMonitorEntity> listMonitor = new ArrayList<>();
                        if (list.get(section).getMonitors() != null && list.get(section).getMonitors().size() > 0) {

                            for (CommonMonitorInfoEntity bean : list.get(section).getMonitors()) {
                                UnitListItemByMonitorEntity entity = new UnitListItemByMonitorEntity();
                                entity.setItemType(UnitListItemByMonitorEntity.TYPE_MONITOR);
                                entity.setName(TextUtils.isEmpty(bean.getName()) ? "未命名" : bean.getName());
                                entity.setSubName(bean.isControlled() ? "云台" : "");
                                entity.setData(bean);
                                listMonitor.add(entity);
                            }
                        } else {
                            UnitListItemByMonitorEntity entity = new UnitListItemByMonitorEntity();
                            entity.setItemType(UnitListItemByMonitorEntity.TYPE_NO_DATA);
                            entity.setName("系统检测到当前地块无视频监控设备，请联系我们");
                            listMonitor.add(entity);
                        }
                        UnitListItemByMonitorAdapter adapter = new UnitListItemByMonitorAdapter(context, null);
                        adapter.setOnMonitorClickListener(onMonitorClickListener);
                        holder.unitListItemRecyclerView.setAdapter(adapter);
                        adapter.setNewData(listMonitor);
                    }
                    onindexPosition = index;
                    break;
                    case 4://历史批次
                    {
                        if (onItemBatchClickListener != null)
                            onItemBatchClickListener.onHistoryBatch(list.get(section).getUnitID());
                        holder.unitListItemTab.setSelectTab(onindexPosition);
                    }
                    break;
                }
            }
        });
//        holder.unitListItemTab.setDismissListener(new BadgeDismissListener() {
//            @Override
//            public void onDismiss(int position) {
//                switch (position){
//                    case 4:
//
//                        break;
//                    default:
//                        break;
//                }
//            }
//        });
        holder.unitListItemTab.setSelectTab(onindexPosition);
    }

    /**
     * 点击Item精准定位到具体头部位置
     */
    public interface OnItemScrollToPositionWithOffset {
        void scrollToPositionWithOffset(int position);
    }

}
