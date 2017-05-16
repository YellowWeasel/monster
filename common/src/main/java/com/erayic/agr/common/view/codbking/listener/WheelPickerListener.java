package com.erayic.agr.common.view.codbking.listener;


import com.erayic.agr.common.view.codbking.bean.DateBean;
import com.erayic.agr.common.view.codbking.bean.DateType;

/**
 * Created by codbking on 2016/9/22.
 */

public interface WheelPickerListener {
     void onSelect(DateType type, DateBean bean);
}
