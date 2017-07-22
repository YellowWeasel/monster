package com.erayic.agr.manage.view.impl;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.method.KeyListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.CustomLinearLayoutManager;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.config.PreferenceUtils;
import com.erayic.agr.common.event.ManageRefreshMessage;
import com.erayic.agr.common.net.back.CommonProduceInfoBean;
import com.erayic.agr.common.net.back.enums.EnumRequestType;
import com.erayic.agr.common.util.DividerItemDecoration;
import com.erayic.agr.common.util.ErayicStack;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.ErayicTextDialog;
import com.erayic.agr.common.view.LoadingDialog;
import com.erayic.agr.common.view.tooblbar.ErayicToolbar;
import com.erayic.agr.manage.R;
import com.erayic.agr.manage.R2;
import com.erayic.agr.manage.adapter.ManageProduceInfoAdapter;
import com.erayic.agr.manage.adapter.ManageProduceListAdapter;
import com.erayic.agr.manage.adapter.entity.ManageProduceInfoEntity;
import com.erayic.agr.manage.presenter.IProduceInfoPresenter;
import com.erayic.agr.manage.presenter.impl.ProduceInfoPresenterImpl;
import com.erayic.agr.manage.view.IProduceInfoView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.compress.Luban;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

@Route(path = "/manage/activity/ProduceInfoActivity", name = "产品详情")
public class ProduceInfoActivity extends BaseActivity implements IProduceInfoView {

    private final static int ACTIVITY_REQUEST = 9000;

    @BindView(R2.id.toolbar)
    ErayicToolbar toolbar;
    @BindView(R2.id.manage_produce_info_list)
    RecyclerView manageProduceInfoList;

    @Autowired
    String proName;
    @Autowired
    String proID;

    private boolean isUpdater;

    private LoadingDialog dialog;

    private IProduceInfoPresenter presenter;
    private ManageProduceInfoAdapter adapter;
    private KeyListener keyListener;

