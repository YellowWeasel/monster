package com.erayic.agr.manage.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.manage.R;
import com.erayic.agr.manage.adapter.entity.ManageUnitInfoEntity;
import com.erayic.agr.manage.adapter.holder.ManageContentGroupViewHolder;
import com.erayic.agr.manage.adapter.holder.ManageContentTextViewHolder;
import com.erayic.agr.manage.adapter.holder.ManageDividerViewHolder;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ManageUnitInfoAdapter extends BaseMultiItemQuickAdapter<ManageUnitInfoEntity, BaseViewHolder> {

    private Context context;

    private OnItemTypeClickListener onItemTypeClickListener;

    public ManageUnitInfoAdapter(Context context, List<ManageUnitInfoEntity> data) {
        super(data);
        this.context = context;
    }

    public void setOnItemTypeClickListener(OnItemTypeClickListener onItemTypeClickListener) {
        this.onItemTypeClickListener = onItemTypeClickListener;
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ManageUnitInfoEntity.TYPE_DIVIDER:
                return new ManageDividerViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_divider, parent, false));
            case ManageUnitInfoEntity.TYPE_UNIT_NAME:
                return new ManageContentTextViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_text, parent, false));
            case ManageUnitInfoEntity.TYPE_UNIT_AREA:
                return new ManageContentTextViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_text, parent, false));
            case ManageUnitInfoEntity.TYPE_UNIT_REGIN:
                return new ManageContentTextViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_text, parent, false));
            case ManageUnitInfoEntity.TYPE_UNIT_WORK:
                return new ManageContentTextViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_text, parent, false));
            case ManageUnitInfoEntity.TYPE_UNIT_GREEN:
                return new ManageContentGroupViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_group, parent, false));
            default:
                return super.onCreateDefViewHolder(parent, viewType);
        }

    }

    @Override
    protected void convert(final BaseViewHolder helper, final ManageUnitInfoEntity item) {
        switch (helper.getItemViewType()) {
            case ManageUnitInfoEntity.TYPE_UNIT_NAME:
                if (helper instanceof ManageContentTextViewHolder) {
                    ((ManageContentTextViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentName.setText(TextUtils.isEmpty(item.getName()) ? "" : item.getName());
                    ((ManageContentTextViewHolder) helper).manageContentSub.setText(TextUtils.isEmpty(item.getSubName()) ? "" : item.getSubName());
                    ((ManageContentTextViewHolder) helper).itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onItemTypeClickListener != null)
                                onItemTypeClickListener.onItemClick(v, helper.getAdapterPosition(), item, false);
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
            case ManageUnitInfoEntity.TYPE_UNIT_AREA:
                if (helper instanceof ManageContentTextViewHolder) {
                    ((ManageContentTextViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentName.setText(TextUtils.isEmpty(item.getName()) ? "" : item.getName());
                    ((ManageContentTextViewHolder) helper).manageContentSub.setText(TextUtils.isEmpty(item.getSubName()) ? "" : item.getSubName() + "亩");
                    ((ManageContentTextViewHolder) helper).itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onItemTypeClickListener != null)
                                onItemTypeClickListener.onItemClick(v, helper.getAdapterPosition(), item, false);
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
            case ManageUnitInfoEntity.TYPE_UNIT_REGIN:
                if (helper instanceof ManageContentTextViewHolder) {
                    ((ManageContentTextViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentName.setText(TextUtils.isEmpty(item.getName()) ? "" : item.getName());
                    ((ManageContentTextViewHolder) helper).manageContentSub.setText(TextUtils.isEmpty(item.getSubName()) ? "" : item.getSubName());
                    ((ManageContentTextViewHolder) helper).itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onItemTypeClickListener != null)
                                onItemTypeClickListener.onItemClick(v, helper.getAdapterPosition(), item, false);
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
            case ManageUnitInfoEntity.TYPE_UNIT_WORK:
                if (helper instanceof ManageContentTextViewHolder) {
                    ((ManageContentTextViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentName.setText(TextUtils.isEmpty(item.getName()) ? "" : item.getName());
                    ((ManageContentTextViewHolder) helper).manageContentSub.setText(TextUtils.isEmpty(item.getSubName()) ? "" : item.getSubName());
                    ((ManageContentTextViewHolder) helper).itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onItemTypeClickListener != null)
                                onItemTypeClickListener.onItemClick(v, helper.getAdapterPosition(), item, false);
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
            case ManageUnitInfoEntity.TYPE_UNIT_GREEN:
                if (helper instanceof ManageContentGroupViewHolder) {
                    ((ManageContentGroupViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    ((ManageContentGroupViewHolder) helper).manageContentGoto.setVisibility(View.GONE);
                    ((ManageContentGroupViewHolder) helper).manageContentName.setText(TextUtils.isEmpty(item.getName()) ? "" : item.getName());
                    ((ManageContentGroupViewHolder) helper).manageContentButton1.setText("大棚");
                    ((ManageContentGroupViewHolder) helper).manageContentButton2.setText("露天");
                    if (item.isGreenhouse()) {
                        ((ManageContentGroupViewHolder) helper).manageContentButton1.setChecked(true);
                    } else {
                        ((ManageContentGroupViewHolder) helper).manageContentButton2.setChecked(true);
                    }
                    ((ManageContentGroupViewHolder) helper).manageContentButton1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onItemTypeClickListener != null)
                                onItemTypeClickListener.onItemClick(v, helper.getAdapterPosition(), item, true);
                        }
                    });
                    ((ManageContentGroupViewHolder) helper).manageContentButton2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onItemTypeClickListener != null)
                                onItemTypeClickListener.onItemClick(v, helper.getAdapterPosition(), item, false);
                        }
                    });
                }
                break;
            default:
                break;
        }
    }

    public interface OnItemTypeClickListener {
        void onItemClick(View v, int position, ManageUnitInfoEntity entity, boolean isGreen);
    }
}
