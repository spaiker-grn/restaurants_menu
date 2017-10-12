package spaiker_grn.github.com.restaurants_menu.Json_GsonParser;

public class AdapterForTime {

    private final String inPutString;


    public class UncorrectedDateType extends Exception {

        UncorrectedDateType(final String pmessage, final Exception pE) {
            super(pmessage, pE);
        }
    }

    public AdapterForTime(final String pInPutString) {
        inPutString = pInPutString;
    }

    public String getOutPut() throws UncorrectedDateType {

        String outPutString = "";
        final char[] mChars = inPutString.toCharArray();
        if (mChars.length == 14) {
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
            if (Integer.parseInt(year) < 2001) {
                throw new UncorrectedDateType("Uncorrected format of year", null);
            }
            final String hours = new String(mChars, 8, 2);
            final String minutes = new String(mChars, 10, 2);
            final String seconds = new String(mChars, 12, 2);
            if (Integer.parseInt(hours) < 0 || Integer.parseInt(minutes) < 0 || Integer.parseInt(seconds) < 0 ||
                    Integer.parseInt(hours) > 24 || Integer.parseInt(minutes) > 60 || Integer.parseInt(seconds) > 60) {
                throw new UncorrectedDateType("Uncorrected format of time", null);
            }

            outPutString = day + " " + month + " " + year
                    + ", " + hours + ":" + minutes + ":" + seconds;

        } else {
            throw new UncorrectedDateType("Uncorrected length of response", null);
        }

        return outPutString;

    }

}
