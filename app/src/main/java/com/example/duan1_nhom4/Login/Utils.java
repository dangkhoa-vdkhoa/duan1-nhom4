package com.example.duan1_nhom4.Login;

import java.util.Random;

public class Utils {
    public static String generateOTP() {
        int otpLength = 6; // You can adjust the length as needed
        String characters = "0123456789";
        StringBuilder otp = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < otpLength; i++) {
            otp.append(characters.charAt(random.nextInt(characters.length())));
        }

        return otp.toString();
}
}
