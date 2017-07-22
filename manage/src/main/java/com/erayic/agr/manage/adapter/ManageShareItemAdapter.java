package com.erayic.agr.manage.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.common.AgrConstant;
import com.erayic.agr.manage.R;
import com.erayic.agr.manage.adapter.entity.ManageShareEntity;
import com.erayic.agr.manage.adapter.holder.ManageShareImageViewHolder;
import com.erayic.agr.manage.adapter.holder.ManageShareOtherViewHolder;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ManageShareItemAdapter extends BaseMultiItemQuickAdapter<ManageShareEntity, BaseViewHolder> {

    private Context context;

    private OnShareClickListener onShareClickListener;

    public ManageShareItemAdapter(Context context, List<ManageShareEntity> data) {
        super(data);
        this.context = context;
    }

    public void setOnShareClickListener(OnShareClickListener onShareClickListener) {
        this.onShareClickListener = onShareClickListener;
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ManageShareEntity.TYPE_SHARE_IMAGE:
                return new ManageShareImageViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_share_image, parent, false));
            case ManageShareEntity.TYPE_SHARE_OHTER:
                return new ManageShareOtherViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_share_other, parent, false));
            default:
        }
        return super.onCreateDefViewHolder(parent, viewType);
    }

    @Override
    protected void convert(BaseViewHolder helper, ManageShareEntity item) {
        switch (helper.getItemViewType()) {
            case ManageShareEntity.TYPE_SHARE_IMAGE:
                if (helper instanceof ManageShareImageViewHolder) {
                    Glide.with(context).load(TextUtils.isEmpty(item.getName()) ? "" : (AgrConstant.IMAGE_URL_IMAGE + item.getName()))
                            .apply(AgrConstant.headOptions)
                            .into(((ManageShareImageViewHolder) helper).manageContentHead);
                    ((ManageShareImageViewHolder) helper).manageContentName.setText(TextUtils.isEmpty(item.getSubName()) ? "分享二维码" : (item.getSubName() + "的二维码"));
                }
                break;
            case ManageShareEntity.TYPE_SHARE_OHTER:
                if (helper instanceof ManageShareOtherViewHolder) {
                    ((ManageShareOtherViewHolder) helper).manageContentWeixin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onShareClickListener != null)
                                onShareClickListener.onClick(v, null, 0);
                        }
                    });
                    ((ManageShareOtherViewHolder) helper).manageContentFriends.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onShareClickListener != null)
                                onShareClickListener.onClick(v, null, 1);
                        }
                    });
                    ((ManageShareOtherViewHolder) helper).manageContentQq.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onShareClickListener != null)
                                onShareClickListener.onClick(v, null, 2);
                        }
                    });
                    ((ManageShareOtherViewHolder) helper).manageContentWeixin.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            return true;
                        }
                    });
                    ((ManageShareOtherViewHolder) helper).manageContentFriends.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            return true;
                        }
                    });
                    ((ManageShareOtherViewHolder) helper).manageContentQq.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            return true;
                        }
                    });
                }
                break;
            default:
        }
    }

    public interface OnShareClickListener {
        /**
         * @param key   生成专用key用于服务端识别（未实现）
         * @param index 0微信 1朋友圈 2QQ
         */
        void onClick(View view, String key, int index);
    }
}