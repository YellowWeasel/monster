package com.erayic.agr.unit.view.impl;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.erayic.agr.common.AgrConstant;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.base.CommonGridImageAdapter;
import com.erayic.agr.common.base.CommonLocalMedia;
import com.erayic.agr.common.config.FullyGridLayoutManager;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.event.UnitRefreshMessage;
import com.erayic.agr.common.net.back.base.CommonImagesEntity;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchSaveLogBean;
import com.erayic.agr.common.net.back.work.CommonJobsInfoBean;
import com.erayic.agr.common.util.ErayicStack;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.ErayicTextDialog;
import com.erayic.agr.common.view.LoadingDialog;
import com.erayic.agr.common.view.tooblbar.ErayicToolbar;
import com.erayic.agr.unit.R;
import com.erayic.agr.unit.R2;
import com.erayic.agr.unit.presenter.IAddLogPresenter;
import com.erayic.agr.unit.presenter.impl.AddLogPresenterImpl;
import com.erayic.agr.unit.view.IAddLogView;
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

@Route(path = "/unit/activity/AddLogActivity", name = "增加工作日志")
public class AddLogActivity extends BaseActivity implements IAddLogView {

    @BindView(R2.id.toolbar)
    ErayicToolbar toolbar;
    @BindView(R2.id.unit_log_add_name)
    EditText unitLogAddName;
    @BindView(R2.id.unit_log_add_list)
    RecyclerView unitLogAddList;

    @Autowired
    String batchID;

    private LoadingDialog dialog;
    private CommonGridImageAdapter imageAdapter;
    //相册初始化
    private List<CommonLocalMedia> selectMedia = new ArrayList<>();

