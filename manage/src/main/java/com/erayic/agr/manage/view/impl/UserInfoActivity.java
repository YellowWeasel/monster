package com.erayic.agr.manage.view.impl;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.CustomLinearLayoutManager;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.config.PreferenceUtils;
import com.erayic.agr.common.net.back.CommonUserInfoBean;
import com.erayic.agr.common.util.DividerItemDecoration;
import com.erayic.agr.common.util.ErayicStack;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.ErayicEditDialog;
import com.erayic.agr.common.view.ErayicTextDialog;
import com.erayic.agr.common.view.LoadingDialog;
import com.erayic.agr.common.view.tooblbar.ErayicToolbar;
import com.erayic.agr.manage.R;
import com.erayic.agr.manage.R2;
import com.erayic.agr.manage.adapter.ManageUserItemAdapter;
import com.erayic.agr.manage.adapter.entity.ManageUserEntity;
import com.erayic.agr.manage.event.UserInfoEvent;
import com.erayic.agr.manage.presenter.IUserInfoPresenter;
import com.erayic.agr.manage.presenter.impl.UserInfoPresenterImpl;
import com.erayic.agr.manage.view.IUserInfoView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.compress.Luban;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */
@Route(path = "/manage/activity/UserInfoActivity", name = "个人信息")
public class UserInfoActivity extends BaseActivity implements IUserInfoView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R2.id.toolbar)
    ErayicToolbar toolbar;
    @BindView(R2.id.manage_user_RecyclerView)
    RecyclerView manageUserRecyclerView;
    @BindView(R2.id.manage_user_swipe)
    SwipeRefreshLayout manageUserSwipe;

    private IUserInfoPresenter presenter;
    private ManageUserItemAdapter adapter;
    private LoadingDialog dialog;

    private int positionBuffer = 0;
    private ManageUserEntity entityBuffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_user);
    }

    @Override
    public void initView() {
        toolbar.setTitle("个人信息");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        manageUserSwipe.setOnRefreshListener(this);

        //使用线性布局管理器
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(UserInfoActivity.this);
        manager.setScrollEnabled(true);//滑动监听
        manageUserRecyclerView.setLayoutManager(manager);
        adapter = new ManageUserItemAdapter(UserInfoActivity.this, null);
        adapter.setOnItemTypeClickListener(new AdapterClickListener());
        manageUserRecyclerView.setAdapter(adapter);
        manageUserRecyclerView.addItemDecoration(new DividerItemDecoration(UserInfoActivity.this, DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    public void initData() {
        presenter = new UserInfoPresenterImpl(this);
        onRefresh();
    }


    @Override
    public void onRefresh() {
        presenter.getUserInfo();
    }

    @Override
    public void openRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (manageUserSwipe != null && !manageUserSwipe.isRefreshing())
                    manageUserSwipe.setRefreshing(true);
            }
        });
    }

    @Override
    public void clearRefresh() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (manageUserSwipe != null && manageUserSwipe.isRefreshing())
                    manageUserSwipe.setRefreshing(false);
            }
        });
    }

    @Override
    public void showLoading() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialog == null)
                    dialog = new LoadingDialog(UserInfoActivity.this);
                dialog.show();
            }
        });
    }

    @Override
    public void dismissLoading() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialog == null)
                    dialog = new LoadingDialog(UserInfoActivity.this);
                dialog.dismiss();
            }
        });
    }

    @Override
    public void updateSure(final String strBuffer) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                entityBuffer.setSubTitle(strBuffer);
                adapter.setData(positionBuffer, entityBuffer);
                if (entityBuffer.getItemType() == ManageUserEntity.TYPE_USER_NAME) {
                    EventBus.getDefault().post(new UserInfoEvent(UserInfoEvent.TYPE_USER_NAME, strBuffer));
                }
                if (entityBuffer.getItemType() == ManageUserEntity.TYPE_USER_ICON) {
                    EventBus.getDefault().post(new UserInfoEvent(UserInfoEvent.TYPE_USER_ICON, strBuffer));
                }
            }
        });
    }

    @Override
    public void refreshUserInfoView(final CommonUserInfoBean bean) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                List<ManageUserEntity> list = new ArrayList<>();
                //分割线
                ManageUserEntity entityDivider = new ManageUserEntity();
                entityDivider.setItemType(ManageUserEntity.TYPE_DIVIDER);
                list.add(entityDivider);
                //头像
                ManageUserEntity entityIcon = new ManageUserEntity();
                entityIcon.setItemType(ManageUserEntity.TYPE_USER_ICON);
                entityIcon.setTitle("头像");
                entityIcon.setSubTitle(bean.getIcon());
                list.add(entityIcon);
                //分割线
                list.add(entityDivider);
                //姓名
                ManageUserEntity entityName = new ManageUserEntity();
                entityName.setItemType(ManageUserEntity.TYPE_USER_NAME);
                entityName.setTitle("姓名");
                entityName.setSubTitle(bean.getName());
                list.add(entityName);
                //电话
                ManageUserEntity entityTel = new ManageUserEntity();
                entityTel.setItemType(ManageUserEntity.TYPE_USER_TEL);
                entityTel.setTitle("电话");
                entityTel.setSubTitle(bean.getTelNum());
                list.add(entityTel);
                //企业
                ManageUserEntity entityEnt = new ManageUserEntity();
                entityEnt.setItemType(ManageUserEntity.TYPE_USER_ENT);
                entityEnt.setTitle("企业");
                entityEnt.setSubTitle(bean.getEntName());
                list.add(entityEnt);
                //基地
                ManageUserEntity entityBase = new ManageUserEntity();
                entityBase.setItemType(ManageUserEntity.TYPE_USER_BASE);
                entityBase.setTitle("基地");
                entityBase.setSubTitle(bean.getBaseName());
                list.add(entityBase);
                //微信
                ManageUserEntity entityWeixin = new ManageUserEntity();
                entityWeixin.setItemType(ManageUserEntity.TYPE_USER_WEIXIN);
                entityWeixin.setTitle("微信");
                if (bean.isWeixin()) {
                    entityWeixin.setSubTitle("已绑定");
                } else {
                    entityWeixin.setSubTitle("未绑定");
                }
                list.add(entityWeixin);
                //分割线
                list.add(entityDivider);
                //更换密码
                ManageUserEntity entityPass = new ManageUserEntity();
                entityPass.setItemType(ManageUserEntity.TYPE_USER_PASS);
                entityPass.setTitle("更换密码");
                entityPass.setSubTitle("");
                list.add(entityPass);
                //分割线
                list.add(entityDivider);
                //注销登录
                ManageUserEntity entityLogout = new ManageUserEntity();
                entityLogout.setItemType(ManageUserEntity.TYPE_USER_LOGOUT);
                entityLogout.setTitle("注销登录");
                entityLogout.setSubTitle("");
                list.add(entityLogout);
                adapter.setNewData(list);
            }
        });
    }

    private class AdapterClickListener implements ManageUserItemAdapter.OnItemTypeClickListener {

        @Override
        public void onItemClick(View v, int position, final ManageUserEntity entity) {
            positionBuffer = position;
            entityBuffer = entity;
            MainLooperManage.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    switch (entity.getItemType()) {
                        case ManageUserEntity.TYPE_USER_ICON://头像
                        {
                            PictureSelector.create(UserInfoActivity.this)
                                    .openGallery(PictureMimeType.ofImage())
                                    .imageSpanCount(4)// 每行显示个数 int
                                    .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                                    .previewImage(false)// 是否可预览图片 true or false
                                    .isCamera(true)// 是否显示拍照按钮 true or false
                                    .enableCrop(true)// 是否裁剪 true or false
                                    .compress(false)// 是否压缩 true or false
                                    .compressMode(PictureConfig.LUBAN_COMPRESS_MODE)//系统自带 or 鲁班压缩 PictureConfig.SYSTEM_COMPRESS_MODE or LUBAN_COMPRESS_MODE
                                    .compressGrade(Luban.CUSTOM_GEAR)
                                    .compressMaxKB(500)//压缩最大值kb compressGrade()为Luban.CUSTOM_GEAR有效 int
                                    .withAspectRatio(1, 1)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                                    .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                                    .circleDimmedLayer(false)// 是否圆形裁剪 true or false
//                                    .cropWH(1, 1)// 裁剪宽高比，设置如果大于图片本身宽高则无效 int
                                    .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                                    .showCropGrid(true)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                                    .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
                                    .forResult(PictureConfig.CHOOSE_REQUEST);
                        }
                        break;
                        case ManageUserEntity.TYPE_USER_NAME://姓名
                        {
                            new ErayicEditDialog.Builder(UserInfoActivity.this)
                                    .setMessage(entity.getSubTitle(), null)
                                    .setTitle("设置名字")
                                    .setButton1("取消", new ErayicEditDialog.OnClickListener() {
                                        @Override
                                        public void onClick(Dialog dialog, int which, CharSequence s) {

                                            dialog.dismiss();
                                        }
                                    })
                                    .setButton2("确定", new ErayicEditDialog.OnClickListener() {
                                        @Override
                                        public void onClick(Dialog dialog, int which, CharSequence s) {
                                            presenter.updateUserName(s.toString());
                                            dialog.dismiss();
                                        }
                                    }).show();
                        }
                        break;
                        case ManageUserEntity.TYPE_USER_TEL://电话
                        {

                        }
                        break;
                        case ManageUserEntity.TYPE_USER_BASE://基地
                        {
                            ARouter.getInstance().build("/manage/activity/SwitchBaseActivity").navigation();
                        }
                        break;
                        case ManageUserEntity.TYPE_USER_WEIXIN://微信
                        {
                            showToast("暂未实现");
                        }
                        break;
                        case ManageUserEntity.TYPE_USER_PASS://修改密码
                        {

                        }
                        break;
                        case ManageUserEntity.TYPE_USER_LOGOUT://注销登录
                        {
                            new ErayicTextDialog.Builder(UserInfoActivity.this)
                                    .setMessage("注销登录后将接受不到任何消息\n该应用会清除您的一切个人信息，直到您重新登录\n您确定要注销吗？", null)
                                    .setTitle("温馨提示")
                                    .setButton1("取消", new ErayicTextDialog.OnClickListener() {
                                        @Override
                                        public void onClick(Dialog dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    })
                                    .setButton2("确定", new ErayicTextDialog.OnClickListener() {
                                        @Override
                                        public void onClick(Dialog dialog, int which) {
                                            if (PreferenceUtils.clearData()) {
                                                dialog.dismiss();
                                                ErayicStack.getInstance().finishAllActivity();//清空所有的activity
                                                ARouter.getInstance().build("/index/Activity/LoginActivity").navigation();
                                            }
                                        }
                                    }).show();
                        }
                        break;
                        default:
                            break;
                    }
                }
            });
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//返回
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void showToast(final String msg) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ErayicToast.TextToast(getApplicationContext(), msg);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    String iconPath = "";
                    // 图片选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    if (selectList != null && selectList.size() > 0) {
                        LocalMedia media = selectList.get(0);
                        if (media.isCompressed())
                            iconPath = media.getCompressPath();
                        else if (media.isCut())
                            iconPath = media.getCutPath();
                        else
                            iconPath = media.getPath();
                        presenter.updateUserIcon(iconPath);
                    }
                    break;
            }
        }
    }
}
