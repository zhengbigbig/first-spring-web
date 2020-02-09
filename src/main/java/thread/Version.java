package thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Version {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        Future<?> future = threadPool.submit(() -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println("我开始工作啦");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("结束工作");
        });

        System.out.println("提交工作");

        System.out.println(future.get());

        System.out.println("提交工作完毕");

    }
}
