package com.ts.main.auth;

public class FcTestMode {
    public int CoreType;
    public int HardType;
    public int KeyType;
    public int RadioIC;
    public int Support_360View;
    public int Support_AHD;
    public int Support_carplay;
    public int USB_PORT;
    public int audio;
    public int bGps;
    public int bIMEI;
    public int bSD;
    public int bSim;
    public int bWifi;
    public int ram;
    public int rom;

    public int getCoreType() {
        return this.CoreType;
    }

    public void setCoreType(int coreType) {
        this.CoreType = coreType;
    }

    public int getHardType() {
        return this.HardType;
    }

    public void setHardType(int hardType) {
        this.HardType = hardType;
    }

    public int getKeyType() {
        return this.KeyType;
    }

    public void setKeyType(int keyType) {
        this.KeyType = keyType;
    }

    public int getRam() {
        return this.ram;
    }

    public void setRam(int ram2) {
        this.ram = ram2;
    }

    public int getRom() {
        return this.rom;
    }

    public void setRom(int rom2) {
        this.rom = rom2;
    }

    public int getbSD() {
        return this.bSD;
    }

    public void setbSD(int bSD2) {
        this.bSD = bSD2;
    }

    public int getbIMEI() {
        return this.bIMEI;
    }

    public void setbIMEI(int bIMEI2) {
        this.bIMEI = bIMEI2;
    }

    public int getbSim() {
        return this.bSim;
    }

    public void setbSim(int bSim2) {
        this.bSim = bSim2;
    }

    public int getbGps() {
        return this.bGps;
    }

    public void setbGps(int bGps2) {
        this.bGps = bGps2;
    }

    public int getbWifi() {
        return this.bWifi;
    }

    public void setbWifi(int bWifi2) {
        this.bWifi = bWifi2;
    }

    public int getUSB_PORT() {
        return this.USB_PORT;
    }

    public void setUSB_PORT(int uSB_PORT) {
        this.USB_PORT = uSB_PORT;
    }

    public int getRadioIC() {
        return this.RadioIC;
    }

    public void setRadioIC(int radioIC) {
        this.RadioIC = radioIC;
    }

    public void SetAutoAudio(int naudio) {
        this.audio = naudio;
    }

    public void SetCarplay(int carplay) {
        this.Support_carplay = carplay;
    }

    public void Set360View(int n360View) {
        this.Support_360View = n360View;
    }

    public void SetAhd(int nahd) {
        this.Support_AHD = nahd;
    }

    public String toString() {
        return "FcTestMode [CoreType=" + this.CoreType + ", HardType=" + this.HardType + ", KeyType=" + this.KeyType + ", ram=" + this.ram + ", rom=" + this.rom + ", bSD=" + this.bSD + ", bIMEI=" + this.bIMEI + ", bSim=" + this.bSim + ", bGps=" + this.bGps + ", bWifi=" + this.bWifi + ", USB_PORT=" + this.USB_PORT + ", RadioIC=" + this.RadioIC + "]";
    }
}
