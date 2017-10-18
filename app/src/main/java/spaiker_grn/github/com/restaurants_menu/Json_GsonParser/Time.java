package spaiker_grn.github.com.restaurants_menu.Json_GsonParser;

public class Time {

    String day;
    String month;
    String year;
    String hours;
    String minutes;
    String seconds;

    public String getDay() {
        return day;
    }

    public void setDay(String pDay) {
        day = pDay;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String pMonth) {
        month = pMonth;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String pYear) {
        year = pYear;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String pHours) {
        hours = pHours;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String pMinutes) {
        minutes = pMinutes;
    }

    public String getSeconds() {
        return seconds;
    }

    public void setSeconds(String pSeconds) {
        seconds = pSeconds;
    }



    public Time(){}

    public Time(String pDay, String pMounth, String pYear, String pHours, String pMinutes, String pSeconds) {
        day = pDay;
        month = pMounth;
        year = pYear;
        hours = pHours;
        minutes = pMinutes;
        seconds = pSeconds;
    }

    public String toString(){

        return day + " " + month + " " + year
                + ", " + hours + ":" + minutes + ":" + seconds;

    }

}
