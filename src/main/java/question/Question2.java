package question;

public class Question2 {

    /*
    小明想要用下列代码找出一个字符串中，大写字母、小写字母和数字分别有多少个，但是结果很明显不对。请帮小明找到问题并修复程序中的bug。
     */
    static int upperCase = 0, lowerCase = 0, digit = 0;

    public static void main(String[] args) {
        count("AbC1DefG230");
        System.out.println("upperCase: " + upperCase + ", lowerCase: " + lowerCase + ", digit: " + digit);

    }

    // 统计字符串中的大写字符、小写字符、数字出现的个数
    public static void count(String s) {
        for (char ch : s.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                upperCase++;
            } else if (Character.isLowerCase(ch)) {
                lowerCase++;
            } else if (Character.isDigit(ch)) {
                digit++;
            }
        }
    }
}
