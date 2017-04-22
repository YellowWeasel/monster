package com.erayic.agr.common.net.back;

import java.io.Serializable;

/**
 * 作者：Hkceey
 * 邮箱：hkceey@outlook.com
 * 注解：种子Bean
 */

public class ManageSeedBean implements Serializable {

    private static final long serialVersionUID = -7620435178023928252L;

    private String ResID = "00000000-0000-0000-0000-000000000000";
    private int Type = 4;

    private String PID;//登记号
    private String ProductName;//名称
    private String Manufacturer;//生产厂家

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
