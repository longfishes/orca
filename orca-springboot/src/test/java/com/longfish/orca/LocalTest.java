package com.longfish.orca;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class LocalTest {

    @Test
    @SuppressWarnings("all")
    public void testNullPointer() {
        System.out.println(null == new Object());
    }

    @Test
    public void testFormat() {
        LocalDate date = LocalDate.parse("1111-11-11");
        System.out.println(date.getYear());
        System.out.println(date.getMonth());
        System.out.println(date.getDayOfMonth());
    }
}
