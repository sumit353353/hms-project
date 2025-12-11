package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String DISPLAY_FORMAT = "dd-MM-yyyy";

    /**
     * Format date to string
     */
    public static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(date);
    }

    /**
     * Format date-time to string
     */
    public static String formatDateTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT);
        return sdf.format(date);
    }

    /**
     * Format date for display
     */
    public static String formatDisplayDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DISPLAY_FORMAT);
        return sdf.format(date);
    }

    /**
     * Parse string to date
     */
    public static Date parseDate(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            return sdf.parse(dateStr);
        } catch (Exception e) {
            System.err.println("Error parsing date: " + e.getMessage());
            return null;
        }
    }

    /**
     * Check if date is in past
     */
    public static boolean isDateInPast(Date date) {
        return date.before(new Date());
    }

    /**
     * Get current date
     */
    public static Date getCurrentDate() {
        return new Date();
    }
}