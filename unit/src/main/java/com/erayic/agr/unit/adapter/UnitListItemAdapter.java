package com.erayic.agr.unit.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.erayic.agr.common.config.CustomLinearLayoutManager;
import com.erayic.agr.common.util.DividerItemDecoration;
import com.erayic.agr.common.view.SectionedRecyclerViewAdapter;
import com.erayic.agr.unit.R;
import com.erayic.agr.unit.adapter.entity.UnitListItemByBatchEntity;
import com.erayic.agr.unit.adapter.holder.UnitListItemChildViewHolder;
import com.erayic.agr.unit.adapter.holder.UnitListItemGroupViewHolder;
import com.jpeng.jptabbar.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;


/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class UnitListItemAdapter extends SectionedRecyclerViewAdapter<UnitListItemGroupViewHolder, UnitListItemChildViewHolder, RecyclerView.ViewHolder> {

    private final String[] titlesName = new String[]{"批次", "环境", "控制", "监控"};
    private final int[] titleNormalIcons = new int[]{R.drawable.image_unit_batch_nomal, R.drawable.image_unit_environment_nomal,
            R.drawable.image_unit_control_nomal, R.drawable.image_unit_monitor_nomal};
    private final int[] titleSelectedIcons = new int[]{R.drawable.image_unit_batch_press, R.drawable.image_unit_environment_press,
            R.drawable.image_unit_control_press, R.drawable.image_unit_monitor_press};

    private Context context;
    private SparseBooleanArray mBooleanMap;
    private int onItemPosition = -1;

    private onItemScrollToPositionWithOffset onItemScrollToPositionWithOffset;

    public UnitListItemAdapter(Context context) {
        this.context = context;
        mBooleanMap = new SparseBooleanArray();
    }

    public void setOnItemScrollToPositionWithOffset(UnitListItemAdapter.onItemScrollToPositionWithOffset onItemScrollToPositionWithOffset) {
        this.onItemScrollToPositionWithOffset = onItemScrollToPositionWithOffset;
    }

    @Override
    protected int getSectionCount() {
        return 10;
    }

    @Override
    protected int getItemCountForSection(int section) {
        int count = 1;
        if (!mBooleanMap.get(section)) {
            count = 0;
        }
        return count;
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
                if (onItemScrollToPositionWithOffset!=null){
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
//                ErayicToast.TextToast(context, section + "  " + index);

                List<UnitListItemByBatchEntity> list = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    UnitListItemByBatchEntity entity = new UnitListItemByBatchEntity();
                    if (i == 0)
                        entity.setItemType(UnitListItemByBatchEntity.TYPE_BATCH_ADD);
                    else
                        entity.setItemType(UnitListItemByBatchEntity.TYPE_BATCH_CONTENT);
                    entity.setName(section + "");
                    entity.setSubName(index + "");
                    list.add(entity);
                }

                UnitListItemByBatchAdapter adapter = new UnitListItemByBatchAdapter(context, null);
                holder.unitListItemRecyclerView.setAdapter(adapter);
                adapter.setNewData(list);
            }
        });
        holder.unitListItemTab.setSelectTab(0);
    }

    /**
     * 点击Item精准定位到具体头部位置
     */
    public interface onItemScrollToPositionWithOffset{
        void scrollToPositionWithOffset(int position);
    }

}
