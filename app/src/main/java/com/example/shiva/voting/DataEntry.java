package com.example.shiva.voting;

/**
 * Created by shiva on 7/17/2017.
 */

public class DataEntry {
    String aadhar_no;
    String address,dob,gender,name;

    public DataEntry(String aadhar_no, String address, String dob, String gender, String name) {
        this.aadhar_no = aadhar_no;
        this.address = address;
        this.dob = dob;
        this.gender = gender;
        this.name = name;
    }

    public DataEntry() {
    }

    public String getAadhar_no() {
        return aadhar_no;
    }

    public String getAddress() {
        return address;
    }

    public String getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }
}
