package com.erayic.agr.jobs.view.impl;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.erayic.agr.common.AgrConstant;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.base.CommonGridImageAdapter;
import com.erayic.agr.common.base.CommonLocalMedia;
import com.erayic.agr.common.config.CustomLinearLayoutManager;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.event.MainRefreshMessage;
import com.erayic.agr.common.net.back.base.CommonImagesEntity;
import com.erayic.agr.common.net.back.img.CommonResultImage;
import com.erayic.agr.common.net.back.work.CommonJobsInfoBean;
import com.erayic.agr.common.util.DividerItemDecoration;
import com.erayic.agr.common.util.ErayicStack;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.ErayicTextDialog;
import com.erayic.agr.common.view.LoadingDialog;
import com.erayic.agr.common.view.tooblbar.ErayicToolbar;
import com.erayic.agr.jobs.R;
import com.erayic.agr.jobs.R2;
import com.erayic.agr.jobs.adapter.JobsInfoItemAdapter;
import com.erayic.agr.jobs.adapter.entity.JobsInfoEntity;
import com.erayic.agr.jobs.presenter.IJobsInfoPresenter;
import com.erayic.agr.jobs.presenter.impl.JobsInfoPresenterImpl;
import com.erayic.agr.jobs.view.IJobsInfoView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.compress.Luban;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

@Route(path = "/jobs/activity/JobsInfoActivity", name = "执行工作安排（查询、执行）")
public class JobsInfoActivity extends BaseActivity implements IJobsInfoView {

    @BindView(R2.id.toolbar)
    ErayicToolbar toolbar;
    @BindView(R2.id.job_info_list_RecyclerView)
    RecyclerView jobInfoListRecyclerView;

    private LoadingDialog dialog;

    @Autowired
    String strTitle;//标题
    @Autowired
    String schID;//
    @Autowired
    String unitID;
    @Autowired
    boolean isFinish;//是否完成

    private IJobsInfoPresenter presenter;
    private JobsInfoItemAdapter adapter;

