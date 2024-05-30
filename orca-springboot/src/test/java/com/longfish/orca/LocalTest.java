package com.longfish.orca;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class LocalTest {

    @Test
    public void testSplit() {
        String s = "/a/b/c";
        Arrays.stream(s.split("/")).forEach(System.out::println);

        String s2 = "/";
        Arrays.stream(s2.split("/")).forEach(System.out::println);
    }

    @Test
    @SuppressWarnings("all")
    public void testIter() {
        List<?> list = null;
        try {
            for (Object o : list) {
                System.out.println(o);
            }
        } catch (NullPointerException e) {
            System.out.println("空指针");
        }
        try {
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
        } catch (NullPointerException e) {
            System.out.println("空指针2");
        }
    }

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
