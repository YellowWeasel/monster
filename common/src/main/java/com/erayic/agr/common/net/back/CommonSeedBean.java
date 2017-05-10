package com.erayic.agr.common.net.back;

import com.erayic.agr.common.net.back.enums.EnumResourceType;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：种子
 */

public class CommonSeedBean {

    private String ResID = "00000000-0000-0000-0000-000000000000";
    private int Type = EnumResourceType.TYPE_SEED;

    private String PID ;//登记ID
    private String ProductName;//登记名称
    private String Manufacturer;//生产厂家
    private boolean IsReadOnly;//是否只读

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getManufacturer() {
        return Manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        Manufacturer = manufacturer;
    }

    public boolean isReadOnly() {
        return IsReadOnly;
    }

    public void setReadOnly(boolean readOnly) {
        IsReadOnly = readOnly;
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
}
