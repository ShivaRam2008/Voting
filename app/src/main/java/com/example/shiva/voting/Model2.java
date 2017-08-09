package com.example.shiva.voting;

/**
 * Created by shiva on 7/9/2017.
 */

public class Model2 {
    String mobile;
    String otp;

    public Model2() {
    }

    public Model2(String otp,String mobile) {

        this.mobile = mobile;
        this.otp = otp;
    }

    public String getMobile() {
        return mobile;
    }

    public String getOtp() {
        return otp;
    }
}
