package com.txznet.sdk.bean;

import java.io.Serializable;
import java.util.Date;

/* compiled from: Proguard */
public class WeatherData implements Serializable {
    public String cityCode;
    public String cityName;
    public Date updateTime;
    public WeatherDay[] weatherDays;

    /* compiled from: Proguard */
    public static class WeatherDay implements Serializable {
        public String carWashIndex;
        public String carWashIndexDesc;
        public String coldIndex;
        public String coldIndexDesc;
        public String comfortIndex;
        public String comfortIndexDesc;
        public int currentTemperature;
        public String datingIndex;
        public String datingIndexDesc;
        public int day;
        public int dayOfWeek;
        public String dressIndex;
        public String dressIndexDesc;
        public String dryingIndex;
        public String dryingIndexDesc;
        public int highestTemperature;
        public int lowestTemperature;
        public int month;
        public String morningExerciseIndex;
        public String morningExerciseIndexDesc;
        public int pm2_5;
        public String quality;
        public String sportIndex;
        public String sportIndexDesc;
        public String suggest;
        public String sunBlockIndex;
        public String sunBlockIndexDesc;
        public String travelIndex;
        public String travelIndexDesc;
        public String umbrellaIndex;
        public String umbrellaIndexDesc;
        public String weather;
        public String wind;
        public int year;
    }
}
