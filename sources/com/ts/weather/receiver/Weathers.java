package com.ts.weather.receiver;

public class Weathers {
    private String adCode;
    private String city;
    private String humidity;
    private String province;
    private String refreshTime;
    private String reportTime;
    private String temperature;
    private String weather;
    private String windDirection;
    private String windPower;

    public String getAdCode() {
        return this.adCode;
    }

    public void setAdCode(String adCode2) {
        this.adCode = adCode2;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city2) {
        this.city = city2;
    }

    public String getHumidity() {
        return this.humidity;
    }

    public void setHumidity(String humidity2) {
        this.humidity = humidity2;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province2) {
        this.province = province2;
    }

    public String getReportTime() {
        return this.reportTime;
    }

    public void setReportTime(String reportTime2) {
        this.reportTime = reportTime2;
    }

    public String getTemperature() {
        return this.temperature;
    }

    public void setTemperature(String temperature2) {
        this.temperature = temperature2;
    }

    public String getWeather() {
        return this.weather;
    }

    public void setWeather(String weather2) {
        this.weather = weather2;
    }

    public String getWindDirection() {
        return this.windDirection;
    }

    public void setWindDirection(String windDirection2) {
        this.windDirection = windDirection2;
    }

    public String getWindPower() {
        return this.windPower;
    }

    public void setWindPower(String windPower2) {
        this.windPower = windPower2;
    }

    public String getRefreshTime() {
        return this.refreshTime;
    }

    public void setRefreshTime(String refreshTime2) {
        this.refreshTime = refreshTime2;
    }

    public String toString() {
        return "Weathers [adCode=" + this.adCode + ", city=" + this.city + ", humidity=" + this.humidity + ", province=" + this.province + ", reportTime=" + this.reportTime + ", temperature=" + this.temperature + ", weather=" + this.weather + ", windDirection=" + this.windDirection + ", windPower=" + this.windPower + ", refreshTime=" + this.refreshTime + "]";
    }
}
