package com.erayic.agr.manage.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.erayic.agr.common.config.PreferenceUtils;
import com.erayic.agr.common.net.back.CommonBaseListBean;
import com.erayic.agr.manage.R;
import com.erayic.agr.manage.adapter.holder.ManageContentTextViewHolder;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ManageBaseListAdapter extends BaseQuickAdapter<CommonBaseListBean, ManageContentTextViewHolder> {

    private Context context;

    public ManageBaseListAdapter(Context context, List<CommonBaseListBean> data) {
        super(R.layout.adapter_manage_content_text, data);
        this.context = context;
    }

    @Override
    protected void convert(ManageContentTextViewHolder helper, CommonBaseListBean item) {
        helper.manageContentName.setText(TextUtils.isEmpty(item.getBaseName()) ? "未命名" : item.getBaseName());
        helper.manageContentIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.app_base_image_item_icon_green));
        if (item.getBaseID().equals(PreferenceUtils.getParam("ActiveBaseID"))) {
            helper.manageContentSub.setText("当前基地");
        }else {
            helper.manageContentSub.setText("");
        }
    }
}
