package com.erayic.agr.serverproduct.entity;

import java.util.List;

/**
 * Created by wxk on 2017/5/4.
 */

public class BaseForecastInfo {
        public String baseName;
        public List<ForecastInfo> forecastInfos;
        public ForecastInfo nowForecastInfo;
        public double fertilizationIndex;
        public double sprayInsecticideIndex;
        public double harvestIndex;
        public double irrigationIndex;

        public double getFertilizationIndex() {
                return fertilizationIndex;
        }

        public void setFertilizationIndex(double fertilizationIndex) {
                this.fertilizationIndex = fertilizationIndex;
        }

        public double getSprayInsecticideIndex() {
                return sprayInsecticideIndex;
        }

        public void setSprayInsecticideIndex(double sprayInsecticideIndex) {
                this.sprayInsecticideIndex = sprayInsecticideIndex;
        }

        public double getHarvestIndex() {
                return harvestIndex;
        }

        public void setHarvestIndex(double harvestIndex) {
                this.harvestIndex = harvestIndex;
        }

        public double getIrrigationIndex() {
                return irrigationIndex;
        }

        public void setIrrigationIndex(double irrigationIndex) {
                this.irrigationIndex = irrigationIndex;
        }

        public List<ForecastInfo> getForecastInfos() {
                return forecastInfos;
        }

        public void setForecastInfos(List<ForecastInfo> forecastInfos) {
                this.forecastInfos = forecastInfos;
        }

        public ForecastInfo getNowForecastInfo() {
                return (forecastInfos==null&&forecastInfos.size()>0)?
                        null:forecastInfos.get(0);
        }

        public void setNowForecastInfo(ForecastInfo nowForecastInfo) {
                this.nowForecastInfo = nowForecastInfo;
        }

        public String getBaseName() {
                return baseName;
        }

        public void setBaseName(String baseName) {
                this.baseName = baseName;
        }
}

