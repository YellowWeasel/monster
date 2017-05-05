package com.erayic.agr.serverproduct.entity;

import java.util.Date;

/**
 * Created by wxk on 2017/5/4.
 */

public class ForecastInfo {
    public double tmp;
    public double humi;
    public double vis;
    public double rain;
    public double wind;
    public Date date;

    public double getWind() {
        return wind;
    }

    public void setWind(double wind) {
        this.wind = wind;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTmp() {
        return tmp;
    }

    public void setTmp(double tmp) {
        this.tmp = tmp;
    }

    public double getHumi() {
        return humi;
    }

    public void setHumi(double humi) {
        this.humi = humi;
    }

    public double getVis() {
        return vis;
    }

    public void setVis(double vis) {
        this.vis = vis;
    }

    public double getRain() {
        return rain;
    }

    public void setRain(double rain) {
        this.rain = rain;
    }
}
