package com.ts.bt.lib;

public class NameInfo {
    String contactId;
    String disPlayName;
    String firstName;
    String givenName;
    String middleName;

    public String getContactId() {
        return this.contactId;
    }

    public void setContactId(String contactId2) {
        this.contactId = contactId2;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName2) {
        this.firstName = firstName2;
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public void setMiddleName(String middleName2) {
        this.middleName = middleName2;
    }

    public String getGivenName() {
        return this.givenName;
    }

    public void setGivenName(String givenName2) {
        this.givenName = givenName2;
    }

    public String getDisPlayName() {
        return this.disPlayName;
    }

    public void setDisPlayName(String disPlayName2) {
        this.disPlayName = disPlayName2;
    }

    public String toString() {
        return "NameInfo [contactId=" + this.contactId + ", firstName=" + this.firstName + ", middleName=" + this.middleName + ", givenName=" + this.givenName + ", disPlayName=" + this.disPlayName + "]";
    }
}
