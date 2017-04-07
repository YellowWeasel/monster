package com.erayic.agr.common.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.LayoutInflater;
import android.view.View;

import com.erayic.agr.common.R;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class LoadingDialog extends Dialog {

    private AVLoadingIndicatorView common_dialog_loading;
    private Context context;

    public LoadingDialog(@NonNull Context context) {
        super(context, R.style.LoadingDialog);
        this.context = context;
    }

    public LoadingDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    protected LoadingDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载布局文件
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
        setContentView(view);
        setCancelable(false);// 设置点击屏幕Dialog不消失
        common_dialog_loading = (AVLoadingIndicatorView) view.findViewById(R.id.common_dialog_loading);
        //设置dialog大小，这里是一个小赠送，模块好的控件大小设置
//        Window dialogWindow = getWindow();
//        WindowManager manager = ((Activity) context).getWindowManager();
//        WindowManager.LayoutParams params = dialogWindow.getAttributes(); // 获取对话框当前的参数值
//        dialogWindow.setGravity(Gravity.CENTER);//设置对话框位置
//        Display d = manager.getDefaultDisplay(); // 获取屏幕宽、高度
//        params.width = (int) (d.getWidth() * 0.8); // 宽度设置为屏幕的0.65，根据实际情况调整
//        dialogWindow.setAttributes(params);
    }

    @Override
    public void show() {
        super.show();
        common_dialog_loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        common_dialog_loading.setVisibility(View.GONE);
    }

    @Override
    public void setCanceledOnTouchOutside(boolean cancel) {
        super.setCanceledOnTouchOutside(false);//点击屏幕不消失
    }
}
