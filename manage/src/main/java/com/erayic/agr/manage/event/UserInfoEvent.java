package com.erayic.agr.manage.event;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class UserInfoEvent {

    private String userName;//用户名称
    private String userIcon;//用户头像

    public UserInfoEvent( String userName,String userIcon){
        this.userName = userName;
        this.userIcon = userIcon;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }
}
