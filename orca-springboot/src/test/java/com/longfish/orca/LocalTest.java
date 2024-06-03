package com.longfish.orca;

import cn.hutool.core.bean.BeanUtil;
import com.longfish.orca.pojo.dto.TemplateDTO;
import com.longfish.orca.pojo.entity.Template;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.longfish.orca.constant.CommonConstant.POST_TAG;
import static com.longfish.orca.constant.CommonConstant.PRE_TAG;

public class LocalTest {

    @Test
    public void testSearchStrategyImplCore() {
        String content = "--------------------------------------------------------0XX00XX00XX---------------------------------------------------------";
        String keywords = "XX";
        int preLength = 15;
        int postLength = 30;
        List<String> ret = new ArrayList<>();
        List<Integer> indexes = new ArrayList<>();
        Matcher matcher = Pattern.compile("(?i)(" + Pattern.quote(keywords) + ")").matcher(content);
        while (matcher.find()) {
            indexes.add(matcher.start());
        }
        indexes.forEach(i -> {
            int preIndex = Math.max(i - preLength, 0);
            int postIndex = Math.min(i + keywords.length() + postLength, content.length());
            ret.add(new StringBuilder(content.substring(preIndex, postIndex))
                    .insert(i - preIndex + keywords.length(), POST_TAG)
                    .insert(i - preIndex, PRE_TAG)
                    .toString());
        });
        System.out.println(ret);
    }

    @Test
    public void testStringBuilderInsert() {
        String content = "ffffffffffffffffaafffffffffffffaafffffffffffaaffffff";
        StringBuilder sb = new StringBuilder(content);
        sb.insert(18, POST_TAG);
        sb.insert(16, PRE_TAG);
        System.out.println(sb);
        sb.insert(0, "T");
        System.out.println(sb);
    }

    @Test
    public void testFindIndexes() {
        String text = "sfffffffffffff";
        List<Integer> indexes = new ArrayList<>();
        Matcher matcher = Pattern.compile("(?i)(" + Pattern.quote("s") + ")").matcher(text);
        while (matcher.find()) {
            indexes.add(matcher.start());
        }
        System.out.println(indexes);
    }

    @Test
    public void testMark() {
        String text = "sBss";
        String keyword = "s";
        String regex = "(?i)(" + Pattern.quote(keyword) + ")";
        String output = text.replaceAll(regex, PRE_TAG + "$1" + POST_TAG);
        System.out.println(output);
    }

    @Test
    public void testSubString() {
        try {
            System.out.println("111".substring(0, 31));
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("越界");
        }
    }

    @Test
    public void testCopy() {
        Template temp = new Template();
        temp.setUserId(1L);
        temp.setCover("first");

        TemplateDTO dto = TemplateDTO.builder().title("first").cover("2").build();
        BeanUtil.copyProperties(temp, dto);
        System.out.println(temp);
        System.out.println(dto);
    }

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
