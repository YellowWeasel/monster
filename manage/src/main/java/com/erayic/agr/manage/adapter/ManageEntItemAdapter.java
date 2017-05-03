package com.erayic.agr.manage.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.manage.R;
import com.erayic.agr.manage.adapter.entity.ManageEntEntity;
import com.erayic.agr.manage.adapter.holder.ManageContentDepictViewHolder;
import com.erayic.agr.manage.adapter.holder.ManageContentServiceViewHolder;
import com.erayic.agr.manage.adapter.holder.ManageContentTextViewHolder;
import com.erayic.agr.manage.adapter.holder.ManageDividerViewHolder;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ManageEntItemAdapter extends BaseMultiItemQuickAdapter<ManageEntEntity, BaseViewHolder> {

    private Context context;

    private OnItemTypeClickListener onItemTypeClickListener;

    public ManageEntItemAdapter(Context context, List<ManageEntEntity> data) {
        super(data);
        this.context = context;
    }

    public void setOnItemTypeClickListener(OnItemTypeClickListener onItemTypeClickListener) {
        this.onItemTypeClickListener = onItemTypeClickListener;
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ManageEntEntity.TYPE_DIVIDER:
                return new ManageDividerViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_divider, parent, false));
            case ManageEntEntity.TYPE_ENT_NEMA:
                return new ManageContentTextViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_text, parent, false));
            case ManageEntEntity.TYPE_ENT_BL:
                return new ManageContentTextViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_text, parent, false));
            case ManageEntEntity.TYPE_ENT_OCC:
                return new ManageContentTextViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_text, parent, false));
            case ManageEntEntity.TYPE_ENT_SERVICE:
                return new ManageContentServiceViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_service, parent, false));
            case ManageEntEntity.TYPE_ENT_CONTACTER:
                return new ManageContentTextViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_text, parent, false));
            case ManageEntEntity.TYPE_ENT_IMAGE:
                return new ManageContentTextViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_text, parent, false));
            case ManageEntEntity.TYPE_ENT_OTHER:
                return new ManageContentTextViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_text, parent, false));
            case ManageEntEntity.TYPE_ENT_DEPICT:
                return new ManageContentDepictViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_depict, parent, false));
            default:
                return super.onCreateDefViewHolder(parent, viewType);
        }

    }

    @Override
    protected void convert(final BaseViewHolder helper, final ManageEntEntity item) {
        switch (helper.getItemViewType()) {
            case ManageEntEntity.TYPE_ENT_NEMA:
                if (helper instanceof ManageContentTextViewHolder) {
                    ((ManageContentTextViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentName.setText(item.getTitle());
                    ((ManageContentTextViewHolder) helper).manageContentSub.setText(item.getSubTitle());
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
            case ManageEntEntity.TYPE_ENT_BL:
                if (helper instanceof ManageContentTextViewHolder) {
                    ((ManageContentTextViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentName.setText(item.getTitle());
                    if (item.isSubBool()) {
                        ((ManageContentTextViewHolder) helper).manageContentSub.setText("已上传");
                    } else {
                        ((ManageContentTextViewHolder) helper).manageContentSub.setText("未上传");
                    }
                }
                break;
            case ManageEntEntity.TYPE_ENT_OCC:
                if (helper instanceof ManageContentTextViewHolder) {
                    ((ManageContentTextViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentName.setText(item.getTitle());
                    if (item.isSubBool()) {
                        ((ManageContentTextViewHolder) helper).manageContentSub.setText("已上传");
                    } else {
                        ((ManageContentTextViewHolder) helper).manageContentSub.setText("未上传");
                    }
                }
                break;
            case ManageEntEntity.TYPE_ENT_SERVICE:
                if (helper instanceof ManageContentServiceViewHolder) {
                    ((ManageContentServiceViewHolder) helper).manageContentServiceIcon.setVisibility(View.GONE);
                    ((ManageContentServiceViewHolder) helper).manageContentServiceName.setText(item.getTitle());
                    if (Integer.valueOf(item.getSubTitle()).intValue() <= 0) {
                        ((ManageContentServiceViewHolder) helper).manageContentServiceSub.setText(Html.fromHtml("<font color='red'>" + "服务已到期，操作受限" + "</font>"));
                    } else if (Integer.valueOf(item.getSubTitle()).intValue() > 0 && Integer.valueOf(item.getSubTitle()).intValue() < 10) {
                        ((ManageContentServiceViewHolder) helper).manageContentServiceSub.setText(Html.fromHtml("距到期还有<font color='red'>" + Integer.valueOf(item.getSubTitle()).intValue() +
                                "</font>天，请尽快<font color='red'>续费</font>"));
                    } else {
                        ((ManageContentServiceViewHolder) helper).manageContentServiceSub.setText(Html.fromHtml("距到期还有<font color='red'>" + Integer.valueOf(item.getSubTitle()).intValue() +
                                "</font>天"));
                    }
                    ((ManageContentServiceViewHolder) helper).manageContentServiceBuy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onItemTypeClickListener != null)
                                onItemTypeClickListener.onItemClick(v, helper.getAdapterPosition(), item);

                        }
                    });
                    ((ManageContentServiceViewHolder) helper).manageContentServiceBuy.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            return true;
                        }
                    });
                }
                break;
            case ManageEntEntity.TYPE_ENT_CONTACTER:
                if (helper instanceof ManageContentTextViewHolder) {
                    ((ManageContentTextViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentName.setText(item.getTitle());
                    ((ManageContentTextViewHolder) helper).manageContentSub.setText(item.getSubTitle());
                }
                break;
            case ManageEntEntity.TYPE_ENT_IMAGE:
                if (helper instanceof ManageContentTextViewHolder) {
                    ((ManageContentTextViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentName.setText(item.getTitle());
                }
                break;
            case ManageEntEntity.TYPE_ENT_OTHER:
                if (helper instanceof ManageContentTextViewHolder) {
                    ((ManageContentTextViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentName.setText(item.getTitle());
                }
                break;
            case ManageEntEntity.TYPE_ENT_DEPICT:
                if (helper instanceof ManageContentDepictViewHolder) {
                    ((ManageContentDepictViewHolder) helper).manageContentDepictIcon.setVisibility(View.GONE);
                    ((ManageContentDepictViewHolder) helper).manageContentDepictName.setText(item.getTitle());
                    ((ManageContentDepictViewHolder) helper).manageContentDepictSub.setText(item.getSubTitle());
                }
                break;
            default:
                break;
        }
    }

    public interface OnItemTypeClickListener {
        void onItemClick(View v, int position, ManageEntEntity entity);
    }
}
