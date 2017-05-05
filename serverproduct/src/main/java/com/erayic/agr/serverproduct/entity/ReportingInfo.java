package com.erayic.agr.serverproduct.entity;

/**
 * Created by wxk  on 2017/5/5.
 */

public class ReportingInfo {
    public double[] rainDatas;
    public double[] tmpDatas;
    public double[] windDatas;
    public double maxRainLabel;
    public double minRainLabel;
    public double rainYInterval;

    public double[] getRainDatas() {
        return rainDatas;
    }

    public void setRainDatas(double[] rainDatas) {
        this.rainDatas = rainDatas;
    }

    public double[] getTmpDatas() {
        return tmpDatas;
    }

    public void setTmpDatas(double[] tmpDatas) {
        this.tmpDatas = tmpDatas;
    }

    public double[] getWindDatas() {
        return windDatas;
    }

    public void setWindDatas(double[] windDatas) {
        this.windDatas = windDatas;
    }

    public double getMaxRainLabel() {
        return maxRainLabel;
    }

    public void setMaxRainLabel(double maxRainLabel) {
        this.maxRainLabel = maxRainLabel;
    }

    public double getMinRainLabel() {
        return minRainLabel;
    }

    public void setMinRainLabel(double minRainLabel) {
        this.minRainLabel = minRainLabel;
    }

    public double getRainYInterval() {
        return rainYInterval;
    }

    public void setRainYInterval(double rainYInterval) {
        this.rainYInterval = rainYInterval;
    }
}
