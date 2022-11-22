package com.example.mymap.Model;

public class UserSingleton {
  public static   String name;
  public static   String mobile;
   public static String adharno;
   public  static  String uid;
    private  UserSingleton(){

    }

    private static  UserSingleton instant;

    public  static  UserSingleton getInstance(String name,String mobile,String adharno,String uid){
        if(instant==null) {
            instant = new UserSingleton();
            instant.name = name;
            instant.mobile=mobile;
            instant.adharno=adharno;
            instant.uid=uid;
        }
        return instant;
    }



}
