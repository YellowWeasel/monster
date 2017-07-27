package com.erayic.agr.other_1.adapter.holder;

import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.other_1.R;

/**
 * 作者:LiJiang
 * 邮箱:1755036940@qq.com
 * 作用:
 */

public class CommandHolder extends BaseViewHolder {
    public Button mButton;

    public CommandHolder(View view) {
        super(view);
        mButton= (Button) view.findViewById(R.id.name_reboot_id);
    }
}