    //相册初始化
    private List<CommonLocalMedia> selectMedia = new ArrayList<>();
    private CommonGridImageAdapter imageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs_jobs_info);

    }

    @Override
    public void initView() {
        toolbar.setTitle(TextUtils.isEmpty(strTitle) ? "" : strTitle);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        adapter = new JobsInfoItemAdapter(JobsInfoActivity.this, null);
        imageAdapter = new CommonGridImageAdapter(JobsInfoActivity.this, onAddPicClickListener);
        imageAdapter.setList(selectMedia);
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

//                PictureConfig.getInstance().externalPicturePreview(JobsInfoActivity.this, "/Erayic/image", position, list);
                // 预览图片 可自定长按保存路径
                PictureSelector.create(JobsInfoActivity.this).externalPicturePreview(position, "/Erayic/image", list);
            }
        });
        adapter.setImageAdapter(imageAdapter);
        //使用线性布局管理器
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(JobsInfoActivity.this);
        manager.setScrollEnabled(true);//滑动监听
        jobInfoListRecyclerView.setLayoutManager(manager);
        jobInfoListRecyclerView.setAdapter(adapter);
        if (isFinish) {
            adapter.setEdit(false);
        } else {
            adapter.setEdit(true);
        }
        jobInfoListRecyclerView.addItemDecoration(new DividerItemDecoration(JobsInfoActivity.this, DividerItemDecoration.VERTICAL_LIST));

    }

    @Override
    public void initData() {
        presenter = new JobsInfoPresenterImpl(this);
        presenter.getDayWorkDetail(schID, unitID);
    }

    @Override
    public void showLoading() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialog == null)
                    dialog = new LoadingDialog(JobsInfoActivity.this);
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
                    dialog = new LoadingDialog(JobsInfoActivity.this);
                dialog.dismiss();
            }
        });
    }

    @Override
    public void refreshJobsView(final CommonJobsInfoBean bean) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                List<JobsInfoEntity> list = new ArrayList<>();

                //工作内容
                {
                    JobsInfoEntity entity = new JobsInfoEntity();
                    entity.setItemType(JobsInfoEntity.TYPE_WORK_CONTENT);
                    entity.setName("工作内容");
                    entity.setSubName(bean.getUnitName());
                    Map<String, Object> map = new ArrayMap<>();
                    String strBuffer = "";
                    for (CommonJobsInfoBean.ContentInfo contentInfo : bean.getContent()) {
                        strBuffer += contentInfo.getResName();
                        strBuffer += (TextUtils.isEmpty(contentInfo.getNorm()) ? "" : ("    " + contentInfo.getNorm())) + "\n";
                    }
                    if (strBuffer.length() > 0)
                        map.put("key1", strBuffer.substring(0, strBuffer.length() - 1));
                    else
                        map.put("key1", "");
                    entity.setMap(map);
                    list.add(entity);
                }

                //工作要求
                if (!TextUtils.isEmpty(bean.getJobDescript())) {
                    JobsInfoEntity entity = new JobsInfoEntity();
                    entity.setItemType(JobsInfoEntity.TYPE_WORK_REQUIRE);
                    entity.setName("工作要求");
                    entity.setSubName(bean.getJobDescript());
                    list.add(entity);
                }

                //批次信息
                {
                    JobsInfoEntity entity = new JobsInfoEntity();
                    entity.setItemType(JobsInfoEntity.TYPE_WORK_UNIT);
                    entity.setName("工作范围");
                    Map<String, Object> map = new ArrayMap<>();
                    map.put("key1", bean.getBatchs());
                    entity.setMap(map);
                    list.add(entity);
                }

                //工作记录
                {
                    JobsInfoEntity entity = new JobsInfoEntity();
                    entity.setItemType(JobsInfoEntity.TYPE_WORK_NOTE);
                    entity.setName("工作记录");
                    entity.setSubName(bean.getRecords().getDescript());
                    if (isFinish)
                        imageAdapter.setEdit(false);
                    else
                        imageAdapter.setEdit(true);
                    selectMedia = new ArrayList<>();
                    for (CommonImagesEntity applyPicInfo : bean.getRecords().getRecords()) {
                        CommonLocalMedia jobsLocalMedia = new CommonLocalMedia();
                        jobsLocalMedia.setUpload(true);
                        CommonResultImage resultImage = new CommonResultImage();
                        resultImage.setRamFileName(applyPicInfo.getImgPath());
                        resultImage.setThumbnailFile(applyPicInfo.getThumbnail());
                        jobsLocalMedia.setResultImage(resultImage);
                        selectMedia.add(jobsLocalMedia);
                    }
                    imageAdapter.setList(selectMedia);
                    list.add(entity);
                }
                adapter.setNewData(list);
                imageAdapter.notifyDataSetChanged();

            }
        });
    }

    @Override
    public void uploadImageResult(final List<CommonLocalMedia> selectMedia) {

        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int position = 0;
                CommonJobsInfoBean.RecordsInfo recoder = new CommonJobsInfoBean.RecordsInfo();
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
                if (position == selectMedia.size()) {//图片全部上传成功
                    List<String> batchList = new ArrayList<>();
                    for (CommonJobsInfoBean.BatchInfo batchInfo : adapter.getBatchList()) {
                        if (batchInfo.isApply()) {
                            batchList.add(batchInfo.getBatchID());
                        }
                    }
                    if (batchList.size() == 0) {
                        showToast("请选择工作范围");
                        return;
                    }
                    if (position == 0) {
                        showToast("请至少选择一张图片");
                        return;
                    }
                    for (JobsInfoEntity entity : adapter.getData()) {
                        if (entity.getItemType() == JobsInfoEntity.TYPE_WORK_NOTE)
                            recoder.setDescript(entity.getSubName());
                    }
                    recoder.setRecords(records);
                    presenter.executeDayWork(schID, unitID, batchList, recoder);
                } else {//图片未全部上传成功
                    new ErayicTextDialog.Builder(JobsInfoActivity.this)
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
    public void submitWorkSure() {
        MainRefreshMessage message = new MainRefreshMessage();
        message.setMsgType(MainRefreshMessage.MAIN_MASTER_JOB);
        message.setSubType(-1);
        EventBus.getDefault().post(message);
        ErayicStack.getInstance().finishCurrentActivity();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!isFinish)
            getMenuInflater().inflate(R.menu.menu_jobs_jobs_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//返回
            ErayicStack.getInstance().finishCurrentActivity();
        } else if (item.getItemId() == R.id.action_bar_jobs_jobs_submit) {
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
//                            .setMaxSelectNum(9 - selectMedia.size()) // 可选择图片的数量
//                            .setMaxB(202400) // 压缩最大值 例如:200kb  就设置202400，202400 / 1024 = 200kb左右
//                            .setCheckNumMode(true)
//                            .setNumComplete(true) // 0/9 完成  样式
//                            .create();
//                    PictureConfig.getInstance().init(options).openPhoto(mContext, resultCallback);

                    // 进入相册 以下是例子：用不到的api可以不写
                    PictureSelector.create(JobsInfoActivity.this)
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
