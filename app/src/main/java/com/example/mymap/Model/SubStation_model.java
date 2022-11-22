package com.example.mymap.Model;

import java.io.Serializable;

public class SubStation_model implements Serializable {
    String Name;
    String rupees;
    String main_from;
    String main_to;
    public  SubStation_model(String name,String main_from,String main_to,String rupees){
        this.Name=name;
        this.main_from=main_from;
        this.main_to=main_to;
        this.rupees=rupees;
    }

    public String getMain_from() {
        return main_from;
    }

    public void setMain_from(String main_from) {
        this.main_from = main_from;
    }

    public String getMain_to() {
        return main_to;
    }

    public void setMain_to(String main_to) {
        this.main_to = main_to;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRupees() {
        return rupees;
    }

    public void setRupees(String rupees) {
        this.rupees = rupees;
    }
}
