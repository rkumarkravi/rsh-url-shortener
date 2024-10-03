package com.rkumarkravi.urlshortener.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Util {
    private static Random random = new Random();

    public static String generateUniqueValue(String input) {
        try {
            // Create a MessageDigest instance for SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Perform the hashing
            byte[] hashBytes = digest.digest(input.getBytes());

            // Convert byte array to hexadecimal format
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0'); // Append leading zero
                hexString.append(hex);
            }

            return hexString.toString(); // Return the unique value
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating unique value", e);
        }
    }

    private static final String BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int SHORT_URL_LENGTH = 10;

    public static String generateUniqueValue(int id) {
        StringBuilder shortUrl = new StringBuilder();
        while (id > 0) {
            shortUrl.append(BASE62.charAt(id % 62));
            id /= 62;
        }
        // Reverse and pad to ensure minimum length
        while (shortUrl.length() < SHORT_URL_LENGTH) {
            shortUrl.append('0'); // Padding with '0'
        }
        return shortUrl.reverse().toString(); // Return reversed string
    }

    public static String generateUniqueValueV2() {
        StringBuilder shortUrl = new StringBuilder();
        shortUrl.append(UUID.randomUUID().toString().replace("-", ""), 0, 12);
        for (int i = 0; i < getRandomInt(0, shortUrl.length()-1); i++) {
            int randomInt = getRandomInt(0, shortUrl.length()-1);
            shortUrl.setCharAt(randomInt, Character.toUpperCase(shortUrl.charAt(randomInt)));
        }
        return shortUrl.reverse().toString(); // Return reversed string
    }

    private static int getRandomInt(int min, int max) {
        return random.nextInt(max - min) + min;
    }

    public static void main(String[] args) {
        List<String> s = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            var a = generateUniqueValueV2();
            if (s.contains(a)) {
                System.out.println("Duplicate");
            }
            s.add(a);
        }
        System.out.println("completed");
        System.out.println(s);
    }


}
