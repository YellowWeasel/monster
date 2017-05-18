package com.erayic.agr.serverproduct;

import android.util.Log;

import com.erayic.agr.common.util.ErayicLog;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.serverproduct.view.IShowClockView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 23060 on 2017/5/10.
 */

public class DateFormatUtils {
    private  static Timer timer;
    private  static TimerTask task;
    private static  SimpleDateFormat format;
    private static IShowClockView context;

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
    public static  void   showClock(long deloy,String formattype,final IShowClockView mContext){
        if (timer==null){
            timer=new Timer();
        }
        if (format==null){
            format=new SimpleDateFormat(formattype);
        }
        if (task==null){
            task=new TimerTask() {
                @Override
                public void run() {
                    mContext.showClock(getDate());
                }
            };
        }
        timer.schedule(task,deloy);
    }

    public static  String getDate(){
        return format.format(new Date());
    }
    public static  void release(){
        timer=null;
        task=null;
        format=null;
    }

}
