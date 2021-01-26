package com.ts.bt;

public class PhonebookData {
    public String addr;
    public int collect;
    public String first_name;
    public String given_name;
    public String middle_name;
    public String name;
    public String num;
    public String pinyin;

    public String getAddr() {
        return this.addr;
    }

    public void setAddr(String addr2) {
        this.addr = addr2;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name2) {
        this.name = name2;
    }

    public String getNum() {
        return this.num;
    }

    public void setNum(String num2) {
        this.num = num2;
    }

    public String getPinyin() {
        return this.pinyin;
    }

    public void setPinyin(String pinyin2) {
        this.pinyin = pinyin2;
    }

    public int getCollect() {
        return this.collect;
    }

    public void setCollect(int collect2) {
        this.collect = collect2;
    }

    public String getFirst_name() {
        return this.first_name;
    }

    public void setFirst_name(String first_name2) {
        this.first_name = first_name2;
    }

    public String getMiddle_name() {
        return this.middle_name;
    }

    public void setMiddle_name(String middle_name2) {
        this.middle_name = middle_name2;
    }

    public String getGiven_name() {
        return this.given_name;
    }

    public void setGiven_name(String given_name2) {
        this.given_name = given_name2;
    }

    public String toString() {
        return "PhonebookData [addr=" + this.addr + ", name=" + this.name + ", num=" + this.num + ", pinyin=" + this.pinyin + ", collect=" + this.collect + ", first_name=" + this.first_name + ", middle_name=" + this.middle_name + ", given_name=" + this.given_name + "]";
    }
}
