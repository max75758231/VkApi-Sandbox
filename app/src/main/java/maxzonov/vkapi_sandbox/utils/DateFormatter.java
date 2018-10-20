package maxzonov.vkapi_sandbox.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateFormatter {

    public static String convertDateToDayString(long date) {
        Date defaultDate = new Date(date * 1000L);

        DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");

        return dateFormat.format(defaultDate);
    }

    public static String convertDateToTimeString(long date) {
        Date defaultDate = new Date(date * 1000L);

        DateFormat dateFormat = new SimpleDateFormat("hh:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+3"));

        return dateFormat.format(defaultDate);
    }
}
