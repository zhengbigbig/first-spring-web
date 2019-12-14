package question;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

public class Question3 {
    public static void main(String[] args) throws IOException {
        String a = Files.readAllLines(new File("/Users/zhengzhiheng/desktop/config.txt").toPath()).get(0).trim();
        System.out.println("a = " + a);

        String b = new String(Base64.getEncoder().encode(("DEFABC").getBytes()));
        System.out.println("b = " + b);
    }
}
