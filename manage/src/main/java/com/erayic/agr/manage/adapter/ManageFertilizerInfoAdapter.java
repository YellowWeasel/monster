package com.erayic.agr.manage.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.common.net.back.CommonFertilizerBean;
import com.erayic.agr.manage.R;
import com.erayic.agr.manage.adapter.entity.ManageFertilizerEntity;
import com.erayic.agr.manage.adapter.holder.ManageContentEdit1ViewHolder;
import com.erayic.agr.manage.adapter.holder.ManageContentTextViewHolder;
import com.erayic.agr.manage.adapter.holder.ManageDividerViewHolder;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ManageFertilizerInfoAdapter extends BaseMultiItemQuickAdapter<ManageFertilizerEntity, BaseViewHolder> {

    private Context context;

    private CommonFertilizerBean bean;
    private KeyListener keyListener = null;

    public ManageFertilizerInfoAdapter(Context context, List<ManageFertilizerEntity> data) {
        super(data);
        this.context = context;
    }

    public void setBean(CommonFertilizerBean bean) {
        this.bean = bean;
    }

    public void setKeyListener(KeyListener keyListener) {
        this.keyListener = keyListener;
    }

    public CommonFertilizerBean getBean() {
        return bean;
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ManageFertilizerEntity.TYPE_DIVIDER:
                return new ManageDividerViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_divider, parent, false));
            case ManageFertilizerEntity.TYPE_IMPORT_NAME:
                return new ManageContentEdit1ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_edit_1, parent, false));
            case ManageFertilizerEntity.TYPE_IMPORT_MANUFACTURER:
                return new ManageContentEdit1ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_edit_1, parent, false));
            case ManageFertilizerEntity.TYPE_PID:
                return new ManageContentTextViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_text, parent, false));
            case ManageFertilizerEntity.TYPE_COMMON_NAME:
                return new ManageContentTextViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_text, parent, false));
            case ManageFertilizerEntity.TYPE_PRODUCT_NAME:
                return new ManageContentTextViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_text, parent, false));
            case ManageFertilizerEntity.TYPE_MANUFACTURER:
                return new ManageContentTextViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_text, parent, false));
            case ManageFertilizerEntity.TYPE_CROPS:
                return new ManageContentTextViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_text, parent, false));
            case ManageFertilizerEntity.TYPE_NORM:
                return new ManageContentTextViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_text, parent, false));
            case ManageFertilizerEntity.TYPE_SHAPE:
                return new ManageContentTextViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_text, parent, false));
            default:
                return super.onCreateDefViewHolder(parent, viewType);
        }
    }

    @Override
    protected void convert(BaseViewHolder helper, ManageFertilizerEntity item) {
        switch (helper.getItemViewType()) {
            case ManageFertilizerEntity.TYPE_DIVIDER:
                if (helper instanceof ManageDividerViewHolder) {

                }
                break;
            case ManageFertilizerEntity.TYPE_IMPORT_NAME:
                if (helper instanceof ManageContentEdit1ViewHolder) {
                    ((ManageContentEdit1ViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    ((ManageContentEdit1ViewHolder) helper).manageContentGoto.setVisibility(View.GONE);
                    ((ManageContentEdit1ViewHolder) helper).manageContentName.setText("化肥名称");
                    ((ManageContentEdit1ViewHolder) helper).manageContentSubName.setKeyListener(keyListener);
                    ((ManageContentEdit1ViewHolder) helper).manageContentSubName.setText(TextUtils.isEmpty(item.getSubMap().get("key1")) ? "" : item.getSubMap().get("key1"));
                    ((ManageContentEdit1ViewHolder) helper).manageContentSubName.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            bean.setCommonName(s.toString());
                        }
                    });
                }
                break;
            case ManageFertilizerEntity.TYPE_IMPORT_MANUFACTURER:
                if (helper instanceof ManageContentEdit1ViewHolder) {
                    ((ManageContentEdit1ViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    ((ManageContentEdit1ViewHolder) helper).manageContentGoto.setVisibility(View.GONE);
                    ((ManageContentEdit1ViewHolder) helper).manageContentName.setText("生产厂家");
                    ((ManageContentEdit1ViewHolder) helper).manageContentSubName.setKeyListener(keyListener);
                    ((ManageContentEdit1ViewHolder) helper).manageContentSubName.setText(TextUtils.isEmpty(item.getSubMap().get("key1")) ? "" : item.getSubMap().get("key1"));
                    ((ManageContentEdit1ViewHolder) helper).manageContentSubName.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            bean.setManufacturer(s.toString());
                        }
                    });
                }
                break;
            case ManageFertilizerEntity.TYPE_PID:
                if (helper instanceof ManageContentTextViewHolder) {
                    ((ManageContentTextViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentGoto.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentName.setText("登记证号");
                    ((ManageContentTextViewHolder) helper).manageContentSub.setText(TextUtils.isEmpty(item.getSubMap().get("key1")) ? "" : item.getSubMap().get("key1"));
                }
                break;
            case ManageFertilizerEntity.TYPE_COMMON_NAME:
                if (helper instanceof ManageContentTextViewHolder) {
                    ((ManageContentTextViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentGoto.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentName.setText("通用名称");
                    ((ManageContentTextViewHolder) helper).manageContentSub.setText(TextUtils.isEmpty(item.getSubMap().get("key1")) ? "" : item.getSubMap().get("key1"));
                }
                break;
            case ManageFertilizerEntity.TYPE_PRODUCT_NAME:
                if (helper instanceof ManageContentTextViewHolder) {
                    ((ManageContentTextViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentGoto.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentName.setText("商家名称");
                    ((ManageContentTextViewHolder) helper).manageContentSub.setText(TextUtils.isEmpty(item.getSubMap().get("key1")) ? "" : item.getSubMap().get("key1"));
                }
                break;
            case ManageFertilizerEntity.TYPE_MANUFACTURER:
                if (helper instanceof ManageContentTextViewHolder) {
                    ((ManageContentTextViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentGoto.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentName.setText("生产厂家");
                    ((ManageContentTextViewHolder) helper).manageContentSub.setText(TextUtils.isEmpty(item.getSubMap().get("key1")) ? "" : item.getSubMap().get("key1"));
                }
                break;
            case ManageFertilizerEntity.TYPE_CROPS:
                if (helper instanceof ManageContentTextViewHolder) {
                    ((ManageContentTextViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentGoto.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentName.setText("适宜作物");
                    ((ManageContentTextViewHolder) helper).manageContentSub.setText(TextUtils.isEmpty(item.getSubMap().get("key1")) ? "" : item.getSubMap().get("key1"));
                }
                break;
            case ManageFertilizerEntity.TYPE_NORM:
                if (helper instanceof ManageContentTextViewHolder) {
                    ((ManageContentTextViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentGoto.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentName.setText("技术指标");
                    ((ManageContentTextViewHolder) helper).manageContentSub.setText(TextUtils.isEmpty(item.getSubMap().get("key1")) ? "" : item.getSubMap().get("key1"));
                }
                break;
            case ManageFertilizerEntity.TYPE_SHAPE:
                if (helper instanceof ManageContentTextViewHolder) {
                    ((ManageContentTextViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentGoto.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentName.setText("产品形态");
                    ((ManageContentTextViewHolder) helper).manageContentSub.setText(TextUtils.isEmpty(item.getSubMap().get("key1")) ? "" : item.getSubMap().get("key1"));
                }
                break;
            default:
                break;
        }
    }
}
