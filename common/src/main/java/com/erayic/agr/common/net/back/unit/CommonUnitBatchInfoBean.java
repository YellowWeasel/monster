package com.erayic.agr.common.net.back.unit;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：批次详情
 */

public class CommonUnitBatchInfoBean {

    private Batch BatchInfo;//批次信息
    private boolean IsBindSuggest;//是否绑定生产建议
    private boolean IsBindCycle;//是否绑定生长期评估
    private boolean IsBindFeild;//是否绑定产量评估
    private List<CropCycle> Cycles;//生长期评估
    private FarmingSuggestionResult Suggest;// 生产建议

    public static class Batch {
        private String BatchID;//批次ID
        private String ProductID;//对应产品ID
        private String ProductName;//对应产品名称
        private String ProductIcon;//对应产品图片
        private String StartTime;//开始时间
        private String EndTime;//截止时间
        private double Quantity;//数量
        private int Unit;//数量单位
        private String Operater;//操作者ID
        private String OpeName;//操作者姓名
        private int Status;//批次状态

        public String getBatchID() {
            return BatchID;
        }

        public void setBatchID(String batchID) {
            BatchID = batchID;
        }

        public String getProductID() {
            return ProductID;
        }

        public void setProductID(String productID) {
            ProductID = productID;
        }

        public String getProductName() {
            return ProductName;
        }

        public void setProductName(String productName) {
            ProductName = productName;
        }

        public String getProductIcon() {
            return ProductIcon;
        }

        public void setProductIcon(String productIcon) {
            ProductIcon = productIcon;
        }

        public String getStartTime() {
            return StartTime;
        }

        public void setStartTime(String startTime) {
            StartTime = startTime;
        }

        public String getEndTime() {
            return EndTime;
        }

        public void setEndTime(String endTime) {
            EndTime = endTime;
        }

        public double getQuantity() {
            return Quantity;
        }

        public void setQuantity(double quantity) {
            Quantity = quantity;
        }

        public int getUnit() {
            return Unit;
        }

        public void setUnit(int unit) {
            Unit = unit;
        }

        public String getOperater() {
            return Operater;
        }

        public void setOperater(String operater) {
            Operater = operater;
        }

        public String getOpeName() {
            return OpeName;
        }

        public void setOpeName(String opeName) {
            OpeName = opeName;
        }

        public int getStatus() {
            return Status;
        }

        public void setStatus(int status) {
            Status = status;
        }
    }

    public static class CropCycle implements Serializable {
        private static final long serialVersionUID = -7060210544600464481L;
        private SubCycleInfo SubCycle;//生长周期信息
        private String StartDay;//开始时间
        private String EndDay;//结束时间
        private boolean IsUpdate;//是否可被更新

        public SubCycleInfo getSubCycle() {
            return SubCycle;
        }

        public void setSubCycle(SubCycleInfo subCycle) {
            SubCycle = subCycle;
        }

        public String getStartDay() {
            return StartDay;
        }

        public void setStartDay(String startDay) {
            StartDay = startDay;
        }

        public String getEndDay() {
            return EndDay;
        }

        public void setEndDay(String endDay) {
            EndDay = endDay;
        }

        public boolean isUpdate() {
            return IsUpdate;
        }

        public void setUpdate(boolean update) {
            IsUpdate = update;
        }
    }

    public static class SubCycleInfo implements Serializable {
        private static final long serialVersionUID = -7060210544600464481L;
        private int SubCycleNum;//周期编号
        private String SubCycleName;//周期名称

        public int getSubCycleNum() {
            return SubCycleNum;
        }

        public void setSubCycleNum(int subCycleNum) {
            SubCycleNum = subCycleNum;
        }

        public String getSubCycleName() {
            return SubCycleName;
        }

        public void setSubCycleName(String subCycleName) {
            SubCycleName = subCycleName;
        }
    }

    public static class FarmingSuggestionResult implements Serializable {
        private static final long serialVersionUID = -7060210544600464481L;
        private List<SuggestionResult> SuggestionResultList;//建议集合

        public List<SuggestionResult> getSuggestionResultList() {
            return SuggestionResultList;
        }

        public void setSuggestionResultList(List<SuggestionResult> suggestionResultList) {
            SuggestionResultList = suggestionResultList;
        }
    }

    public static class SuggestionResult implements Serializable {
        private static final long serialVersionUID = -7060210544600464481L;
        private int ResultIndex;//结果指数
        private String SuggestionContent;//结果内容
        private int ErrorCode;//错误代码？
        private int Key;//操作类型

        public int getResultIndex() {
            return ResultIndex;
        }

        public void setResultIndex(int resultIndex) {
            ResultIndex = resultIndex;
        }

        public String getSuggestionContent() {
            return SuggestionContent;
        }

        public void setSuggestionContent(String suggestionContent) {
            SuggestionContent = suggestionContent;
        }

        public int getErrorCode() {
            return ErrorCode;
        }

        public void setErrorCode(int errorCode) {
            ErrorCode = errorCode;
        }

        public int getKey() {
            return Key;
        }

        public void setKey(int key) {
            Key = key;
        }
    }

    public static String getDes(int type) {//操作类型Key
        switch (type) {
            case 0:
                return "打药";
            case 1:
                return "施肥";
            case 2:
                return "灌溉";
            case 3:
                return "采摘";
            case 4:
                return "病虫害";
            default:
                return "未知";
        }
    }

    public Batch getBatchInfo() {
        return BatchInfo;
    }

    public void setBatchInfo(Batch batchInfo) {
        BatchInfo = batchInfo;
    }

    public boolean isBindSuggest() {
        return IsBindSuggest;
    }

    public void setBindSuggest(boolean bindSuggest) {
        IsBindSuggest = bindSuggest;
    }

    public boolean isBindCycle() {
        return IsBindCycle;
    }

    public void setBindCycle(boolean bindCycle) {
        IsBindCycle = bindCycle;
    }

    public boolean isBindFeild() {
        return IsBindFeild;
    }

    public void setBindFeild(boolean bindFeild) {
        IsBindFeild = bindFeild;
    }

    public List<CropCycle> getCycles() {
        return Cycles;
    }

    public void setCycles(List<CropCycle> cycles) {
        Cycles = cycles;
    }

    public FarmingSuggestionResult getSuggest() {
        return Suggest;
    }

    public void setSuggest(FarmingSuggestionResult suggest) {
        Suggest = suggest;
    }
}
