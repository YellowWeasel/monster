package com.erayic.agr.jobs.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.jobs.R;
import com.erayic.agr.jobs.adapter.entity.JobInfoEntity;
import com.erayic.agr.jobs.adapter.holder.JobsContentEdit2ViewHolder;
import com.erayic.agr.jobs.adapter.holder.JobsContentText1ViewHolder;
import com.erayic.agr.jobs.adapter.holder.JobsDividerViewHolder;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class JobInfoItemAdapter extends BaseMultiItemQuickAdapter<JobInfoEntity, BaseViewHolder> {

    private Context context;
    private OnJobItemClickListener onJobItemClickListener;
    private boolean isShowNoticeDate;//是否显示通知时间

    public JobInfoItemAdapter(Context context, List<JobInfoEntity> data) {
        super(data);
        this.context = context;
    }

    public void setShowNoticeDate(boolean showNoticeDate) {
        isShowNoticeDate = showNoticeDate;
    }

    public void setOnJobItemClickListener(OnJobItemClickListener onJobItemClickListener) {
        this.onJobItemClickListener = onJobItemClickListener;
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case JobInfoEntity.TYPE_JOB_DIVIDER:
                return new JobsDividerViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_jobs_divider, parent, false));
            case JobInfoEntity.TYPE_JOB_WORK:
                return new JobsContentText1ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_jobs_content_text_1, parent, false));
            case JobInfoEntity.TYPE_JOB_UNIT_ADD:
                return new JobsContentText1ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_jobs_content_text_1, parent, false));
            case JobInfoEntity.TYPE_JOB_UNIT:
                return new JobsContentEdit2ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_jobs_content_edit_2, parent, false));
            case JobInfoEntity.TYPE_JOB_DATE:
                return new JobsContentText1ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_jobs_content_text_1, parent, false));
            case JobInfoEntity.TYPE_JOB_NOTICE:
                return new JobsContentText1ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_jobs_content_text_1, parent, false));
            case JobInfoEntity.TYPE_JOB_NOTICE_DATE:
                return new JobsContentText1ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_jobs_content_text_1, parent, false));
        }
        return super.onCreateDefViewHolder(parent, viewType);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final JobInfoEntity item) {
        //        强行关闭复用
        switch (helper.getItemViewType()) {
            case JobInfoEntity.TYPE_JOB_DIVIDER:
                if (helper instanceof JobsDividerViewHolder) {

                }
                break;
            case JobInfoEntity.TYPE_JOB_WORK:
                if (helper instanceof JobsContentText1ViewHolder) {
                    ((JobsContentText1ViewHolder) helper).jobsContentIcon.setVisibility(View.GONE);
                    ((JobsContentText1ViewHolder) helper).jobsContentName.setText(TextUtils.isEmpty(item.getName()) ? "" : item.getName());
                    ((JobsContentText1ViewHolder) helper).jobsContentSub.setText(TextUtils.isEmpty(item.getSubName()) ? "" : item.getSubName());
                    ((JobsContentText1ViewHolder) helper).itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onJobItemClickListener != null)
                                onJobItemClickListener.onClick(v, item, helper.getAdapterPosition());
                        }
                    });
                    ((JobsContentText1ViewHolder) helper).itemView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            return true;
                        }
                    });
                }
                break;
            case JobInfoEntity.TYPE_JOB_UNIT_ADD:
                if (helper instanceof JobsContentText1ViewHolder) {
                    ((JobsContentText1ViewHolder) helper).jobsContentIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_jobs_work_icon));
                    ((JobsContentText1ViewHolder) helper).jobsContentName.setText(TextUtils.isEmpty(item.getName()) ? "" : item.getName());
                    ((JobsContentText1ViewHolder) helper).jobsContentSub.setTextColor(ContextCompat.getColor(context, R.color.app_base_text_price));
                    ((JobsContentText1ViewHolder) helper).jobsContentSub.setText("添加");
                    ((JobsContentText1ViewHolder) helper).jobsContentGoto.setVisibility(View.INVISIBLE);
                    ((JobsContentText1ViewHolder) helper).itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onJobItemClickListener != null)
                                onJobItemClickListener.onClick(v, item, helper.getAdapterPosition());
                        }
                    });
                    ((JobsContentText1ViewHolder) helper).itemView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            return true;
                        }
                    });
                }
                break;
            case JobInfoEntity.TYPE_JOB_UNIT:
                if (helper instanceof JobsContentEdit2ViewHolder) {
                    ((JobsContentEdit2ViewHolder) helper).itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.app_base_layout_background));
                    ((JobsContentEdit2ViewHolder) helper).jobsContentIcon.setVisibility(View.INVISIBLE);
                    ((JobsContentEdit2ViewHolder) helper).jobsContentProp.setVisibility(View.GONE);
                    ((JobsContentEdit2ViewHolder) helper).jobsContentName.setText(TextUtils.isEmpty(item.getName()) ? "" : item.getName());
                    ((JobsContentEdit2ViewHolder) helper).jobsContentSubName.setText(TextUtils.isEmpty(item.getSubName()) ? "" : item.getSubName());
                    ((JobsContentEdit2ViewHolder) helper).jobsContentDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            getData().remove(helper.getAdapterPosition());
                            notifyDataSetChanged();
                        }
                    });

                    ((JobsContentEdit2ViewHolder) helper).jobsContentDelete.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            return true;
                        }
                    });
                }
                break;
            case JobInfoEntity.TYPE_JOB_DATE:
                if (helper instanceof JobsContentText1ViewHolder) {
                    ((JobsContentText1ViewHolder) helper).jobsContentIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_jobs_job_date));
                    ((JobsContentText1ViewHolder) helper).jobsContentName.setText(TextUtils.isEmpty(item.getName()) ? "" : item.getName());
                    ((JobsContentText1ViewHolder) helper).jobsContentSub.setText(TextUtils.isEmpty(item.getSubName()) ? "" : item.getSubName());
                    ((JobsContentText1ViewHolder) helper).itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onJobItemClickListener != null)
                                onJobItemClickListener.onClick(v, item, helper.getAdapterPosition());
                        }
                    });
                    ((JobsContentText1ViewHolder) helper).itemView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            return true;
                        }
                    });
                }
                break;
            case JobInfoEntity.TYPE_JOB_NOTICE:
                if (helper instanceof JobsContentText1ViewHolder) {
                    ((JobsContentText1ViewHolder) helper).jobsContentIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_jobs_job_notice));
                    ((JobsContentText1ViewHolder) helper).jobsContentName.setText(TextUtils.isEmpty(item.getName()) ? "" : item.getName());
                    ((JobsContentText1ViewHolder) helper).jobsContentSub.setText(TextUtils.isEmpty(item.getSubName()) ? "" : item.getSubName());
                    ((JobsContentText1ViewHolder) helper).itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onJobItemClickListener != null)
                                onJobItemClickListener.onClick(v, item, helper.getAdapterPosition());
                        }
                    });
                    ((JobsContentText1ViewHolder) helper).itemView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            return true;
                        }
                    });
                }
                break;
            case JobInfoEntity.TYPE_JOB_NOTICE_DATE:
                if (helper instanceof JobsContentText1ViewHolder) {
                   ((JobsContentText1ViewHolder) helper).setVisibility(isShowNoticeDate);
                    ((JobsContentText1ViewHolder) helper).jobsContentIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_jobs_job_notice));
                    ((JobsContentText1ViewHolder) helper).jobsContentName.setText(TextUtils.isEmpty(item.getName()) ? "" : item.getName());
                    ((JobsContentText1ViewHolder) helper).jobsContentSub.setText(TextUtils.isEmpty(item.getSubName()) ? "" : item.getSubName());
                    ((JobsContentText1ViewHolder) helper).itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onJobItemClickListener != null)
                                onJobItemClickListener.onClick(v, item, helper.getAdapterPosition());
                        }
                    });
                    ((JobsContentText1ViewHolder) helper).itemView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            return true;
                        }
                    });

                }
                break;
        }
    }

    public interface OnJobItemClickListener {
        void onClick(View view, JobInfoEntity entity, int position);
    }
}
