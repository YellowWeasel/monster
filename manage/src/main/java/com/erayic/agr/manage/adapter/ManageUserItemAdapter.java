package com.erayic.agr.manage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.common.AgrConstant;
import com.erayic.agr.manage.R;
import com.erayic.agr.manage.adapter.entity.ManageUserEntity;
import com.erayic.agr.manage.adapter.holder.ManageContentButtonViewHolder;
import com.erayic.agr.manage.adapter.holder.ManageContentImageViewHolder;
import com.erayic.agr.manage.adapter.holder.ManageContentTextViewHolder;
import com.erayic.agr.manage.adapter.holder.ManageDividerViewHolder;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ManageUserItemAdapter extends BaseMultiItemQuickAdapter<ManageUserEntity, BaseViewHolder> {
    private Context context;

    private OnItemTypeClickListener onItemTypeClickListener;

    public ManageUserItemAdapter(Context context, List<ManageUserEntity> data) {
        super(data);
        this.context = context;
    }

    public void setOnItemTypeClickListener(OnItemTypeClickListener onItemTypeClickListener) {
        this.onItemTypeClickListener = onItemTypeClickListener;
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ManageUserEntity.TYPE_DIVIDER:
                return new ManageDividerViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_divider, parent, false));
            case ManageUserEntity.TYPE_USER_ICON:
                return new ManageContentImageViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_image, parent, false));
            case ManageUserEntity.TYPE_USER_NAME:
                return new ManageContentTextViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_text, parent, false));
            case ManageUserEntity.TYPE_USER_TEL:
                return new ManageContentTextViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_text, parent, false));
            case ManageUserEntity.TYPE_USER_ENT:
                return new ManageContentTextViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_text, parent, false));
            case ManageUserEntity.TYPE_USER_BASE:
                return new ManageContentTextViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_text, parent, false));
            case ManageUserEntity.TYPE_USER_WEIXIN:
                return new ManageContentTextViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_text, parent, false));
            case ManageUserEntity.TYPE_USER_PASS:
                return new ManageContentButtonViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_buttom, parent, false));
            case ManageUserEntity.TYPE_USER_LOGOUT:
                return new ManageContentButtonViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_buttom, parent, false));
            default:
                return super.onCreateDefViewHolder(parent, viewType);
        }

    }

    @Override
    protected void convert(final BaseViewHolder helper, final ManageUserEntity item) {
        switch (helper.getItemViewType()) {
            case ManageUserEntity.TYPE_DIVIDER://分割线
                break;
            case ManageUserEntity.TYPE_USER_ICON://头像
                if (helper instanceof ManageContentImageViewHolder) {
                    ((ManageContentImageViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    ((ManageContentImageViewHolder) helper).manageContentName.setText(item.getTitle());
                    Glide.with(context).load(AgrConstant.IMAGE_URL_PREFIX + item.getSubTitle()).error(R.drawable.app_base_android_1).into(((ManageContentImageViewHolder) helper).manageContentHead);
                    ((ManageContentImageViewHolder) helper).itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onItemTypeClickListener != null) {
                                onItemTypeClickListener.onItemClick(v, helper.getAdapterPosition(), item);
                            }
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
            case ManageUserEntity.TYPE_USER_NAME://姓名
                if (helper instanceof ManageContentTextViewHolder) {
                    ((ManageContentTextViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentName.setText(item.getTitle());
                    ((ManageContentTextViewHolder) helper).manageContentSub.setText(item.getSubTitle());
                    ((ManageContentTextViewHolder) helper).itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onItemTypeClickListener != null) {
                                onItemTypeClickListener.onItemClick(v, helper.getAdapterPosition(), item);
                            }
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
            case ManageUserEntity.TYPE_USER_TEL://电话
                if (helper instanceof ManageContentTextViewHolder) {
                    ((ManageContentTextViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentName.setText(item.getTitle());
                    ((ManageContentTextViewHolder) helper).manageContentSub.setText(item.getSubTitle());
                    ((ManageContentTextViewHolder) helper).itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onItemTypeClickListener != null) {
                                onItemTypeClickListener.onItemClick(v, helper.getAdapterPosition(), item);
                            }
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
            case ManageUserEntity.TYPE_USER_ENT://企业
                if (helper instanceof ManageContentTextViewHolder) {
                    ((ManageContentTextViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentName.setText(item.getTitle());
                    ((ManageContentTextViewHolder) helper).manageContentSub.setText(item.getSubTitle());
                    ((ManageContentTextViewHolder) helper).itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onItemTypeClickListener != null) {
                                onItemTypeClickListener.onItemClick(v, helper.getAdapterPosition(), item);
                            }
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
            case ManageUserEntity.TYPE_USER_BASE://基地
                if (helper instanceof ManageContentTextViewHolder) {
                    ((ManageContentTextViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentName.setText(item.getTitle());
                    ((ManageContentTextViewHolder) helper).manageContentSub.setText(item.getSubTitle());
                    ((ManageContentTextViewHolder) helper).itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onItemTypeClickListener != null) {
                                onItemTypeClickListener.onItemClick(v, helper.getAdapterPosition(), item);
                            }
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
            case ManageUserEntity.TYPE_USER_WEIXIN://微信
                if (helper instanceof ManageContentTextViewHolder) {
                    ((ManageContentTextViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentName.setText(item.getTitle());
                    ((ManageContentTextViewHolder) helper).manageContentSub.setText(item.getSubTitle());
                    ((ManageContentTextViewHolder) helper).itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onItemTypeClickListener != null) {
                                onItemTypeClickListener.onItemClick(v, helper.getAdapterPosition(), item);
                            }
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
            case ManageUserEntity.TYPE_USER_PASS://密码
                if (helper instanceof ManageContentButtonViewHolder) {
                    ((ManageContentButtonViewHolder) helper).manageContentButton.setText(item.getTitle());
                    ((ManageContentButtonViewHolder) helper).manageContentButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onItemTypeClickListener != null) {
                                onItemTypeClickListener.onItemClick(v, helper.getAdapterPosition(), item);
                            }
                        }
                    });
                    ((ManageContentButtonViewHolder) helper).manageContentButton.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            return true;
                        }
                    });
                }
                break;
            case ManageUserEntity.TYPE_USER_LOGOUT://登出
                if (helper instanceof ManageContentButtonViewHolder) {
                    ((ManageContentButtonViewHolder) helper).manageContentButton.setText(item.getTitle());
                    ((ManageContentButtonViewHolder) helper).manageContentButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onItemTypeClickListener != null) {
                                onItemTypeClickListener.onItemClick(v, helper.getAdapterPosition(), item);
                            }
                        }
                    });
                    ((ManageContentButtonViewHolder) helper).manageContentButton.setOnLongClickListener(new View.OnLongClickListener() {
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

    public interface OnItemTypeClickListener {
        void onItemClick(View v, int position, ManageUserEntity entity);
    }
}
