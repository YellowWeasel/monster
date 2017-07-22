package com.erayic.agr.jobs.adapter;

import android.content.Context;
import android.text.TextUtils;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.erayic.agr.common.AgrConstant;
import com.erayic.agr.common.net.back.work.CommonJobsListManagerBean;
import com.erayic.agr.jobs.R;
import com.erayic.agr.jobs.adapter.holder.JobsListGridImageViewHolder;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class JobsListGridImageAdapter extends BaseQuickAdapter<CommonJobsListManagerBean.RecordsBean, JobsListGridImageViewHolder> {

    private Context context;

    public JobsListGridImageAdapter(Context context, List<CommonJobsListManagerBean.RecordsBean> list) {
        super(R.layout.adapter_jobs_jobs_list_image, list);
        this.context = context;
    }

    @Override
    protected void convert(JobsListGridImageViewHolder helper, CommonJobsListManagerBean.RecordsBean item) {

        Glide.with(context.getApplicationContext())
                .load(TextUtils.isEmpty(item.getImgPath()) ? "" : (AgrConstant.IMAGE_URL_IMAGE + item.getImgPath()))
                .apply(AgrConstant.contentDefaultOptions)
                .into(helper.jobsContentImage);
    }
}
