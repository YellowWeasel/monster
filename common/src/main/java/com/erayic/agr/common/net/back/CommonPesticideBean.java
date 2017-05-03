package com.erayic.agr.common.net.back;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：农药
 */

public class CommonPesticideBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String PID = "";//登记证号
    private String ValidStart = "";//有效起始日
    private String ValidEnd = "";//有效截止日
    private String RegisterName = "";//农药名称
    private String Toxicity = "";//农药毒性
    private String Formulations = "";//农药剂型
    private String Manufacturer = "";//生产厂家
    private List<ActivesInfo> Actives;//有效成分
    private List<ApplysInfo> Applys;//防治作物
    private String ResID = "00000000-0000-0000-0000-000000000000";//资源ID
    private int Type = 1;//资源类型
    private String Name = "";//资源名称
    private String CommonDesc = "";
    private boolean IsReadOnly;//是否只读

    public static class ActivesInfo {
        private String ActiveName = "";//有效成分
        private String Content = "";//成分含量

        public String getActiveName() {
            return ActiveName;
        }

        public void setActiveName(String activeName) {
            ActiveName = activeName;
        }

        public String getContent() {
            return Content;
        }

        public void setContent(String content) {
            Content = content;
        }
    }

    public static class ApplysInfo {
        private String crop = "";//作物名称
        private String Prevention = "";//防治对象
        private String Dosage = "";//用药量
        private String method = "";//方式

        public String getCrop() {
            return crop;
        }

        public void setCrop(String crop) {
            this.crop = crop;
        }

        public String getPrevention() {
            return Prevention;
        }

        public void setPrevention(String prevention) {
            Prevention = prevention;
        }

        public String getDosage() {
            return Dosage;
        }

        public void setDosage(String dosage) {
            Dosage = dosage;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public String getValidStart() {
        return ValidStart;
    }

    public void setValidStart(String validStart) {
        ValidStart = validStart;
    }

    public String getValidEnd() {
        return ValidEnd;
    }

    public void setValidEnd(String validEnd) {
        ValidEnd = validEnd;
    }

    public String getRegisterName() {
        return RegisterName;
    }

    public void setRegisterName(String registerName) {
        RegisterName = registerName;
    }

    public String getToxicity() {
        return Toxicity;
    }

    public void setToxicity(String toxicity) {
        Toxicity = toxicity;
    }

    public String getFormulations() {
        return Formulations;
    }

    public void setFormulations(String formulations) {
        Formulations = formulations;
    }

    public String getManufacturer() {
        return Manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        Manufacturer = manufacturer;
    }

    public List<ActivesInfo> getActives() {
        return Actives;
    }

    public void setActives(List<ActivesInfo> actives) {
        Actives = actives;
    }

    public List<ApplysInfo> getApplys() {
        return Applys;
    }

    public void setApplys(List<ApplysInfo> applys) {
        Applys = applys;
    }

    public String getResID() {
        return ResID;
    }

    public void setResID(String resID) {
        ResID = resID;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCommonDesc() {
        return CommonDesc;
    }

    public void setCommonDesc(String commonDesc) {
        CommonDesc = commonDesc;
    }

    public boolean isReadOnly() {
        return IsReadOnly;
    }

    public void setReadOnly(boolean readOnly) {
        IsReadOnly = readOnly;
    }
}
