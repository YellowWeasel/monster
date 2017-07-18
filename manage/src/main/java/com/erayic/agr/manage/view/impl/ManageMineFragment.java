package com.erayic.agr.manage.view.impl;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.base.BaseFragment;
import com.erayic.agr.common.config.CustomLinearLayoutManager;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.config.PreferenceUtils;
import com.erayic.agr.common.util.DividerItemDecoration;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.tooblbar.ErayicToolbar;
import com.erayic.agr.manage.R;
import com.erayic.agr.manage.R2;
import com.erayic.agr.manage.adapter.ManageMineItemAdapter;
import com.erayic.agr.manage.adapter.entity.ManageMineEntity;
import com.erayic.agr.manage.event.UserInfoEvent;
import com.erayic.agr.manage.view.IMineView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */
@Route(path = "/manage/fragment/ManageMineFragment", name = "我的")
public class ManageMineFragment extends BaseFragment implements IMineView {

    //    /* 标题栏 */
    @Autowired
    String titleName;
    @BindView(R2.id.fake_status_bar)
    View fakeStatusBar;
    @BindView(R2.id.toolbar)
    ErayicToolbar toolbar;
    @BindView(R2.id.manage_mine_RecyclerView)
    RecyclerView manageMineRecyclerView;

    private ManageMineItemAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_manage_mine;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        EventBus.getDefault().register(this);// 注册EventBus
        toolbar.setTitle(titleName);
        //使用线性布局管理器
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(getActivity());
        manager.setScrollEnabled(true);//滑动监听
        manageMineRecyclerView.setLayoutManager(manager);
        adapter = new ManageMineItemAdapter(getActivity(), null);
        adapter.setOnItemUrlClickListener(new ManageMineItemAdapter.onItemUrlClickListener() {
            @Override
            public void onItemClick(View v, String url) {
                if (TextUtils.equals(url, "/xxx/xxx"))
                    showToast("暂未开放");
                else if (TextUtils.equals(url, "/unit/activity/BatchDahuaVideoActivity")) {
                    ARouter.getInstance()
                            .build(url)
                            .withString("ip", "192.168.0.199")
                            .withInt("port", 37777)
                            .withString("loginName", "admin")
                            .withString("loginPass", "admin")
                            .withInt("passNum", 0)
                            .withBoolean("isControl", true)
                            .navigation();//跳转到具体的页面
                } else
                    ARouter.getInstance().build(url).navigation();//跳转到具体的页面

            }
        });
        manageMineRecyclerView.setAdapter(adapter);
        manageMineRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST, DividerItemDecoration.DividerType.TYPE_F2F2F2));
    }

    @Override
    protected void initData() {
        //初始化数据
        List<ManageMineEntity> list = new ArrayList<>();
        //设置分割区域
        ManageMineEntity entityDivider = new ManageMineEntity();
        entityDivider.setItemType(ManageMineEntity.TYPE_DIVIDER);
        //设置个人
        ManageMineEntity entityUserInfo = new ManageMineEntity();
        entityUserInfo.setItemType(ManageMineEntity.TYPE_INFO);
        entityUserInfo.setToUrl("/manage/activity/UserInfoActivity");
        ManageMineEntity.PersonalInfo personalInfo = new ManageMineEntity.PersonalInfo();
        personalInfo.setHeadImg(PreferenceUtils.getParam("UserIcon"));
        personalInfo.setUserName(PreferenceUtils.getParam("UserName"));
        personalInfo.setUserRole(PreferenceUtils.getParam("UserRole", 0));
        entityUserInfo.setPersonalInfo(personalInfo);
        list.add(entityUserInfo);
        //分割区域
        list.add(entityDivider);
        //设置基地
        ManageMineEntity entityBaseInfo = new ManageMineEntity();
        entityBaseInfo.setItemType(ManageMineEntity.TYPE_CONTENT);
        entityBaseInfo.setToUrl("/manage/activity/BaseListActivity");
        entityBaseInfo.setName("基地信息");
        entityBaseInfo.setIcon(R.drawable.image_manage_base_icon);
        list.add(entityBaseInfo);
        //设置人员
        ManageMineEntity entityPeople = new ManageMineEntity();
        entityPeople.setItemType(ManageMineEntity.TYPE_CONTENT);
        entityPeople.setToUrl("/manage/activity/PersonnelListActivity");
        entityPeople.setName("人员信息");
        entityPeople.setIcon(R.drawable.image_manage_people_icon);
        list.add(entityPeople);
        //设置企业
        ManageMineEntity entityEnt = new ManageMineEntity();
        entityEnt.setItemType(ManageMineEntity.TYPE_CONTENT);
        entityEnt.setToUrl("/manage/activity/EnterpriseActivity");
        entityEnt.setName("企业信息");
        entityEnt.setIcon(R.drawable.image_manage_ent_icon);
        list.add(entityEnt);
        //分割区域
        list.add(entityDivider);
        //设置产品
        ManageMineEntity entityGoods = new ManageMineEntity();
        entityGoods.setItemType(ManageMineEntity.TYPE_CONTENT);
        entityGoods.setToUrl("/manage/activity/ProduceListActivity");
        entityGoods.setName("产品");
        entityGoods.setIcon(R.drawable.image_manage_goods_icon);
        list.add(entityGoods);
        //设置作业
        ManageMineEntity entityWork = new ManageMineEntity();
        entityWork.setItemType(ManageMineEntity.TYPE_CONTENT);
        entityWork.setToUrl("/jobs/activity/AdvanceWorkActivity");
        entityWork.setName("预设作业");
        entityWork.setIcon(R.drawable.image_manage_job_icon);
        list.add(entityWork);
        //设置生产资料
        ManageMineEntity entityMaterial = new ManageMineEntity();
        entityMaterial.setItemType(ManageMineEntity.TYPE_CONTENT);
        entityMaterial.setToUrl("/manage/activity/ResourceActivity");
        entityMaterial.setName("生产资料");
        entityMaterial.setIcon(R.drawable.image_manage_material_icon);
        list.add(entityMaterial);
        //设置设备
        ManageMineEntity entityEqu = new ManageMineEntity();
        entityEqu.setItemType(ManageMineEntity.TYPE_CONTENT);
        entityEqu.setToUrl("/xxx/xxx");
        entityEqu.setName("设备");
        entityEqu.setIcon(R.drawable.image_manage_equ_icon);
        list.add(entityEqu);
        //分割区域
        list.add(entityDivider);
        //设置推荐给好友
        ManageMineEntity entityRecommend = new ManageMineEntity();
        entityRecommend.setItemType(ManageMineEntity.TYPE_CONTENT);
        entityRecommend.setToUrl("/xxx/xxx");
        entityRecommend.setName("推荐给好友");
        entityRecommend.setIcon(R.drawable.image_manage_recommend_icon);
        list.add(entityRecommend);
        //设置系统帮助
        ManageMineEntity entityHelp = new ManageMineEntity();
        entityHelp.setItemType(ManageMineEntity.TYPE_CONTENT);
        entityHelp.setToUrl("/xxx/xxx");
        entityHelp.setName("系统帮助");
        entityHelp.setIcon(R.drawable.image_manage_help_icon);
        list.add(entityHelp);
        //分割区域
        list.add(entityDivider);
        //设置测试
        ManageMineEntity entityTest = new ManageMineEntity();
        entityTest.setItemType(ManageMineEntity.TYPE_CONTENT);
        entityTest.setToUrl("/unit/activity/BatchDahuaVideoActivity");
        entityTest.setName("测试视频");
        entityTest.setIcon(R.drawable.image_manage_recommend_icon);
        list.add(entityTest);
        adapter.setNewData(list);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUpdateUserName(UserInfoEvent event) {
        for (ManageMineEntity entity : adapter.getData()) {
            if (entity.getItemType() == ManageMineEntity.TYPE_INFO) {
                switch (event.getType()) {
                    case UserInfoEvent.TYPE_USER_NAME://名字
                    {
                        entity.getPersonalInfo().setUserName(event.getData().toString());
                    }
                    break;
                    case UserInfoEvent.TYPE_USER_ICON://图片
                    {
                        entity.getPersonalInfo().setHeadImg(event.getData().toString());
                    }
                    break;
                }
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void showToast(final String msg) {
        MainLooperManage.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ErayicToast.TextToast(getActivity().getApplicationContext(), msg);
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//解绑EventBus
    }
}
