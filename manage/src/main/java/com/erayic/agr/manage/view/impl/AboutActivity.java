package com.erayic.agr.manage.view.impl;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.CustomLinearLayoutManager;
import com.erayic.agr.common.config.MainLooperManage;
import com.erayic.agr.common.util.DividerItemDecoration;
import com.erayic.agr.common.util.ErayicStack;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.tooblbar.ErayicToolbar;
import com.erayic.agr.manage.R;
import com.erayic.agr.manage.R2;
import com.erayic.agr.manage.adapter.ManageAboutItemAdapter;
import com.erayic.agr.manage.adapter.entity.ManageAboutEntity;
import com.erayic.agr.manage.view.IAboutView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */
@Route(path = "/manage/activity/AboutActivity", name = "关于")
public class AboutActivity extends BaseActivity implements IAboutView {

    @BindView(R2.id.toolbar)
    ErayicToolbar toolbar;
    @BindView(R2.id.manage_about_recycler)
    RecyclerView manageAboutRecycler;
    @BindView(R2.id.manage_about_copyright)
    TextView manageAboutCopyright;

    private ManageAboutItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_about);
    }

    @Override
    public void initView() {
        toolbar.setTitle("关于农小二");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //使用线性布局管理器
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(AboutActivity.this);
        manager.setScrollEnabled(true);//滑动监听
        manageAboutRecycler.setLayoutManager(manager);
        adapter = new ManageAboutItemAdapter(AboutActivity.this, null);
        manageAboutRecycler.setAdapter(adapter);
        manageAboutRecycler.addItemDecoration(new DividerItemDecoration(AboutActivity.this, DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    public void initData() {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ManageAboutEntity entity = (ManageAboutEntity) adapter.getData().get(position);
                switch (entity.getIndex()) {
                    case 0://logo
                        break;
                    case 1://版本介绍
                    case 2://官网
                    case 3://帮助
                    case 4://资质
                    default:
                        showToast("暂未开放");
                        break;
                }
            }
        });

        List<ManageAboutEntity> list = new ArrayList<>();

        //logo
        {
            ManageAboutEntity entity = new ManageAboutEntity();
            entity.setItemType(ManageAboutEntity.TYPE_APP_LOGO);
            entity.setIndex(0);
            list.add(entity);
        }

        //版本介绍
        {
            ManageAboutEntity entity = new ManageAboutEntity();
            entity.setItemType(ManageAboutEntity.TYPE_ITEM);
            entity.setName("版本介绍");
            entity.setIndex(1);
            list.add(entity);
        }

        //官网
        {
            ManageAboutEntity entity = new ManageAboutEntity();
            entity.setItemType(ManageAboutEntity.TYPE_ITEM);
            entity.setName("农小二官网");
            entity.setIndex(2);
            list.add(entity);
        }

        //帮助
        {
            ManageAboutEntity entity = new ManageAboutEntity();
            entity.setItemType(ManageAboutEntity.TYPE_ITEM);
            entity.setName("农小二帮助");
            entity.setIndex(3);
            list.add(entity);
        }

        //资质
        {
            ManageAboutEntity entity = new ManageAboutEntity();
            entity.setItemType(ManageAboutEntity.TYPE_ITEM);
            entity.setName("农小二资质");
            entity.setIndex(4);
            list.add(entity);
        }

        adapter.setNewData(list);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//返回
            ErayicStack.getInstance().finishCurrentActivity();
        }
        return super.onOptionsItemSelected(item);
    }

}
