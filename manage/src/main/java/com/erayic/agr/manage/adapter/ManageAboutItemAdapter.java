package com.erayic.agr.manage.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.common.util.ErayicApp;
import com.erayic.agr.manage.R;
import com.erayic.agr.manage.adapter.entity.ManageAboutEntity;
import com.erayic.agr.manage.adapter.holder.ManageContentLogoViewHolder;
import com.erayic.agr.manage.adapter.holder.ManageContentTextViewHolder;
import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class ManageAboutItemAdapter extends BaseMultiItemQuickAdapter<ManageAboutEntity, BaseViewHolder> {

    private Context context;

    public ManageAboutItemAdapter(Context context, List<ManageAboutEntity> data) {
        super(data);
        this.context = context;
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ManageAboutEntity.TYPE_APP_LOGO:
                return new ManageContentLogoViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_logo, parent, false));
            case ManageAboutEntity.TYPE_ITEM:
                return new ManageContentTextViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_manage_content_text, parent, false));
            default:
        }
        return super.onCreateDefViewHolder(parent, viewType);
    }

    @Override
    protected void convert(BaseViewHolder helper, ManageAboutEntity item) {
        switch (helper.getItemViewType()) {
            case ManageAboutEntity.TYPE_APP_LOGO:
                if (helper instanceof ManageContentLogoViewHolder) {
                    ((ManageContentLogoViewHolder) helper).adapterAboutItemVersion.setText(ErayicApp.getVersionName(context));
                }
                break;
            case ManageAboutEntity.TYPE_ITEM:
                if (helper instanceof ManageContentTextViewHolder) {
                    ((ManageContentTextViewHolder) helper).manageContentIcon.setVisibility(View.GONE);
                    ((ManageContentTextViewHolder) helper).manageContentName.setText(TextUtils.isEmpty(item.getName()) ? "" : item.getName());
                }
                break;
            default:
        }
    }
}
