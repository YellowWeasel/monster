package com.erayic.agr.serverproduct.view;

import com.erayic.agr.common.base.IBaseView;
import com.erayic.agr.serverproduct.adapter.entity.PoliciesRegulationsDetailDatas;

/**
 * Created by wxk on 2017/5/12.
 */

public interface IPoliciesRegulationDetailView extends IBaseView {
        void refreshPoliciesRegulationDatas(PoliciesRegulationsDetailDatas datas);
}
