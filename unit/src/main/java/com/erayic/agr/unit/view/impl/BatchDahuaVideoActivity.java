package com.erayic.agr.unit.view.impl;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.company.NetSDK.CFG_CAP_ALARM_INFO;
import com.company.NetSDK.CFG_DSPENCODECAP_INFO;
import com.company.NetSDK.FinalVar;
import com.company.NetSDK.INetSDK;
import com.company.NetSDK.NET_DEVICEINFO;
import com.company.NetSDK.NET_PARAM;
import com.company.NetSDK.SDKDEV_DSP_ENCODECAP_EX;
import com.company.NetSDK.SDK_PRODUCTION_DEFNITION;
import com.company.NetSDK.SDK_PTZ_ControlType;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.dahua.DaHuaVideoView;
import com.erayic.agr.common.dahua.DeviceDisConnect;
import com.erayic.agr.common.dahua.DeviceReConnect;
import com.erayic.agr.common.dahua.DeviceSubDisConnect;
import com.erayic.agr.common.dahua.ErayicDaHuaBundle;
import com.erayic.agr.common.net.back.device.CommonMonitorInfoEntity;
import com.erayic.agr.common.util.ErayicStack;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.LoadingDialog;
import com.erayic.agr.common.view.tooblbar.ErayicToolbar;
import com.erayic.agr.unit.R;
import com.erayic.agr.unit.R2;
import com.erayic.agr.unit.presenter.IDahuaVideoPresenter;
import com.erayic.agr.unit.presenter.impl.DahuaVideoPresenterImpl;
import com.erayic.agr.unit.view.IDaHuaVideoView;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = "/unit/activity/BatchDahuaVideoActivity", name = "视频监控")
public class BatchDahuaVideoActivity extends AppCompatActivity implements IDaHuaVideoView, OnClickListener {

    @BindView(R2.id.toolbar)
    ErayicToolbar toolbar;

    private DaHuaVideoView monitorView;
    private LinearLayout lin_live_title_left, lin_live_title_right;
    //	private PopupWindowsLiveMenu menus;
    private ImageButton iv_live_bt_top, iv_live_bt_bottom, iv_live_bt_left, iv_live_bt_right, iv_live_bt_screen;
    private LoadingDialog dialog;

    @Autowired
    String serialNum;

//    @Autowired
//    String ip;//设备IP
//    @Autowired
//    int port;//设备端口
//    @Autowired
//    String loginName;//登陆名称
//    @Autowired
//    String loginPass;//登陆密码
//    @Autowired
//    int passNum;//通道编号
//    @Autowired
//    boolean isControl;//是否可操作

    private int m_nGlobalChn = 0;
    private int outPortNum = 1;// 码流

    static CFG_CAP_ALARM_INFO stCfgCapAlarm = new CFG_CAP_ALARM_INFO();

    static long m_loginHandle = 0;
    static NET_DEVICEINFO deviceInfo;
    static boolean m_speedCtrl;
    static int m_schedule;
    static int nStreaMask = 0;

    private SDKDEV_DSP_ENCODECAP_EX stEncodeCapOld = new SDKDEV_DSP_ENCODECAP_EX();
    private CFG_DSPENCODECAP_INFO stEncodeCapNew = new CFG_DSPENCODECAP_INFO();

    private int nSpecCap = 20;

    private int nExtraChnNum;//DVR通道个数
    private int nExtraAlarmOutPortNum;//从码流个数

