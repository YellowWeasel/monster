package com.erayic.agr.serverproduct.presenter;

import com.erayic.agr.serverproduct.adapter.entity.PoliciesRegulationsTitleDatas;

import java.util.List;

/**
 * Created by wxk on 2017/5/11.
 */

public interface IPoliciesRegulationsPresenter {
    void getPoliciesRegulationsDatas(int pageIndex,int pageSize);
    void initPoliciesRegulationsDatas(int pageSize);
    List<PoliciesRegulationsTitleDatas> sortPoliciesRegulationsDatasByProvince(String province, List<PoliciesRegulationsTitleDatas> titleDatasList,int sort);
}
