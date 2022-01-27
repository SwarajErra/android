package com.example.getjob_andriod;
import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class postjobhelper implements Serializable{
    String companyName,jobDescription,companyLocation,payRate,jobType,shift,reqqua;

    // getter method for our id
    public String getId() {
        return id;
    }
    // setter method for our id
    public void setId(String id) {
        this.id = id;
    }

    // we are using exclude because
    // we are not saving our id
    @Exclude
    private String id;


    public postjobhelper() {

    }

    public postjobhelper(String companyName, String jobDescription, String companyLocation, String payRate, String jobType, String shift, String reqqua) {
        this.companyName = companyName;
        this.jobDescription = jobDescription;
        this.companyLocation = companyLocation;
        this.payRate = payRate;
        this.jobType = jobType;
        this.shift = shift;
        this.reqqua = reqqua;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getjobDescription() {
        return jobDescription;
    }

    public void setjobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getcompanyLocation() {
        return companyLocation;
    }

    public void setcompanyLocation(String companyLocation) {
        this.companyLocation = companyLocation;
    }

    public String getpayRate() {
        return payRate;
    }

    public void setpayRate(String payRate) {
        this.payRate = payRate;
    }

    public String getjobType() {
        return jobType;
    }

    public void setjobType(String jobType) {
        this.jobType = jobType;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String Shift) {
        this.shift = Shift;
    }

    public String getReqqua() {
        return reqqua;
    }

    public void setReqqua(String reqqua) {
        this.reqqua = reqqua;
    }
}
