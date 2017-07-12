package com.erayic.agr.unit.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.erayic.agr.common.AgrConstant;
import com.erayic.agr.common.net.back.base.CommonImagesEntity;
import com.erayic.agr.common.net.back.enums.EnumOperationType;
import com.erayic.agr.common.net.back.unit.CommonUnitBatchResumeBean;
import com.erayic.agr.common.util.ErayicNetDate;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.unit.R;
import com.erayic.agr.unit.adapter.holder.UnitBatchInfoByResumeViewHolder;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;

import org.joda.time.DateTime;
import org.joda.time.Period;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：生产履历
 */

public class UnitBatchInfoByResumeItemAdapter extends BaseQuickAdapter<CommonUnitBatchResumeBean, UnitBatchInfoByResumeViewHolder> {

    private Context context;
    public UnitBatchInfoByResumeItemAdapter(Context context, List<CommonUnitBatchResumeBean> entityList) {
        super(R.layout.adapter_unit_batch_info_resume, entityList);
        this.context = context;
    }

    @Override
    protected void convert(UnitBatchInfoByResumeViewHolder helper, CommonUnitBatchResumeBean item) {
        //时间
        if (helper.getAdapterPosition() == 0) {
            helper.jobsContentDate.setText(new DateTime(ErayicNetDate.getLongDates(item.getOpeTime())).toString("yyyy-MM-dd\nHH:mm"));
        } else {
            Period p = new Period(new DateTime(ErayicNetDate.getLongDates(item.getOpeTime())),
                    new DateTime(ErayicNetDate.getLongDates(getData().get(helper.getAdapterPosition() - 1).getOpeTime())));
            if (p.getDays() > 0)
                helper.jobsContentDate.setText(new DateTime(ErayicNetDate.getLongDates(item.getOpeTime())).toString("yyyy-MM-dd\nHH:mm"));
            else
                helper.jobsContentDate.setText(new DateTime(ErayicNetDate.getLongDates(item.getOpeTime())).toString("HH:mm"));
        }
        //类型
        helper.jobsContentType.setText(EnumOperationType.getStatueDes(item.getOpeType()));
        //内容
        String strContentSub = "";
        for (CommonUnitBatchResumeBean.OpesInfo content : item.getOpes()) {
            strContentSub += TextUtils.isEmpty(content.getResName()) ? "" : (content.getResName() + "    ");//资源名称
            strContentSub += (TextUtils.isEmpty(content.getNorm()) ? "" : content.getNorm()) + "\n";//配比
        }
        if (TextUtils.isEmpty(item.getRecoder().getDescript())) {
            strContentSub = strContentSub.substring(0, strContentSub.length() - 1);
        } else {
            strContentSub += item.getRecoder().getDescript();
        }
        helper.jobsContentName.setText(strContentSub);
        //图片
        helper.jobsContentImg.setAdapter(mAdapter);
        List<LocalMedia> listImg = new ArrayList<>();
        for (int i = 0; i < item.getRecoder().getRecords().size(); i++) {
            CommonImagesEntity entity = item.getRecoder().getRecords().get(i);
            LocalMedia localMedia = new LocalMedia();
            localMedia.setMimeType(1);
            localMedia.setPath(TextUtils.isEmpty(entity.getImgPath()) ? "" : (AgrConstant.IMAGE_URL_IMAGE + entity.getImgPath()));
            localMedia.setPosition(i);
            listImg.add(localMedia);
        }
        helper.jobsContentImg.setImagesData(listImg);

        helper.jobsContentGoto.setVisibility(View.GONE);
    }


    private NineGridImageViewAdapter<LocalMedia> mAdapter = new NineGridImageViewAdapter<LocalMedia>() {

        @Override
        protected void onDisplayImage(Context context, ImageView imageView, LocalMedia s) {
            //加载
//            imageView.setTag(s);
            Glide.with(context)
                    .load(s.getPath())
                    .apply(AgrConstant.iconOptions)
                    .into(imageView);
        }

        @Override
        protected ImageView generateImageView(Context context) {
            return super.generateImageView(context);
        }

        @Override
        protected void onItemImageClick(Context mContext, ImageView imageView, int index, List<LocalMedia> list) {
//            super.onItemImageClick(context, imageView, index, list);
            //点击事件
//            ErayicToast.TextToast(context, list.get(index));
//            PictureConfig.getInstance().externalPicturePreview((Activity) context, "/Erayic/image", index, list);
            PictureSelector.create((Activity) context).externalPicturePreview(index, "/Erayic/image", list);
        }
    };
}