    private IAddLogPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_batch_add_log);
    }

    @Override
    public void initView() {
        toolbar.setTitle("增加工作日志");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        FullyGridLayoutManager manager = new FullyGridLayoutManager(AddLogActivity.this, 3, GridLayoutManager.VERTICAL, false);
        unitLogAddList.setLayoutManager(manager);

        imageAdapter = new CommonGridImageAdapter(AddLogActivity.this, onAddPicClickListener);
        imageAdapter.setList(selectMedia);
        imageAdapter.setEdit(true);//可编辑
        imageAdapter.setSelectMax(9);
        imageAdapter.setOnItemClickListener(new CommonGridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                List<LocalMedia> list = new ArrayList<>();
                for (int i = 0; i < imageAdapter.getList().size(); i++) {
                    CommonLocalMedia jobsLocalMedia = imageAdapter.getList().get(i);
                    LocalMedia localMedia = new LocalMedia();
                    localMedia.setMimeType(1);
                    if (jobsLocalMedia.isUpload())
                        localMedia.setPath(TextUtils.isEmpty(jobsLocalMedia.getResultImage().getRamFileName()) ? "" : (AgrConstant.IMAGE_URL_IMAGE + jobsLocalMedia.getResultImage().getRamFileName()));
                    else
                        localMedia = jobsLocalMedia.getLocalMedia();
                    localMedia.setPosition(i);
                    list.add(localMedia);
                }

//                PictureConfig.getInstance().externalPicturePreview(AddLogActivity.this, "/Erayic/image", position, list);
                PictureSelector.create(AddLogActivity.this).externalPicturePreview(position, "/Erayic/image", list);
            }
        });
        unitLogAddList.setAdapter(imageAdapter);
    }

    @Override
    public void initData() {
        presenter = new AddLogPresenterImpl(this);
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
    public void showLoading() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialog == null)
                    dialog = new LoadingDialog(AddLogActivity.this);
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
                    dialog = new LoadingDialog(AddLogActivity.this);
                dialog.dismiss();
            }
        });
    }

    @Override
    public void uploadImageResult(final List<CommonLocalMedia> selectMedia) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int position = 0;
                CommonUnitBatchSaveLogBean bean = new CommonUnitBatchSaveLogBean();
                List<CommonImagesEntity> records = new ArrayList<>();
                for (CommonLocalMedia jobsLocalMedia : selectMedia) {
                    if (jobsLocalMedia.isUpload()) {
                        position++;
                        CommonImagesEntity applyPicInfo = new CommonImagesEntity();
                        applyPicInfo.setImgPath(jobsLocalMedia.getResultImage().getRamFileName());
                        applyPicInfo.setThumbnail(jobsLocalMedia.getResultImage().getThumbnailFile());
                        records.add(applyPicInfo);
                    }
                }
                if (TextUtils.isEmpty(unitLogAddName.getText())) {
                    showToast("请填写工作日志");
                    return;
                }
                if (position == selectMedia.size()) {//图片全部上传成功
                    CommonUnitBatchSaveLogBean.ApplyPic applyPic = new CommonUnitBatchSaveLogBean.ApplyPic();
                    applyPic.setPics(records);
                    bean.setPics(applyPic);
                    bean.setBatchID(batchID);
                    bean.setDescript(unitLogAddName.getText().toString());
                    presenter.saveWorkLog(bean);
                } else {//图片未全部上传成功
                    new ErayicTextDialog.Builder(AddLogActivity.this)
                            .setMessage("有部分图片未上传成功\n是否继续上传？", null)
                            .setTitle("设置产品名称")
                            .setButton1("取消", new ErayicTextDialog.OnClickListener() {
                                @Override
                                public void onClick(Dialog dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setButton2("重新上传", new ErayicTextDialog.OnClickListener() {
                                @Override
                                public void onClick(Dialog dialog, int which) {
                                    presenter.submitImage(selectMedia);
                                    dialog.dismiss();
                                }
                            }).show();
                }


            }
        });
    }

    @Override
    public void addLogSure() {
        UnitRefreshMessage message = new UnitRefreshMessage();
        message.setMsgType(UnitRefreshMessage.UNIT_MASTER_LOG);
        EventBus.getDefault().post(message);//通知刷新
        ErayicStack.getInstance().finishCurrentActivity();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.unit_log_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//返回
            ErayicStack.getInstance().finishCurrentActivity();
        }
        if (item.getItemId() == R.id.action_unit_log_save) {//保存
            presenter.submitImage(selectMedia);
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 删除图片回调接口
     */

    private CommonGridImageAdapter.onAddPicClickListener onAddPicClickListener = new CommonGridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick(int type, int position) {
            switch (type) {
                case 0:
                    // 进入相册
//                    FunctionOptions options = new FunctionOptions.Builder()
//                            .setType(FunctionConfig.TYPE_IMAGE)
//                            .setCompress(true) //是否压缩
//                            .setEnablePixelCompress(true) //是否启用像素压缩
//                            .setEnableQualityCompress(true) //是否启质量压缩
//                            .setMaxSelectNum(9) // 可选择图片的数量
//                            .setMaxB(202400) // 压缩最大值 例如:200kb  就设置202400，202400 / 1024 = 200kb左右
//                            .setCheckNumMode(true)
//                            .setNumComplete(true) // 0/9 完成  样式
//                            .create();
//                    PictureConfig.getInstance().init(options).openPhoto(mContext, resultCallback);

                    // 进入相册 以下是例子：用不到的api可以不写
                    PictureSelector.create(AddLogActivity.this)
                            .openGallery(PictureMimeType.ofImage())
                            .maxSelectNum(9 - selectMedia.size())// 最大图片选择数量 int
                            .compress(true)// 是否压缩 true or false
                            .compressMode(PictureConfig.LUBAN_COMPRESS_MODE)//系统自带 or 鲁班压缩 PictureConfig.SYSTEM_COMPRESS_MODE or LUBAN_COMPRESS_MODE
//                            .selectionMedia()// 是否传入已选图片 List<LocalMedia> list
                            .previewEggs(true)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
                            .compressGrade(Luban.CUSTOM_GEAR)
                            .compressMaxKB(500)//压缩最大值kb compressGrade()为Luban.CUSTOM_GEAR有效 int
                            .isCamera(true)// 是否显示拍照按钮 true or false
                            .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
                    break;
                case 1:
                    // 删除图片
                    selectMedia.remove(position);
                    imageAdapter.notifyItemRemoved(position);
                    break;
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
//                    adapter.setList(selectList);
//                    adapter.notifyDataSetChanged();
//                    DebugUtil.i(TAG, "onActivityResult:" + selectList.size());
                    if (selectList == null)
                        return;
                    for (LocalMedia media : selectList) {
//                LocalMedia media = resultList.get(0);
                        CommonLocalMedia jobsLocalMedia = new CommonLocalMedia();
                        jobsLocalMedia.setLocalMedia(media);
                        if (media.isCut() && !media.isCompressed()) {
                            // 裁剪过
//                    String path = media.getCutPath();
                            jobsLocalMedia.setFinalPath(media.getCutPath());
                        } else if (media.isCompressed() || (media.isCut() && media.isCompressed())) {
                            // 压缩过,或者裁剪同时压缩过,以最终压缩过图片为准
//                    String path = media.getCompressPath();
                            jobsLocalMedia.setFinalPath(media.getCompressPath());
                        } else {
                            // 原图地址
//                    String path = media.getPath();
                            jobsLocalMedia.setFinalPath(media.getPath());
                        }
                        selectMedia.add(jobsLocalMedia);
                    }

//            Log.i("callBack_result", selectMedia.size() + "");

                    if (selectMedia != null) {
                        imageAdapter.setList(selectMedia);
                        imageAdapter.notifyDataSetChanged();
                    }
                    break;
            }
        }
    }

