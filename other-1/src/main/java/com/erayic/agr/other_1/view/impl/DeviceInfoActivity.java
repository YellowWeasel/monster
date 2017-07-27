package com.erayic.agr.other_1.view.impl;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.CustomLinearLayoutManager;
import com.erayic.agr.common.util.DividerItemDecoration;
import com.erayic.agr.common.view.tooblbar.ErayicToolbar;
import com.erayic.agr.other_1.R;
import com.erayic.agr.other_1.view.IDeviceInfoView;
import com.erayic.agr.other_1.adapter.DeviceInfoItemAdapter;
import com.erayic.agr.other_1.entity.MonitoringData;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */
@Route(path = "/device/activity/DeviceInfoActivity", name = "设备信息")
public class DeviceInfoActivity extends BaseActivity implements IDeviceInfoView {
     private ErayicToolbar mToolbar;
     private RecyclerView mRecyclerView;
     private DeviceInfoItemAdapter mAdministrationAdapter;
     private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_deviceinfo);
        mContext=this;
        initID();

    }
    /*初始化*/
    private void initID(){
        mToolbar= (ErayicToolbar) findViewById(R.id.name_toolbar_id);
        mRecyclerView= (RecyclerView) findViewById(R.id.name_recyclerview_id);


        initTitle();
        initSetup();
    }
    /*标题栏*/
    private void initTitle(){
        mToolbar.setTitle("远程监控管理");
        setSupportActionBar(mToolbar);
        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
    /*布局*/
    private void initSetup(){
        //使用线性布局管理器
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(mContext);
        manager.setScrollEnabled(true);//滑动监听
        mRecyclerView.setLayoutManager(manager);
        mAdministrationAdapter=new DeviceInfoItemAdapter(mContext,null);
        mAdministrationAdapter.setmOndeviceInfoItem(new DeviceInfoItemAdapter.OndeviceInfoItem() {
            @Override
            public void onClick(View view, String uri) {
                Toast.makeText(mContext,uri,Toast.LENGTH_LONG).show();

            }
        });
        mRecyclerView.setAdapter(mAdministrationAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL_LIST,DividerItemDecoration.DividerType.TYPE_F2F2F2));
        addpend();
    }

    public void addpend(){
        List<MonitoringData> mList =new ArrayList<>();
        /*分割线*/
        MonitoringData mHaivig=new MonitoringData();
        mHaivig.setItemType(MonitoringData.TYPE_ONE);
        mList.add(mHaivig);
        /*分割线2*/
        MonitoringData mHaivi=new MonitoringData();
        mHaivi.setItemType(MonitoringData.TYPE_FIVE);


       /*远程监控*/
        MonitoringData mMonitoring=new MonitoringData();
        mMonitoring.setItemType(MonitoringData.TYPE_TWO);
        mMonitoring.setUri("/other/activity/DeviceInfoActivity");
        mMonitoring.setTheMoment("远程监控");
        mList.add(mMonitoring);

        /*运行模式*/
        MonitoringData mOperation=new MonitoringData();
        mOperation.setItemType(MonitoringData.TYPE_THREE);
        mOperation.setUri("/other/activity/DeviceInfoActivity");
        mOperation.setOperation("运行模式");
        mOperation.setMotion("自动");
        mList.add(mOperation);
        /*控制温度*/
        MonitoringData mCommand=new MonitoringData();
        mCommand.setItemType(MonitoringData.TYPE_FOUR);
        mCommand.setMonitoring("控制温度");
        mCommand.setTemp("28.6c");
        mList.add(mCommand);

        /*设备状态*/
        MonitoringData mEquipment=new MonitoringData();
        mEquipment.setItemType(MonitoringData.TYPE_FOUR);
        mEquipment.setMonitoring("设备状态");
        mEquipment.setTemp("在线");
        mList.add(mEquipment);


        /*风扇控制方式*/
        MonitoringData mCommunicarion=new MonitoringData();
        mCommunicarion.setItemType(MonitoringData.TYPE_THREE);
        mCommunicarion.setUri("/other/activity/DeviceInfoActivity");
        mCommunicarion.setOperation("风扇控制方式");
        mCommunicarion.setMotion("自动");
       // mList.add(mCommunicarion);

        /*高温风扇启动阈值*/
        MonitoringData mTemperature=new MonitoringData();
        mTemperature.setItemType(MonitoringData.TYPE_THREE);
        mTemperature.setUri("/other/activity/DeviceInfoActivity");
        mTemperature.setOperation("高温风扇启动阈值");
        mTemperature.setMotion("40c");
       // mList.add(mTemperature);

        /*低温风扇停止阈值*/
        MonitoringData mMicrotherm=new MonitoringData();
        mMicrotherm.setItemType(MonitoringData.TYPE_THREE);
        mMicrotherm.setUri("/other/activity/DeviceInfoActivity");
        mMicrotherm.setOperation("低温风扇停止阈值");
        mMicrotherm.setMotion("28c");
       // mList.add(mMicrotherm);

         /*风扇状态*/
        MonitoringData mStop=new MonitoringData();
        mStop.setItemType(MonitoringData.TYPE_FOUR);
        mStop.setMonitoring("风扇状态");
        mStop.setTemp("已停止");
        mList.add(mStop);

        /*蜂鸣器状态*/
        MonitoringData mWarning=new MonitoringData();
        mWarning.setItemType(MonitoringData.TYPE_THREE);
        mWarning.setUri("/other/activity/DeviceInfoActivity");
        mWarning.setOperation("蜂鸣器状态");
        mWarning.setMotion("预警鸣");
        mList.add(mWarning);
        mList.add(mHaivig);
        /*状态刷新*/
        MonitoringData mBreak =new MonitoringData();
        mBreak.setItemType(MonitoringData.TYPE_SIX);
        mBreak.setUri("/other/activity/DeviceInfoActivity");
        mBreak.setReboot("状态刷新");

        mList.add(mBreak);
        /*设备重启*/
        mList.add(mHaivi);
        MonitoringData mRstart =new MonitoringData();
        mRstart.setItemType(MonitoringData.TYPE_SIX);
        mRstart.setUri("/other/activity/DeviceInfoActivity");
        mRstart.setReboot("设备重启");
        mList.add(mRstart);


        mAdministrationAdapter.setNewData(mList);

    }

    @Autowired
    String serialNum;//	控制设备序号
    @Autowired
    String deviceName;//设备名称

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
