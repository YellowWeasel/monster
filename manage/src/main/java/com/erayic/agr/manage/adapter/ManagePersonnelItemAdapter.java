package com.erayic.agr.manage.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.erayic.agr.common.net.back.CommonPersonnelBean;
import com.erayic.agr.common.net.back.enums.EnumUserRole;
import com.erayic.agr.manage.R;
import com.erayic.agr.manage.adapter.holder.ManageContentTextViewHolder;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ManagePersonnelItemAdapter extends BaseQuickAdapter<CommonPersonnelBean, ManageContentTextViewHolder> {

    private Context context;

    public ManagePersonnelItemAdapter(Context context, List<CommonPersonnelBean> data) {
        super(R.layout.adapter_manage_content_text, data);
        this.context = context;
    }

    @Override
    protected void convert(ManageContentTextViewHolder helper, CommonPersonnelBean item) {
        helper.manageContentName.setText(item.getName());
        helper.manageContentIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.app_base_image_item_icon_green));
        helper.manageContentSub.setText(EnumUserRole.getRoleDes(item.getRole()));
    }
}
