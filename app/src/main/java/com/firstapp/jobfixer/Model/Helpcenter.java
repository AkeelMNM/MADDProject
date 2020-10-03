package com.firstapp.jobfixer.Model;

public class Helpcenter {

    private String userId;
    private String HCId;
    private String name;
    private String email;
    private String message;

    private  String Adminmsg;


    public Helpcenter() {
    }

    public Helpcenter(String userId, String HCId, String name, String email, String message, String adminmsg) {
        this.userId = userId;
        this.HCId = HCId;
        this.name = name;
        this.email = email;
        this.message = message;
        this.Adminmsg = adminmsg;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHCId() {
        return HCId;
    }

    public void setHCId(String HCId) {
        this.HCId = HCId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAdminmsg() {
        return Adminmsg;
    }

    public void setAdminmsg(String adminmsg) {
        Adminmsg = adminmsg;
    }
}
