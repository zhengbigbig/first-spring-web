package com;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest {
    private static final Pattern SOME_PRECOMPILED_PATTERN = Pattern.compile("正则表达式");
    public static void main(String[] args) {
        String s = "1.2.100";
        // 正则表达式传递给java必须加\ 在这里.前面也需要加\，因此\\
        System.out.println(Arrays.toString(s.split("\\.")));

        Pattern pattern = Pattern.compile("(\\d{3})-(\\d{8})|(\\d{4})-(\\d{7,8})");

        Matcher matcher = pattern.matcher("012-12345678 012-1234567 012-123456784");

        // 分组只看左括号
        // ?: 加上则忽略
        while (matcher.find()){
            System.out.println(matcher.group(0)); // 文本中完整的匹配
            System.out.println(matcher.group(1)); // 第一个(匹配到的
            System.out.println(matcher.group(2));
            System.out.println(matcher.group(3));
            System.out.println(matcher.group(4));
        }
    }
}
