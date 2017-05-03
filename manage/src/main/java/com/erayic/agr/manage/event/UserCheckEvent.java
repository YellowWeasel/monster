package com.erayic.agr.manage.event;

import java.util.Map;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：选择管理单元负责人
 */

public class UserCheckEvent {

    private Map<String,String> userMap;

    public Map<String, String> getUserMap() {
        return userMap;
    }

    public void setUserMap(Map<String, String> userMap) {
        this.userMap = userMap;
    }
}
