package com.erayic.agr.unit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.erayic.agr.common.config.CustomLinearLayoutManager;
import com.erayic.agr.common.util.DividerItemDecoration;
import com.erayic.agr.common.util.ErayicToast;
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
    private final int[] titleNormalIcons = new int[]{R.drawable.app_base_android_3, R.drawable.app_base_android_3, R.drawable.app_base_android_3, R.drawable.app_base_android_3};
    private final int[] titleSelectedIcons = new int[]{R.drawable.app_base_android_3, R.drawable.app_base_android_3, R.drawable.app_base_android_3, R.drawable.app_base_android_3};

    private Context context;
    private SparseBooleanArray mBooleanMap;

    public UnitListItemAdapter(Context context) {
        this.context = context;
        mBooleanMap = new SparseBooleanArray();
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
    protected void onBindSectionHeaderViewHolder(UnitListItemGroupViewHolder holder, final int section) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isOpen = mBooleanMap.get(section);
                mBooleanMap.put(section, !isOpen);
                notifyDataSetChanged();
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
                    entity.setItemType(UnitListItemByBatchEntity.TYPE_BATCH_ADD);
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

}
