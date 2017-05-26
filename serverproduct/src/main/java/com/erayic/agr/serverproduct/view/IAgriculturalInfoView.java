package com.erayic.agr.serverproduct.view;

import com.erayic.agr.common.base.IBaseView;
import com.erayic.agr.serverproduct.adapter.entity.AgriculturalInfoDatas;

import java.util.List;

/**
 * Created by wxk on 2017/5/19.
 */

public interface IAgriculturalInfoView extends IBaseView{
        void refreshAgriculturatlInfoView(List<AgriculturalInfoDatas> beans);
        void openRefresh();
        void clearRefresh();
        void loadMoreSure(List<AgriculturalInfoDatas> beans);
        void loadMoreFailure();
}
