package com.erayic.agr.manage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.manage.R;
import com.erayic.agr.manage.adapter.entity.ManageSelectEntity;
import com.erayic.agr.manage.adapter.holder.ManageSelectContentViewHolder;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ManageSelectAdapter extends BaseMultiItemQuickAdapter<ManageSelectEntity, BaseViewHolder> {

    private Context context;
    private int position = -1;
    private boolean isRadio;//是否单选

    public ManageSelectAdapter(Context context, List<ManageSelectEntity> data, boolean isRadio) {
        super(data);
        this.context = context;
        this.isRadio = isRadio;
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ManageSelectEntity.TYPE_SELECT_USER://用户
                return new ManageSelectContentViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_select_content_1, parent, false));
            case ManageSelectEntity.TYPE_SELECT_PRODUCT://产品
                return new ManageSelectContentViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_select_content_1, parent, false));
            case ManageSelectEntity.TYPE_SELECT_FERTILIZER://化肥
                return new ManageSelectContentViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_select_content_1, parent, false));
            case ManageSelectEntity.TYPE_SELECT_PESTICIDE://农药
                return new ManageSelectContentViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_select_content_1, parent, false));
            case ManageSelectEntity.TYPE_SELECT_SEED://种子
                return new ManageSelectContentViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_select_content_1, parent, false));
        }
        return super.onCreateDefViewHolder(parent, viewType);
    }

    @Override
    protected void convert(final BaseViewHolder helper, ManageSelectEntity item) {
        switch (helper.getItemViewType()) {
            case ManageSelectEntity.TYPE_SELECT_USER://用户
                if (helper instanceof ManageSelectContentViewHolder) {
                    ((ManageSelectContentViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    Glide.with(context)
                            .load(item.getIcon())
                            .placeholder(R.drawable.app_base_default_plant)//待加载时显示
                            .error(R.drawable.app_base_default_plant)//加载错误时显示
                            .into(((ManageSelectContentViewHolder) helper).manageContentIcon);
                    ((ManageSelectContentViewHolder) helper).manageContentName.setText(item.getName());
                    ((ManageSelectContentViewHolder) helper).manageContentSub.setText(item.getSubName());
                    ((ManageSelectContentViewHolder) helper).manageContentCheck.setChecked(item.isCheck());
                    ((ManageSelectContentViewHolder) helper).itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (isRadio) {
                                if (position != -1) {
                                    getData().get(position).setCheck(!getData().get(position).isCheck());
                                    notifyItemChanged(position);
                                }
                                position = helper.getAdapterPosition();
                                getData().get(position).setCheck(!getData().get(position).isCheck());
                                notifyItemChanged(position);
                            } else {
                                getData().get(helper.getAdapterPosition()).setCheck(!getData().get(helper.getAdapterPosition()).isCheck());
                                notifyItemChanged(helper.getAdapterPosition());
                            }
                        }
                    });
                    ((ManageSelectContentViewHolder) helper).itemView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            return true;
                        }
                    });
                }
                break;
            case ManageSelectEntity.TYPE_SELECT_PRODUCT://产品
                if (helper instanceof ManageSelectContentViewHolder) {
                    ((ManageSelectContentViewHolder) helper).manageContentIcon.setVisibility(View.VISIBLE);
                    Glide.with(context)
                            .load(item.getIcon())
                            .placeholder(R.drawable.app_base_default_plant)//待加载时显示
                            .error(R.drawable.app_base_default_plant)//加载错误时显示
                            .into(((ManageSelectContentViewHolder) helper).manageContentIcon);
                    ((ManageSelectContentViewHolder) helper).manageContentName.setText(item.getName());
                    ((ManageSelectContentViewHolder) helper).manageContentSub.setText(item.getSubName());
                    ((ManageSelectContentViewHolder) helper).manageContentCheck.setChecked(item.isCheck());
                    ((ManageSelectContentViewHolder) helper).itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (isRadio) {
                                if (position != -1) {
                                    getData().get(position).setCheck(!getData().get(position).isCheck());
                                    notifyItemChanged(position);
                                }
                                position = helper.getAdapterPosition();
                                getData().get(position).setCheck(!getData().get(position).isCheck());
                                notifyItemChanged(position);
                            } else {
                                getData().get(helper.getAdapterPosition()).setCheck(!getData().get(helper.getAdapterPosition()).isCheck());
                                notifyItemChanged(helper.getAdapterPosition());
                            }
                        }
                    });
                    ((ManageSelectContentViewHolder) helper).itemView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            return true;
                        }
                    });

                }
                break;
            case ManageSelectEntity.TYPE_SELECT_FERTILIZER://化肥
                if (helper instanceof ManageSelectContentViewHolder) {
                    ((ManageSelectContentViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    Glide.with(context)
                            .load(item.getIcon())
                            .placeholder(R.drawable.app_base_default_plant)//待加载时显示
                            .error(R.drawable.app_base_default_plant)//加载错误时显示
                            .into(((ManageSelectContentViewHolder) helper).manageContentIcon);
                    ((ManageSelectContentViewHolder) helper).manageContentName.setText(item.getName());
                    ((ManageSelectContentViewHolder) helper).manageContentSub.setText(item.getSubName());
                    ((ManageSelectContentViewHolder) helper).manageContentCheck.setChecked(item.isCheck());
                    ((ManageSelectContentViewHolder) helper).itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (isRadio) {
                                if (position != -1) {
                                    getData().get(position).setCheck(!getData().get(position).isCheck());
                                    notifyItemChanged(position);
                                }
                                position = helper.getAdapterPosition();
                                getData().get(position).setCheck(!getData().get(position).isCheck());
                                notifyItemChanged(position);
                            } else {
                                getData().get(helper.getAdapterPosition()).setCheck(!getData().get(helper.getAdapterPosition()).isCheck());
                                notifyItemChanged(helper.getAdapterPosition());
                            }
                        }
                    });
                    ((ManageSelectContentViewHolder) helper).itemView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            return true;
                        }
                    });
                }
                break;
            case ManageSelectEntity.TYPE_SELECT_PESTICIDE://农药
                if (helper instanceof ManageSelectContentViewHolder) {
                    ((ManageSelectContentViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    Glide.with(context)
                            .load(item.getIcon())
                            .placeholder(R.drawable.app_base_default_plant)//待加载时显示
                            .error(R.drawable.app_base_default_plant)//加载错误时显示
                            .into(((ManageSelectContentViewHolder) helper).manageContentIcon);
                    ((ManageSelectContentViewHolder) helper).manageContentName.setText(item.getName());
                    ((ManageSelectContentViewHolder) helper).manageContentSub.setText(item.getSubName());
                    ((ManageSelectContentViewHolder) helper).manageContentCheck.setChecked(item.isCheck());
                    ((ManageSelectContentViewHolder) helper).itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (isRadio) {
                                if (position != -1) {
                                    getData().get(position).setCheck(!getData().get(position).isCheck());
                                    notifyItemChanged(position);
                                }
                                position = helper.getAdapterPosition();
                                getData().get(position).setCheck(!getData().get(position).isCheck());
                                notifyItemChanged(position);
                            } else {
                                getData().get(helper.getAdapterPosition()).setCheck(!getData().get(helper.getAdapterPosition()).isCheck());
                                notifyItemChanged(helper.getAdapterPosition());
                            }
                        }
                    });
                    ((ManageSelectContentViewHolder) helper).itemView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            return true;
                        }
                    });
                }
                break;
            case ManageSelectEntity.TYPE_SELECT_SEED://种子
                if (helper instanceof ManageSelectContentViewHolder) {
                    ((ManageSelectContentViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    Glide.with(context)
                            .load(item.getIcon())
                            .placeholder(R.drawable.app_base_default_plant)//待加载时显示
                            .error(R.drawable.app_base_default_plant)//加载错误时显示
                            .into(((ManageSelectContentViewHolder) helper).manageContentIcon);
                    ((ManageSelectContentViewHolder) helper).manageContentName.setText(item.getName());
                    ((ManageSelectContentViewHolder) helper).manageContentSub.setText(item.getSubName());
                    ((ManageSelectContentViewHolder) helper).manageContentCheck.setChecked(item.isCheck());
                    ((ManageSelectContentViewHolder) helper).itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (isRadio) {
                                if (position != -1) {
                                    getData().get(position).setCheck(!getData().get(position).isCheck());
                                    notifyItemChanged(position);
                                }
                                position = helper.getAdapterPosition();
                                getData().get(position).setCheck(!getData().get(position).isCheck());
                                notifyItemChanged(position);
                            } else {
                                getData().get(helper.getAdapterPosition()).setCheck(!getData().get(helper.getAdapterPosition()).isCheck());
                                notifyItemChanged(helper.getAdapterPosition());
                            }
                        }
                    });
                    ((ManageSelectContentViewHolder) helper).itemView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            return true;
                        }
                    });
                }
                break;
        }
    }
}
