package com.erayic.agr.manage.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.common.net.back.enums.EnumUserRole;
import com.erayic.agr.manage.R;
import com.erayic.agr.manage.adapter.entity.ManageMineEntity;
import com.erayic.agr.manage.adapter.holder.ManageContentTextViewHolder;
import com.erayic.agr.manage.adapter.holder.ManageDividerViewHolder;
import com.erayic.agr.manage.adapter.holder.ManageMineInfoViewHolder;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ManageMineItemAdapter extends BaseMultiItemQuickAdapter<ManageMineEntity, BaseViewHolder> {

    private Context context;

    private onItemUrlClickListener onItemUrlClickListener;

    public ManageMineItemAdapter(Context context, List<ManageMineEntity> data) {
        super(data);
        this.context = context;
    }

    public void setOnItemUrlClickListener(ManageMineItemAdapter.onItemUrlClickListener onItemUrlClickListener) {
        this.onItemUrlClickListener = onItemUrlClickListener;
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ManageMineEntity.TYPE_DIVIDER:
                return new ManageDividerViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_divider, parent, false));
            case ManageMineEntity.TYPE_INFO:
                return new ManageMineInfoViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_mine_info, parent, false));
            case ManageMineEntity.TYPE_CONTENT:
                return new ManageContentTextViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_text, parent, false));
            default:
                return super.onCreateDefViewHolder(parent, viewType);
        }

    }

    @Override
    protected void convert(BaseViewHolder helper, final ManageMineEntity item) {
        switch (helper.getItemViewType()) {
            case ManageMineEntity.TYPE_DIVIDER:
                if (helper instanceof ManageDividerViewHolder) {

                }
                break;
            case ManageMineEntity.TYPE_INFO:
                if (helper instanceof ManageMineInfoViewHolder) {
                    ((ManageMineInfoViewHolder) helper).manageMineInfoName.setText(item.getPersonalInfo().getUserName());
                    ((ManageMineInfoViewHolder) helper).manageMineInfoRole.setText(EnumUserRole.getRoleDes(item.getPersonalInfo().getUserRole()));
                    ((ManageMineInfoViewHolder) helper).itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onItemUrlClickListener != null)
                                onItemUrlClickListener.onItemClick(v, item.getToUrl());
                        }
                    });
                    ((ManageMineInfoViewHolder) helper).itemView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            return true;
                        }
                    });
                }
                break;
            case ManageMineEntity.TYPE_CONTENT:
                if (helper instanceof ManageContentTextViewHolder) {
                    ((ManageContentTextViewHolder) helper).manageContentName.setText(item.getName());
                    ((ManageContentTextViewHolder) helper).manageContentSub.setText(item.getSubName());
                    ((ManageContentTextViewHolder) helper).manageContentIcon.setImageDrawable(ContextCompat.getDrawable(context, item.getIcon()));
                    ((ManageContentTextViewHolder) helper).itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onItemUrlClickListener != null)
                                onItemUrlClickListener.onItemClick(v, item.getToUrl());
                        }
                    });
                    ((ManageContentTextViewHolder) helper).itemView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            return true;
                        }
                    });
                }
                break;
            default:
                break;
        }
    }

    public interface onItemUrlClickListener {
        void onItemClick(View v, String url);
    }
}
