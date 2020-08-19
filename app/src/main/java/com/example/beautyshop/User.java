package com.example.beautyshop;

public class User {

   public String nm ;
   public String contactno;
   public String emaild;
   public String password;
   public String addry;

    public User(String nm, String contactno, String emaild, String password, String addry) {

        this.nm = nm;
        this.contactno = contactno;
        this.emaild = emaild;
        this.password = password;
        this.addry = addry;
    }

    public User() {
    }

    public String getNm() {
        return nm;
    }

    public void setNm(String nm) {
        this.nm = nm;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getEmaild() {
        return emaild;
    }

    public void setEmaild(String emaild) {
        this.emaild = emaild;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddry() {
        return addry;
    }

    public void setAddry(String addry) {
        this.addry = addry;
    }
}
