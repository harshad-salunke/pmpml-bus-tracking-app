package com.example.mymap.Model;

public class Booking_model {
//UserSingleton userSingleton;
String from;
String to;
String ticket_no;
String date;
String time;
String total;

public  Booking_model(){}

    public Booking_model( String from, String to, String ticket_no, String date, String time, String total) {
        this.from = from;
        this.to = to;
        this.ticket_no = ticket_no;
        this.date = date;
        this.time = time;
        this.total = total;
    }


    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTicket_no() {
        return ticket_no;
    }

    public void setTicket_no(String ticket_no) {
        this.ticket_no = ticket_no;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
