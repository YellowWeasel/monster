package com.erayic.agr.manage.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.common.AgrConstant;
import com.erayic.agr.manage.R;
import com.erayic.agr.manage.adapter.entity.ManageProduceInfoEntity;
import com.erayic.agr.manage.adapter.holder.ManageContentEdit1ViewHolder;
import com.erayic.agr.manage.adapter.holder.ManageContentImageViewHolder;
import com.erayic.agr.manage.adapter.holder.ManageContentTextViewHolder;
import com.erayic.agr.manage.adapter.holder.ManageDividerViewHolder;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ManageProduceInfoAdapter extends BaseMultiItemQuickAdapter<ManageProduceInfoEntity, BaseViewHolder> {

    private Context context;
    private KeyListener keyListener;

    private OnProduceInfoClickListener onProduceInfoClickListener;

    public ManageProduceInfoAdapter(Context context, List<ManageProduceInfoEntity> data) {
        super(data);
        this.context = context;
    }

    public void setKeyListener(KeyListener keyListener) {
        this.keyListener = keyListener;
    }

    public void setOnProduceInfoClickListener(OnProduceInfoClickListener onProduceInfoClickListener) {
        this.onProduceInfoClickListener = onProduceInfoClickListener;
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ManageProduceInfoEntity.TYPE_DIVIDER:
                return new ManageDividerViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_divider, parent, false));
            case ManageProduceInfoEntity.TYPE_NAME:
                return new ManageContentEdit1ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_edit_1, parent, false));
            case ManageProduceInfoEntity.TYPE_ICON:
                return new ManageContentImageViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_image, parent, false));
            case ManageProduceInfoEntity.TYPE_TYPE:
                return new ManageContentTextViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_text, parent, false));
            case ManageProduceInfoEntity.TYPE_DEPICT:
            case ManageProduceInfoEntity.TYPE_IMAGE_LIST:
            default:
                return super.onCreateDefViewHolder(parent, viewType);
        }

    }

    @Override
    protected void convert(final BaseViewHolder helper, ManageProduceInfoEntity item) {
        switch (helper.getItemViewType()) {
            case ManageProduceInfoEntity.TYPE_DIVIDER:
                break;
            case ManageProduceInfoEntity.TYPE_NAME:
                if (helper instanceof ManageContentEdit1ViewHolder) {
                    ((ManageContentEdit1ViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    ((ManageContentEdit1ViewHolder) helper).manageContentGoto.setVisibility(View.INVISIBLE);
                    ((ManageContentEdit1ViewHolder) helper).manageContentName.setText(item.getName());
                    ((ManageContentEdit1ViewHolder) helper).manageContentSubName.setText(TextUtils.isEmpty(item.getSubName()) ? "" : item.getSubName());
                    ((ManageContentEdit1ViewHolder) helper).manageContentSubName.setKeyListener(keyListener);
                    ((ManageContentEdit1ViewHolder) helper).manageContentSubName.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            getData().get(helper.getAdapterPosition()).setSubName(s.toString());
                        }
                    });
                    if (keyListener == null) {
                        ((ManageContentEdit1ViewHolder) helper).manageContentSubName.setBackground(null);
                    } else {
                        ((ManageContentEdit1ViewHolder) helper).manageContentSubName.setBackground(ContextCompat.getDrawable(context, R.drawable.app_base_edit_back_gray));
                    }
                }
                break;
            case ManageProduceInfoEntity.TYPE_ICON:
                if (helper instanceof ManageContentImageViewHolder) {
                    ((ManageContentImageViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    ((ManageContentImageViewHolder) helper).manageContentName.setText(item.getName());
                    Glide.with(context).load(TextUtils.isEmpty(item.getSubName()) ? "" : (AgrConstant.IMAGE_URL_IMAGE + item.getSubName()))
                            .apply(AgrConstant.iconOptions)
                            .into(((ManageContentImageViewHolder) helper).manageContentHead);
                    ((ManageContentImageViewHolder) helper).itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (keyListener != null && onProduceInfoClickListener != null)
                                onProduceInfoClickListener.onItemClick(v, helper.getItemViewType(), helper.getAdapterPosition());
                        }
                    });
                    ((ManageContentImageViewHolder) helper).itemView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            return true;
                        }
                    });
                }
                break;
            case ManageProduceInfoEntity.TYPE_TYPE:
                if (helper instanceof ManageContentTextViewHolder) {
                    ((ManageContentTextViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentName.setText(item.getName());
                    ((ManageContentTextViewHolder) helper).manageContentSub.setText(TextUtils.isEmpty(item.getSubName()) ? "未指定" : item.getSubName());
                    ((ManageContentTextViewHolder) helper).itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (keyListener != null && onProduceInfoClickListener != null)
                                onProduceInfoClickListener.onItemClick(v, helper.getItemViewType(), helper.getAdapterPosition());
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
            case ManageProduceInfoEntity.TYPE_DEPICT:
                break;
            case ManageProduceInfoEntity.TYPE_IMAGE_LIST:
                break;
            default:
                break;
        }
    }

    public interface OnProduceInfoClickListener {
        void onItemClick(View v, int itemType, int position);
    }
}
