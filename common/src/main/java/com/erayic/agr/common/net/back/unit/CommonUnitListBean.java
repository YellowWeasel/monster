package com.erayic.agr.common.net.back.unit;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class CommonUnitListBean {

    private String UnitID;//管理单元ID
    private String UnitName;//管理单元名称
    private UnitEnvInfoBean EnvInfo;//环境参数
    private UnitListRemoteCtrlBean RemoteCtrl;//控制设备
    private List<UnitListMonitorsBean> Monitors;//监控
    private List<UnitBatchListBean> Batchs;//批次


    /**
     * 批次
     */
    public static class UnitBatchListBean {
        private String BatchID;//批次ID
        private String ProductName;//种植作物
        private String Icon;//图片
        private String StartTime;//种植时间
        private String Mature;//预计成熟时间

        public String getBatchID() {
            return BatchID;
        }

        public void setBatchID(String batchID) {
            BatchID = batchID;
        }

        public String getProductName() {
            return ProductName;
        }

        public void setProductName(String productName) {
            ProductName = productName;
        }

        public String getIcon() {
            return Icon;
        }

        public void setIcon(String icon) {
            Icon = icon;
        }

        public String getStartTime() {
            return StartTime;
        }

        public void setStartTime(String startTime) {
            StartTime = startTime;
        }

        public String getMature() {
            return Mature;
        }

        public void setMature(String mature) {
            Mature = mature;
        }
    }

    /**
     * 环境参数
     */
    public static class UnitEnvInfoBean {
        private double Rain_1H;//1H降水量
        private String Rain_Desc;//降水描述
        private double Wind_Max;//最大风速
        private String Wind_Desc;//风速描述
        private double Temp;//实时温度
        private double Humi;//实时湿度
        private double TempSoil;//土壤温度
        private double HumiSoil;//土壤湿度
        private double Illumination;//光照强度

        public double getRain_1H() {
            return Rain_1H;
        }

        public void setRain_1H(double rain_1H) {
            Rain_1H = rain_1H;
        }

        public String getRain_Desc() {
            return Rain_Desc;
        }

        public void setRain_Desc(String rain_Desc) {
            Rain_Desc = rain_Desc;
        }

        public double getWind_Max() {
            return Wind_Max;
        }

        public void setWind_Max(double wind_Max) {
            Wind_Max = wind_Max;
        }

        public String getWind_Desc() {
            return Wind_Desc;
        }

        public void setWind_Desc(String wind_Desc) {
            Wind_Desc = wind_Desc;
        }

        public double getTemp() {
            return Temp;
        }

        public void setTemp(double temp) {
            Temp = temp;
        }

        public double getHumi() {
            return Humi;
        }

        public void setHumi(double humi) {
            Humi = humi;
        }

        public double getTempSoil() {
            return TempSoil;
        }

        public void setTempSoil(double tempSoil) {
            TempSoil = tempSoil;
        }

        public double getHumiSoil() {
            return HumiSoil;
        }

        public void setHumiSoil(double humiSoil) {
            HumiSoil = humiSoil;
        }

        public double getIllumination() {
            return Illumination;
        }

        public void setIllumination(double illumination) {
            Illumination = illumination;
        }
    }

    /**
     * 控制设备
     */
    public static class UnitListRemoteCtrlBean {
        private String SerialNum;//设备序列号
        private String Name;//设备名称
        private String Mode;//设备模式
        private double Tempeture;//柜机温度
        private String Status;//设备状态
        private List<UnitListCtrlItemsBean> CtrlItems;//继电器控制组

        public String getSerialNum() {
            return SerialNum;
        }

        public void setSerialNum(String serialNum) {
            SerialNum = serialNum;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getMode() {
            return Mode;
        }

        public void setMode(String mode) {
            Mode = mode;
        }

        public double getTempeture() {
            return Tempeture;
        }

        public void setTempeture(double tempeture) {
            Tempeture = tempeture;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String status) {
            Status = status;
        }

        public List<UnitListCtrlItemsBean> getCtrlItems() {
            return CtrlItems;
        }

        public void setCtrlItems(List<UnitListCtrlItemsBean> ctrlItems) {
            CtrlItems = ctrlItems;
        }
    }

    /**
     * 继电器控制
     */
    public static class UnitListCtrlItemsBean {
        private String SerialNum;//设备序列号
        private int PassNum;//通道号
        private int RelayType;//继电器类别(1 启停 2正反转)
        private int Category;//设备当前状态
        private String Name;//名称
        private int Status;//状态
        private String StatusDesc;//状态

        public String getSerialNum() {
            return SerialNum;
        }

        public void setSerialNum(String serialNum) {
            SerialNum = serialNum;
        }

        public int getPassNum() {
            return PassNum;
        }

        public void setPassNum(int passNum) {
            PassNum = passNum;
        }

        public int getRelayType() {
            return RelayType;
        }

        public void setRelayType(int relayType) {
            RelayType = relayType;
        }

        public int getCategory() {
            return Category;
        }

        public void setCategory(int category) {
            Category = category;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public int getStatus() {
            return Status;
        }

        public void setStatus(int status) {
            Status = status;
        }

        public String getStatusDesc() {
            return StatusDesc;
        }

        public void setStatusDesc(String statusDesc) {
            StatusDesc = statusDesc;
        }
    }

    /**
     * 监控
     */
    public static class UnitListMonitorsBean {
        private String SerialNum;//监控序列号
        private String Name;//监控名称
        private int EquipmentType;//类型

        public String getSerialNum() {
            return SerialNum;
        }

        public void setSerialNum(String serialNum) {
            SerialNum = serialNum;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public int getEquipmentType() {
            return EquipmentType;
        }

        public void setEquipmentType(int equipmentType) {
            EquipmentType = equipmentType;
        }
    }

    public String getUnitID() {
        return UnitID;
    }

    public void setUnitID(String unitID) {
        UnitID = unitID;
    }

    public String getUnitName() {
        return UnitName;
    }

    public void setUnitName(String unitName) {
        UnitName = unitName;
    }

    public UnitEnvInfoBean getEnvInfo() {
        return EnvInfo;
    }

    public void setEnvInfo(UnitEnvInfoBean envInfo) {
        EnvInfo = envInfo;
    }

    public UnitListRemoteCtrlBean getRemoteCtrl() {
        return RemoteCtrl;
    }

    public void setRemoteCtrl(UnitListRemoteCtrlBean remoteCtrl) {
        RemoteCtrl = remoteCtrl;
    }

    public List<UnitListMonitorsBean> getMonitors() {
        return Monitors;
    }

    public void setMonitors(List<UnitListMonitorsBean> monitors) {
        Monitors = monitors;
    }

    public List<UnitBatchListBean> getBatchs() {
        return Batchs;
    }

    public void setBatchs(List<UnitBatchListBean> batchs) {
        Batchs = batchs;
    }
}
