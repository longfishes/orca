package com.longfish.orca.util;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class PhoneUtil {

    private static final Pattern PHONE_PATTERN = Pattern.compile("^1[3-9]\\d{9}$");

    public static boolean isValid(String phone) {
        if (phone == null) {
            return false;
        }
        return PHONE_PATTERN.matcher(phone).matches();
    }
}
