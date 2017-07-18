package com.erayic.agr.common.dahua;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.LinearLayout;

import com.company.NetSDK.CFG_DSPENCODECAP_INFO;
import com.company.NetSDK.FinalVar;
import com.company.NetSDK.INetSDK;
import com.company.NetSDK.SDKDEV_DSP_ENCODECAP_EX;
import com.company.NetSDK.SDK_PTZ_ControlType;
import com.company.NetSDK.SDK_RealPlayType;
import com.company.PlaySDK.Constants;
import com.company.PlaySDK.IPlaySDK;
import com.erayic.agr.common.R;
import com.erayic.agr.common.util.ErayicLog;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.util.SDCardUtils;

import org.joda.time.DateTime;

import java.io.File;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：大华视频集成
 */

public class DaHuaVideoView extends LinearLayout implements View.OnTouchListener {

    private Context mContext;
    private SurfaceView m_PlayView;
    private SurfaceHolder holder;
    private long m_loginHandle;// 登录Handle
    private int m_nGlobalChn;// 通道
    private int nExtraAlarmOutPortNum;
    private boolean manageable = false;// 设备可控权限 true有权限

    private GestureDetector mGesture = null;
    private ScaleGestureDetector mScaleGestureDetector = null;

    ErayicRealDataCallBackEx m_callback = new ErayicRealDataCallBackEx();
    ErayicVideoDataCallBack m_VideoCallback = new ErayicVideoDataCallBack();

    private SDKDEV_DSP_ENCODECAP_EX stEncodeCapOld = new SDKDEV_DSP_ENCODECAP_EX();
    private CFG_DSPENCODECAP_INFO stEncodeCapNew = new CFG_DSPENCODECAP_INFO();

    public DaHuaVideoView(Context context) {
        super(context, null);
        mContext = context;
    }

    public DaHuaVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        INetSDK.LoadLibrarys();
        mGesture = new GestureDetector(mContext, new MyGestureListener());
        mGesture.setIsLongpressEnabled(true);
        mScaleGestureDetector = new ScaleGestureDetector(mContext,
                new MyScaleGestureListener());
        m_PlayView = new SurfaceView(mContext);
        m_PlayView.setLayoutParams(new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        DaHuaVideoView.this.addView(m_PlayView);

        holder = m_PlayView.getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {// 函数传递

            @Override
            public void surfaceDestroyed(SurfaceHolder arg0) {
                // TODO Auto-generated method stub
                // 当Surface销毁的时候调用该函数
                ErayicLog.d("[playsdk]surface", "surfaceDestroyed");
            }

            @Override
            public void surfaceCreated(SurfaceHolder arg0) {
                // TODO Auto-generated method stub
                // 当Surface第一次创建后会立即调用该函数
                IPlaySDK.InitSurface(ErayicDaHuaBundle.nPort, m_PlayView);
                ErayicLog.d("[playsdk]surface", "surfaceCreated");
            }

            @Override
            public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2,
                                       int arg3) {
                // TODO Auto-generated method stub
                // 当Surface的状态（大小和格式）发生变化的时候会调用该函数，在surfaceCreated调用后该函数至少会被调用一次。
                ErayicLog.d("[playsdk]surface", "surfaceChanged");
            }
        });
    }

    /**
     * 设置登录Handle
     */
    public void setM_loginHandle(long m_loginHandle) {
        this.m_loginHandle = m_loginHandle;
    }

    /**
     * 设置通道
     */
    public void setM_nGlobalChn(int m_nGlobalChn) {
        this.m_nGlobalChn = m_nGlobalChn;
    }

    /**
     * 辅流码个数
     */
    public void setExtraAlarmOutPortNum(int arg) {
        this.nExtraAlarmOutPortNum = arg;
    }

    /**
     * 设置设备可控权限
     */
    public void setManageable(boolean b) {
        this.manageable = b;
    }

    public void start() {// 建立连接通讯
        initView();
    }

    public void stop() {// 断开
        StopRealPlay();
    }

    private void initView() {

        // 码流设置（为了传输，有辅流码的摄像头辅流码优先）
        if (ErayicDaHuaBundle.isbGesture()) {
            m_PlayView.setOnTouchListener(this);
            m_PlayView.setFocusable(true);
            m_PlayView.setClickable(true);
        }

        final int nnType = nExtraAlarmOutPortNum;// SDK_RType_Realplay_0就为主码流。nType从码流个数
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                Looper.prepare();// 启用Looper
//                SDK_RType_Realplay = 0;实时预览
//                SDK_RType_Multiplay = 1;多画面预览
//                SDK_RType_Realplay_0 = 2;实时监视-主码流，等同于SDK_RType_Realplay
//                SDK_RType_Realplay_1 = 3;实时监视-从码流1
//                SDK_RType_Realplay_2 = 4;实时监视-从码流2
//                SDK_RType_Realplay_3 = 5;实时监视-从码流3
//                SDK_RType_Multiplay_1 = 6;多画面预览－1画面
//                SDK_RType_Multiplay_4 = 7;多画面预览－4画面
//                SDK_RType_Multiplay_8 = 8;多画面预览－8画面
//                SDK_RType_Multiplay_9 = 9;多画面预览－9画面
//                SDK_RType_Multiplay_16 = 10;多画面预览－16画面
//                SDK_RType_Multiplay_6 = 11;多画面预览－6画面
//                SDK_RType_Multiplay_12 = 12;多画面预览－12画面
                if (StartRealPlay(SDK_RealPlayType.SDK_RType_Realplay_0
                        + nnType)) {
                    m_callback = new ErayicRealDataCallBackEx();
                    m_VideoCallback = new ErayicVideoDataCallBack();
                    if (ErayicDaHuaBundle.lRealHandle != 0) {
                        INetSDK.SetRealDataCallBackEx(ErayicDaHuaBundle.lRealHandle,
                                m_callback, 1);
                    }
                }
                Looper.loop();// 让Looper开始工作，从消息队列里取消息，处理消息。写在Looper.loop()之后的代码不会被执行

            }
        }).start();

        // 查询DSP扩展能力描述