    private IDahuaVideoPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_unit_batch_dahua_video);
        ErayicStack.getInstance().addActivity(this);//Activity 入栈
        ARouter.getInstance().inject(this);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    public void initView() {
        toolbar.setTitle("");
        toolbar.setBackgroundColor(getResources().getColor(R.color.transparent));
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        monitorView = (DaHuaVideoView) findViewById(R.id.erayic_jar);
        iv_live_bt_top = (ImageButton) findViewById(R.id.iv_live_bt_top);
        iv_live_bt_top.setOnClickListener(this);
        iv_live_bt_bottom = (ImageButton) findViewById(R.id.iv_live_bt_bottom);
        iv_live_bt_bottom.setOnClickListener(this);
        iv_live_bt_left = (ImageButton) findViewById(R.id.iv_live_bt_left);
        iv_live_bt_left.setOnClickListener(this);
        iv_live_bt_right = (ImageButton) findViewById(R.id.iv_live_bt_right);
        iv_live_bt_right.setOnClickListener(this);
        iv_live_bt_screen = (ImageButton) findViewById(R.id.iv_live_bt_screen);
        iv_live_bt_screen.setOnClickListener(this);

    }

    public void initData() {
        presenter = new DahuaVideoPresenterImpl(this);
        presenter.getMonitorInfo(serialNum);

    }

    @Override
    public void loadingConnection(final CommonMonitorInfoEntity entity) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (entity.isControlled()) {
                    iv_live_bt_top.setVisibility(View.VISIBLE);
                    iv_live_bt_bottom.setVisibility(View.VISIBLE);
                    iv_live_bt_left.setVisibility(View.VISIBLE);
                    iv_live_bt_right.setVisibility(View.VISIBLE);
                }
            }
        });
        initVideo();
        videoLogin(entity.getGateWay().getIP(), entity.getGateWay().getPort(), entity.getGateWay().getLoginName(), entity.getGateWay().getLoginPass(),
                entity.getPassNum(), entity.isControlled());


    }

    /**
     * 初始化视频
     */
    private void initVideo() {
        INetSDK.LoadLibrarys();
        DeviceDisConnect disConnect = new DeviceDisConnect();// 网络断线处理
        INetSDK.Init(disConnect);// 初始化SDK
        INetSDK.SetConnectTime(4000, 3);// 设置连接设备超时时间和尝试次数

        NET_PARAM stNetParam = new NET_PARAM();// 初始化登录时的相关设置
        stNetParam.nWaittime = 30000; // 函数等待超时时间
        stNetParam.nSearchRecordTime = 30000; // 录像回放超时时间
        INetSDK.SetNetworkParam(stNetParam);// 设置登录环境
        stCfgCapAlarm = new CFG_CAP_ALARM_INFO();

    }

    @SuppressLint("UseValueOf")
    private void videoLogin(String ip, int port, String loginName, String loginPass, int passNum, boolean isControl) {
        deviceInfo = new NET_DEVICEINFO();// 初始化设备信息
        if (m_loginHandle != 0) {
            INetSDK.Logout(m_loginHandle);
            m_loginHandle = 0;
        }

        DeviceReConnect reConnect = new DeviceReConnect();
        INetSDK.SetAutoReconnect(reConnect);

        DeviceSubDisConnect subDisConnect = new DeviceSubDisConnect();
        INetSDK.SetSubconnCallBack(subDisConnect);

        m_loginHandle = INetSDK.LoginEx(ip, port, loginName,
                loginPass, 20, null, deviceInfo, new Integer(0));
        if (m_loginHandle != 0) {

            stCfgCapAlarm = new CFG_CAP_ALARM_INFO();
            char szOutBuffer[] = new char[10240];
            Integer stError = new Integer(0);
            boolean bQN = INetSDK.QueryNewSystemInfo(m_loginHandle,
                    FinalVar.CFG_CAP_ALARM, 0, szOutBuffer, stError, 5000);// 新系统能力查询接口,查询系统能力信息
            if (bQN) {
                bQN = INetSDK.ParseData(FinalVar.CFG_CAP_ALARM, szOutBuffer,
                        stCfgCapAlarm, null);// 组成要设置的配置信息
            }
            nExtraChnNum = deviceInfo.byChanNum;
            SDK_PRODUCTION_DEFNITION stDef = new SDK_PRODUCTION_DEFNITION();
            if (-1 == deviceInfo.byChanNum) {

                boolean bRet = INetSDK.QueryProductionDefinition(m_loginHandle,
                        stDef, 3000);
                if (bRet) {
                    nExtraChnNum = stDef.nVideoInChannel
                            + stDef.nMaxRemoteInputChannels;
                }
            }
            nExtraAlarmOutPortNum = deviceInfo.byAlarmOutPortNum;
            supportInvalidateOptionsMenu();
            showVideo(passNum, isControl);
            dismissLoading();
        } else {
            showToast("登陆失败");
            dismissLoading();
            loginFail();
        }
    }

    private void showVideo(final int passNum, final boolean isControl) {
        if (passNum >= nExtraChnNum) {
            showToast("通道号错误，请联系管理员");
            loginFail();
            return;
        }
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                videoView = new DaHuaVideoView(BatchDahuaVideoActivity.this);
//                videoView.setLayoutParams(new LinearLayout.LayoutParams(
//                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
//                monitorView.addView(videoView);
                monitorView.setManageable(isControl);// 设置设备权限
                monitorView.setM_nGlobalChn(passNum);// 通道
                monitorView.setExtraAlarmOutPortNum(nExtraAlarmOutPortNum);// 从码流
                monitorView.setM_loginHandle(BatchDahuaVideoActivity.m_loginHandle);// Handle
                ErayicDaHuaBundle.setbGesture(true);
                monitorView.stop();//重要说明：：：：先停止，避免第一次加载黑屏的问题
                monitorView.start();
            }
        });
    }

    public void Logout() {
        if (m_loginHandle == 0) {
            return;
        }
        boolean bResult = INetSDK.Logout(m_loginHandle);

        if (bResult) {
            m_loginHandle = 0;
        }
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        int i = v.getId();
        if (i == R.id.iv_live_bt_top) {
            INetSDK.SDKPTZControl(BatchDahuaVideoActivity.m_loginHandle, m_nGlobalChn, SDK_PTZ_ControlType.SDK_PTZ_UP_CONTROL,
                    (byte) 0, (byte) 3, (byte) 0, false);
            delay(500);
            INetSDK.SDKPTZControl(BatchDahuaVideoActivity.m_loginHandle, m_nGlobalChn, SDK_PTZ_ControlType.SDK_PTZ_UP_CONTROL,
                    (byte) 0, (byte) 3, (byte) 0, true);

        } else if (i == R.id.iv_live_bt_bottom) {
            INetSDK.SDKPTZControl(BatchDahuaVideoActivity.m_loginHandle, m_nGlobalChn, SDK_PTZ_ControlType.SDK_PTZ_DOWN_CONTROL,
                    (byte) 0, (byte) 3, (byte) 0, false);
            delay(500);
            INetSDK.SDKPTZControl(BatchDahuaVideoActivity.m_loginHandle, m_nGlobalChn, SDK_PTZ_ControlType.SDK_PTZ_DOWN_CONTROL,
                    (byte) 0, (byte) 3, (byte) 0, true);

        } else if (i == R.id.iv_live_bt_left) {
            INetSDK.SDKPTZControl(BatchDahuaVideoActivity.m_loginHandle, m_nGlobalChn, SDK_PTZ_ControlType.SDK_PTZ_LEFT_CONTROL,
                    (byte) 0, (byte) 3, (byte) 0, false);
            delay(500);
            INetSDK.SDKPTZControl(BatchDahuaVideoActivity.m_loginHandle, m_nGlobalChn, SDK_PTZ_ControlType.SDK_PTZ_LEFT_CONTROL,
                    (byte) 0, (byte) 3, (byte) 0, true);

        } else if (i == R.id.iv_live_bt_right) {
            INetSDK.SDKPTZControl(BatchDahuaVideoActivity.m_loginHandle, m_nGlobalChn, SDK_PTZ_ControlType.SDK_PTZ_RIGHT_CONTROL,
                    (byte) 0, (byte) 3, (byte) 0, false);
            delay(500);
            INetSDK.SDKPTZControl(BatchDahuaVideoActivity.m_loginHandle, m_nGlobalChn, SDK_PTZ_ControlType.SDK_PTZ_RIGHT_CONTROL,
                    (byte) 0, (byte) 3, (byte) 0, true);

        } else if (i == R.id.iv_live_bt_screen) {
            monitorView.capture();
        }
    }

    private void delay(int timer) {// 延时操作
        try {
            Thread.sleep(timer);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        monitorView.stop();
        Logout();
        INetSDK.Cleanup();
        ErayicStack.getInstance().finishActivity(this);//Activity 出栈
        super.onDestroy();
    }

    @Override
    public void showToast(final String msg) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ErayicToast.TextToast(getApplicationContext(), msg);
            }
        });
    }

    @Override
    public void showLoading() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialog == null)
                    dialog = new LoadingDialog(BatchDahuaVideoActivity.this);
                dialog.show();
            }
        });
    }

    @Override
    public void dismissLoading() {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialog == null)
                    dialog = new LoadingDialog(BatchDahuaVideoActivity.this);
                dialog.dismiss();
            }
        });
    }

    @Override
    public void loginFail() {
        ErayicStack.getInstance().finishCurrentActivity();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//返回
            finish();
        } else if (item.getItemId() == R.id.action_video_1) {//主码流
            monitorView.stop();
            monitorView.setExtraAlarmOutPortNum(0);// 码流
            monitorView.start();
        } else if (item.getItemId() == R.id.action_video_2) {//辅码流1
            monitorView.stop();
            monitorView.setExtraAlarmOutPortNum(1);// 码流
            monitorView.start();
        } else if (item.getItemId() == R.id.action_video_3) {//辅码流2
            monitorView.stop();
            monitorView.setExtraAlarmOutPortNum(2);// 码流
            monitorView.start();
        } else if (item.getItemId() == R.id.action_video_4) {//辅码流3
            monitorView.stop();
            monitorView.setExtraAlarmOutPortNum(3);// 码流
            monitorView.start();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_unit_video, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem stream1 = menu.findItem(R.id.action_video_1);
        MenuItem stream2 = menu.findItem(R.id.action_video_2);
        MenuItem stream3 = menu.findItem(R.id.action_video_3);
        MenuItem stream4 = menu.findItem(R.id.action_video_4);
        stream1.setVisible(nExtraAlarmOutPortNum >= 0);
        stream2.setVisible(nExtraAlarmOutPortNum >= 1);
        stream3.setVisible(nExtraAlarmOutPortNum >= 2);
        stream4.setVisible(nExtraAlarmOutPortNum >= 3);
        return super.onPrepareOptionsMenu(menu);
    }
}
