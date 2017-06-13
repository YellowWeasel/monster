package com.erayic.agr.jobs.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.common.config.CustomLinearLayoutManager;
import com.erayic.agr.common.config.FullyGridLayoutManager;
import com.erayic.agr.common.net.back.work.CommonJobsInfoBean;
import com.erayic.agr.jobs.R;
import com.erayic.agr.jobs.adapter.entity.JobsInfoEntity;
import com.erayic.agr.jobs.adapter.holder.JobsInfoItemImgsViewHolder;
import com.erayic.agr.jobs.adapter.holder.JobsInfoItemListViewHolder;
import com.erayic.agr.jobs.adapter.holder.JobsInfoItemTextViewHolder;

import java.util.List;
import java.util.Map;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class JobsInfoItemAdapter extends BaseMultiItemQuickAdapter<JobsInfoEntity, BaseViewHolder> {

    private Context context;
    private JobsBatchItemAdapter adapter;

    private JobsInfoGridImageAdapter imageAdapter;
    private boolean isEdit = false;

    public JobsInfoItemAdapter(Context context, List<JobsInfoEntity> data) {
        super(data);
        this.context = context;
        adapter = new JobsBatchItemAdapter(context, null);
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
        adapter.setEdit(edit);
    }

    public void setImageAdapter(JobsInfoGridImageAdapter imageAdapter) {
        this.imageAdapter = imageAdapter;
    }

    public List<CommonJobsInfoBean.BatchInfo> getBatchList() {
        return adapter.getData();
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case JobsInfoEntity.TYPE_WORK_CONTENT://工作内容
                return new JobsInfoItemTextViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_jobs_jobs_info_text, parent, false));
            case JobsInfoEntity.TYPE_WORK_REQUIRE://工作要求
                return new JobsInfoItemTextViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_jobs_jobs_info_text, parent, false));
            case JobsInfoEntity.TYPE_WORK_UNIT://工作范围
                return new JobsInfoItemListViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_jobs_jobs_info_list, parent, false));
            case JobsInfoEntity.TYPE_WORK_NOTE://图片集合
                return new JobsInfoItemImgsViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_jobs_jobs_info_content, parent, false));
        }
        return super.onCreateDefViewHolder(parent, viewType);
    }

    @Override
    protected void convert(BaseViewHolder helper, final JobsInfoEntity item) {
        switch (helper.getItemViewType()) {
            case JobsInfoEntity.TYPE_WORK_CONTENT://工作内容
                if (helper instanceof JobsInfoItemTextViewHolder) {
                    ((JobsInfoItemTextViewHolder) helper).jobsContentName.setText(TextUtils.isEmpty(item.getName()) ? "" : item.getName());
//                    ((JobsInfoItemTextViewHolder) helper).jobsContentSub.setText(TextUtils.isEmpty(item.getSubName()) ? "" : item.getSubName());
//                    ((JobsInfoItemTextViewHolder) helper).jobsContentSub.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//下划线
//                    ((JobsInfoItemTextViewHolder) helper).jobsContentSub.getPaint().setAntiAlias(true);//抗锯齿
                    ((JobsInfoItemTextViewHolder) helper).jobsContentSub.setVisibility(View.GONE);
                    ((JobsInfoItemTextViewHolder) helper).jobsContentContent.setTextSize(18);
                    ((JobsInfoItemTextViewHolder) helper).jobsContentContent.setTextColor(ContextCompat.getColor(context, R.color.app_base_text_title_1));
                    String strContentBuffer = "";
                    for (Map.Entry entry : item.getMap().entrySet()) {
                        strContentBuffer += entry.getValue().toString() + "\n";
                    }
                    ((JobsInfoItemTextViewHolder) helper).jobsContentContent.setText(strContentBuffer.substring(0, strContentBuffer.length() - 1));
                }
                break;
            case JobsInfoEntity.TYPE_WORK_REQUIRE://工作要求
                if (helper instanceof JobsInfoItemTextViewHolder) {
                    ((JobsInfoItemTextViewHolder) helper).jobsContentName.setText(TextUtils.isEmpty(item.getName()) ? "" : item.getName());
//                    Drawable textImg = ContextCompat.getDrawable(context, R.drawable.image_jobs_work_icon);
//                    textImg.setBounds(0, 0, textImg.getMinimumWidth(), textImg.getMinimumHeight());
//                    ((JobsInfoItemTextViewHolder) helper).jobsContentName.setCompoundDrawables(null, textImg, null, null);
                    ((JobsInfoItemTextViewHolder) helper).jobsContentSub.setVisibility(View.GONE);
                    ((JobsInfoItemTextViewHolder) helper).jobsContentContent.setTextColor(ContextCompat.getColor(context, R.color.app_base_text_title_1));
                    ((JobsInfoItemTextViewHolder) helper).jobsContentContent.setText(TextUtils.isEmpty(item.getSubName()) ? "" : item.getSubName());
                }
                break;
            case JobsInfoEntity.TYPE_WORK_UNIT://工作范围
                if (helper instanceof JobsInfoItemListViewHolder) {
                    ((JobsInfoItemListViewHolder) helper).jobsContentName.setText(TextUtils.isEmpty(item.getName()) ? "" : item.getName());
                    //使用线性布局管理器
                    CustomLinearLayoutManager manager = new CustomLinearLayoutManager(context);
                    manager.setScrollEnabled(true);//滑动监听
                    ((JobsInfoItemListViewHolder) helper).jobsContentList.setLayoutManager(manager);
                    ((JobsInfoItemListViewHolder) helper).jobsContentList.setAdapter(adapter);
                    adapter.setNewData((List<CommonJobsInfoBean.BatchInfo>) item.getMap().get("key1"));
                }
                break;
            case JobsInfoEntity.TYPE_WORK_NOTE://图片集合
                if (helper instanceof JobsInfoItemImgsViewHolder) {
                    ((JobsInfoItemImgsViewHolder) helper).jobsContentEdit.setKeyListener(isEdit ? (new EditText(context).getKeyListener()) : null);
                    ((JobsInfoItemImgsViewHolder) helper).jobsContentEdit.setText(TextUtils.isEmpty(item.getSubName()) ? "" : item.getSubName());
                    ((JobsInfoItemImgsViewHolder) helper).jobsContentEdit.setHint("请简要概述工作情况");
                    ((JobsInfoItemImgsViewHolder) helper).jobsContentText.setText(TextUtils.isEmpty(item.getSubName()) ? "未填写说明" : item.getSubName());
                    ((JobsInfoItemImgsViewHolder) helper).jobsContentName.setText(TextUtils.isEmpty(item.getName()) ? "" : item.getName());
                    FullyGridLayoutManager manager = new FullyGridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false);
                    ((JobsInfoItemImgsViewHolder) helper).jobsContentList.setLayoutManager(manager);
                    ((JobsInfoItemImgsViewHolder) helper).jobsContentList.setAdapter(imageAdapter);
                    ((JobsInfoItemImgsViewHolder) helper).jobsContentEdit.addTextChangedListener(new TextWatcher() {
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
                    if (isEdit) {
                        ((JobsInfoItemImgsViewHolder) helper).jobsContentEdit.setVisibility(View.VISIBLE);
                        ((JobsInfoItemImgsViewHolder) helper).jobsContentText.setVisibility(View.GONE);

                    } else {
                        ((JobsInfoItemImgsViewHolder) helper).jobsContentEdit.setVisibility(View.GONE);
                        ((JobsInfoItemImgsViewHolder) helper).jobsContentText.setVisibility(View.VISIBLE);
                    }

                }
                break;
        }
    }


}