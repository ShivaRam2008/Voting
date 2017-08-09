package com.example.shiva.voting;

/**
 * Created by shiva on 7/30/2017.
 */

public class getInfo {
    String geAddress,gName,gGender,gAddress,gDOB;

    public getInfo() {
    }

    public getInfo(String geAddress, String gName, String gGender, String gAddress, String gDOB) {
        this.geAddress = geAddress;
        this.gName = gName;
        this.gGender = gGender;
        this.gAddress = gAddress;
        this.gDOB = gDOB;
    }

    public String getGeAddress() {
        return geAddress;
    }

    public String getgName() {
        return gName;
    }

    public String getgGender() {
        return gGender;
    }

    public String getgAddress() {
        return gAddress;
    }

    public String getgDOB() {
        return gDOB;
    }
}
