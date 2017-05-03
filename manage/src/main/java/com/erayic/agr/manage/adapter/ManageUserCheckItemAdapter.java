package com.erayic.agr.manage.adapter;

import android.content.Context;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.erayic.agr.common.net.back.CommonPersonnelBean;
import com.erayic.agr.common.net.back.enums.EnumUserRole;
import com.erayic.agr.manage.R;
import com.erayic.agr.manage.adapter.holder.ManageContentCheckViewHolder;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ManageUserCheckItemAdapter extends BaseQuickAdapter<CommonPersonnelBean, ManageContentCheckViewHolder> {

    private Context context;

    public ManageUserCheckItemAdapter(Context context, List<CommonPersonnelBean> data) {
        super(R.layout.adapter_manage_content_check, data);
    }

    @Override
    protected void convert(ManageContentCheckViewHolder helper, CommonPersonnelBean item) {
        helper.manageContentIcon.setVisibility(View.GONE);
        helper.manageContentName.setText(item.getName());
        helper.manageContentSub.setText(EnumUserRole.getRoleDes(item.getRole()));
        helper.manageContentCheck.setChecked(item.isCheck());
    }
}
