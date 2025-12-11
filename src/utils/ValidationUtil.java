package utils;

import java.util.regex.Pattern;

public class ValidationUtil {

    /**
     * Validate email format
     */
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return Pattern.matches(emailRegex, email);
    }

    /**
     * Validate phone number (10 digits)
     */
    public static boolean isValidPhone(String phone) {
        String phoneRegex = "^[0-9]{10}$";
        return Pattern.matches(phoneRegex, phone);
    }

    /**
     * Validate age (between 1 and 150)
     */
    public static boolean isValidAge(int age) {
        return age >= 1 && age <= 150;
    }

    /**
     * Validate name (non-empty, no numbers)
     */
    public static boolean isValidName(String name) {
        return !name.isEmpty() && !name.matches(".*\\d.*");
    }

    /**
     * Validate time format (HH:MM)
     */
    public static boolean isValidTime(String time) {
        String timeRegex = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$";
        return Pattern.matches(timeRegex, time);
    }

    /**
     * Validate password strength
     */
    public static boolean isStrongPassword(String password) {
        // At least 8 characters, 1 uppercase, 1 lowercase, 1 digit
        return password.length() >= 8 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*[a-z].*") &&
                password.matches(".*\\d.*");
    }
}