//        if (INetSDK.QueryDevState(m_loginHandle, FinalVar.SDK_DEVSTATE_DSP_EX,
//                stEncodeCapOld, 15000)) {
//        } else if (GetDevConfig(FinalVar.CFG_CMD_HDVR_DSP, stEncodeCapNew,
//                m_loginHandle, m_nGlobalChn, 1024 * 70)) {
//        }

    }

    @SuppressLint("UseValueOf")
    public static boolean GetDevConfig(String strCmd, Object cmdObject,
                                       long hHandle, int nChn, int nBufferLen) {
        boolean result = false;
        Integer error = new Integer(0);
        char szBuffer[] = new char[nBufferLen];

        if (INetSDK.GetNewDevConfig(hHandle, strCmd, nChn, szBuffer,
                nBufferLen, error, 10000)) {
            if (INetSDK.ParseData(strCmd, szBuffer, cmdObject, null)) {
                result = true;
            } else {
                result = false;
            }
        } else {
            result = false;
        }
        return result;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View view, MotionEvent event) {
        // TODO Auto-generated method stub
        int pointCount = event.getPointerCount();
        if (pointCount == 1) {
            return mGesture.onTouchEvent(event);
        } else {
            return mScaleGestureDetector.onTouchEvent(event);
        }
    }

    /**
     * 截屏
     */
    public void capture() {
        if (0 != ErayicDaHuaBundle.lRealHandle) {
            String fileName = new DateTime().toString("yyyyMMddHHmmssSSS") + ".jpg";
            if (createFile(
                    SDCardUtils.getSDPath() + "/Erayic/Agro/VideoScreenshot/",
                    fileName)) {
                int nRet = IPlaySDK.PLAYCatchPicEx(ErayicDaHuaBundle.nPort,
                        SDCardUtils.getSDPath() + "/Erayic/Agro/VideoScreenshot/"
                                + fileName, Constants.PicFormat_JPEG);
                if (0 != nRet) {
                    playAudio();
                    ErayicToast.TextToast(mContext, "图片路径：/Erayic/Agro/VideoScreenshot/"
                            + fileName);
                }
            }
        }
    }

    /**
     * 播放音效
     */
    private void playAudio() {
        MediaPlayer mediaPlayer;
        mediaPlayer = MediaPlayer.create(mContext, R.raw.screenshot);
        mediaPlayer.start();
    }

    public boolean createFile(String strPath, String strFile) {
        File path = new File(strPath);
        if (!path.exists()) {
            try {
                if (!path.mkdirs()) {
                    ErayicToast.TextToast(mContext, "应用程序无权创建路径：" + strPath);
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        File file = new File(strPath + strFile);
        if (file.exists()) {
            file.delete();
        }

        try {
            if (!file.createNewFile()) {
                ErayicToast.TextToast(mContext, "应用程序无权创建文件：" + strFile);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    public boolean StartRealPlay(int nStreamType) {
        ErayicDaHuaBundle.lRealHandle = INetSDK.RealPlayEx(m_loginHandle,
                m_nGlobalChn, nStreamType);

        if (ErayicDaHuaBundle.lRealHandle == 0) {
            return false;
        }
        ErayicDaHuaBundle.nPort = IPlaySDK.PLAYGetFreePort();
        boolean bOpenRet = IPlaySDK.PLAYOpenStream(ErayicDaHuaBundle.nPort, null, 0,
                1024 * 1024 * 2) == 0 ? false : true;
        if (bOpenRet) {
            boolean bPlayRet = IPlaySDK
                    .PLAYPlay(ErayicDaHuaBundle.nPort, m_PlayView) == 0 ? false
                    : true;
            if (bPlayRet) {
                boolean bSuccess = IPlaySDK
                        .PLAYPlaySoundShare(ErayicDaHuaBundle.nPort) == 0 ? false
                        : true;
                if (!bSuccess) {
                    IPlaySDK.PLAYStop(ErayicDaHuaBundle.nPort);
                    IPlaySDK.PLAYCloseStream(ErayicDaHuaBundle.nPort);
                    return false;
                }

                if (-1 == ErayicDaHuaBundle.nCurVolume) {
                    ErayicDaHuaBundle.nCurVolume = IPlaySDK
                            .PLAYGetVolume(ErayicDaHuaBundle.nPort);
                } else {
                    IPlaySDK.PLAYSetVolume(ErayicDaHuaBundle.nPort,
                            ErayicDaHuaBundle.nCurVolume);
                }
            } else {
                IPlaySDK.PLAYCloseStream(ErayicDaHuaBundle.nPort);
                return false;
            }
        } else {
            return false;
        }

        return true;
    }

    private void StopRealPlay() {
        try {
            IPlaySDK.PLAYStop(ErayicDaHuaBundle.nPort);
            IPlaySDK.PLAYStopSoundShare(ErayicDaHuaBundle.nPort);
            IPlaySDK.PLAYCloseStream(ErayicDaHuaBundle.nPort);

            if (ErayicDaHuaBundle.bRecordFlag) {
                INetSDK.StopSaveRealData(ErayicDaHuaBundle.lRealHandle);
                ErayicDaHuaBundle.bRecordFlag = false;
            }

            INetSDK.StopRealPlayEx(ErayicDaHuaBundle.lRealHandle);

            if (null != ErayicDaHuaBundle.m_Fout) {
                ErayicDaHuaBundle.m_Fout.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ErayicDaHuaBundle.lRealHandle = 0;
    }

    /**
     * 单点处理
     */
    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            // 参数解释：
            // e1：第1个ACTION_DOWN MotionEvent
            // e2：最后一个ACTION_MOVE MotionEvent
            // velocityX：X轴上的移动速度，像素/秒
            // velocityY：Y轴上的移动速度，像素/秒

            // 权限判断
            if (!manageable) {
                return false;
            }
            // 触发条件 ：
            // X轴的坐标位移大于FLING_MIN_DISTANCE，且移动速度大于FLING_MIN_VELOCITY个像素/秒
            final int FLING_MIN_DISTANCE = 50, FLING_MIN_VELOCITY = 100;
            if (Math.abs(event1.getX() - event2.getX()) >= Math.abs(event1
                    .getY() - event2.getY())) {
                if (event1.getX() - event2.getX() > FLING_MIN_DISTANCE
                        && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
                    // 右
                    byte step = stepCount(velocityX);
                    PTZControlAction(m_nGlobalChn,
                            SDK_PTZ_ControlType.SDK_PTZ_RIGHT_CONTROL,
                            (byte) 0, step, (byte) 0, false);
                    delay(500);
                    PTZControlAction(m_nGlobalChn,
                            SDK_PTZ_ControlType.SDK_PTZ_RIGHT_CONTROL,
                            (byte) 0, step, (byte) 0, true);

                } else if (event2.getX() - event1.getX() > FLING_MIN_DISTANCE
                        && Math.abs(velocityX) > FLING_MIN_VELOCITY) {

                    // 左
                    byte step = stepCount(velocityX);
                    PTZControlAction(m_nGlobalChn,
                            SDK_PTZ_ControlType.SDK_PTZ_LEFT_CONTROL, (byte) 0,
                            step, (byte) 0, false);
                    delay(500);
                    PTZControlAction(m_nGlobalChn,
                            SDK_PTZ_ControlType.SDK_PTZ_LEFT_CONTROL, (byte) 0,
                            step, (byte) 0, true);

                }
            } else {
                if (event1.getY() - event2.getY() > FLING_MIN_DISTANCE
                        && Math.abs(velocityY) > FLING_MIN_VELOCITY) {
                    // 下
                    byte step = stepCount(velocityY);
                    PTZControlAction(m_nGlobalChn,
                            SDK_PTZ_ControlType.SDK_PTZ_DOWN_CONTROL, (byte) 0,
                            step, (byte) 0, false);
                    delay(500);
                    PTZControlAction(m_nGlobalChn,
                            SDK_PTZ_ControlType.SDK_PTZ_DOWN_CONTROL, (byte) 0,
                            step, (byte) 0, true);
                } else if (event2.getY() - event1.getY() > FLING_MIN_DISTANCE
                        && Math.abs(velocityY) > FLING_MIN_VELOCITY) {
                    // 上
                    byte step = stepCount(velocityY);
                    PTZControlAction(m_nGlobalChn,
                            SDK_PTZ_ControlType.SDK_PTZ_UP_CONTROL, (byte) 0,
                            step, (byte) 0, false);
                    delay(500);
                    PTZControlAction(m_nGlobalChn,
                            SDK_PTZ_ControlType.SDK_PTZ_UP_CONTROL, (byte) 0,
                            step, (byte) 0, true);
                }
            }
            return false;
        }
    }

    /**
     * 多点处理
     */
    private class MyScaleGestureListener implements ScaleGestureDetector.OnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            // TODO Auto-generated method stub
            // 权限判断
            if (!manageable) {
                return false;
            }
            // 响应缩放手势的事件进展
            float span1 = detector.getCurrentSpan();
            float span2 = detector.getPreviousSpan();
            // span1>span2 放大
            System.out.println("span1=" + span1);
            System.out.println("span2=" + span2);
            if (span1 > span2) {
                PTZControlAction(m_nGlobalChn,
                        SDK_PTZ_ControlType.SDK_PTZ_ZOOM_ADD_CONTROL, (byte) 0,
                        (byte) 10, (byte) 0, false);
                delay(500);
                PTZControlAction(m_nGlobalChn,
                        SDK_PTZ_ControlType.SDK_PTZ_ZOOM_ADD_CONTROL, (byte) 0,
                        (byte) 10, (byte) 0, true);
            } else if (span1 < span2) {
                PTZControlAction(m_nGlobalChn,
                        SDK_PTZ_ControlType.SDK_PTZ_ZOOM_DEC_CONTROL, (byte) 0,
                        (byte) 10, (byte) 0, false);
                delay(500);
                PTZControlAction(m_nGlobalChn,
                        SDK_PTZ_ControlType.SDK_PTZ_ZOOM_DEC_CONTROL, (byte) 0,
                        (byte) 10, (byte) 0, true);
            }

            return false;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector arg0) {
            // TODO Auto-generated method stub
            // 一定要返回true才会进入onScale()这个函数
            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector arg0) {
            // TODO Auto-generated method stub

        }

    }

    private void PTZControlAction(int nChn, int nControl, byte param1,
                                  byte param2, byte param3, boolean arg) {

        boolean zRet = INetSDK.SDKPTZControl(m_loginHandle, nChn, nControl,
                param1, param2, param3, arg);
        System.out.println(zRet);
    }

    private byte stepCount(float velocity) {// 步长计算
        byte arg = 0;
        velocity = Math.abs(velocity);
        if (velocity >= 6000) {
            arg = 8;
        } else if (velocity < 6000 && velocity >= 5000) {
            arg = 7;
        } else if (velocity < 5000 && velocity >= 4000) {
            arg = 6;
        } else if (velocity < 4000 && velocity >= 3000) {
            arg = 5;
        } else if (velocity < 3000 && velocity >= 2000) {
            arg = 4;
        } else if (velocity < 2000 && velocity >= 1000) {
            arg = 3;
        } else if (velocity < 1000 && velocity >= 500) {
            arg = 2;
        } else if (velocity < 500 && velocity >= 200) {
            arg = 1;
        } else if (velocity < 200) {
            arg = 0;
        }
        return arg;
    }

    private void delay(int timer) {// 延时操作
        try {
            Thread.sleep(timer);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
