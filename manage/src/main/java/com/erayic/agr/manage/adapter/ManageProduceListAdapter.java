package com.erayic.agr.manage.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

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

    private OnProduceItemClickListener onProduceItemClickListener;

    public ManageProduceListAdapter(Context context, List<CommonProduceListBean> data) {
        super(R.layout.adapter_manage_content_text_2, data);
        this.context = context;
    }

    public void setOnProduceItemClickListener(OnProduceItemClickListener onProduceItemClickListener) {
        this.onProduceItemClickListener = onProduceItemClickListener;
    }

    @Override
    protected void convert(ManageContentText2ViewHolder helper, final CommonProduceListBean item) {

        Glide.with(context).
                load(TextUtils.isEmpty(item.getIcon()) ? "" : (AgrConstant.IMAGE_URL_IMAGE + item.getIcon()))
                .apply(AgrConstant.iconOptions).into(helper.manageContentIcon);

        helper.manageContentName.setText(item.getProductName());
        helper.manageContentSubName.setText(TextUtils.isEmpty(item.getClassifyName()) ? "未指定类别" : item.getClassifyName());
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onProduceItemClickListener != null)
                    onProduceItemClickListener.onItemClick(v, item.getProductName(), item.getProID());
            }
        });
        helper.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
    }

    public interface OnProduceItemClickListener {
        void onItemClick(View view, String proName, String proID);
    }
}
