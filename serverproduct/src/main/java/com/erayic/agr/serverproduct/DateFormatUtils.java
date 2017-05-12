package com.erayic.agr.serverproduct;

import android.util.Log;

import com.erayic.agr.common.util.ErayicLog;
import com.erayic.agr.common.util.ErayicToast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 23060 on 2017/5/10.
 */

public class DateFormatUtils {
    public static int formatDate(Date date,String formatText){
        SimpleDateFormat format=new SimpleDateFormat(formatText);
        String strDate=format.format(date);
        try {
            return Integer.valueOf(strDate);
        }catch (Exception ex){
            ErayicLog.e("数值转化异常","输入合法的时间格式");
            return 0;
        }
    }
}
