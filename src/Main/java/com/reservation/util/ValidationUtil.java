package com.reservation.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ValidationUtil {
    
    // Checks if the string contains only numbers
    public static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        return str.matches("\\d+");
    }
    
    // Checks if date is in yyyy-MM-dd format
    public static boolean isValidDate(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) return false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    
    // Checks if string contains only alphabets and spaces
    public static boolean isAlpha(String str) {
        if (str == null || str.trim().isEmpty()) {
            return false;
        }
        return str.matches("^[a-zA-Z\\s]+$");
    }
    
    // Checks if string is not null or empty
    public static boolean isValidString(String str) {
        return str != null && !str.trim().isEmpty();
    }
}
