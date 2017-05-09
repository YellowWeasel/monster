package com.erayic.agr.serverproduct.adapter.entity;

/**
 * Created by wxk on 2017/5/4.
 */

public class BaseForecastInfo {
        private String BaseName;
        private RealTimeForecastInfo NowForecastInfo;
        private FeatureForecastDatas FeatureForecastDatas;
        private double FertilizationIndex;
        private double SprayInsecticideIndex;
        private double HarvestIndex;
        private double IrrigationIndex;

        public BaseForecastInfo() {

        }

        public String getBaseName() {
                return BaseName;
        }

        public void setBaseName(String baseName) {
                BaseName = baseName;
        }

        public RealTimeForecastInfo getNowForecastInfo() {
                return NowForecastInfo;
        }

        public void setNowForecastInfo(RealTimeForecastInfo nowForecastInfo) {
                NowForecastInfo = nowForecastInfo;
        }

        public com.erayic.agr.serverproduct.adapter.entity.FeatureForecastDatas getFeatureForecastDatas() {
                return FeatureForecastDatas;
        }

        public void setFeatureForecastDatas(com.erayic.agr.serverproduct.adapter.entity.FeatureForecastDatas featureForecastDatas) {
                FeatureForecastDatas = featureForecastDatas;
        }

        public double getFertilizationIndex() {
                return FertilizationIndex;
        }

        public void setFertilizationIndex(double fertilizationIndex) {
                FertilizationIndex = fertilizationIndex;
        }

        public double getSprayInsecticideIndex() {
                return SprayInsecticideIndex;
        }

        public void setSprayInsecticideIndex(double sprayInsecticideIndex) {
                SprayInsecticideIndex = sprayInsecticideIndex;
        }

        public double getHarvestIndex() {
                return HarvestIndex;
        }

        public void setHarvestIndex(double harvestIndex) {
                HarvestIndex = harvestIndex;
        }

        public double getIrrigationIndex() {
                return IrrigationIndex;
        }

        public void setIrrigationIndex(double irrigationIndex) {
                IrrigationIndex = irrigationIndex;
        }
}

