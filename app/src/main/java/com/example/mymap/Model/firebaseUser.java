package com.example.mymap.Model;

public class firebaseUser {
    public  String user_uid;
    public    String name;
    public    String mobile;
    public  String adharno;
    public  String email="not Available";
    public  String gender="meal";
    public  String image="no";

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public  firebaseUser(){}
    public  firebaseUser(String name,String mobile,String adharno,String uid){
        this.name=name;
        this.mobile=mobile;
        this.adharno=adharno;
        this.user_uid=uid;
    }

    public String getUser_uid() {
        return user_uid;
    }

    public void setUser_uid(String user_uid) {
        this.user_uid = user_uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public  String getAdharno() {
        return adharno;
    }

    public  void setAdharno(String adharno) {
       adharno = adharno;
    }
}
