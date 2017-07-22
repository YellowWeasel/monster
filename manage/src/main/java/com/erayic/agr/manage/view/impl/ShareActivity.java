package com.erayic.agr.manage.view.impl;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.common.config.CustomLinearLayoutManager;
import com.erayic.agr.common.config.PreferenceUtils;
import com.erayic.agr.common.util.DividerItemDecoration;
import com.erayic.agr.common.util.ErayicStack;
import com.erayic.agr.common.util.ErayicToast;
import com.erayic.agr.common.view.tooblbar.ErayicToolbar;
import com.erayic.agr.manage.R;
import com.erayic.agr.manage.R2;
import com.erayic.agr.manage.adapter.ManageAboutItemAdapter;
import com.erayic.agr.manage.adapter.ManageShareItemAdapter;
import com.erayic.agr.manage.adapter.entity.ManageShareEntity;
import com.erayic.agr.manage.view.IShareView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */
@Route(path = "/manage/activity/ShareActivity", name = "分享")
public class ShareActivity extends BaseActivity implements IShareView {

    @BindView(R2.id.toolbar)
    ErayicToolbar toolbar;
    @BindView(R2.id.manage_share_recycler)
    RecyclerView manageShareRecycler;

    private ManageShareItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_share);
    }

    @Override
    public void initView() {
        toolbar.setTitle("推荐给好友");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //使用线性布局管理器
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(ShareActivity.this);
        manager.setScrollEnabled(true);//滑动监听
        manageShareRecycler.setLayoutManager(manager);
        adapter = new ManageShareItemAdapter(ShareActivity.this, null);
        manageShareRecycler.setAdapter(adapter);
    }

    @Override
    public void initData() {
        adapter.setOnShareClickListener(new ManageShareItemAdapter.OnShareClickListener() {
            @Override
            public void onClick(View view, String key, int index) {
                switch (index) {
                    case 0://微信
                    case 1://朋友圈
                    case 2://QQ
                    default:
                        showToast("暂未开放");
                        break;
                }
            }
        });
        List<ManageShareEntity> list = new ArrayList<>();
        {
            ManageShareEntity entity = new ManageShareEntity();
            entity.setItemType(ManageShareEntity.TYPE_SHARE_IMAGE);
            entity.setName(PreferenceUtils.getParam("UserIcon"));
            entity.setSubName(PreferenceUtils.getParam("UserName"));
            list.add(entity);
        }
        {
            ManageShareEntity entity = new ManageShareEntity();
            entity.setItemType(ManageShareEntity.TYPE_SHARE_OHTER);
            list.add(entity);
        }
        adapter.setNewData(list);
    }

    @Override
    public void showToast(String msg) {
        ErayicToast.TextToast(getApplicationContext(), msg);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//返回
            ErayicStack.getInstance().finishCurrentActivity();
        }
        return super.onOptionsItemSelected(item);
    }


}