//    private PictureConfig.OnSelectResultCallback resultCallback = new PictureConfig.OnSelectResultCallback() {
//        @Override
//        public void onSelectSuccess(List<LocalMedia> resultList) {
//            // 多选回调
////            selectMedia = resultList;
//            selectMedia = new ArrayList<>();
//            for (LocalMedia media : resultList) {
////                LocalMedia media = resultList.get(0);
//                CommonLocalMedia jobsLocalMedia = new CommonLocalMedia();
//                jobsLocalMedia.setLocalMedia(media);
//                if (media.isCut() && !media.isCompressed()) {
//                    // 裁剪过
////                    String path = media.getCutPath();
//                    jobsLocalMedia.setFinalPath(media.getCutPath());
//                } else if (media.isCompressed() || (media.isCut() && media.isCompressed())) {
//                    // 压缩过,或者裁剪同时压缩过,以最终压缩过图片为准
////                    String path = media.getCompressPath();
//                    jobsLocalMedia.setFinalPath(media.getCompressPath());
//                } else {
//                    // 原图地址
////                    String path = media.getPath();
//                    jobsLocalMedia.setFinalPath(media.getPath());
//                }
//                selectMedia.add(jobsLocalMedia);
//            }
//
////            Log.i("callBack_result", selectMedia.size() + "");
//
//            if (selectMedia != null) {
//                imageAdapter.setList(selectMedia);
//                imageAdapter.notifyDataSetChanged();
//            }
//        }
//
//        @Override
//        public void onSelectSuccess(LocalMedia media) {
//            // 单选回调
//            CommonLocalMedia jobsLocalMedia = new CommonLocalMedia();
//            jobsLocalMedia.setLocalMedia(media);
//            if (media.isCut() && !media.isCompressed()) {
//                // 裁剪过
////                    String path = media.getCutPath();
//                jobsLocalMedia.setFinalPath(media.getCutPath());
//            } else if (media.isCompressed() || (media.isCut() && media.isCompressed())) {
//                // 压缩过,或者裁剪同时压缩过,以最终压缩过图片为准
////                    String path = media.getCompressPath();
//                jobsLocalMedia.setFinalPath(media.getCompressPath());
//            } else {
//                // 原图地址
////                    String path = media.getPath();
//                jobsLocalMedia.setFinalPath(media.getPath());
//            }
//            selectMedia.add(jobsLocalMedia);
//            if (selectMedia != null) {
//                imageAdapter.setList(selectMedia);
//                imageAdapter.notifyDataSetChanged();
//            }
//        }
//    };


}
