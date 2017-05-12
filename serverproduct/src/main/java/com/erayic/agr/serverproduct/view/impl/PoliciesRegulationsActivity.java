package com.erayic.agr.serverproduct.view.impl;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erayic.agr.common.base.BaseActivity;
import com.erayic.agr.serverproduct.R;
import com.erayic.agr.serverproduct.R2;
import com.erayic.agr.serverproduct.adapter.entity.PoliciesRegulationsDetailDatas;
import com.erayic.agr.serverproduct.adapter.entity.PoliciesRegulationsTitleDatas;
import com.erayic.agr.serverproduct.presenter.IPoliciesRegulationsPresenter;
import com.erayic.agr.serverproduct.presenter.impl.PoliciesRegulationsPresenter;
import com.erayic.agr.serverproduct.view.IPoliciesRegulartionsView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by wxk on 2017/5/8.
 */
@Route(path = "/serverproduct/activity/PoliciesRegulationsActivity",name = "政策法规")
public class PoliciesRegulationsActivity extends BaseActivity implements IPoliciesRegulartionsView, AdapterView.OnItemClickListener {
   @BindView(R2.id.serverproduct_polices_regulations_title_textview)
    TextView titleTextView;
   @BindView(R2.id.serverproduct_policies_regulations_listview)
    ListView contentListView;
    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    PoliciesRegulationAdapter adapter;
    IPoliciesRegulationsPresenter presenter;
    List<PoliciesRegulationsTitleDatas> titleDatas;
    int pageIndex;
    int pageSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policies_regulations);
    }

    @Override
    public void initView() {
        toolbar.setTitle("政策法规");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        contentListView.setAdapter((adapter=new PoliciesRegulationAdapter()));
        contentListView.setOnItemClickListener(this);
    }

    @Override
    public void initData() {
        presenter=new PoliciesRegulationsPresenter(this);
        pageIndex=1;
        pageSize=15;
        presenter.getPoliciesRegulationsDatas(pageIndex,pageSize);
    }

    @Override
    public void refreshPoliciesRegulartionsDataView(List<PoliciesRegulationsDetailDatas> mBeans) {
          if (adapter!=null)adapter.notifyDataSetChanged();
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PoliciesRegulationsDetailDatas item= (PoliciesRegulationsDetailDatas) parent.getItemAtPosition(position);
                ARouter.getInstance().build("").withParcelable("mPoliciesRegulationsData",item).navigation();
                this.finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//返回
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public class  PoliciesRegulationAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return (titleDatas ==null)?0: titleDatas.size();
        }
        @Override
        public Object getItem(int position) {
            return titleDatas.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        public class  ViewHolder{
            TextView policiesTextView;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
           ViewHolder holder;
            if (convertView==null){
                convertView= LayoutInflater.from(PoliciesRegulationsActivity.this).inflate(R.layout.policies_regulation_item,parent,false);
                holder=new ViewHolder();
                convertView.setTag(holder);
            }else{
                holder= (ViewHolder) convertView.getTag();
            }
            holder.policiesTextView.setText(titleDatas.get(position).getTitle());
            return convertView;
        }
    }
}
