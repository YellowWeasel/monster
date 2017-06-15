package com.erayic.agr.serverproduct.view;

import com.erayic.agr.common.base.IBaseView;
import com.erayic.agr.serverproduct.adapter.entity.AgriculturalDetailInfoDatas;
import com.erayic.agr.serverproduct.adapter.entity.AgriculturalInfoDatas;

import java.util.List;

/**
 * Created by wxk on 2017/5/19.
 */

public interface IAgriculturalDetailInfoView extends IBaseView{
        void refreshAgriculturatlDetailInfoView(List<AgriculturalDetailInfoDatas> bean);
}
