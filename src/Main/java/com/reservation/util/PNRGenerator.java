package com.reservation.util;

import java.util.Random;

public class PNRGenerator {
    
    // Generates a unique 10-digit PNR number
    public static String generatePNR() {
        Random random = new Random();
        long pnr = 1000000000L + (long)(random.nextDouble() * 8999999999L);
        return String.valueOf(pnr);
    }
}
