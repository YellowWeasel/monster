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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.common.net.back.CommonPesticideBean;
import com.erayic.agr.common.util.ErayicNetDate;
import com.erayic.agr.manage.R;
import com.erayic.agr.manage.adapter.entity.ManagePesticideEntity;
import com.erayic.agr.manage.adapter.holder.ManageContentEdit1ViewHolder;
import com.erayic.agr.manage.adapter.holder.ManageContentEdit2ViewHolder;
import com.erayic.agr.manage.adapter.holder.ManageContentTextViewHolder;
import com.erayic.agr.manage.adapter.holder.ManageDividerViewHolder;
import com.erayic.agr.manage.adapter.holder.ManagePesticideActiveViewHolder;
import com.erayic.agr.manage.adapter.holder.ManagePesticideMethodViewHolder;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ManagePesticideInfoAdapter extends BaseMultiItemQuickAdapter<ManagePesticideEntity, BaseViewHolder> {

    private Context context;

    private KeyListener keyListener;//editText编辑状态
    private CommonPesticideBean bean;

    public ManagePesticideInfoAdapter(Context context, List<ManagePesticideEntity> data) {
        super(data);
        this.context = context;
    }

    public void setKeyListener(KeyListener keyListener) {
        this.keyListener = keyListener;
    }

    public void setBean(CommonPesticideBean bean) {
        this.bean = bean;
    }

    public CommonPesticideBean getBean() {
        return bean;
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ManagePesticideEntity.TYPE_DIVIDER:
                return new ManageDividerViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_divider, parent, false));
            case ManagePesticideEntity.TYPE_NUM:
                return new ManageContentTextViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_text, parent, false));
            case ManagePesticideEntity.TYPE_NAME:
                return new ManageContentTextViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_text, parent, false));
            case ManagePesticideEntity.TYPE_TOXICITY:
                return new ManageContentTextViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_text, parent, false));
            case ManagePesticideEntity.TYPE_DOSAGE:
                return new ManageContentTextViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_text, parent, false));
            case ManagePesticideEntity.TYPE_FACTORY:
                return new ManageContentTextViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_text, parent, false));
            case ManagePesticideEntity.TYPE_START_TIME:
                return new ManageContentTextViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_text, parent, false));
            case ManagePesticideEntity.TYPE_END_TIME:
                return new ManageContentTextViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_text, parent, false));
            case ManagePesticideEntity.TYPE_ACTIVE_ADD:
                return new ManageContentTextViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_text, parent, false));
            case ManagePesticideEntity.TYPE_ACTIVE:
                return new ManagePesticideActiveViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_pesticide_active, parent, false));
            case ManagePesticideEntity.TYPE_METHOD_ADD:
                return new ManageContentTextViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_text, parent, false));
            case ManagePesticideEntity.TYPE_METHOD:
                return new ManagePesticideMethodViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_pesticide_method, parent, false));
            case ManagePesticideEntity.TYPE_IMPORT_NAME:
                return new ManageContentEdit1ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_edit_1, parent, false));
            case ManagePesticideEntity.TYPE_IMPORT_FACTORY:
                return new ManageContentEdit1ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_edit_1, parent, false));
            case ManagePesticideEntity.TYPE_IMPORT_TOXICITY:
                return new ManageContentEdit1ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_edit_1, parent, false));
            default:
                return super.onCreateDefViewHolder(parent, viewType);
        }

    }

    @Override
    protected void convert(final BaseViewHolder helper, ManagePesticideEntity item) {
        switch (helper.getItemViewType()) {
            case ManagePesticideEntity.TYPE_DIVIDER:
                break;
            case ManagePesticideEntity.TYPE_NUM:
                if (helper instanceof ManageContentTextViewHolder) {
                    ((ManageContentTextViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentGoto.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentName.setText(TextUtils.isEmpty(item.getName()) ? "" : item.getName());
                    ((ManageContentTextViewHolder) helper).manageContentSub.setText(TextUtils.isEmpty(item.getSubMap().get("key1")) ? "" : item.getSubMap().get("key1"));
                }
                break;
            case ManagePesticideEntity.TYPE_NAME:
                if (helper instanceof ManageContentTextViewHolder) {
                    ((ManageContentTextViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentGoto.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentName.setText(TextUtils.isEmpty(item.getName()) ? "" : item.getName());
                    ((ManageContentTextViewHolder) helper).manageContentSub.setText(TextUtils.isEmpty(item.getSubMap().get("key1")) ? "" : item.getSubMap().get("key1"));
                }
                break;
            case ManagePesticideEntity.TYPE_TOXICITY:
                if (helper instanceof ManageContentTextViewHolder) {
                    ((ManageContentTextViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentGoto.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentName.setText(TextUtils.isEmpty(item.getName()) ? "" : item.getName());
                    ((ManageContentTextViewHolder) helper).manageContentSub.setText(TextUtils.isEmpty(item.getSubMap().get("key1")) ? "" : item.getSubMap().get("key1"));
                }
                break;
            case ManagePesticideEntity.TYPE_DOSAGE:
                if (helper instanceof ManageContentTextViewHolder) {
                    ((ManageContentTextViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentGoto.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentName.setText(TextUtils.isEmpty(item.getName()) ? "" : item.getName());
                    ((ManageContentTextViewHolder) helper).manageContentSub.setText(TextUtils.isEmpty(item.getSubMap().get("key1")) ? "" : item.getSubMap().get("key1"));
                }
                break;
            case ManagePesticideEntity.TYPE_FACTORY:
                if (helper instanceof ManageContentTextViewHolder) {
                    ((ManageContentTextViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentGoto.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentName.setText(TextUtils.isEmpty(item.getName()) ? "" : item.getName());
                    ((ManageContentTextViewHolder) helper).manageContentSub.setText(TextUtils.isEmpty(item.getSubMap().get("key1")) ? "" : item.getSubMap().get("key1"));
                }
                break;
            case ManagePesticideEntity.TYPE_START_TIME:
                if (helper instanceof ManageContentTextViewHolder) {
                    ((ManageContentTextViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentGoto.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentName.setText(TextUtils.isEmpty(item.getName()) ? "" : item.getName());
                    ((ManageContentTextViewHolder) helper).manageContentSub.setText(TextUtils.isEmpty(item.getSubMap().get("key1")) ? "" : ErayicNetDate.getStringDate(item.getSubMap().get("key1")));
                }
                break;
            case ManagePesticideEntity.TYPE_END_TIME:
                if (helper instanceof ManageContentTextViewHolder) {
                    ((ManageContentTextViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentGoto.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentName.setText(TextUtils.isEmpty(item.getName()) ? "" : item.getName());
                    ((ManageContentTextViewHolder) helper).manageContentSub.setText(TextUtils.isEmpty(item.getSubMap().get("key1")) ? "" : ErayicNetDate.getStringDate(item.getSubMap().get("key1")));
                }
                break;
            case ManagePesticideEntity.TYPE_ACTIVE_ADD:
                if (helper instanceof ManageContentTextViewHolder) {
                    ((ManageContentTextViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentGoto.setVisibility(View.INVISIBLE);
                    ((ManageContentTextViewHolder) helper).manageContentName.setText(TextUtils.isEmpty(item.getName()) ? "" : item.getName());
                    ((ManageContentTextViewHolder) helper).manageContentSub.setVisibility(View.GONE);
                }
                break;
            case ManagePesticideEntity.TYPE_ACTIVE:
                if (helper instanceof ManagePesticideActiveViewHolder) {
                    ((ManagePesticideActiveViewHolder) helper).manageContentIcon1.setVisibility(View.INVISIBLE);
                    ((ManagePesticideActiveViewHolder) helper).manageContentIcon2.setVisibility(View.INVISIBLE);
                    ((ManagePesticideActiveViewHolder) helper).manageContentName1.setText("有效成分");
                    ((ManagePesticideActiveViewHolder) helper).manageContentSubName1.setText(TextUtils.isEmpty(item.getSubMap().get("key1")) ? "" : item.getSubMap().get("key1"));
                    ((ManagePesticideActiveViewHolder) helper).manageContentName2.setText("含量");
                    ((ManagePesticideActiveViewHolder) helper).manageContentSubName2.setText(TextUtils.isEmpty(item.getSubMap().get("key2")) ? "" : item.getSubMap().get("key2"));
                }
                break;
            case ManagePesticideEntity.TYPE_METHOD_ADD:
                if (helper instanceof ManageContentTextViewHolder) {
                    ((ManageContentTextViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentGoto.setVisibility(View.INVISIBLE);
                    ((ManageContentTextViewHolder) helper).manageContentName.setText(TextUtils.isEmpty(item.getName()) ? "" : item.getName());
                    ((ManageContentTextViewHolder) helper).manageContentSub.setVisibility(View.GONE);
                }
                break;
            case ManagePesticideEntity.TYPE_METHOD:
                if (helper instanceof ManagePesticideMethodViewHolder) {
                    ((ManagePesticideMethodViewHolder) helper).manageContentIcon1.setVisibility(View.INVISIBLE);
                    ((ManagePesticideMethodViewHolder) helper).manageContentIcon2.setVisibility(View.INVISIBLE);
                    ((ManagePesticideMethodViewHolder) helper).manageContentIcon3.setVisibility(View.INVISIBLE);
                    ((ManagePesticideMethodViewHolder) helper).manageContentIcon4.setVisibility(View.INVISIBLE);
                    ((ManagePesticideMethodViewHolder) helper).manageContentName1.setText("作物");
                    ((ManagePesticideMethodViewHolder) helper).manageContentSubName1.setText(TextUtils.isEmpty(item.getSubMap().get("key1")) ? "" : item.getSubMap().get("key1"));
                    ((ManagePesticideMethodViewHolder) helper).manageContentName2.setText("防治对象");
                    ((ManagePesticideMethodViewHolder) helper).manageContentSubName2.setText(TextUtils.isEmpty(item.getSubMap().get("key2")) ? "" : item.getSubMap().get("key2"));
                    ((ManagePesticideMethodViewHolder) helper).manageContentName3.setText("用药量");
                    ((ManagePesticideMethodViewHolder) helper).manageContentSubName3.setText(TextUtils.isEmpty(item.getSubMap().get("key3")) ? "" : item.getSubMap().get("key3"));
                    ((ManagePesticideMethodViewHolder) helper).manageContentName4.setText("施用方法");
                    ((ManagePesticideMethodViewHolder) helper).manageContentSubName4.setText(TextUtils.isEmpty(item.getSubMap().get("key4")) ? "" : item.getSubMap().get("key4"));
                }
                break;
            case ManagePesticideEntity.TYPE_IMPORT_NAME:
                if (helper instanceof ManageContentEdit1ViewHolder) {
                    ((ManageContentEdit1ViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    ((ManageContentEdit1ViewHolder) helper).manageContentGoto.setVisibility(View.GONE);
                    ((ManageContentEdit1ViewHolder) helper).manageContentName.setText(TextUtils.isEmpty(item.getName()) ? "" : item.getName());
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
                            bean.setRegisterName(s.toString());
                        }
                    });
                }
                break;
            case ManagePesticideEntity.TYPE_IMPORT_FACTORY:
                if (helper instanceof ManageContentEdit1ViewHolder) {
                    ((ManageContentEdit1ViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    ((ManageContentEdit1ViewHolder) helper).manageContentGoto.setVisibility(View.GONE);
                    ((ManageContentEdit1ViewHolder) helper).manageContentName.setText(TextUtils.isEmpty(item.getName()) ? "" : item.getName());
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
            case ManagePesticideEntity.TYPE_IMPORT_TOXICITY:
                if (helper instanceof ManageContentEdit1ViewHolder) {
                    ((ManageContentEdit1ViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    ((ManageContentEdit1ViewHolder) helper).manageContentGoto.setVisibility(View.GONE);
                    ((ManageContentEdit1ViewHolder) helper).manageContentName.setText(TextUtils.isEmpty(item.getName()) ? "" : item.getName());
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
                            bean.setToxicity(s.toString());
                        }
                    });
                }
                break;
            default:
                break;
        }
    }
}
