package com.ts.bt.lib;

public class PhoneInfo {
    String contactId;
    String name;
    String number;

    public String getContactId() {
        return this.contactId;
    }

    public void setContactId(String contactId2) {
        this.contactId = contactId2;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name2) {
        this.name = name2;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number2) {
        this.number = number2;
    }

    public String toString() {
        return "PhoneInfo [contactId=" + this.contactId + ", name=" + this.name + ", number=" + this.number + "]";
    }
}
