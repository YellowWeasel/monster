package com.erayic.agr.common.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.R;
import com.erayic.agr.common.util.ErayicStack;
import com.jaeger.library.StatusBarUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;


/**
 * 作者：Hkceey
 * 邮箱：hkceey@outlook.com
 * 注解：Activity 基类
 */

public abstract class BaseActivity extends AppCompatActivity implements BGASwipeBackHelper.Delegate {

    protected AppCompatActivity mContext;
    protected BGASwipeBackHelper mSwipeBackHelper;
//    protected Handler baseHandler;

    /**
     * 页面布局的 根view
     */
    protected View mContentView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initSwipeBackFinish();
        super.onCreate(savedInstanceState);
        // 设置不能横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        mContext = this;

        ErayicStack.getInstance().addActivity(this);//Activity 入栈

        ARouter.getInstance().inject(this);//初始化ARouter
    }

    @Override
    public void setContentView(int layoutResID) {
        View view = LayoutInflater.from(this).inflate(layoutResID, null);
        setContentView(view);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.bind(this);
        mContentView = view;
//        baseHandler = new Handler(Looper.getMainLooper());
        initView();
        setStatusBar();
        initData();
    }

    /**
     * 初始化view
     */
    public abstract void initView();

    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 初始化状态栏
     */
    protected void setStatusBar() {
        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.app_base_bar_background),StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA);
    }

    /**
     * 初始化滑动返回。在 super.onCreate(savedInstanceState) 之前调用该方法
     */
    private void initSwipeBackFinish() {
        mSwipeBackHelper = new BGASwipeBackHelper(this, this);

        // 「必须在 Application 的 onCreate 方法中执行 BGASwipeBackManager.getInstance().init(this) 来初始化滑动返回」
        // 下面几项可以不配置，这里只是为了讲述接口用法。

        // 设置滑动返回是否可用。默认值为 true
        mSwipeBackHelper.setSwipeBackEnable(true);
        // 设置是否仅仅跟踪左侧边缘的滑动返回。默认值为 true
        mSwipeBackHelper.setIsOnlyTrackingLeftEdge(true);
        // 设置是否是微信滑动返回样式。默认值为 true
        mSwipeBackHelper.setIsWeChatStyle(false);
        // 设置阴影资源 id。默认值为 R.drawable.bga_sbl_shadow
        mSwipeBackHelper.setShadowResId(R.drawable.bga_sbl_shadow);
        // 设置是否显示滑动返回的阴影效果。默认值为 true
        mSwipeBackHelper.setIsNeedShowShadow(true);
        // 设置阴影区域的透明度是否根据滑动的距离渐变。默认值为 true
        mSwipeBackHelper.setIsShadowAlphaGradient(true);
        // 设置触发释放后自动滑动返回的阈值，默认值为 0.3f
        mSwipeBackHelper.setSwipeBackThreshold(0.4f);
    }

    /**
     * 是否支持滑动返回。父类中默认返回 true 来支持滑动返回，如果某个界面不想支持滑动返回则重写该方法返回 false 即可
     *
     * @return
     */
    @Override
    public boolean isSupportSwipeBack() {
        return true;
    }

    /**
     * 正在滑动返回
     *
     * @param slideOffset 从 0 到 1
     */
    @Override
    public void onSwipeBackLayoutSlide(float slideOffset) {
    }

    /**
     * 没达到滑动返回的阈值，取消滑动返回动作，回到默认状态
     */
    @Override
    public void onSwipeBackLayoutCancel() {
    }

    /**
     * 滑动返回执行完毕，销毁当前 Activity
     */
    @Override
    public void onSwipeBackLayoutExecuted() {
        mSwipeBackHelper.swipeBackward();
    }

    @Override
    public void onBackPressed() {
        // 正在滑动返回的时候取消返回按钮事件
        if (mSwipeBackHelper.isSliding()) {
            return;
        }
        mSwipeBackHelper.backward();
    }

//    /**
//     * 跳转页面
//     *
//     * @param clazz
//     */
//    public void skipAct(Class clazz) {
//        Intent intent = new Intent(this, clazz);
//        intent.putExtra("fromWhere", getClass().getSimpleName());
//        startActivity(intent);
//    }
//
//    public void skipAct(Class clazz, Bundle bundle) {
//        Intent intent = new Intent(this, clazz);
//        intent.putExtras(bundle);
//        intent.putExtra("fromWhere", getClass().getSimpleName());
//        startActivity(intent);
//    }
//
//    public void skipAct(Class clazz, Bundle bundle, int flags) {
//        Intent intent = new Intent(this, clazz);
//        intent.putExtra("fromWhere", getClass().getSimpleName());
//        intent.putExtras(bundle);
//        intent.setFlags(flags);
//        startActivity(intent);
//    }


    @Override
    protected void onDestroy() {
        ErayicStack.getInstance().finishActivity(this);//Activity 出栈
        super.onDestroy();
        mContentView = null;

    }

}
