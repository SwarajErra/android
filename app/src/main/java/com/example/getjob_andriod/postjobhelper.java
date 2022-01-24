package com.example.getjob_andriod;


public class postjobhelper {
    String companyName,jobDescription,CompanyLocation,payRate,jobType,shift,reqqua;

    public postjobhelper() {

    }

    public postjobhelper(String companyName, String jobDescription, String CompanyLocation, String payRate, String jobType, String shift, String reqqua) {
        this.companyName = companyName;
        this.jobDescription = jobDescription;
        this.CompanyLocation = CompanyLocation;
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

    public String getCompanyLocation() {
        return CompanyLocation;
    }

    public void setCompanyLocation(String CompanyLocation) {
        this.CompanyLocation = CompanyLocation;
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

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getReqqua() {
        return reqqua;
    }

    public void setReqqua(String reqqua) {
        this.reqqua = reqqua;
    }
}
