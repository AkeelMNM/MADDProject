package com.firstapp.jobfixer.Model;

public class Advertisement {

    private String AdID;
    private String jobID;
    private String jobCategory;
    private String jobTitle;
    private String companyName;
    private String companyAddress;
    private String jobType;
    private String jobSalary;
    private String qualification;

    public Advertisement(){

    }

    public Advertisement(String adID, String jobID, String jobCategory, String jobTitle, String companyName, String companyAddress, String jobType, String jobSalary, String qualification) {
        AdID = adID;
        this.jobID = jobID;
        this.jobCategory = jobCategory;
        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.jobType = jobType;
        this.jobSalary = jobSalary;
        this.qualification = qualification;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getJobID() {
        return jobID;
    }

    public void setJobID(String jobID) {
        this.jobID = jobID;
    }

    public String getAdID() {
        return AdID;
    }

    public void setAdID(String adID) {
        AdID = adID;
    }

    public String getJobCategory() {
        return jobCategory;
    }

    public void setJobCategory(String jobCategory) {
        this.jobCategory = jobCategory;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getJobSalary() {
        return jobSalary;
    }

    public void setJobSalary(String jobSalary) {
        this.jobSalary = jobSalary;
    }
}
