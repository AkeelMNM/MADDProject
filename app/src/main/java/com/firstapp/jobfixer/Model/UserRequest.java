package com.firstapp.jobfixer.Model;

public class UserRequest {


    private String Adminname;
    private String Adminemail;
    private String userId;


    private  String Adminmsg;


    public UserRequest() {
    }


    public String getAdminname() {
        return Adminname;
    }

    public void setAdminname(String adminname) {
        Adminname = adminname;
    }

    public String getAdminemail() {
        return Adminemail;
    }

    public void setAdminemail(String adminemail) {
        Adminemail = adminemail;
    }

    public String getAdminmsg() {
        return Adminmsg;
    }

    public void setAdminmsg(String adminmsg) {
        Adminmsg = adminmsg;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public UserRequest(String adminname, String adminemail, String userId, String adminmsg) {
        Adminname = adminname;
        Adminemail = adminemail;
        this.userId = userId;
        Adminmsg = adminmsg;
    }
}
