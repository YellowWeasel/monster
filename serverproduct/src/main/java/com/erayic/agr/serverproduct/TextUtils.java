package com.erayic.agr.serverproduct;

import java.math.BigDecimal;

/**
 * Created by wxk on 2017/5/18.
 */

public class TextUtils {
    public static  String FormatNumber(double number,int scale){
        BigDecimal format=new BigDecimal(number);
        return format.setScale(scale,BigDecimal.ROUND_HALF_UP).toString();
    }

    public static  String FormatNumber(String number,int scale){
        BigDecimal format=new BigDecimal(number);
        return format.setScale(scale,BigDecimal.ROUND_HALF_UP).toString();
    }
}
