package com.erayic.agr.jobs.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.erayic.agr.common.net.back.work.CommonJobsInfoBean;
import com.erayic.agr.common.util.ErayicNetDate;
import com.erayic.agr.jobs.R;
import com.erayic.agr.jobs.adapter.holder.JobsBatchItemCheckViewHolder;

import org.joda.time.DateTime;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：工作详情批次选择列表
 */


public class JobsBatchItemAdapter extends BaseQuickAdapter<CommonJobsInfoBean.BatchInfo, JobsBatchItemCheckViewHolder> {

    private Context context;
    private boolean isEdit = false;

    public JobsBatchItemAdapter(Context context, List<CommonJobsInfoBean.BatchInfo> data) {
        super(R.layout.adapter_jobs_jobs_batch_item, data);
        this.context = context;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }

    @Override
    protected void convert(final JobsBatchItemCheckViewHolder helper, final CommonJobsInfoBean.BatchInfo item) {
        helper.jobsContentIcon.setVisibility(View.GONE);
        helper.jobsContentName.setText(TextUtils.isEmpty(item.getProductName()) ? "未命名" : item.getProductName());
        helper.jobsContentSub.setText(new DateTime(ErayicNetDate.getLongDates(item.getSeedDate())).toString("yyyy-MM-dd"));
        helper.jobsContentCheck.setChecked(item.isApply());
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEdit) {
                    item.setApply(!item.isApply());
                    notifyItemChanged(helper.getAdapterPosition());
                }
            }
        });
        helper.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
    }
}
