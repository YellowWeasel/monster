package com.erayic.agr.manage.adapter;

import android.content.Context;
import android.text.TextUtils;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.erayic.agr.common.AgrConstant;
import com.erayic.agr.common.net.back.CommonProduceListBean;
import com.erayic.agr.manage.R;
import com.erayic.agr.manage.adapter.holder.ManageContentText2ViewHolder;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ManageProduceListAdapter extends BaseQuickAdapter<CommonProduceListBean, ManageContentText2ViewHolder> {

    private Context context;

    public ManageProduceListAdapter(Context context, List<CommonProduceListBean> data) {
        super(R.layout.adapter_manage_content_text_2, data);
        this.context = context;
    }

    @Override
    protected void convert(ManageContentText2ViewHolder helper, CommonProduceListBean item) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.color.color_f6)
                .error(R.drawable.app_base_android_1)
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(context).load(AgrConstant.IMAGE_URL_PREFIX + item.getIcon()).apply(options).into(helper.manageContentIcon);
        helper.manageContentName.setText(item.getProductName());
        helper.manageContentSubName.setText(TextUtils.isEmpty(item.getClassifyName()) ? "未指定类别" : item.getClassifyName());
    }
}
