package com.firstapp.jobfixer.Model;

public class UserRequest {


    private String Adminname;
    private String Adminemail;


    private  String Adminmsg;


    public UserRequest() {
    }


    public UserRequest(String adminname, String adminemail, String adminmsg) {
        Adminname = adminname;
        Adminemail = adminemail;
        Adminmsg = adminmsg;
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
}
