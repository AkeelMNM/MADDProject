package com.firstapp.jobfixer.Model;

public class SendRequestToCompany {

    private  String userID;
    private  String jobID;
    private String resumeID;
    private String jobName;
    private String applicantName;
    private String applicantEmail;
    private String applyDate;
    private String compName;

    public SendRequestToCompany(){}

    public String getResumeID() {
        return resumeID;
    }

    public void setResumeID(String resumeID) {
        this.resumeID = resumeID;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getJobID() {
        return jobID;
    }

    public void setJobID(String jobID) {
        this.jobID = jobID;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getApplicantEmail() {
        return applicantEmail;
    }

    public void setApplicantEmail(String applicantEmail) {
        this.applicantEmail = applicantEmail;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }
}
