package com.erayic.agr.manage.event;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class UserInfoEvent {

    public static final int TYPE_USER_NAME = 0;//姓名
    public static final int TYPE_USER_ICON = 1;//头像

    private int type;//类型
    private Object data;//数据

    public UserInfoEvent(int type,Object data){
       this.type = type;
        this.data = data;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
