package com.erayic.agr.serverproduct.adapter.entity;

import com.erayic.agr.serverproduct.Constants;

/**
 * Created by wxk  on 2017/5/5.
 */

public class ReportingInfo {
    private double[] rainDatas;
    private double[] tmpDatas;
    private double[] windDatas;
    private double maxRainLabel;
    private double minRainLabel;
    private double rainYInterval;
    private double maxTmpLabel;
    private double minTmpLabel;
    private double tmpYInterval;
    private double maxWindLabel;
    private double minWindLabel;
    private double windYInterval;
    private String[] xDatas;

    public ReportingInfo() {
    }

    public ReportingInfo(BaseForecastInfo infos) {
        setxDatas(new String[24]);
        setTmpDatas(new double[infos.getFeatureForecastDatas().getFeartureBeans().size()]);
        setRainDatas(new double[infos.getFeatureForecastDatas().getFeartureBeans().size()]);
        setWindDatas(new double[infos.getFeatureForecastDatas().getFeartureBeans().size()]);


        for (int i = 0; i < infos.getFeatureForecastDatas().getFeartureBeans().size(); i++) {
            tmpDatas[i] = Math.abs(infos.getFeatureForecastDatas().getFeartureBeans().get(i).getTemperature());
            rainDatas[i] = Math.abs(infos.getFeatureForecastDatas().getFeartureBeans().get(i).getRain());
            windDatas[i] = Math.abs(infos.getFeatureForecastDatas().getFeartureBeans().get(i).getWindSpeed());
            if(i==0){
                minRainLabel=rainDatas[i];
                minTmpLabel=tmpDatas[i];
                minWindLabel=windDatas[i];
            }
            if (tmpDatas[i]>maxTmpLabel)maxTmpLabel=tmpDatas[i];
            if (tmpDatas[i]<minTmpLabel)minTmpLabel=tmpDatas[i];
            if (rainDatas[i]>maxRainLabel)maxRainLabel=rainDatas[i];
            if (rainDatas[i]<minRainLabel)minRainLabel=rainDatas[i];
            if (windDatas[i]>maxWindLabel)maxWindLabel=windDatas[i];
            if (windDatas[i]<minWindLabel)minWindLabel=windDatas[i];
        }
        formatDatas();
        for (int i=1;i< Constants.RAINLVL.length;i++){
               if (Constants.RAINLVL[i]>minRainLabel&&Constants.RAINLVL[i-1]<=minRainLabel){
                   minRainLabel=(Constants.RAINLVL[i-1]<0)?0:Constants.RAINLVL[i-1];
                }
               if (Constants.RAINLVL[i]>=maxRainLabel&&Constants.RAINLVL[i-1]<maxRainLabel){
                maxRainLabel=Constants.RAINLVL[i];
               }
        }
        rainYInterval=(maxRainLabel-minRainLabel)/5;

        for (int i=1;i< Constants.WINDLVL.length;i++){
            if (Constants.WINDLVL[i]>minWindLabel&&Constants.WINDLVL[i-1]<=minWindLabel){
                minWindLabel=(Constants.WINDLVL[i-1]<0)?0:Constants.WINDLVL[i-1];
            }
            if (Constants.WINDLVL[i]>=maxWindLabel&&Constants.WINDLVL[i-1]<maxWindLabel){
                maxWindLabel=Constants.WINDLVL[i];
            }
        }
        windYInterval=(maxWindLabel-minWindLabel)/5;

        tmpYInterval=(maxTmpLabel-minTmpLabel)/5;

        xDatas=infos.getFeatureForecastDatas().getStrDates();
    }
    public void formatDatas(){

            for (int i=0;i<tmpDatas.length;i++){
                if (Math.abs(tmpDatas[i])>50){
                    if (i==0){
                        tmpDatas[0]=(Math.abs(tmpDatas[i+1])<=50)?tmpDatas[i+1]:0;
                    }else if(i==tmpDatas.length-1){
                        tmpDatas[tmpDatas.length-1]=(Math.abs(tmpDatas[tmpDatas.length-2])<=50)?tmpDatas[tmpDatas.length-2]:0;
                    }else{
                        tmpDatas[i]=(Math.abs(tmpDatas[i-1])<=50&&Math.abs(tmpDatas[i+1])<=50)?
                                (tmpDatas[i-1]+tmpDatas[i+1])/2:(Math.abs(tmpDatas[i-1])<=50)?
                                tmpDatas[i-1]:tmpDatas[i+1];
                    }
                }
            }
    }




    public String[] getxDatas() {
        return xDatas;
    }

    public void setxDatas(String[] xDatas) {
        this.xDatas = xDatas;
    }

    public double getMaxTmpLabel() {
        return maxTmpLabel;
    }

    public void setMaxTmpLabel(double maxTmpLabel) {
        this.maxTmpLabel = maxTmpLabel;
    }

    public double getMinTmpLabel() {
        return minTmpLabel;
    }

    public void setMinTmpLabel(double minTmpLabel) {
        this.minTmpLabel = minTmpLabel;
    }

    public double getTmpYInterval() {
        return tmpYInterval;
    }

    public void setTmpYInterval(double tmpYInterval) {
        this.tmpYInterval = tmpYInterval;
    }

    public double getMaxWindLabel() {
        return maxWindLabel;
    }

    public void setMaxWindLabel(double maxWindLabel) {
        this.maxWindLabel = maxWindLabel;
    }

    public double getMinWindLabel() {
        return minWindLabel;
    }

    public void setMinWindLabel(double minWindLabel) {
        this.minWindLabel = minWindLabel;
    }

    public double getWindYInterval() {
        return windYInterval;
    }

    public void setWindYInterval(double windYInterval) {
        this.windYInterval = windYInterval;
    }

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
