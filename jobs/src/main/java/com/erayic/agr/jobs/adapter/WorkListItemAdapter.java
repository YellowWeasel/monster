package com.erayic.agr.jobs.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.erayic.agr.common.net.back.work.CommonWorkListBean;
import com.erayic.agr.jobs.R;
import com.erayic.agr.jobs.adapter.holder.JobsContentText1ViewHolder;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class WorkListItemAdapter extends BaseQuickAdapter<CommonWorkListBean, JobsContentText1ViewHolder> {

    private Context context;
    private OnItemClickListener onItemClickListener;

    public WorkListItemAdapter(Context context, List<CommonWorkListBean> data) {
        super(R.layout.adapter_jobs_content_text_1, data);
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    protected void convert(JobsContentText1ViewHolder helper, final CommonWorkListBean item) {
        helper.jobsContentIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.app_base_image_item_icon));
        helper.jobsContentName.setText(item.getJobName());
        helper.jobsContentSub.setText("");
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null)
                    onItemClickListener.onClick(v, item.getJobID(), item.getJobName());
            }
        });
        helper.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
    }

    public interface OnItemClickListener {
        void onClick(View v, String jobID, String jobName);
    }
}
