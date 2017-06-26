package com.erayic.agr.unit.presenter;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface IBatchBindServicePresenter {

    /**
     * 批次绑定服务
     */
    void bindServiceByBatch(String unitID,int unitType,String batchID, int serType, String serviceID);

    /**
     * 按指定绑定服务类型得到用户已购买的特定作物服务列表
     */
    void getSingleSeviceList(int serType);

    /**
     * 得到当前选定批次绑定的服务
     */
    void getBindService(String batchID, int serType);

    /**
     * 取消当前选定批次绑定的服务
     */
    void cancelServieBind(String batchID, int serType);

    /**
     *得到绑定服务的主题服务ID
     */
    void getBindServiceOfSubject(int serType);

}
