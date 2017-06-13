package com.erayic.agr.jobs.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.jobs.R;
import com.erayic.agr.jobs.adapter.entity.WorkInfoEntity;
import com.erayic.agr.jobs.adapter.holder.JobsContentEdit1ViewHolder;
import com.erayic.agr.jobs.adapter.holder.JobsContentEdit2ViewHolder;
import com.erayic.agr.jobs.adapter.holder.JobsContentEdit3ViewHolder;
import com.erayic.agr.jobs.adapter.holder.JobsContentText1ViewHolder;
import com.erayic.agr.jobs.adapter.holder.JobsDividerViewHolder;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class WorkInfoItemAdapter extends BaseMultiItemQuickAdapter<WorkInfoEntity, BaseViewHolder> {

    private Context context;
    private KeyListener keyListener = null;
    private OnWorkClickListener onWorkClickListener;

    public WorkInfoItemAdapter(Context context, List<WorkInfoEntity> data) {
        super(data);
        this.context = context;
    }

    public void setKeyListener(KeyListener keyListener) {
        this.keyListener = keyListener;
    }

    public void setOnWorkClickListener(OnWorkClickListener onWorkClickListener) {
        this.onWorkClickListener = onWorkClickListener;
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case WorkInfoEntity.TYPE_WORK_DIVIDER:
                return new JobsDividerViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_jobs_divider, parent, false));
            case WorkInfoEntity.TYPE_WORK_NAME://名称
                return new JobsContentEdit1ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_jobs_content_edit_1, parent, false));
            case WorkInfoEntity.TYPE_WORK_CONTENT_TITLE_ADD://工作内容
                return new JobsContentText1ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_jobs_content_text_1, parent, false));
            case WorkInfoEntity.TYPE_WORK_CONTENT_FERTILIZE://施肥
                return new JobsContentEdit2ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_jobs_content_edit_2, parent, false));
            case WorkInfoEntity.TYPE_WORK_CONTENT_PESTICIDE://打药
                return new JobsContentEdit2ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_jobs_content_edit_2, parent, false));
            case WorkInfoEntity.TYPE_WORK_CONTENT_PICK://采摘
                return new JobsContentEdit2ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_jobs_content_edit_2, parent, false));
            case WorkInfoEntity.TYPE_WORK_CONTENT_OTHER://其他
                return new JobsContentEdit2ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_jobs_content_edit_2, parent, false));
            case WorkInfoEntity.TYPE_WORK_CONTENT_NORM://工作要求
                return new JobsContentEdit3ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_jobs_content_edit_3, parent, false));
            case WorkInfoEntity.TYPE_WORK_CONTENT_TITLE_SHOW://
                return new JobsContentText1ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_jobs_content_text_1, parent, false));
        }
        return super.onCreateDefViewHolder(parent, viewType);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final WorkInfoEntity item) {
        // 强行关闭复用
        helper.setIsRecyclable(false);
        switch (helper.getItemViewType()) {
            case WorkInfoEntity.TYPE_WORK_DIVIDER://分割线
                break;
            case WorkInfoEntity.TYPE_WORK_NAME://名称
                if (helper instanceof JobsContentEdit1ViewHolder) {
                    ((JobsContentEdit1ViewHolder) helper).jobsContentIcon.setVisibility(View.GONE);
                    ((JobsContentEdit1ViewHolder) helper).jobsContentGoto.setVisibility(View.GONE);
                    ((JobsContentEdit1ViewHolder) helper).jobsContentName.setText(TextUtils.isEmpty(item.getName()) ? "作业名称" : item.getName());
                    ((JobsContentEdit1ViewHolder) helper).jobsContentSubName.setKeyListener(keyListener);
                    ((JobsContentEdit1ViewHolder) helper).jobsContentSubName.setText(TextUtils.isEmpty(item.getSubName()) ? "" : item.getSubName());
                    ((JobsContentEdit1ViewHolder) helper).jobsContentSubName.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            item.setSubName(s.toString());
                        }
                    });
                }
                break;
            case WorkInfoEntity.TYPE_WORK_CONTENT_TITLE_ADD://增加工作内容
                if (helper instanceof JobsContentText1ViewHolder) {
                    ((JobsContentText1ViewHolder) helper).itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.jobs_work_title_background));
                    ((JobsContentText1ViewHolder) helper).jobsContentIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_jobs_work_icon));
                    ((JobsContentText1ViewHolder) helper).jobsContentName.setText(TextUtils.isEmpty(item.getName()) ? "" : item.getName());
                    ((JobsContentText1ViewHolder) helper).jobsContentSub.setTextColor(ContextCompat.getColor(context, R.color.app_base_text_price));
                    ((JobsContentText1ViewHolder) helper).jobsContentSub.setText(TextUtils.isEmpty(item.getSubName()) ? "" : item.getSubName());
                    ((JobsContentText1ViewHolder) helper).jobsContentGoto.setVisibility(View.INVISIBLE);
                    ((JobsContentText1ViewHolder) helper).itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onWorkClickListener != null)
                                onWorkClickListener.onAddWork(v, helper.getAdapterPosition());
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
            case WorkInfoEntity.TYPE_WORK_CONTENT_FERTILIZE://施肥
                if (helper instanceof JobsContentEdit2ViewHolder) {
                    ((JobsContentEdit2ViewHolder) helper).jobsContentIcon.setVisibility(View.GONE);
                    ((JobsContentEdit2ViewHolder) helper).jobsContentName.setText(TextUtils.isEmpty(item.getName()) ? "" : item.getName());
                    ((JobsContentEdit2ViewHolder) helper).jobsContentSubName.setText(TextUtils.isEmpty(item.getSubName()) ? "" : item.getSubName());
                    ((JobsContentEdit2ViewHolder) helper).jobsContentProp.setKeyListener(keyListener);
                    ((JobsContentEdit2ViewHolder) helper).jobsContentProp.setHint("施肥量");
                    Drawable textImg = ContextCompat.getDrawable(context, R.drawable.image_jobs_pick_kg);
                    textImg.setBounds(0, 0, textImg.getMinimumWidth(), textImg.getMinimumHeight());
                    ((JobsContentEdit2ViewHolder) helper).jobsContentProp.setCompoundDrawables(null, null, textImg, null);
//                    ((JobsContentEdit2ViewHolder) helper).jobsContentProp.
                    if (keyListener == null) {
                        ((JobsContentEdit2ViewHolder) helper).jobsContentDelete.setVisibility(View.INVISIBLE);
                    }
                    ((JobsContentEdit2ViewHolder) helper).jobsContentProp.setText(TextUtils.isEmpty(item.getSubProp()) ? "" : item.getSubProp());
                    ((JobsContentEdit2ViewHolder) helper).jobsContentProp.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            item.setSubProp(s.toString());
                        }
                    });
                    ((JobsContentEdit2ViewHolder) helper).jobsContentDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onWorkClickListener != null)
                                onWorkClickListener.onDeleteWork(v, helper.getAdapterPosition());
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
            case WorkInfoEntity.TYPE_WORK_CONTENT_PESTICIDE://打药
                if (helper instanceof JobsContentEdit2ViewHolder) {
                    ((JobsContentEdit2ViewHolder) helper).jobsContentIcon.setVisibility(View.GONE);
                    ((JobsContentEdit2ViewHolder) helper).jobsContentName.setText(TextUtils.isEmpty(item.getName()) ? "" : item.getName());
                    ((JobsContentEdit2ViewHolder) helper).jobsContentSubName.setText(TextUtils.isEmpty(item.getSubName()) ? "" : item.getSubName());
                    ((JobsContentEdit2ViewHolder) helper).jobsContentProp.setHint("配比");
                    ((JobsContentEdit2ViewHolder) helper).jobsContentProp.setKeyListener(keyListener);
                    if (keyListener == null) {
                        ((JobsContentEdit2ViewHolder) helper).jobsContentDelete.setVisibility(View.INVISIBLE);
                    }
                    ((JobsContentEdit2ViewHolder) helper).jobsContentProp.setText(TextUtils.isEmpty(item.getSubProp()) ? "" : item.getSubProp());
                    ((JobsContentEdit2ViewHolder) helper).jobsContentProp.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            item.setSubProp(s.toString());
                        }
                    });
                    ((JobsContentEdit2ViewHolder) helper).jobsContentDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onWorkClickListener != null)
                                onWorkClickListener.onDeleteWork(v, helper.getAdapterPosition());
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
            case WorkInfoEntity.TYPE_WORK_CONTENT_PICK://采摘
                if (helper instanceof JobsContentEdit2ViewHolder) {
                    ((JobsContentEdit2ViewHolder) helper).jobsContentIcon.setVisibility(View.GONE);
                    ((JobsContentEdit2ViewHolder) helper).jobsContentName.setText(TextUtils.isEmpty(item.getName()) ? "" : item.getName());
                    ((JobsContentEdit2ViewHolder) helper).jobsContentSubName.setText("");
                    ((JobsContentEdit2ViewHolder) helper).jobsContentProp.setKeyListener(keyListener);
                    if (keyListener == null) {
                        ((JobsContentEdit2ViewHolder) helper).jobsContentDelete.setVisibility(View.INVISIBLE);
                    }
                    ((JobsContentEdit2ViewHolder) helper).jobsContentProp.setVisibility(View.GONE);
                    ((JobsContentEdit2ViewHolder) helper).jobsContentDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onWorkClickListener != null)
                                onWorkClickListener.onDeleteWork(v, helper.getAdapterPosition());
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
            case WorkInfoEntity.TYPE_WORK_CONTENT_OTHER://其他
                if (helper instanceof JobsContentEdit2ViewHolder) {
                    ((JobsContentEdit2ViewHolder) helper).jobsContentIcon.setVisibility(View.GONE);
                    ((JobsContentEdit2ViewHolder) helper).jobsContentName.setText(TextUtils.isEmpty(item.getName()) ? "" : item.getName());
                    ((JobsContentEdit2ViewHolder) helper).jobsContentSubName.setText("");
                    ((JobsContentEdit2ViewHolder) helper).jobsContentProp.setKeyListener(keyListener);
                    if (keyListener == null) {
                        ((JobsContentEdit2ViewHolder) helper).jobsContentDelete.setVisibility(View.INVISIBLE);
                    }
                    ((JobsContentEdit2ViewHolder) helper).jobsContentProp.setVisibility(View.GONE);
                    ((JobsContentEdit2ViewHolder) helper).jobsContentDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onWorkClickListener != null)
                                onWorkClickListener.onDeleteWork(v, helper.getAdapterPosition());
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
            case WorkInfoEntity.TYPE_WORK_CONTENT_NORM://工作要求
                if (helper instanceof JobsContentEdit3ViewHolder) {
                    ((JobsContentEdit3ViewHolder) helper).jobsContentContent.setKeyListener(keyListener);
                    ((JobsContentEdit3ViewHolder) helper).jobsContentContent.setText(TextUtils.isEmpty(item.getSubName()) ? "" : item.getSubName());
                    ((JobsContentEdit3ViewHolder) helper).jobsContentContent.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            item.setSubName(s.toString());
                        }
                    });
                }
                break;
            case WorkInfoEntity.TYPE_WORK_CONTENT_TITLE_SHOW://
                if (helper instanceof JobsContentText1ViewHolder) {
                    ((JobsContentText1ViewHolder) helper).itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.jobs_work_title_background));
                    ((JobsContentText1ViewHolder) helper).jobsContentIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.image_jobs_work_icon));
                    ((JobsContentText1ViewHolder) helper).jobsContentName.setText(TextUtils.isEmpty(item.getName()) ? "" : item.getName());
                    ((JobsContentText1ViewHolder) helper).jobsContentSub.setText(TextUtils.isEmpty(item.getSubName()) ? "" : item.getSubName());
                    ((JobsContentText1ViewHolder) helper).jobsContentGoto.setVisibility(View.INVISIBLE);
                }
                break;
        }
    }

    public interface OnWorkClickListener {
        void onAddWork(View view, int position);

        void onDeleteWork(View view, int position);
    }
}