    private CommonProduceInfoBean produceInfoBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_produce_info);
    }

    @Override
    public void initView() {
        toolbar.setTitle((TextUtils.isEmpty(proName) ? "产品" : proName) + "信息");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //使用线性布局管理器
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(ProduceInfoActivity.this);
        manager.setScrollEnabled(true);//滑动监听
        manageProduceInfoList.setLayoutManager(manager);
        adapter = new ManageProduceInfoAdapter(ProduceInfoActivity.this, null);
        adapter.setOnProduceInfoClickListener(new ManageProduceInfoAdapter.OnProduceInfoClickListener() {
            @Override
            public void onItemClick(View v, int itemType, int position) {
                switch (itemType) {
                    case ManageProduceInfoEntity.TYPE_ICON://产品图标
                    {
                        PictureSelector.create(ProduceInfoActivity.this)
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
                    case ManageProduceInfoEntity.TYPE_TYPE://产品类别
                    {
                        ARouter.getInstance().build("/manage/activity/SelectActivity")
                                .withBoolean("isRadio", true)
                                .withInt("selectType", EnumRequestType.TYPE_RETURN_PRODUCE_TYPE)
                                .navigation(ProduceInfoActivity.this, ACTIVITY_REQUEST);
                    }
                    break;
                }
            }
        });
        keyListener = new EditText(ProduceInfoActivity.this).getKeyListener();
        manageProduceInfoList.setAdapter(adapter);
        manageProduceInfoList.addItemDecoration(new DividerItemDecoration(ProduceInfoActivity.this, DividerItemDecoration.VERTICAL_LIST, DividerItemDecoration.DividerType.TYPE_F2F2F2));
    }

    @Override
    public void initData() {
        presenter = new ProduceInfoPresenterImpl(this);
        presenter.getProductDetail(proID);
    }

    @Override
    public void refreshPersonnelView(final CommonProduceInfoBean bean) {
        produceInfoBean = bean;
        if (bean == null) {
            showToast("未查询到数据");
            ErayicStack.getInstance().finishCurrentActivity();
        }

        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                List<ManageProduceInfoEntity> list = new ArrayList<>();
                //分割线定义
                ManageProduceInfoEntity entityDivider = new ManageProduceInfoEntity();
                entityDivider.setItemType(ManageProduceInfoEntity.TYPE_DIVIDER);

                //产品名称
                {
                    ManageProduceInfoEntity entity = new ManageProduceInfoEntity();
                    entity.setItemType(ManageProduceInfoEntity.TYPE_NAME);
                    entity.setName("产品名称");
                    entity.setSubName(bean.getProductName());
                    list.add(entity);
                }

                //产品图片
                {
                    ManageProduceInfoEntity entity = new ManageProduceInfoEntity();
                    entity.setItemType(ManageProduceInfoEntity.TYPE_ICON);
                    entity.setName("产品图标");
                    entity.setSubName(bean.getIcon());
                    list.add(entity);
                }

                //产品类别
                {
                    ManageProduceInfoEntity entity = new ManageProduceInfoEntity();
                    entity.setItemType(ManageProduceInfoEntity.TYPE_TYPE);
                    entity.setName("产品类别");
                    entity.setSubName(bean.getClassifyName());
                    entity.setData(bean.getClassifyID());
                    list.add(entity);
                }

                //分割线
                list.add(entityDivider);
                adapter.setNewData(list);
            }
        });
    }

    @Override
    public void showLoading() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialog == null)
                    dialog = new LoadingDialog(ProduceInfoActivity.this);
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
                    dialog = new LoadingDialog(ProduceInfoActivity.this);
                dialog.dismiss();
            }
        });
    }

    @Override
    public void updateIconSure(final String url) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                produceInfoBean.setIcon(url);
                refreshPersonnelView(produceInfoBean);
            }
        });
    }

    @Override
    public void noticeRefresh() {
        ManageRefreshMessage message = new ManageRefreshMessage();
        message.setMsgType(ManageRefreshMessage.MANAGE_MASTER_PRODUCE_INFO);
        EventBus.getDefault().post(message);
    }

    @Override
    public void finishCurrent() {
        ErayicStack.getInstance().finishCurrentActivity();
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
                        presenter.updateProductIcon(proID, iconPath);
                    }
                    break;
            }
        }
        if (resultCode == EnumRequestType.TYPE_RETURN_PRODUCE_TYPE && requestCode == ACTIVITY_REQUEST) {
            HashMap<String, Object> map = (HashMap<String, Object>) data.getSerializableExtra("map");
            //产品类型
            {
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    for (int i = 0; i < adapter.getData().size(); i++) {
                        ManageProduceInfoEntity entity = adapter.getData().get(i);
                        if (entity.getItemType() == ManageProduceInfoEntity.TYPE_TYPE) {
                            entity.setSubName(entry.getValue().toString());
                            entity.setData(entry.getKey());
                        }
                        adapter.notifyItemChanged(i);
                    }
                }
            }
        }
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
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem save = menu.findItem(R.id.action_manage_produce_info_save);
        MenuItem modify = menu.findItem(R.id.action_manage_produce_info_modify);
        MenuItem delete = menu.findItem(R.id.action_manage_produce_info_delete);
        if (isUpdater) {
            save.setVisible(true);
            modify.setVisible(true);
            modify.setTitle("取消");
            delete.setVisible(false);
            adapter.setKeyListener(keyListener);
            adapter.notifyDataSetChanged();
        } else {
            if (produceInfoBean != null)
                refreshPersonnelView(produceInfoBean);
            save.setVisible(false);
            modify.setVisible(true);
            modify.setTitle("编辑");
            delete.setVisible(true);
            adapter.setKeyListener(null);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_manage_produce_info, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//返回
            ErayicStack.getInstance().finishCurrentActivity();
        } else if (item.getItemId() == R.id.action_manage_produce_info_save) {//保存
            String proName = null;
            int classicID = -1;
            for (ManageProduceInfoEntity entity : adapter.getData()) {
                if (entity.getItemType() == ManageProduceInfoEntity.TYPE_NAME) {
                    proName = TextUtils.isEmpty(entity.getSubName()) ? "" : entity.getSubName();
                }
                if (entity.getItemType() == ManageProduceInfoEntity.TYPE_TYPE) {
                    classicID = Integer.valueOf(entity.getData().toString());
                }
            }
            if (TextUtils.isEmpty(proName)) {
                return super.onOptionsItemSelected(item);
            } else {
                presenter.updateProduct(proID, proName, classicID, produceInfoBean.getDescript());
            }
        } else if (item.getItemId() == R.id.action_manage_produce_info_modify) {//修改
            isUpdater = !isUpdater;
            supportInvalidateOptionsMenu();
        } else if (item.getItemId() == R.id.action_manage_produce_info_delete) {//删除
            new ErayicTextDialog.Builder(ProduceInfoActivity.this)
                    .setMessage("将要删除该产品的一切信息\n确定删除吗？", null)
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
                            dialog.dismiss();
                            presenter.deleteProduct(proID);
                        }
                    }).show();

        }
        return super.onOptionsItemSelected(item);
    }

}
