package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Service {
    static SimpleDateFormat dayFormat;
    public static String setDate(){
        dayFormat = new SimpleDateFormat("MMMM dd, YYYY");
        String date= dayFormat.format(Calendar.getInstance().getTime());
        return date;
    }
}
