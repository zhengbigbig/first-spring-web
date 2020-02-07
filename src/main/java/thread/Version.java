package thread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Version {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                int second = new Random().nextInt(10);
                try {
                    Thread.sleep(second * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程" + finalI + "干完了");

                latch.countDown();
            }).start();
        }

        latch.await(); // 等待线程执行完再执行主线程
        System.out.println("全部干完");
    }
}
