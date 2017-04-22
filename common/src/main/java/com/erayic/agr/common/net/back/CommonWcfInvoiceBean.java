package com.erayic.agr.common.net.back;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：索要发票上传Bean
 */

public class CommonWcfInvoiceBean {

    private List<CommonWcfInvoice> orderIDs;

    public static class CommonWcfInvoice {
        public String InvID;
        public float Price;

        public String getInvID() {
            return InvID;
        }

        public void setInvID(String invID) {
            InvID = invID;
        }

        public float getPrice() {
            return Price;
        }

        public void setPrice(float price) {
            Price = price;
        }
    }

    public List<CommonWcfInvoice> getOrderIDs() {
        return orderIDs;
    }

    public void setOrderIDs(List<CommonWcfInvoice> orderIDs) {
        this.orderIDs = orderIDs;
    }
}
