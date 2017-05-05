package com.erayic.agr.manage.adapter;

import android.content.Context;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.erayic.agr.common.net.back.CommonResourceBean;
import com.erayic.agr.manage.R;
import com.erayic.agr.manage.adapter.holder.ManageResourceItemViewHolder;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ManageResourceListAdapter extends BaseQuickAdapter<CommonResourceBean, ManageResourceItemViewHolder> {

    private Context context;
    private OnItemClickListener onItemClickListener;

    public ManageResourceListAdapter(Context context, List<CommonResourceBean> data) {
        super(R.layout.adapter_manage_content_text, data);
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    protected void convert(ManageResourceItemViewHolder helper, final CommonResourceBean item) {
        helper.manageContentIcon.setVisibility(View.GONE);
        helper.manageContentName.setText(item.getName() == null ? "" : item.getName());
        helper.manageContentSub.setText(item.getCommonDesc() == null ? "" : item.getCommonDesc());

        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null)
                    onItemClickListener.onClick(v, item);
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
        void onClick(View v, CommonResourceBean bean);
    }

}
