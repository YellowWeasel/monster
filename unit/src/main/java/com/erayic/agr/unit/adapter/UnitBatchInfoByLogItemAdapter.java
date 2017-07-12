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
import com.erayic.agr.common.net.back.unit.CommonUnitBatchLogsBean;
import com.erayic.agr.common.util.ErayicNetDate;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.unit.R;
import com.erayic.agr.unit.adapter.entity.UnitBatchInfoByLogEntity;
import com.erayic.agr.unit.adapter.holder.UnitBatchInfoByLogViewHolder;
import com.erayic.agr.unit.view.impl.AddLogActivity;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class UnitBatchInfoByLogItemAdapter extends BaseQuickAdapter<CommonUnitBatchLogsBean, UnitBatchInfoByLogViewHolder> {

    private Context context;

    public UnitBatchInfoByLogItemAdapter(Context context, List<CommonUnitBatchLogsBean> list) {
        super(R.layout.adapter_unit_batch_info_log, list);
        this.context = context;
    }

    @Override
    protected void convert(UnitBatchInfoByLogViewHolder helper, CommonUnitBatchLogsBean item) {
        if (helper.getAdapterPosition() == 0) {
            helper.jobsContentDate.setText(new DateTime(ErayicNetDate.getLongDates(item.getRecordTime())).toString("yyyy-MM-dd\nHH:mm"));
        } else {
            Period p = new Period(new DateTime(ErayicNetDate.getLongDates(item.getRecordTime())),
                    new DateTime(ErayicNetDate.getLongDates(getData().get(helper.getAdapterPosition() - 1).getRecordTime())));
            if (p.getDays() > 0)
                helper.jobsContentDate.setText(new DateTime(ErayicNetDate.getLongDates(item.getRecordTime())).toString("yyyy-MM-dd\nHH:mm"));
            else
                helper.jobsContentDate.setText(new DateTime(ErayicNetDate.getLongDates(item.getRecordTime())).toString("HH:mm"));
        }
        helper.jobsContentType.setText(item.getRecordName());
        helper.jobsContentName.setText(item.getDescipt());
        helper.jobsContentImg.setAdapter(mAdapter);
        List<LocalMedia> listImg = new ArrayList<>();
        for (int i = 0; i < item.getImages().size(); i++) {
            CommonImagesEntity entity = item.getImages().get(i);
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
