package com.example.shiva.voting;

/**
 * Created by shiva on 7/9/2017.
 */

public class Model {

    String name;
    String signUpID;
    String phone;
    String aadharcard;

    public Model() {
    }

    public Model(String signUpID,String name, String phone,String aadharcard) {
        this.name = name;
        this.phone = phone;
        this.aadharcard = aadharcard;
        //this.signUpID = signUpID;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAadharcard() {
        return aadharcard;
    }

    public String getSignUpID() {
        return signUpID;
    }
}
