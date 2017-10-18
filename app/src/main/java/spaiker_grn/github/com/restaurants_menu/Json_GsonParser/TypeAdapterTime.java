package spaiker_grn.github.com.restaurants_menu.Json_GsonParser;


import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

public class TypeAdapterTime extends TypeAdapter<Time> {


    public class UncorrectedDateType extends IOException {

        UncorrectedDateType(final String pmessage, final Exception pE) {
            super(pmessage, pE);
        }
    }

    @Override
    public void write(JsonWriter pJsonWriter, Time time) throws IOException {

        String month = "";

        switch (time.getMonth()) {
            case ("January"):
                month = "01";
                break;
            case ("February"):
                month = "02" ;
                break;
            case ("March"):
                month = "03" ;
                break;
            case ("April"):
                month = "04" ;
                break;
            case ("May"):
                month = "05";
                break;
            case ("June"):
                month = "06";
                break;
            case ("July"):
                month = "07";
                break;
            case ("August"):
                month = "08";
                break;
            case ("September"):
                month = "09";
                break;
            case ("October"):
                month = "10" ;
                break;
            case ("November"):
                month = "11";
                break;
            case ("December"):
                month = "12";
                break;
            default: new UncorrectedDateType("Uncorrect month", null);

        }


        String str = time.getDay() + month + time.getYear() + time.getHours() + time.getMinutes() + time.getSeconds();
        pJsonWriter.value(str);


    }

    @Override
    public Time read(JsonReader pJsonReader) throws IOException {

        Time time = new Time();

        char[] mChars = pJsonReader.nextString().toCharArray();

        final String day = new String(mChars, 0, 2);
        if (Integer.parseInt(day) > 31 || Integer.parseInt(day) < 1) {
            throw new UncorrectedDateType("Uncorrected format of days", null);
        }
        String month = new String(mChars, 2, 2);
        if (Integer.parseInt(month) > 12 || Integer.parseInt(month) < 1) {
            throw new UncorrectedDateType("Uncorrected format of month", null);
        }

        switch (Integer.parseInt(month)) {
            case (1):
                month = "January";
                break;
            case (2):
                month = "February";
                break;
            case (3):
                month = "March";
                break;
            case (4):
                month = "April";
                break;
            case (5):
                month = "May";
                break;
            case (6):
                month = "June";
                break;
            case (7):
                month = "July";
                break;
            case (8):
                month = "August";
                break;
            case (9):
                month = "September";
                break;
            case (10):
                month = "October";
                break;
            case (11):
                month = "November";
                break;
            case (12):
                month = "December";
                break;

        }

        final String year = new String(mChars, 4, 4);
        if (Integer.parseInt(year) < 1800) {
            throw new UncorrectedDateType("Uncorrected format of year", null);
        }
        final String hours = new String(mChars, 8, 2);
        final String minutes = new String(mChars, 10, 2);
        final String seconds = new String(mChars, 12, 2);
        if (Integer.parseInt(hours) < 0 || Integer.parseInt(minutes) < 0 || Integer.parseInt(seconds) < 0 ||
                Integer.parseInt(hours) > 24 || Integer.parseInt(minutes) > 60 || Integer.parseInt(seconds) > 60) {
            throw new UncorrectedDateType("Uncorrected format of time", null);
        }
        time.setDay(day);
        time.setMonth(month);
        time.setYear(year);
        time.setHours(hours);
        time.setMinutes(minutes);
        time.setSeconds(seconds);

        return time;
    }
}
