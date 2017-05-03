package com.erayic.agr.manage.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.manage.R;
import com.erayic.agr.manage.adapter.entity.ManageBaseInfoEntity;
import com.erayic.agr.manage.adapter.holder.ManageContentDepictViewHolder;
import com.erayic.agr.manage.adapter.holder.ManageContentTextViewHolder;
import com.erayic.agr.manage.adapter.holder.ManageDividerViewHolder;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ManageBaseInfoAdapter extends BaseMultiItemQuickAdapter<ManageBaseInfoEntity, BaseViewHolder> {

    private Context context;

    private OnItemTypeClickListener onItemTypeClickListener;

    public ManageBaseInfoAdapter(Context context, List<ManageBaseInfoEntity> data) {
        super(data);
        this.context = context;
    }

    public void setOnItemTypeClickListener(OnItemTypeClickListener onItemTypeClickListener) {
        this.onItemTypeClickListener = onItemTypeClickListener;
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ManageBaseInfoEntity.TYPE_DIVIDER:
                return new ManageDividerViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_divider, parent, false));
            case ManageBaseInfoEntity.TYPE_BASE_NAME:
                return new ManageContentTextViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_text, parent, false));
            case ManageBaseInfoEntity.TYPE_BASE_IMAGE:
                return new ManageContentTextViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_text, parent, false));
            case ManageBaseInfoEntity.TYPE_BASE_POSITION:
                return new ManageContentTextViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_text, parent, false));
            case ManageBaseInfoEntity.TYPE_BASE_ADD_UNIT:
                return new ManageContentTextViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_text, parent, false));
            case ManageBaseInfoEntity.TYPE_BASE_UNIT_NAME:
                return new ManageContentTextViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_text, parent, false));
            case ManageBaseInfoEntity.TYPE_BASE_DEPICT:
                return new ManageContentDepictViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_depict, parent, false));
            default:
                return super.onCreateDefViewHolder(parent, viewType);
        }

    }

    @Override
    protected void convert(final BaseViewHolder helper, final ManageBaseInfoEntity item) {
        switch (helper.getItemViewType()) {
            case ManageBaseInfoEntity.TYPE_DIVIDER://分割线
                if (helper instanceof ManageDividerViewHolder) {
                    ((ManageDividerViewHolder) helper).adapterDividerName.setText(item.getName());
                }
                break;
            case ManageBaseInfoEntity.TYPE_BASE_NAME://名称
                if (helper instanceof ManageContentTextViewHolder) {
                    ((ManageContentTextViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentName.setText(TextUtils.isEmpty(item.getName()) ? "" : item.getName());
                    ((ManageContentTextViewHolder) helper).manageContentSub.setText(TextUtils.isEmpty(item.getSubName()) ? "" : item.getSubName());
                    ((ManageContentTextViewHolder) helper).itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onItemTypeClickListener != null)
                                onItemTypeClickListener.onItemClick(v, helper.getAdapterPosition(), item);
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
            case ManageBaseInfoEntity.TYPE_BASE_IMAGE://图集
                if (helper instanceof ManageContentTextViewHolder) {
                    ((ManageContentTextViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentName.setText(TextUtils.isEmpty(item.getName()) ? "" : item.getName());
                    ((ManageContentTextViewHolder) helper).manageContentSub.setText(TextUtils.isEmpty(item.getSubName()) ? "" : item.getSubName());
                    ((ManageContentTextViewHolder) helper).itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onItemTypeClickListener != null)
                                onItemTypeClickListener.onItemClick(v, helper.getAdapterPosition(), item);
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
            case ManageBaseInfoEntity.TYPE_BASE_POSITION://位置
                if (helper instanceof ManageContentTextViewHolder) {
                    ((ManageContentTextViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentName.setText(TextUtils.isEmpty(item.getName()) ? "" : item.getName());
                    ((ManageContentTextViewHolder) helper).manageContentSub.setText(TextUtils.isEmpty(item.getSubName()) ? "" : item.getSubName());
                    ((ManageContentTextViewHolder) helper).itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onItemTypeClickListener != null)
                                onItemTypeClickListener.onItemClick(v, helper.getAdapterPosition(), item);
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
            case ManageBaseInfoEntity.TYPE_BASE_ADD_UNIT://增加管理单元
                if (helper instanceof ManageContentTextViewHolder) {
                    ((ManageContentTextViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentGoto.setVisibility(View.INVISIBLE);
                    ((ManageContentTextViewHolder) helper).manageContentName.setText(TextUtils.isEmpty(item.getName()) ? "" : item.getName());
                    ((ManageContentTextViewHolder) helper).manageContentSub.setText(TextUtils.isEmpty(item.getSubName()) ? "" : item.getSubName());
                    ((ManageContentTextViewHolder) helper).manageContentSub.setTextColor(ContextCompat.getColor(context, R.color.app_base_text_price));
                    ((ManageContentTextViewHolder) helper).itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onItemTypeClickListener != null)
                                onItemTypeClickListener.onItemClick(v, helper.getAdapterPosition(), item);
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
            case ManageBaseInfoEntity.TYPE_BASE_UNIT_NAME://管理单元列表
                if (helper instanceof ManageContentTextViewHolder) {
                    ((ManageContentTextViewHolder) helper).manageContentIcon.setVisibility(View.INVISIBLE);
                    ((ManageContentTextViewHolder) helper).manageContentName.setText(TextUtils.isEmpty(item.getName()) ? "" : item.getName());
                    ((ManageContentTextViewHolder) helper).manageContentSub.setText(TextUtils.isEmpty(item.getSubName()) ? "未指定" : item.getSubName());
                    ((ManageContentTextViewHolder) helper).itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onItemTypeClickListener != null)
                                onItemTypeClickListener.onItemClick(v, helper.getAdapterPosition(), item);
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
            case ManageBaseInfoEntity.TYPE_BASE_DEPICT://基地描述
                if (helper instanceof ManageContentDepictViewHolder) {
                    ((ManageContentDepictViewHolder) helper).manageContentDepictIcon.setVisibility(View.GONE);
                    ((ManageContentDepictViewHolder) helper).manageContentDepictName.setText(item.getName());
                    ((ManageContentDepictViewHolder) helper).manageContentDepictSub.setText(item.getSubName());
                    ((ManageContentDepictViewHolder) helper).itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onItemTypeClickListener != null)
                                onItemTypeClickListener.onItemClick(v, helper.getAdapterPosition(), item);
                        }
                    });
                    ((ManageContentDepictViewHolder) helper).itemView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            return true;
                        }
                    });
                }
            default:
                break;
        }
    }

    public interface OnItemTypeClickListener {
        void onItemClick(View v, int position, ManageBaseInfoEntity entity);
    }


}
