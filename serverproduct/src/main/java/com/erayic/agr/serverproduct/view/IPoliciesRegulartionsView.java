package com.erayic.agr.serverproduct.view;

import com.erayic.agr.common.base.IBaseView;
import com.erayic.agr.common.net.back.CommonOrderBean;
import com.erayic.agr.serverproduct.adapter.entity.PoliciesRegulationsDetailDatas;
import com.erayic.agr.serverproduct.adapter.entity.PoliciesRegulationsTitleDatas;

import java.util.List;

/**
 * Created by wxk on 2017/5/8.
 */

public interface IPoliciesRegulartionsView extends IBaseView {
     void  refreshPoliciesRegulartionsDataView(List<PoliciesRegulationsDetailDatas> bean);
     /**
      * 开启刷新
      */
     void openRefresh();

     /**
      * 关闭刷新
      */
     void clearRefresh();

     /**
      * 刷新列表
      */
     void refreshPoliciesRegulartionsView(List<PoliciesRegulationsTitleDatas> list);

     /**
      * 加载更多显示
      */
     void loadMoreSure(List<PoliciesRegulationsTitleDatas> list);

     /**
      * 加载更多失败
      */
     void loadMoreFailure();
}
