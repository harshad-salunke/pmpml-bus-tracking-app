package com.example.mymap.Model;

public class Destinations{
        String place;
        String time;
        String rupies;

        public Destinations(String place, String time, String rupies) {
            this.place = place;
            this.time = time;
            this.rupies = rupies;
        }


        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getRupies() {
            return rupies;
        }

        public void setRupies(String rupies) {
            this.rupies = rupies;
        }
    }