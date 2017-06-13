package com.erayic.agr.common.model;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.erayic.agr.common.net.OnDataListener;
import com.erayic.agr.common.net.back.work.CommonJobInfoBean;
import com.erayic.agr.common.net.back.work.CommonJobsInfoBean;
import com.erayic.agr.common.net.back.work.CommonJobsListManagerBean;
import com.erayic.agr.common.net.back.work.CommonJobsListUserBean;
import com.erayic.agr.common.net.back.work.CommonWorkInfoBean;
import com.erayic.agr.common.net.back.work.CommonWorkListBean;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IWorkModel extends IProvider {

    /**
     * 得到所有已定义作业列表
     */
    void getJobList(OnDataListener<List<CommonWorkListBean>> listener);

    /**
     * 保存一个自定义作业
     */
    void updateJob(CommonWorkInfoBean bean, OnDataListener<Object> listener);

    /**
     * 删除一个自定义作业
     */
    void deleteJob(String jobID, OnDataListener<Object> listener);

    /**
     * 得到指定预定义作业详情
     */
    void getJobDetails(String jobID, OnDataListener<CommonWorkInfoBean> listener);

    /**
     * 增加一个工作安排
     */
    void saveSchedule(CommonJobInfoBean bean,List<String> unitIDs,OnDataListener<Object> listener);

    /**
     *得到指定用户、指定日期的工作安排
     */
    void getDayWorkJobByUser(String specifyDay,OnDataListener<CommonJobsListUserBean> listener);


    /**
     * 得到指定工作安排详情
     */
    void getDayWorkDetail(String schID, String unitID, int unitType,OnDataListener<CommonJobsInfoBean> listener);

    /**
     * 执行工作安排
     */
    void executeDayWork(String schID, String unitID, int unitType, List<String> batchIDs, CommonJobsInfoBean.RecordsInfo recoder,OnDataListener<Object> listener);

    /**
     * 得到管理者指定日期的工作安排
     */
    void getDayWorkJobByManager(String specifyDay, int type,OnDataListener<CommonJobsListManagerBean> listener);
}
