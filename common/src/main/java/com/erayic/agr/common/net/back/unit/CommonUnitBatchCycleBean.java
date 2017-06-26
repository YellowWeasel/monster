package com.erayic.agr.common.net.back.unit;

import com.erayic.agr.common.net.back.CommonMapArrayBean;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：作物生长期评估详情
 */

public class CommonUnitBatchCycleBean {


    private List<CommonUnitBatchInfoBean.CropCycle> Cycles;//生长期
    private TempInfo HistoryTemp;//历史累积温度
    private TempInfo CurAccTemp;//当前作物累积温度
    private RainInfo HistoryRain;//历史累积温度
    private RainInfo CurAccRain;//当前作物累积温度

    public static class TempInfo {//温度
        private double TotalTemp;//累积温度
        private List<CommonMapArrayBean> TempSerial;//温度集合

        public double getTotalTemp() {
            return TotalTemp;
        }

        public void setTotalTemp(double totalTemp) {
            TotalTemp = totalTemp;
        }

        public List<CommonMapArrayBean> getTempSerial() {
            return TempSerial;
        }

        public void setTempSerial(List<CommonMapArrayBean> tempSerial) {
            TempSerial = tempSerial;
        }
    }

    public static class RainInfo {//历史累积温度
        private double TotalRain;//累积温度
        private List<CommonMapArrayBean> RainSerial;//温度集合

        public double getTotalRain() {
            return TotalRain;
        }

        public void setTotalRain(double totalRain) {
            TotalRain = totalRain;
        }

        public List<CommonMapArrayBean> getRainSerial() {
            return RainSerial;
        }

        public void setRainSerial(List<CommonMapArrayBean> rainSerial) {
            RainSerial = rainSerial;
        }
    }

    public TempInfo getHistoryTemp() {
        return HistoryTemp;
    }

    public void setHistoryTemp(TempInfo historyTemp) {
        HistoryTemp = historyTemp;
    }

    public TempInfo getCurAccTemp() {
        return CurAccTemp;
    }

    public void setCurAccTemp(TempInfo curAccTemp) {
        CurAccTemp = curAccTemp;
    }

    public RainInfo getHistoryRain() {
        return HistoryRain;
    }

    public void setHistoryRain(RainInfo historyRain) {
        HistoryRain = historyRain;
    }

    public RainInfo getCurAccRain() {
        return CurAccRain;
    }

    public void setCurAccRain(RainInfo curAccRain) {
        CurAccRain = curAccRain;
    }

    public List<CommonUnitBatchInfoBean.CropCycle> getCycles() {
        return Cycles;
    }

    public void setCycles(List<CommonUnitBatchInfoBean.CropCycle> cycles) {
        Cycles = cycles;
    }
}
