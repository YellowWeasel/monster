package com.erayic.agr.common.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * 作者：Hkceey
 * 邮箱：hkceey@outlook.com
 * 注解：Fragment基类
 */

public abstract class BaseFragment extends Fragment {
    public View view;
    public Context ct;
//    protected Handler baseHandler;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);//初始化ARouter
        ct = getActivity();
//        baseHandler = new Handler(Looper.getMainLooper());
        initData(savedInstanceState);
    }

    /**
     * setContentView;
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null)
            view = initView(inflater);
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        return view;
    }

    public View getRootView() {
        return view;
    }

    /**
     * 初始化view
     */
    public abstract View initView(LayoutInflater inflater);

    /**
     * 初始化数据
     */
    public abstract void initData(Bundle savedInstanceState);

//    /**
//     * 跳转页面
//     *
//     * @param clazz
//     */
//    public void skipAct(Class clazz) {
//        Intent intent = new Intent(ct, clazz);
//        intent.putExtra("fromWhere", getClass().getSimpleName());
//        startActivity(intent);
//    }
//
//    public void skipAct(Class clazz, Bundle bundle) {
//        Intent intent = new Intent(ct, clazz);
//        intent.putExtras(bundle);
//        intent.putExtra("fromWhere", getClass().getSimpleName());
//        startActivity(intent);
//    }
//
//    public void skipAct(Class clazz, Bundle bundle, int flags) {
//        Intent intent = new Intent(ct, clazz);
//        intent.putExtra("fromWhere", getClass().getSimpleName());
//        intent.putExtras(bundle);
//        intent.setFlags(flags);
//        startActivity(intent);
//    }
//
//    public void skipActForResult(Class clazz, int REQUEST_CODE) {
//        Intent intent = new Intent(ct, clazz);
//        intent.putExtra("fromWhere", getClass().getSimpleName());
//        startActivityForResult(intent, REQUEST_CODE);
//    }
//
//    public void skipActForResult(Class clazz, Bundle bundle, int REQUEST_CODE) {
//        Intent intent = new Intent(ct, clazz);
//        intent.putExtras(bundle);
//        intent.putExtra("fromWhere", getClass().getSimpleName());
//        startActivityForResult(intent, REQUEST_CODE);
//    }
}
