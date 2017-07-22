package com.erayic.agr.common.net.back.device;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：视频信息
 */

public class CommonMonitorInfoEntity {

    private String SerialNum;//设备序列号
    private String Name;//名称
    private GateWayInfo GateWay;//网关信息
    private int PassNum;//通道号
    private boolean IsControlled;//是否有云台（可控）

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public GateWayInfo getGateWay() {
        return GateWay;
    }

    public void setGateWay(GateWayInfo gateWay) {
        GateWay = gateWay;
    }

    public int getPassNum() {
        return PassNum;
    }

    public void setPassNum(int passNum) {
        PassNum = passNum;
    }

    public boolean isControlled() {
        return IsControlled;
    }

    public void setControlled(boolean controlled) {
        IsControlled = controlled;
    }

    public String getSerialNum() {
        return SerialNum;
    }

    public void setSerialNum(String serialNum) {
        SerialNum = serialNum;
    }

    /**
     * 网关信息
     */
    public static class GateWayInfo {
        private String IP;//IP
        private int Port;//端口
        private String LoginName;//登陆名称
        private String LoginPass;//登陆密码

        public String getIP() {
            return IP;
        }

        public void setIP(String IP) {
            this.IP = IP;
        }

        public int getPort() {
            return Port;
        }

        public void setPort(int port) {
            Port = port;
        }

        public String getLoginName() {
            return LoginName;
        }

        public void setLoginName(String loginName) {
            LoginName = loginName;
        }

        public String getLoginPass() {
            return LoginPass;
        }

        public void setLoginPass(String loginPass) {
            LoginPass = loginPass;
        }
    }
}
