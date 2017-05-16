package com.erayic.agr.serverproduct.view.custom;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;

import com.erayic.agr.common.view.codbking.DatePickDialog;
import com.erayic.agr.common.view.codbking.OnSureLisener;
import com.erayic.agr.common.view.codbking.bean.DateType;
import com.erayic.agr.serverproduct.view.ITenDayReportingView;
import com.erayic.agr.serverproduct.view.impl.WeatherTenDayReportingActivity;

import java.util.Date;

/**
 * Created by wxk on 2017/5/10.
 */

public class ReportingSortDailogManage {
    private ITenDayReportingView context;
    private DatePickDialog datePickDialog;
    private static  ReportingSortDailogManage instance;

    private ReportingSortDailogManage(final ITenDayReportingView context) {
          this.context=context;
          if (this.datePickDialog==null){
                this.datePickDialog=new DatePickDialog((Context) context);
          }

         this.datePickDialog.setOnSureLisener(new OnSureLisener() {
             @Override
             public void onSure(Date date) {
                   context.updateTenDayReportingDatas(date);
                   dimissDialog();
             }
         });
    }

    public static ReportingSortDailogManage getInstance(ITenDayReportingView context){
        if (instance==null){
            instance =new ReportingSortDailogManage(context);
        }
        return instance;
    }

    public void showDialog(){
        if (datePickDialog==null)datePickDialog=new DatePickDialog((Context) context);
        if (!datePickDialog.isShowing()){
            this.datePickDialog.show();
        }
    }

    public void dimissDialog(){
         if (datePickDialog!=null&&datePickDialog.isShowing()){
                datePickDialog.dismiss();
         }
    }

    public ReportingSortDailogManage setting(int showLimits, String title, DateType type, String messageFormat){
        //设置上下年分限制
        datePickDialog.setYearLimt(showLimits);
        //设置标题
        datePickDialog.setTitle(title);
        //设置类型
        datePickDialog.setType(type);
        //设置消息体的显示格式，日期格式
        datePickDialog.setMessageFormat(messageFormat);
        return this;
    }
    public static  void destoryManage(){
        if (instance!=null)instance=null;
    }
}
