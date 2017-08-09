package com.example.shiva.voting;

/**
 * Created by shiva on 7/16/2017.
 */

public class PhioneModel {
    String phnum;
    String y;

    public PhioneModel(String phnum, String y) {
        this.phnum = phnum;
        this.y = y;
    }
    public PhioneModel(){}


    public String getPhnum() {
        return phnum;
    }

    public String getY() {
        return y;
    }
}
