package com.erayic.agr.manage.presenter;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public interface ISwitchBasePresenter {

    /**
     * 得到一个用户的所有基地
     */
    void getBaseListByUser();

    /**
     * 用户变更所属基地(重新保存cookies)
     */
    void changeBase(String newBaseID);

}
