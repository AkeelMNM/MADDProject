package com.firstapp.jobfixer.Model;

import android.widget.EditText;
import android.widget.Spinner;

public class Jobs {

    private String JobID;
    private String UserID;
    private String Category;
    private String Title;
    private String CompanyName;
    private String CompanyAddress;
    private String Type;
    private String Salary;
    private String Description;

    public Jobs() {}

    public Jobs(String jobID, String userID, String category, String title, String companyName, String companyAddress, String type, String salary, String description) {
        JobID = jobID;
        UserID = userID;
        Category = category;
        Title = title;
        CompanyName = companyName;
        CompanyAddress = companyAddress;
        Type = type;
        Salary = salary;
        Description = description;
    }

    public String getJobID() {
        return JobID;
    }

    public void setJobID(String jobID) {
        JobID = jobID;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getCompanyAddress() {
        return CompanyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        CompanyAddress = companyAddress;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getSalary() {
        return Salary;
    }

    public void setSalary(String salary) {
        Salary = salary;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }


}
