package com.erayic.agr.jobs.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.common.net.back.work.CommonJobsInfoBean;
import com.erayic.agr.common.view.SectionedRecyclerViewAdapter;
import com.erayic.agr.jobs.R;
import com.erayic.agr.jobs.adapter.holder.JobsItemChildByUserViewHolder;
import com.erayic.agr.jobs.adapter.holder.JobsItemGroupByUserViewHolder;

import java.util.List;
import java.util.Random;

import butterknife.BindView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class JobsListItemAdapter extends SectionedRecyclerViewAdapter<BaseViewHolder, BaseViewHolder, BaseViewHolder> {

    public static final int TYPE_JOBS_ADMIN = 0;
    public static final int TYPE_JOBS_USER = 1;


    private Context context;
    private SparseBooleanArray mBooleanMap;
    private List<CommonJobsInfoBean.JobsInfo> list;
    private int onItemPosition = -1;//点击的section

    private OnItemScrollToPositionWithOffset onItemScrollToPositionWithOffset;

    private String[] strTestData = new String[]{"浇水", "请及时打开相关设备，保持作物生长正常", "烟嘧磺隆    1:800\n多·福·毒死蜱    1:3000\n注意配药比例",
            "我只是一个测试数据"};

    public JobsListItemAdapter(Context context, List<CommonJobsInfoBean.JobsInfo> list) {
        this.context = context;
        mBooleanMap = new SparseBooleanArray();
        this.list = list;
    }

    public void setList(List<CommonJobsInfoBean.JobsInfo> list) {
        this.list = list;
    }

    public void setOnItemScrollToPositionWithOffset(OnItemScrollToPositionWithOffset onItemScrollToPositionWithOffset) {
        this.onItemScrollToPositionWithOffset = onItemScrollToPositionWithOffset;
    }

    @Override
    protected int getSectionCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    protected int getItemCountForSection(int section) {
        int count = list.get(section).getChildJobInfos() == null ? 0 : list.get(section).getChildJobInfos().size();
        if (count >= 0 && !mBooleanMap.get(section)) {
            count = 0;
        }
        return list.get(section).getChildJobInfos() == null ? 0 : count;
    }

    @Override
    protected boolean hasFooterInSection(int section) {
        return false;
    }

//    @Override
//    protected int getSectionHeaderViewType(int section) {
////        return super.getSectionHeaderViewType(section);
//        return TYPE_JOBS_USER;
//    }
//
//    @Override
//    protected int getSectionItemViewType(int section, int position) {
//
////        return super.getSectionItemViewType(section, position);
//        return TYPE_JOBS_USER;
//    }

    @Override
    protected BaseViewHolder onCreateSectionHeaderViewHolder(ViewGroup parent, int viewType) {
        return new JobsItemGroupByUserViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_jobs_item_group_user, parent, false));
    }

    @Override
    protected BaseViewHolder onCreateSectionFooterViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected BaseViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        return new JobsItemChildByUserViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_jobs_item_child_user, parent, false));
    }

    @Override
    protected void onBindSectionHeaderViewHolder(BaseViewHolder holder, final int section) {
        if (holder instanceof JobsItemGroupByUserViewHolder) {
            ((JobsItemGroupByUserViewHolder) holder).jobsContentIcon.setImageDrawable(ContextCompat.getDrawable(context,
                    mBooleanMap.get(section) ? R.drawable.image_jobs_ui_open : R.drawable.image_jobs_ui_close));
            ((JobsItemGroupByUserViewHolder) holder).jobsContentName.setText(list.get(section).getTestName());
            if (section % 2 == 0) {
                ((JobsItemGroupByUserViewHolder) holder).jobsContentGoto.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_jobs_ui_complete));
            } else {
                ((JobsItemGroupByUserViewHolder) holder).jobsContentGoto.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_jobs_ui_undone));
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isOpen = mBooleanMap.get(section);
                    mBooleanMap.put(section, !isOpen);
                    if (onItemPosition >= 0 && onItemPosition != section) {
                        mBooleanMap.put(onItemPosition, false);
                    }
                    onItemPosition = section;
                    notifyDataSetChanged();
                    if (onItemScrollToPositionWithOffset != null)
                        onItemScrollToPositionWithOffset.scrollToPositionWithOffset(section);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return true;
                }
            });
        }
    }

    @Override
    protected void onBindSectionFooterViewHolder(BaseViewHolder holder, int section) {

    }

    @Override
    protected void onBindItemViewHolder(BaseViewHolder holder, int section, int position) {
        if (holder instanceof JobsItemChildByUserViewHolder) {
            ((JobsItemChildByUserViewHolder) holder).jobsContentName.setText(list.get(section).getChildJobInfos().get(position).getTestChildName());
            ((JobsItemChildByUserViewHolder) holder).jobsContentSub.setText(strTestData[new Random().nextInt(strTestData.length)]);
        }
    }

    /**
     * 点击Item精准定位到具体头部位置
     */
    public interface OnItemScrollToPositionWithOffset {
        void scrollToPositionWithOffset(int position);
    }
}
