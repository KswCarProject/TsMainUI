package com.ts.bt.lib;

public class ContactInfo {
    String firstName;
    String givenName;
    String middleName;
    String name;
    String number;

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

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((this.firstName == null ? 0 : this.firstName.hashCode()) + 31) * 31) + (this.givenName == null ? 0 : this.givenName.hashCode())) * 31) + (this.middleName == null ? 0 : this.middleName.hashCode())) * 31) + (this.name == null ? 0 : this.name.hashCode())) * 31;
        if (this.number != null) {
            i = this.number.hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ContactInfo other = (ContactInfo) obj;
        if (this.firstName == null) {
            if (other.firstName != null) {
                return false;
            }
        } else if (!this.firstName.equals(other.firstName)) {
            return false;
        }
        if (this.givenName == null) {
            if (other.givenName != null) {
                return false;
            }
        } else if (!this.givenName.equals(other.givenName)) {
            return false;
        }
        if (this.middleName == null) {
            if (other.middleName != null) {
                return false;
            }
        } else if (!this.middleName.equals(other.middleName)) {
            return false;
        }
        if (this.name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!this.name.equals(other.name)) {
            return false;
        }
        if (this.number == null) {
            if (other.number != null) {
                return false;
            }
            return true;
        } else if (!this.number.equals(other.number)) {
            return false;
        } else {
            return true;
        }
    }

    public String toString() {
        return "ContactInfo [name=" + this.name + ", number=" + this.number + ", firstName=" + this.firstName + ", middleName=" + this.middleName + ", givenName=" + this.givenName + "]";
    }
}
