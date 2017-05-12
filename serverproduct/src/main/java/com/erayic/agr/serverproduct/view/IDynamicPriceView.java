package com.erayic.agr.serverproduct.view;

import com.erayic.agr.common.base.IBaseView;
import com.erayic.agr.common.net.back.api.CommonDynamicPriceBean;

/**
 * Created by wxk on 2017/5/11.
 */

public interface IDynamicPriceView extends IBaseView{

    void refreshDynamicPrice(CommonDynamicPriceBean bean);
}
